/**
 * 
 */
package joueur;

import java.awt.Point;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import monde.World;
import monde.element.GameElement;
import monde.element.bateaux.Ship;
import monde.element.cases.Case;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public abstract class Joueur {

	private World world;
	private Map<Point, GameElement> sonar;
	private Map<Point, Ship> ships;
	
	public Joueur(World world) {
		this.world = world;
	}

	public abstract List<Point> jouer();

	public Collection<GameElement> sonarElements() {
		return sonar.values();
	}
	
	public Collection<Ship> shipElements() {
		return ships.values();
	}
	
	public void confirmPlacement() {
		world.confirmPlacement();
	}
}
