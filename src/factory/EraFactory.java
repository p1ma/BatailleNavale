/**
 * 
 */
package factory;

import java.util.List;

import bateaux.Bateau;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public interface EraFactory {
	public List<Ship> getFlotte(List<Ship> liste);
}
