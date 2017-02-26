/**
 * 
 */
package monde;

import factory.GrecFactory;
import factory.ModerneFactory;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public class Launcher {

	public static void main(String[] args) {
		World world = new World(new GrecFactory());
		//World world = new World(new ModerneFactory());
		world.jouer();
	}

}
