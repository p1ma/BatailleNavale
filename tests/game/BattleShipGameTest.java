/**
 * 
 */
package game;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import element.Ship;
import game.parameter.BattleShipConfiguration;
import game.parameter.IConfiguration;
import player.Computer;
import player.Human;
import player.IPlayer;
import storage.XMLDAOFactory;
import storage.config.IConfigDAO;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 29, 2017
 */
public class BattleShipGameTest {

	private GameIModel game;
	private IConfiguration ref;
	private static IConfiguration config;

	@BeforeClass
	public static void setUpClass() throws Exception {
		IConfigDAO cfg = XMLDAOFactory.getInstance().getConfigDAO();
		config = new BattleShipConfiguration(
				cfg.getAllDimensions()[0],
				cfg.getAllEras()[0],
				cfg.getAllLevels()[0],
				cfg.getAllAmmunitons()[0]);
	}
	
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
	public void getWinnerHuman() {
		IPlayer winner = game.getWinner();
		// per default the Human is the winner
		assertThat(winner, instanceOf(Human.class) );
	}
	
	@Test
	public void getWinnerComputer() {
		game.setGameConfiguration(config);
		IPlayer human = game.getHuman();
		human.setBoardElements(new LinkedList<Ship>()); // empty list
		
		IPlayer winner = game.getWinner();
		// since the Human has no fleet the winner should be the Computer
		assertThat(winner, instanceOf(Computer.class) );
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
