/**
 * 
 */
package storage.config;

import java.awt.Dimension;

import game.GameIModel;
import game.parameter.Era;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public interface IConfigDAO {
	
	public Era[] getAllEras();
	public String[] getAllLevels();
	public Dimension[] getAllDimensions();
	public int[] getAllAmmunitons();
	public int getNbParams(GameIModel game);
	
}
