package player.strategy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import element.GameElement;
import game.GameIModel;

public class CleverStrategy implements IStrategy {

	private Random rdm;
	private final int coins;
	private int pos;

	public CleverStrategy() {
		rdm = new Random();
		coins = 4;
		pos = 0;
	}

	@Override
	public Point execute(Map<Point, GameElement> radar, GameIModel game) {
		int minW = 0;
		int maxW = 0;
		int minH = 0;
		int maxH = 0;

		pos = rdm.nextInt(coins);

		/*
		 * si pos = 0 alors on tire dans le coin haut gauche
		 * pos = 1 on tire dans le coin haut droit
		 * pos = 2 on tire dans le coin bas gauche
		 * pos = 3 on tire dans le coin bas droit
		 */
		switch(pos) {
		case 0 : 
			minW = 0;
			maxW = game.getWidth()/2;
			minH = 0;
			maxH = game.getHeight()/2;
			break;

		case 1:
			minW = game.getWidth()/2;
			maxW = game.getWidth()-1;
			minH = 0;
			maxH = game.getHeight()/2;
			break;

		case 2:
			minW = 0;
			maxW = game.getWidth()/2;
			minH = game.getHeight()/2;
			maxH = game.getHeight()-1;
			break;

		case 3:
			minW = game.getWidth()/2;
			maxW = game.getWidth()-1;
			minH = game.getHeight()/2;
			maxH = game.getHeight()-1;
			break;

		default :
			return aleatoire(game.getWidth(), game.getHeight(), radar);
		}

		return tirer(minW, minH, maxW, maxH, radar, game);
	}

	private Point tirer(int minW, int minH, int maxW, int maxH, Map<Point, GameElement> radar, GameIModel game) {
		List<Point> caseACote;

		for(Map.Entry<Point, GameElement> entry: radar.entrySet()) {
			GameElement element = entry.getValue();
			
			if (element.isStrategicallyUseful()) {
				Point position = element.getPosition();
				// si l'element est dans la zone alors on regarde si on peut tirer a proximite
				if (position.x >= minW && position.y <= maxH) {
					caseACote = caseACote(position, radar, game);
					if (caseACote.size() != 0) {
						int taille = caseACote.size();
						return caseACote.get(rdm.nextInt(taille));
					}
				}
			}
		}
		// si aucune case a ete trouvee alors on tire aleatoirement dans le bon coin
		Point pos = null;
		boolean exist = true;
		while (exist) {
			pos = new Point(rdm.nextInt(game.getWidth()), rdm.nextInt(game.getHeight()));
			if ( !radar.containsKey(pos) ) {
				exist = false;
			}
		}
		return pos;
	}
	
	private List<Point> caseACote(Point position, Map<Point, GameElement> radar, GameIModel game) {
		List<Point> possibilities = new ArrayList<Point>();
		Point tmp;
		int width = (int)position.getX() + 1;
		if( width < game.getWidth()) {
			tmp = new Point(width,
					(int)position.getY());
			if ( !radar.containsKey(tmp) ) {
				possibilities.add(tmp);
			}
		}

		width = (int)position.getX() - 1;
		if( width >= 0 ) {
			tmp = new Point(width,
					(int)position.getY());
			if ( !radar.containsKey(tmp) ) {
				possibilities.add(tmp);
			}
		}

		int height = (int)position.getY() + 1;
		if( height < game.getHeight()) {
			tmp = new Point((int)position.getX(),
					height);
			if ( !radar.containsKey(tmp) ) {
				possibilities.add(tmp);
			}
		}

		height = (int)position.getY() - 1;
		if( height >= 0) {
			tmp = new Point((int)position.getX(),
					height);
			if ( !radar.containsKey(tmp) ) {
				possibilities.add(tmp);
			}
		}
		return possibilities;
	}

	private Point aleatoire(int w,int  h, Map<Point, GameElement> radar) {
		
		Point pos = null;
		boolean exist = true;

		while (exist) {
			pos = new Point(rdm.nextInt(w), 
					rdm.nextInt(h));
			if ( !radar.containsKey(pos) ) {
				exist = false;
			}
		}
		return pos;
	}

}
