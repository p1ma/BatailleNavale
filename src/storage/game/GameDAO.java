/**
 * 
 */
package storage.game;

import java.io.File;

import game.GameIModel;
import user.IUser;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 9, 2017
 */
public interface GameDAO {

	public void save(GameIModel game);
	public void load(GameIModel game, String fileName);
	public File[] listGame(GameIModel game, IUser user);
	public void remove(GameIModel game);
}
