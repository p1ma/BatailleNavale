/**
 * 
 */
package player;

import java.awt.Point;

import element.Ship;
import game.Game;
import player.strategy.RandomStrategy;
import player.strategy.Strategy;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Computer extends Player {

	private Strategy strategy;
	private Game game;
	
	public Computer(Strategy strat) {
		strategy = strat;
	}
	
	public Computer(Game g) {
		game = g;
		strategy = new RandomStrategy();
	}
	
	public void setStrategy(Strategy strat) {
		strategy = strat;
	}
	
	@Override
	public void play(Ship s, Point p) {
	}

}
