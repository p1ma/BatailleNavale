/**
 * 
 */
package player;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import element.GameElement;
import element.HitBox;
import element.MissedBox;
import element.Ship;
import game.BattleShipGame;
import game.GameIModel;
import game.parameter.BattleShipConfiguration;
import game.parameter.IConfiguration;
import storage.XMLDAOFactory;
import storage.config.IConfigDAO;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 29, 2017
 */
public class BattleShipPlayerTest {

	private GameIModel game;
	private Human human;
	private Computer computer;
	private List<Ship> fleet;
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
		computer = new Computer(game);
		human = new Human(game);

		fleet = new LinkedList<Ship>();
		/*
		 * Ships
		 */
		String id = "identifiant";
		int x = 5, y = 5;
		Point p = new Point(x, y);
		int w = 1, h = 5, o = 0;
		String img = "textures/Moderne/ship.png";
		fleet.add(new Ship(id, p, w, h, o, img));
	}

	@Test
	public void getHitShot() {
		int humanS = human.getHitShot();
		assertTrue(humanS == 0);

		int computerS = computer.getHitShot();
		assertTrue(computerS == 0);
	}

	@Test
	public void getMissedShot() {
		int humanS = human.getMissedShot();
		assertTrue(humanS == 0);

		int computerS = computer.getMissedShot();
		assertTrue(computerS == 0);
	}

	@Test
	public void getBoardElements() {
		List<Ship> humanE = human.getBoardElements();
		int size = humanE.size();

		assertTrue(size == 0);

		List<Ship> computerE = computer.getBoardElements();
		size = computerE.size();

		assertTrue(size == 0);
	}

	@Test
	public void getRadarElements() {
		List<GameElement> humanE = human.getRadarElements();
		int size = humanE.size();

		assertTrue(size == 0);

		List<GameElement> computerE = computer.getRadarElements();
		size = computerE.size();

		assertTrue(size == 0);
	}

	@Test
	public void isAlreadyPlayedFalse() {
		int x = 0, y = 0;
		Point p = new Point(x, y);

		boolean b = human.isAlreadyPlayed(p);
		assertTrue(b == false);

		b = computer.isAlreadyPlayed(p);
		assertTrue(b == false);
	}

	@Test
	public void isAlive() {
		boolean b = human.isAlive();
		assertTrue(!b);

		b = computer.isAlive();
		assertTrue(!b);
	}

	@Test
	public void putBoardElements() {
		human.putBoardElements(fleet);
		computer.putBoardElements(fleet);

		List<Ship> humanE = human.getBoardElements();
		int size = humanE.size();

		assertTrue(size == 1);

		List<Ship> computerE = computer.getBoardElements();
		size = computerE.size();

		assertTrue(size == 1);

		Ship s = fleet.get(0);
		Ship ship = humanE.get(0);

		assertTrue(s.equals(ship));

		s = fleet.get(0);
		ship = computerE.get(0);

		assertTrue(s.equals(ship));
	}

	@Test
	public void updateRadar() {
		GameElement ge = fleet.get(0);
		int x = 2, y = 5;
		Point pos = new Point(x, y);
		human.updateRadar(pos, ge);

		List<GameElement> humanE = human.getRadarElements();
		int size = humanE.size();

		assertTrue(size == 1);

		computer.updateRadar(pos, ge);
		List<GameElement> computerE = computer.getRadarElements();
		size = computerE.size();

		assertTrue(size == 1);

		GameElement ge2 = humanE.get(0);
		assertTrue(ge.equals(ge2));

		ge2 = computerE.get(0);
		assertTrue(ge.equals(ge2));
	}

	@Test
	public void touchedAtHit() {
		game.setGameConfiguration(config);
		human.putBoardElements(fleet);
		computer.putBoardElements(fleet);

		GameElement elem = human.getBoardElements().get(0);
		Point p = new Point(elem.getPosition());
		GameElement ge = human.touchedAt(p);

		assertThat(ge, instanceOf(HitBox.class) );

		ge = computer.touchedAt(p);

		assertThat(ge, instanceOf(HitBox.class) );
	}

	@Test
	public void touchedAtMissed() {
		game.setGameConfiguration(config);
		human.putBoardElements(fleet);
		computer.putBoardElements(fleet);

		GameElement elem = human.getBoardElements().get(0);
		Point p = new Point(elem.getPosition());
		p.translate(-25, -25);
		GameElement ge = human.touchedAt(p);

		assertThat(ge, instanceOf(MissedBox.class) );

		ge = computer.touchedAt(p);

		assertThat(ge, instanceOf(MissedBox.class) );
	}

	@Test
	public void isAlreadyPlayedTrue() {
		game.setGameConfiguration(config);
		human.putBoardElements(fleet);
		computer.putBoardElements(fleet);

		GameElement elem = human.getBoardElements().get(0);
		Point p = new Point(elem.getPosition());
		GameElement ge = human.touchedAt(p);
		human.updateRadar(p, ge);

		assertTrue(human.isAlreadyPlayed(p));

		p.translate(-25, -25);

		assertTrue(!human.isAlreadyPlayed(p));
	}
}
