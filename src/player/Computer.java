/**
 * 
 */
package player;

import java.awt.Point;

import game.GameIModel;
import player.strategy.IStrategy;
import player.strategy.RandomStrategy;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Computer extends BattleShipPlayer {

	/**
	 * A Computer has a IStrategy
	 */
	private IStrategy strategy;

	/**
	 * Constructs a Computer with the given parameter(s)
	 * @param g the Game
	 */
	public Computer(GameIModel g) {
		super("COMPUTER", g );
		// per default the IStrategy is Random
		strategy = new RandomStrategy();
	}
	
	/**
	 * 
	 * Constructs a Computer with the given parameter(s)
	 * @param g the Game
	 * @param strat the IStrategy
	 */
	public Computer(GameIModel g, IStrategy strat) {
		super("COMPUTER",g );
		strategy = strat;
	}

	/**
	 * Constructs a Computer with the given parameter(s)
	 * @param computer the IPlayer to copy
	 * @param strategy the wished IStrategy
	 */
	public Computer(IPlayer computer, GameIModel g, IStrategy strat) {
		super(computer.getName(), g);
		
		strategy = strat;
		putBoardElements(computer.getBoardElements());
		setRadarElements(computer.getRadarElements());
		
		// updates shots
		setHitShot(computer.getHitShot());
		setMissedShot(computer.getMissedShot());
	}

	/**
	 * Returns the GameIModel
	 * @return the GameIModel
	 */
	public GameIModel getGame() {
		return game;
	}

	/**
	 * Sets the IStrategy at strat
	 * @param strat the wished IStrategy
	 */
	public void setStrategy(IStrategy strat) {
		strategy = strat;
	}

	/**
	 * Calls the IStrategy in order to have
	 * a Point to play, then calls the GameIModel
	 * to update the Game
	 */
	public void play() {
		Point p = strategy.execute(radar, game);
		game.play(p);
	}

	/**
	 * Returns true
	 */
	@Override
	public boolean isComputer() {
		return true;
	}

	/**
	 * Returns false
	 */
	@Override
	public boolean isHuman() {
		return false;
	}
}
