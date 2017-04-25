/**
 * 
 */
package storage;

import game.GameIModel;
import storage.config.BattleShipConfigXML;
import storage.config.IConfigDAO;
import storage.game.GameDAO;
import storage.game.BattleShipGameXML;
import storage.image.ImageDAO;
import storage.image.BattleShipImageXML;
import storage.user.IUserDAO;
import storage.user.UserXMLDAO;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class XMLDAOFactory implements DAOFactory {

	private static DAOFactory INSTANCE = null;
	
	private IConfigDAO configDAO;
	private GameDAO gameDAO;
	private ImageDAO imageDAO;
	private IUserDAO userDAO;
	
	private XMLDAOFactory(IConfigDAO icdao, GameDAO gdao, ImageDAO idao, IUserDAO iudao ) {
		configDAO = icdao;
		gameDAO = gdao;
		imageDAO = idao;
		userDAO = iudao;
	}
	
	public static DAOFactory getInstance() {
		if ( INSTANCE == null) {
				INSTANCE = new XMLDAOFactory(new BattleShipConfigXML(),
						new BattleShipGameXML(),
						new BattleShipImageXML(),
						new UserXMLDAO()
						);
		}
		return INSTANCE;
	}

	@Override
	public IConfigDAO getConfigDAO() {
		return configDAO;
	}
	
	@Override
	public GameDAO getGameDAO() {
		return gameDAO;
	}
	
	@Override
	public ImageDAO getImageDAO() {
		return imageDAO;
	}

	@Override
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	
}
