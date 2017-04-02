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
				// The box is hit and there is a ship
				if ((l = this.getListNotFreeCases(radar, p)).size() != 4) {
					// There is at least one box available around the affected box
					if ((l = this.getListCasesHit(radar, l)).size() != 0) {
						// There is a piece of ship around p
						// We shoot opposite this box with compared to p and if it is free
						for (Point point : l) {
							int newX = (int) (p.getX() + p.getX() - point.getX());
							int newY = (int) (p.getY() + p.getY() - point.getY());
							Point point2;
							if (!radar.containsKey(point2 = new Point(newX, newY))) {
								// We can shoot here
								return point2;
							}
						}
					} else {
						// A random box is drawn around the affected ship
						l = this.getListFreeCases(radar, p);
						Random random = new Random();
						return l.get(random.nextInt(l.size()));
					}
				}
			}
		}

		// Otherwise we shoot in cross on the map
		return this.getCrossCase(radar);
	}
	
	/**
	 * Returns a list of points being possible to target, all around p
	 * @param radar : Map<Point, Box>
	 * @param p : Point
	 * @return List<Point>
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
	 * Returns a non-touch point on the map to finally shoot in cross
	 * @return Point
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
	 * Returns a Point list corresponding to the coordinates of the 
	 * boxes where a shoot has touched a boat around p
	 * @param radar : Map<Point, Box>
	 * @param l : List<Point>
	 * @return : List<Point>
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
	 * Returns a list of points corresponding to the coordinates of a 
	 * box that has already been touched around p
	 * @param radar : Map<Point, Box>
	 * @param p : Point
	 * @return List<Point>
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
