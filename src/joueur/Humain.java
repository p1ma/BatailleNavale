/**
 * 
 */
package joueur;

import java.awt.Point;
import java.util.Random;

import monde.World;
import strategie.IA;
import strategie.Input;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public class Humain extends Joueur{

	private IA strategie;
	
	public Humain(World world) {
		super(world);
		strategie = new Input();
	}
	
	@Override
	public Point jouer() {
		return strategie.executer(sonar);
	}

	public String toString() {
		return "Humain";
	}
}
