/**
 * 
 */
package joueur;

import java.util.LinkedList;
import java.util.List;

import bateaux.Bateau;
import monde.World;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public abstract class Joueur {

	protected Bateau[][] plateau;
	protected boolean[][] radar;
	protected List<Bateau> flotte;
	private World monde;
	
	public Joueur(World world) {
		monde = world;
		flotte = monde.getFlotte(new LinkedList<Bateau>());
	}
	
	public boolean estPerdant() {
		return (flotte.size() == 0);
	}
	
	public abstract void jouer();

	public String afficherFlotte() {
		StringBuilder sb = new StringBuilder("");
		for(Bateau b : flotte) {
			sb.append(b.toString() + "\n");
		}
		return sb.toString();
	}
}
