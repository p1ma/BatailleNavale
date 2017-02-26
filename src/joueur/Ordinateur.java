/**
 * 
 */
package joueur;

import java.util.Random;

import monde.World;
import strategie.IA;
import strategie.Rand;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public class Ordinateur extends Joueur{

	private IA strategie;
	
	public Ordinateur(World world) {
		super(world);
		strategie = new Rand();
	}
	
	public void setStrategie(IA strat) {
		strategie = strat;
	}

	@Override
	public void jouer() {
		flotte.remove( (new Random()).nextInt(flotte.size()));
		strategie.executer();
	}
	
	public String toString() {
		return "Ordinateur";
	}
}
