/**
 * 
 */
package joueur;

import java.awt.Point;
import java.util.List;
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

	private IA strategy;
	
	public Ordinateur(World world) {
		super(world);
	}

	/* (non-Javadoc)
	 * @see joueur.Joueur#jouer()
	 */
	@Override
	public List<Point> jouer() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDifficulty() {
		strategy = new Rand();
		
	}
}
