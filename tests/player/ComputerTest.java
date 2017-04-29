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
public class ComputerTest {
	
	private GameIModel game;
	private Computer computer;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		game = new BattleShipGame();
		computer = new Computer(game);
	}

	@Test
	public void isComputer() {
		assertTrue(computer.isComputer());
	}
	
	@Test
	public void isHuman() {
		assertTrue(!computer.isHuman());
	}
}
