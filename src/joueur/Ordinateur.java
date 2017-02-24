/**
 * 
 */
package joueur;

import strategie.IA;
import strategie.Rand;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public class Ordinateur extends Joueur{

	private IA strategie;
	
	public Ordinateur() {
		strategie = new Rand();
	}
	
	public void setStrategie(IA strat) {
		strategie = strat;
	}

	@Override
	public void jouer() {
		strategie.executer();
	}
}
