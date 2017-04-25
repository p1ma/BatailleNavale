/**
 * 
 */
package storage;

import storage.config.IConfigDAO;
import storage.game.GameDAO;
import storage.image.ImageDAO;
import storage.user.IUserDAO;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public interface DAOFactory {

	public IConfigDAO getConfigDAO();
	
	public GameDAO getGameDAO();

	public ImageDAO getImageDAO();
	
	public IUserDAO getUserDAO();
}
