/**
 * 
 */
package storage.config;

import static org.junit.Assert.*;

import java.awt.Dimension;

import org.junit.BeforeClass;
import org.junit.Test;

import game.parameter.Era;
import storage.XMLDAOFactory;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 29, 2017
 */
public class BattleShipConfigTest {

	/**
	 * TEST FILE IS GAME.XML ( see DAO/Game.xml )
	 */
	private static IConfigDAO config;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		config = XMLDAOFactory.getInstance().getConfigDAO();
	}

	@Test
	public void getAllEras() {
		Era[] eras = config.getAllEras();

		/* In our file there is only 2 eras
		 * so modify when news eras are added.
		 */
		String moderne = "Moderne";
		String xxi = "XXI";
		assert(moderne.equals(eras[0].toString()));
		assert(xxi.equals(eras[1].toString()));	

		int nbShip = eras[0].getFleet().size();
		assertTrue(nbShip == 5);

		nbShip = eras[1].getFleet().size();
		assertTrue(nbShip == 5);
	}

	@Test
	public void getAllDimensions() {
		Dimension[] dimensions = config.getAllDimensions();
		Dimension[] dim = {
				new Dimension(10,10),
				new Dimension(15,15),
				new Dimension(20,20)
		};

		/*
		 * In our file there is only 3 dimensions,
		 * so modify when new dimensions are added
		 */
		for (int i = 0 ; i < dimensions.length ; i++) {
			assert(dim[i].equals(dimensions[i]));
		}
	}

	@Test
	public void getAllLevels() {
		String[] diff = config.getAllLevels();
		String[] levels = {
				"FACILE",
				"NORMAL",
				"DIFFICILE"
		};
		
		/*
		 * In our file there is only 3 levels,
		 * so modify when new levels are added
		 */
		for (int i = 0; i < diff.length ; i++) {
			assert(levels[i].equals(diff[i]));
		}
	}
	
	@Test
	public void getAllAmmunitons() {
		int[] ammu = config.getAllAmmunitons();
		int[] array = {
				75,
				50,
				25
		};
		
		/*
		 * In our file there is only 3 ammunition levels,
		 * so modify when new ammunition are added
		 */
		for (int i = 0; i < ammu.length ; i++) {
			assert(array[i] == ammu[i]);
		}
		
	}
}
