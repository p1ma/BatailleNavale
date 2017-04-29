/**
 * 
 */
package game;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import game.parameter.BattleShipConfiguration;
import game.parameter.IConfiguration;
import player.Computer;
import player.Human;
import player.IPlayer;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 29, 2017
 */
public class BattleShipGameTest {

	private GameIModel game;
	private IConfiguration ref;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		game = new BattleShipGame();
		ref = new BattleShipConfiguration();
		game.loadGameConfiguration(ref);
	}

	@Test
	public void isGameOver() {
		assertTrue(game.isGameOver() == false);
	}
	
	@Test
	public void getShotsRemaining() {
		int shots = game.getShotsRemaining();
		assertTrue(shots == 0);
	}
	
	@Test
	public void getWinner() {
		IPlayer winner = game.getWinner();
		assertTrue(winner == null);
	}

	@Test
	public void setGameOver() {
		game.setGameOver(null);
		assertTrue(game.isGameOver() == true);
	}
	
	@Test
	public void setGameOverHuman() {
		game.setGameOver(new Human(game));
		assertTrue(game.isGameOver() == true);
	}
	
	@Test
	public void setGameOverComputer() {
		game.setGameOver(new Computer(game));
		assertTrue(game.isGameOver() == true);
	}
	
	@Test
	public void setDifficulty() {
		String diff = "FACILE";
		game.setDifficulty(diff);
		assertTrue(game.getDifficulty().equals(diff));
	}
	
	@Test
	public void isPlayerTurn() {
		assertTrue(game.isPlayerTurn());
	}
	
	@Test
	public void checkIfOver() {
		game.checkIfOver(); // no ammu so should be true
		assertTrue(game.isGameOver());
	}
	
	@Test
	public void getConfig() {
		IConfiguration cfg = game.getConfig();
		assertTrue(cfg.getDifficulty().equals(ref.getDifficulty()));
		assertTrue(cfg.getLimit() == ref.getLimit());
		
		// eras and dimensions are null ...
	}
	
}
