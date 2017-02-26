/**
 * 
 */
package joueur;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import bateaux.Bateau;
import monde.World;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public abstract class Joueur {

	protected Map<Point, Bateau> plateau;
	protected boolean[][] sonar;
	protected List<Bateau> flotte;
	private World monde;
	
	public Joueur(World world) {
		monde = world;
		flotte = monde.getFlotte(new LinkedList<Bateau>());

		sonar = new boolean[monde.longueur()][monde.largeur()];
		plateau = new HashMap<Point, Bateau>(flotte.size());
		positionner();
	}
	
	public boolean estPerdant() {
		return (plateau.keySet().size() == 0);
	}

	public String afficherFlotte() {
		StringBuilder sb = new StringBuilder("");
		for(Bateau b : flotte) {
			sb.append(b.toString() + "\n");
		}
		return sb.toString();
	}
	
	public abstract Point jouer();
	
	// Version test
	public void positionner() {
		System.out.println("Bateaux de " + toString());
		Bateau b  = null;
		for (int i = 0 ; i < flotte.size() ; i++) {
			b = flotte.get(i);
			System.out.println("Position de " + b );
			for(int j = 0 ; j < b.longueur() ; j++) {
				plateau.put(new Point(i,j), b);
				System.out.println(new Point(i,j));
			}
		}
	}

	public boolean toucher(Point pos) {
		boolean touche = plateau.containsKey(pos);
		plateau.remove(pos);
		Bateau bateau = plateau.get(pos);
		plateau.remove( bateau );
		return touche;
	}

	public void actualiserSonar(boolean touche, Point pos) {
		sonar[pos.x][pos.y] = touche;
	}
}
