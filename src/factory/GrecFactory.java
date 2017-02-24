/**
 * 
 */
package factory;

import bateaux.Bateau2Cases;
import bateaux.Bateau3Cases;
import bateaux.Bateau4Cases;
import bateaux.grec.Acatium;
import bateaux.grec.Pentecontere;
import bateaux.grec.Triere;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public class GrecFactory extends Factory{

	@Override
	protected Bateau4Cases getBateau4Cases() {
		return new Triere();
	}

	@Override
	protected Bateau3Cases getBateau3Cases() {
		return new Pentecontere();
	}

	@Override
	protected Bateau2Cases getBateau2Cases() {
		return new Acatium();
	}

}
