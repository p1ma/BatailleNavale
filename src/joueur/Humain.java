/**
 * 
 */
package joueur;

import java.util.Random;

import monde.World;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public class Humain extends Joueur{

	public Humain(World world) {
		super(world);
	}
	
	@Override
	public void jouer() {
		flotte.remove( (new Random()).nextInt(flotte.size()));
	}

	public String toString() {
		return "Humain";
	}
}
