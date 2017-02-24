/**
 * 
 */
package joueur;

import bateaux.Bateau;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public abstract class Joueur {

	public Bateau[][] plateau;
	
	public Joueur() {
		
	}
	
	public abstract void jouer();
}
