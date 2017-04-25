/**
 * 
 */
package player;

import game.GameIModel;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Human extends BattleShipPlayer {
 
	/**
	 * Constructs a Human with the given parameter(s)
	 * @param model the Game
	 */
	public Human(GameIModel model) {
		super("HUMAN", model);
	}
	
	/**
	 * 
	 * Constructs a Human with the given parameter(s)
	 * @param id the Human's name
	 * @param model the Game
	 */
	public Human(String id, GameIModel model) {
		super(id, model);
	}

	/**
	 * This function is empty, but if in the futur
	 * we want to add a IA to the Human, we will do it here
	 */
	@Override
	public void play() {
		// empty
	}

	/**
	 * Returns false
	 */
	@Override
	public boolean isComputer() {
		return false;
	}

	/**
	 * Returns true
	 */
	@Override
	public boolean isHuman() {
		return true;
	}
}
