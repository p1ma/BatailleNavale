/**
 * 
 */
package factory;

import bateaux.Bateau2Cases;
import bateaux.Bateau3Cases;
import bateaux.Bateau4Cases;
import bateaux.moderne.Croiseur;
import bateaux.moderne.SousMarin;
import bateaux.moderne.Torpilleur;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public class ModerneFactory extends Factory{

	@Override
	protected Bateau4Cases getBateau4Cases() {
		return new Croiseur();
	}

	@Override
	protected Bateau3Cases getBateau3Cases() {
		return new SousMarin();
	}

	@Override
	protected Bateau2Cases getBateau2Cases() {
		return new Torpilleur();
	}

	
}
