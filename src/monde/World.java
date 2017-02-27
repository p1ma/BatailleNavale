/**
 * 
 */
package monde;

import java.awt.Point;
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

	private final int LONGUEUR = 10;
	private final int LARGEUR = 10;
	
	private Factory factory;
	private Queue<Joueur> quiJoue;
	
	public World(Factory fact) {
		factory = fact;
		quiJoue = new LinkedList<Joueur>();
		quiJoue.add(new Humain(this));
		quiJoue.add(new Ordinateur(this));
	}
	
	public World() {
		factory = null;
		quiJoue = new LinkedList<Joueur>();
		//quiJoue.add(new Humain(this));
		//quiJoue.add(new Ordinateur(this));
	}
	
	public int largeur() {
		return LARGEUR;
	}
	
	public int longueur() {
		return LONGUEUR;
	}
	
	public List<Bateau> getFlotte(List<Bateau> flotte) {
		//flotte = factory.getFlotte(flotte);
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
		Joueur adversaire = null;
		Point pos = null;
		boolean touche = false;
		
		while (!joueur.estPerdant()) {
			pos = joueur.jouer();
			System.out.println(joueur.toString() + " Tire en (" + pos.x + "," + pos.y + ")");
			adversaire = quiJoue.peek();
			System.out.println(adversaire + " est touché ?");
			touche = adversaire.toucher(pos);

			if (touche) {
				System.out.println("Touché !");
			} else {
				System.out.println("Raté !");
			}
			joueur.actualiserSonar(touche,pos);
			quiJoue.add(joueur);
			joueur = quiJoue.poll();
		}
		System.out.println(joueur + " a perdu !");
	}

}
