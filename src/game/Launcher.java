/**
 * 
 */
package game;

import graphics.GameScreen;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 14, 2017
 */
public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameScreen gameScreen = new GameScreen();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		System.err.println( " TIME IS DONE " );
		gameScreen.update(null, "BOARD");
	}

}
