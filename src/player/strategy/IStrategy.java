/**
 * 
 */
package player.strategy;

import java.awt.Point;
import java.util.Map;

import element.GameElement;
import game.GameIModel;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public interface IStrategy {
	
	/**
	 * Executes a IStrategy
	 * @param radar the Map where the IStrategy will search its infos
	 * @param game the GameIModel
	 * @return a best Point available
	 */
	public Point execute(Map<Point, GameElement> radar, GameIModel game);
}
