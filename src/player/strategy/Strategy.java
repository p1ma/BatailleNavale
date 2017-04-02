package player.strategy;

import java.awt.Point;
import java.util.Map;

import element.Box;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public interface Strategy {
	
	/**
	 * Chosen a point on the radar where a shot must be made
	 * @param radar : Map<Point, Box>
	 * @return Point
	 */
	public Point execute(Map<Point, Box> radar);
}
