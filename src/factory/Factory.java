/**
 * 
 */
package factory;

import java.util.List;

import bateaux.Bateau;
import bateaux.Bateau2Cases;
import bateaux.Bateau3Cases;
import bateaux.Bateau4Cases;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public abstract class Factory {
	
	public List<Bateau> getFlotte(List<Bateau> liste) {
		liste.add(getBateau2Cases());
		liste.add(getBateau3Cases());
		liste.add(getBateau4Cases());
		return liste;
	}
	
	protected abstract Bateau4Cases getBateau4Cases();
	protected abstract Bateau3Cases getBateau3Cases();
	protected abstract Bateau2Cases getBateau2Cases();
	
}
