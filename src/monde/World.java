/**
 * 
 */
package monde;

import java.util.LinkedList;
import java.util.List;

import bateaux.Bateau;
import factory.Factory;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public class World {

	public final int LONGUEUR = 7;
	public final int LARGEUR = 6;
	
	private Factory factory;
	private List<Bateau> flottes;
	
	public World(Factory fact) {
		factory = fact;
		flottes = factory.getFlotte(new LinkedList<Bateau>());
	}
	
	public String afficherFlotte() {
		StringBuilder sb = new StringBuilder("");
		for(Bateau b : flottes) {
			sb.append(b.toString() + "\n");
		}
		return sb.toString();
	}
}
