/**
 * 
 */
package monde;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import bateaux.Bateau;
import factory.Factory;
import joueur.Humain;
import joueur.Joueur;
import joueur.Ordinateur;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public class World {

	public final int LONGUEUR = 7;
	public final int LARGEUR = 6;
	
	private Factory factory;
	private Queue<Joueur> quiJoue;
	
	public World(Factory fact) {
		factory = fact;
		quiJoue = new LinkedList<Joueur>();
		quiJoue.add(new Humain(this));
		quiJoue.add(new Ordinateur(this));
	}
	
	public List<Bateau> getFlotte(List<Bateau> flotte) {
		flotte = factory.getFlotte(flotte);
		return flotte;
	}
	public String afficherFlotte() {
		StringBuilder sb = new StringBuilder("");
		for(Joueur j : quiJoue) {
			sb.append(j.toString() + " \n");
			sb.append(j.afficherFlotte() + "\n");
			sb.append("---- \n");
		}
		return sb.toString();
	}
	
	public void jouer() {
		Joueur joueur = quiJoue.poll();
		while (!joueur.estPerdant()) {
			System.out.println("Joueur : " + joueur.toString());
			joueur.jouer();
			quiJoue.add(joueur);
			joueur = quiJoue.poll();
		}
		System.out.println(joueur + " a perdu !");
	}
}
