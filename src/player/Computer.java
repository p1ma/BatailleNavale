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

	/**
	 * Computer strategy 
	 */
	private Strategy strategy;

	/**
	 * Current Game
	 */
	private Game game;





	/**
	 * Constructor
	 * @param strat : Strategy
	 * @param g : Game
	 */
	public Computer(Strategy strat, Game g) {
		game = g;
		strategy = strat;
	}





	@Override
	public void play(Ship s, Point p) {

	}





	/**
	 * Set the computer strategy
	 * @param strat : Strategy
	 */
	public void setStrategy(Strategy strat) {
		strategy = strat;
	}

}
