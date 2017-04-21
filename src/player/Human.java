package player;

import java.awt.Point;

import element.HitBox;
import element.MissedBox;
import element.Ship;
import game.Game;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Human extends Player {

	/**
	 * Current Game
	 */
	private Game game;





	/**
	 * Constructor
	 * @param g : Game
	 */
	public Human(Game g) {
		game = g;
	}





	@Override
	public void play(Ship s, Point p) {
		
	}
	
}
