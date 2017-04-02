package player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import element.Box;
import element.Ship;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public abstract class Player {

	/**
	 * ... a completer ...
	 */
	private int losingShot;
	
	/**
	 * ... a completer ...
	 */
	private int winningShot;

	/**
	 * Player's ships
	 */
	private Map<Point, Ship> fleet;
	
	/**
	 * Areas where the player had shoot
	 */
	private Map<Point, Box> radar;

	
	
	
	
	/**
	 * Constructor
	 */
	public Player() {
		losingShot = 0;
		winningShot = 0;

		fleet = new HashMap<Point, Ship>();
		radar = new HashMap<Point, Box>();
	}
	
	
	
	
	
	/**
	 * ... a completer ...
	 * @param s : Ship
	 * @param p : Point
	 */
	public abstract void play(Ship s, Point p);

	/**
	 * Returns true if a ship is touched
	 * False otherwise
	 * @param p : Point
	 * @return boolean
	 */
	public boolean isTouched(Point p) {
		return (fleet.containsKey(p));
	}

	/**
	 * Returns true if the point p is already touched
	 * False otherwise
	 * @param p : Point
	 * @return boolean
	 */
	public boolean checkRadar(Point p) {
		return (radar.containsKey(p));
	}

	/**
	 * ... a completer ...
	 * @param s : Ship
	 * @param p : Point
	 */
	public void damage(Ship s, Point p) {
		Ship touched = fleet.get(p);
		touched.damage(s);
	}

	/**
	 * ... a completer ...
	 * @param pos : Point
	 * @param box : Box
	 */
	public void updateRadar(Point pos, Box box) {
		radar.put(pos, box);
	}

	/**
	 * ... a completer ...
	 * @param ship : Ship
	 * @param pos : Point
	 */
	public void setShipPosition(Ship ship, Point pos) {
		for(int i = 0 ; i < ship.getHeight() ; i++) {
			fleet.put(new Point((int)pos.getX() + i, (int)pos.getY()), ship);
		}
	}


	
	
	
	/**
	 * Returns the list of player's ships
	 * @return List<Ship>
	 */
	public List<Ship> getFleet() {
		List<Ship> res = new ArrayList<Ship>();

		for (Entry<Point, Ship> e : this.fleet.entrySet())
			if (!res.contains(e.getValue()))
				res.add(e.getValue());

		return res;
	}
	
	/**
	 * Returns the list of player's shoots
	 * @return Collection<Box>
	 */
	public Collection<Box> getRadar() {
		return radar.values();
	}

	/**
	 * Changes the list of player's boats
	 * @param l : List<Ship>
	 */
	public void setFleet(List<Ship> l) {
		this.fleet = new HashMap<Point, Ship>();
		
		for (Ship ship : l) {
			if (ship.getOrientation() == 0) {
				// Horizontal
				for (int i = 0; i < ship.getWidth(); i++) {
					int x = ship.getPosition().x + i;
					int y = ship.getPosition().y;
					this.fleet.put(new Point(x, y), ship);
				}
			} else if (ship.getOrientation() == 1) {
				// vertical
				for (int i = 0; i < ship.getHeight(); i++) {
					int x = ship.getPosition().x;
					int y = ship.getPosition().y + i;
					this.fleet.put(new Point(x, y), ship);
				}
			} else {
				System.err.println("Player.setFleet() : error type orientation");
				System.exit(0);
			}
		}
	}
}
