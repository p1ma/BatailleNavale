/**
 * 
 */
package joueur;

import java.awt.Point;
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
	public Point jouer() {
		return null;
	}

	public String toString() {
		return "Humain";
	}
}
