/**
 * 
 */
package storage.user;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import storage.XMLDAOFactory;
import user.IUser;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 29, 2017
 */
public class UserTest {

	/**
	 * TEST FILE IS GAME.XML ( see DAO/Game.xml )
	 */
	private static IUserDAO config;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		config = XMLDAOFactory.getInstance().getUserDAO();
	}

	@Test
	public void getAllUsers() {
		/*
		 * We have only 1 user to test,
		 * so if more added etc.
		 * please update this test
		 */
		IUser[] users = config.getAllUsers();
		
		assertTrue(users.length == 1);
	}

}
