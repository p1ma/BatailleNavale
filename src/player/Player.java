/**
 * 
 */
package player;

import java.awt.Point;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import element.Box;
import element.Ship;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public abstract class Player {

	private int losingShot;
	private int winningShot;
	
	private Map<Point, Ship> fleet;
	private Map<Point, Box> radar;
	
	public Player() {
		losingShot = 0;
		winningShot = 0;
		
		fleet = new HashMap<Point, Ship>();
		radar = new HashMap<Point, Box>();
	}
	
	public Collection<Ship> getFleet() {
		return fleet.values();
	}
	
	public Collection<Box> getRadar() {
		return radar.values();
	}
	
	public boolean isTouched(Point p) {
		return (fleet.containsKey(p));
	}
	
	public void damage(Ship s, Point p) {
		Ship touched = fleet.get(p);
		touched.damage(s);
	}
	
	public abstract void play(Ship s, Point p);
	
}
