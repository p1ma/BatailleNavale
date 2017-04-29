/**
 * 
 */
package player;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.BattleShipGame;
import game.GameIModel;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 29, 2017
 */
public class HumanTest {

	private GameIModel game;
	private Human human;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		game = new BattleShipGame();
		human = new Human(game);
	}

	@Test
	public void isComputer() {
		assert(!human.isComputer());
	}
	
	@Test
	public void isHuman() {
		assertTrue(human.isHuman());
	}
}
