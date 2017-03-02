/**
 * 
 */
package monde;

import java.util.LinkedList;
import java.util.Queue;

import joueur.Humain;
import joueur.Joueur;
import joueur.Ordinateur;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public class World {

	private Queue<Joueur> quiJoue;
	private Configuration config;
	private boolean WARM_UP = true;
	
	public World() {
		quiJoue = new LinkedList<Joueur>();
		Humain humain = new Humain(this);
		Ordinateur ordinateur = new Ordinateur(this);
		
		config = new Configuration();
		
		ordinateur.setDifficulty();
		
		quiJoue.add(humain);
		quiJoue.add(ordinateur);
		
	}
	
	public int width() {
		return config.width();
	}
	
	public int height() {
		return config.height();
	}
}
