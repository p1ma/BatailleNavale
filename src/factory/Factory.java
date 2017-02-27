/**
 * 
 */
package factory;

import java.util.List;

import bateaux.Bateau;
import monde.Epoque;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public abstract class Factory {
	
	public List<Bateau> getFlotte(List<Bateau> liste, Epoque epoque) {
		return liste;
	}	
}
