/**
 * 
 */
package player.strategy;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import element.GameElement;
import game.GameIModel;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class CrossStrategy implements IStrategy {

	/**
	 * Random 
	 */
	private final Random random = new Random();
	
	/**
	 * Executes a CrossStrategy (MEDIUM level)
	 * the shot will looks like X on the Screen
	 * @param radar the Map where the IStrategy will search its infos
	 * @param game the GameIModel
	 * @return a best Point available
	 */
	@Override
	public Point execute(Map<Point, GameElement> radar, GameIModel game) {
		if (radar.size() == 0) {
			// Random shot
			int w = game.getWidth();
			int h = game.getHeight();
			
			Point pos = null;
			boolean exist = true;
			
			while (exist) {
				pos = new Point(random.nextInt(w), 
						random.nextInt(h));
				if ( !radar.containsKey(pos) ) {
					exist = false;
				}
			}
			return pos;
		} else {
			List<GameElement> previousShot = new LinkedList<GameElement>(radar.values());
			GameElement possibility = null;
			Point res = null;
			
			for(int i = 0 ; i < previousShot.size() ; i++) {
				possibility = previousShot.get( i );
				List<Point> possible = hasEmptyPointNextToHim(radar, possibility, game);
				if (possible.size() != 0) {
					res = possible.get( random.nextInt(possible.size()) );
					break;
				}
			}
			return res;
		}
	}
	
	/**
	 * Checks if around the GameElement ge there is
	 * some Position available for a future shot
	 * @param radar a Map used to verify if a Point exists or not
	 * @param ge a GameElement
	 * @param model the GameIModel
	 * @return a list of possible Points availables
	 */
	private List<Point> hasEmptyPointNextToHim(Map<Point, GameElement> radar, GameElement ge, GameIModel model) {
		Point p = new Point(ge.getPosition());
		
		/* 4 possibilities :
		 *  corner up left, 
		 *  corner up right,
		 *  corner bottom left,
		 *  corner bottom right
		 */
		Point[] possibilities = new Point[4];
		possibilities[0] = new Point((int)p.getX() - 1,
				(int)p.getY() - 1);
		possibilities[1] = new Point((int)p.getX() + 1,
				(int)p.getY() - 1);
		possibilities[2] = new Point((int)p.getX() - 1,
				(int)p.getY() + 1);
		possibilities[3] = new Point((int)p.getX() + 1,
				(int)p.getY() + 1);
		
		List<Point> res = new LinkedList<Point>();
		for(Point point : possibilities) {
			if ( isValid(point, model) &&
					!radar.containsKey(point) ) {
				res.add(point);
			}
		}
		return res;
	}
	
	/**
	 * Checks if the Point p is correct according
	 * to the GameIModel game
	 * @param p the Point to check
	 * @param game the GameIModel
	 * @return true if the Point is correct.
	 */
	private boolean isValid(Point p, GameIModel game) {
		if ( p.getX() < 0 ) {
			return false;
		}
		if ( p.getY() < 0 ) {
			return false;
		}
		if ( p .getX() >= game.getWidth() ) {
			return false;
		}
		if ( p.getY() >= game.getHeight() ) {
			return false;
		}
		return true;
	}
}
