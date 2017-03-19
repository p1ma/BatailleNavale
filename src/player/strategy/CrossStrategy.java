/**
 * 
 */
package player.strategy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import element.Box;
import element.HitBox;
import game.Configuration;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class CrossStrategy implements Strategy {

	@Override
	public Point execute(Map<Point, Box> radar) {
		List<Point> l;

		for (Entry<Point, Box> entry : radar.entrySet()) {
			Point p = entry.getKey();
			Box b = entry.getValue();

			if (b instanceof HitBox) { 
				// la case est touche et ya un bateau
				if ((l = this.getListNotFreeCases(radar, p)).size() != 4) {
					// ya au - une case dispo autour de la case touche
					if ((l = this.getListCasesHit(radar, l)).size() != 0) {
						// ya un bout de bateau autour de p
						// on tire a l'opose de cette case par rapport a p et ssi elle est libre
						for (Point point : l) {
							int newX = (int) (p.getX() + p.getX() - point.getX());
							int newY = (int) (p.getY() + p.getY() - point.getY());
							Point point2;
							if (!radar.containsKey(point2 = new Point(newX, newY))) {
								// on peut tirer la
								return point2;
							}
						}
					} else {
						// on tape une case aleatoire autour du bateau touche
						l = this.getListFreeCases(radar, p);
						Random random = new Random();
						return l.get(random.nextInt(l.size()));
					}
				}
			}
		}

		// sinon on tape en croix sur la map
		return this.getCrossCase(radar);
	}
	
	/**
	 * retourne une liste de Point etant possibles a cibler, le tout autour de p
	 * @param radar
	 * @param p
	 * @return
	 */
	private List<Point> getListFreeCases(Map<Point, Box> radar, Point point) {
		List<Point> res = new ArrayList<Point>();
		Point p;

		if (!radar.containsKey(p = new Point((int)point.getX()-1, (int)point.getY()))) {
			res.add(p);
		}
		if (!radar.containsKey(p = new Point((int)point.getX(), (int)point.getY()-1))) {
			res.add(p);
		}
		if (!radar.containsKey(p = new Point((int)point.getX()+1, (int)point.getY()))) {
			res.add(p);
		}
		if (!radar.containsKey(p = new Point((int)point.getX(), (int)point.getY()+1))) {
			res.add(p);
		}

		return res;
	}

	/**
	 * retourne un Point non touche sur la map pour au final taper en croix
	 * @return
	 */
	private Point getCrossCase(Map<Point, Box> radar) {
		for (int x = 0; x < Configuration.WIDTH; x++) {
			for (int y = 0; y < Configuration.HEIGHT; y++) {
				if ((x%2 == 0 && y%2 == 0) || (x%2 == 1 && y%2 == 1)) {
					Point p = new Point(x, y);

					if (!radar.containsKey(p)) { 
						return p;
					}
				}
			}
		}

		return null;
	}

	/**
	 * retourne une liste de Point correspondant aux coordonnees des cases ou un tire a touche
	 * un bateau autour de p
	 * @param radar
	 * @param p
	 * @return
	 */
	private List<Point> getListCasesHit(Map<Point, Box> radar, List<Point> l) {
		List<Point> res = new ArrayList<Point>();

		for (Point p : l) {
			if (radar.get(p) instanceof HitBox) {
				res.add(p);
			}
		}

		return res;
	}

	/**
	 * retourne une liste de Point
	 * un Point correspond au coordonnees d'une case qui a deja ete touchee autour de p
	 * @param radar
	 * @param p
	 * @return
	 */
	private List<Point> getListNotFreeCases(Map<Point, Box> radar, Point point) {
		List<Point> res = new ArrayList<Point>();
		Point p;

		if (radar.containsKey(p = new Point((int)point.getX()-1, (int)point.getY()))) {
			res.add(p);
		}
		if (radar.containsKey(p = new Point((int)point.getX(), (int)point.getY()-1))) {
			res.add(p);
		}
		if (radar.containsKey(p = new Point((int)point.getX()+1, (int)point.getY()))) {
			res.add(p);
		}
		if (radar.containsKey(p = new Point((int)point.getX(), (int)point.getY()+1))) {
			res.add(p);
		}

		return res;
	}

}
