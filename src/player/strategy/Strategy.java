/**
 * 
 */
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
	
	public Point execute(Map<Point, Box> radar);
}
