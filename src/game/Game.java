package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import element.Drawable;
import element.HitBox;
import element.MissedBox;
import element.Ship;
import player.Computer;
import player.Human;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Game extends Observable {

	/**
	 * Human player of the Game
	 */
	private Human human;
	
	/**
	 * Computer player of the Game
	 */
	private Computer computer;
	
	/**
	 * Configuration of the Game
	 */
	private Configuration configuration;

	
	
	
	
	/**
	 * Constructor
	 */
	public Game() {
		super();
		human = new Human(this);
		computer = new Computer(null, this);
		configuration = new Configuration();

		// TESTS
		// on simule notre liste de Ship
		/*
		temporaryFleet = new LinkedList<Ship>();

		Point point = new Point(0,0);
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("textures/ship.png"));
		} catch (IOException e) {}

		temporaryFleet.add(new Ship(point, 5, 1, img));
		/*
		try {
			img = ImageIO.read(new File("textures/war.png"));
		} catch (IOException e) {}
		point = new Point(point.x,  point.y + 50);
		temporaryFleet.add(new Ship(point,50,200, img));
		 */
		//		computer.setShipPosition(new Ship(new Point(0,0),1,5), new Point(0,0));

		// FIN TEST
	}

	
	
	
	
	/**
	 * Called when a new party is started
	 */
	public void newParty() {

	}

	/**
	 * ... a completer ...
	 * @param pos : Point
	 * @param ship : Ship
	 */
	public void shootAt(Point pos, Ship ship) {
		boolean touched = computer.isTouched(pos);

		if ( touched ) {
			human.updateRadar(pos, new HitBox(pos));
		} else {
			human.updateRadar(pos, new MissedBox(pos));
		}

		this.setChanged();
		this.notifyObservers("RADAR");
	}

	/**
	 * ... a completer ...
	 * @param clicked : Point
	 * @return boolean
	 */
	public boolean checkRadar(Point clicked) {
		return (human.checkRadar(clicked));
	}

	/**
	 * ... a completer ...
	 * @param clicked : Point
	 * @return Ship
	 */
	public Ship selectShip(Point clicked) {
		Ship res = null;
		for (Ship s : this.human.getFleet()) {
			if (s.intersect(clicked)) {
				return s;
			}
		}
		return res;
	}

	/**
	 * Returns true if the Ship given in parameter has
	 * a collision with at least one other ship
	 * @param ship : Ship, the Ship to test
	 * @param pt : Point, the coordinates to validate or not
	 * @return boolean
	 */
	public boolean intersectOtherShips(Ship ship, Point pt) {
		Point lastPoint = ship.getPosition();
		int id = this.human.getFleet().indexOf(ship);
		ship.setPosition(pt);

		for (Ship s : this.human.getFleet()) {
			if (this.human.getFleet().indexOf(s) != id && s.intersect(ship)) {
				ship.setPosition(lastPoint);
				return true;
			}
		}

		ship.setPosition(lastPoint);
		return false;
	}

	
	
	
	
	

	public void rotate(Drawable element) {
		// recherche la pos dispo la plus proche
		Point p = this.getClosestPosition(element);

		if (p != null)
			element.rotate(p);
		else
			System.err.println("Aucune position permettant une rotation");

		this.setChanged();
		this.notifyObservers("BOARD");
	}

	/**
	 * recupere la position disponible la plus proche de la position actuelle
	 * @return
	 */
	private Point getClosestPosition(Drawable element) {
		element.rotate(element.getPosition());

		// on recupere la liste des points possibles
		List<Point> lp = new ArrayList<Point>();
		for (int x = 0; x < this.getWidth(); x++) {
			for (int y = 0; y < this.getHeight(); y++) {
				Point p = new Point(x, y);
				// on regarde si ya pas d'intersection avec un autre bateau ou si il depasse pas le bord de la map
				if ( (!this.intersectOtherShips((Ship) element, p) 
						|| (p.equals(element.getPosition()) && !this.intersectOtherShips((Ship) element, p)))
						&& x+element.getWidth() <= this.getWidth()
						&& y+element.getHeight() <= this.getHeight() ) {
					lp.add(p);
				}
			}
		}

		element.rotate(element.getPosition());

		// on trouve le point le plus proche de la position d'element
		Point p = new Point (element.getPosition().x, element.getPosition().y);
		Point res = null;
		for (Point point : lp) {
			if (res == null) {
				res = point;
			} else {
				if (this.getDistance(p, res) > this.getDistance(p, point)) {
					res = point;
				}
			}
		}

		return res;
	}

	
	
	
	
	/**
	 * Returns the width of the Game
	 * @return int
	 */
	public int getWidth() {
		return Configuration.WIDTH;
	}

	/**
	 * Returns the height of the world
	 * @return int
	 */
	public int getHeight() {
		return Configuration.HEIGHT;
	}

	/**
	 * Returns the list of human ship
	 * @return List<Ship>
	 */
	public List<Ship> getHumanFleet() {
		return this.human.getFleet();
	}

	/**
	 * Returns the list of human radar
	 * @return List<Drawable>
	 */
	public List<Drawable> getHumanRadar() {
		return new LinkedList<Drawable>(human.getRadar());
	}

	/**
	 * Returns the list of computer radar
	 * @return List<Drawable>
	 */
	public List<Drawable> getComputerRadar() {
		List<Drawable> result = new LinkedList<Drawable>();
		result.addAll(computer.getRadar());
		return result;
	}
	
	/**
	 * Returns the distance between the points p1 and p2
	 * @param p1 : Point
	 * @param p2 : Point
	 * @return double
	 */
	public double getDistance(Point p1, Point p2) {
		return Math.sqrt(Math.pow((p2.y - p1.y),2) + Math.pow((p2.x - p1.x),2));
	}
	
	/**
	 * Replaces the list of human ship by the list l
	 * @param l : List<Ship>
	 */
	public void setHumanFleet(List<Ship> l) {
		this.human.setFleet(l);
	}
	
	/**
	 * Displays the game's welcome screen
	 */
	public void setStartScreen() {
		this.setChanged();
		this.notifyObservers("setStartScreen");
	}

	/**
	 * Displays the configuration screen of a game
	 */
	public void setConfigPartyScreen() {
		this.setChanged();
		this.notifyObservers("setConfigPartyScreen");
	}

	/**
	 * Displays a current game
	 */
	public void setPartyScreen() {
		this.setChanged();
		this.notifyObservers("setPartyScreen");
	}

	/**
	 * Changes the ship position
	 * @param selected : Ship
	 * @param point : Point
	 */
	public void setShipPosition(Ship selected, Point point) {
		selected.setPosition(point);

		this.setChanged();
		this.notifyObservers("BOARD");
	}

	
	
	
	
	@Override
	public void notifyObservers() {
		super.notifyObservers();
	}

}
