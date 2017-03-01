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
public interface Factory {
	public List<Bateau> getFlotte(List<Bateau> liste);
}
