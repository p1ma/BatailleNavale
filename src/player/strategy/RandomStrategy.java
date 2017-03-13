/**
 * 
 */
package player.strategy;

import java.awt.Point;
import java.util.Map;
import java.util.Random;

import element.Box;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class RandomStrategy implements Strategy {

	private Random random = new Random();
	
	@Override
	public Point execute(Map<Point, Box> radar) {
		return null;
	}

}
