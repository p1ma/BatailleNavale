/**
 * 
 */
package player.strategy;

import java.awt.Point;
import java.util.Map;
import java.util.Random;

import element.GameElement;
import game.GameIModel;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class RandomStrategy implements IStrategy {

	/**
	 * Random
	 */
	private Random random = new Random();
	
	/**
	 * Executes a RandomStrategy (FACILE level)
	 * @param radar the Map where the IStrategy will search its infos
	 * @param game the GameIModel
	 * @return a best Point available
	 */
	@Override
	public Point execute(Map<Point, GameElement> radar, GameIModel game) {
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
	}
}
