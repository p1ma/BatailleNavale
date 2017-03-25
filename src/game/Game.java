/**
 * 
 */
package game;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import javax.imageio.ImageIO;

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

	private Human human;
	private Computer computer;
	private Configuration configuration;

	/*
	 *  On pourrait directement travailler avec la liste 
	 *  de bateaux du Joueur
	 *  ( à discuter )
	 */
	private List<Ship> temporaryFleet;

	private boolean warmup;

	public Game() {
		super();
		human = new Human(this);
		computer = new Computer(this);
		configuration = new Configuration();
		warmup = true;

		// TESTS
		// on simule notre liste de Ship

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
	 * called when a new party is started
	 */
	public void newParty() {

	}

	public boolean getWarmup() {
		return warmup;
	}

	public int getWidth() {
		return Configuration.WIDTH;
	}

	public int getHeight() {
		return Configuration.HEIGHT;
	}

	public List<Ship> getFleet() {
		return temporaryFleet;
	}

	public void setFleet(List<Ship> l) {
		this.temporaryFleet = l;
	}

	@Override
	public void notifyObservers() {
		super.notifyObservers();
	}

	public void confirmShipsPosition() {
		warmup = false;
		temporaryFleet.clear();
	}

	public void shootAt(Point pos, Ship ship) {
		boolean touched = computer.isTouched(pos);

		if ( touched ) {
			// TEST
			//computer.damage(ship, pos);
			human.updateRadar(pos, new HitBox(pos));
		} else {
			human.updateRadar(pos, new MissedBox(pos));
		}

		this.setChanged();
		this.notifyObservers("RADAR");
	}

	public List<Drawable> getRadarElements() {
		return new LinkedList<Drawable>(human.getRadar());
	}

	public List<Drawable> getBoardElements() {
		List<Drawable> result = new LinkedList<Drawable>();
		result.addAll(computer.getRadar());
		result.addAll(human.getFleet());
		return result;
	}

	public boolean checkRadar(Point clicked) {
		return (human.checkRadar(clicked));
	}

	public Ship selectShip(Point clicked) {
		Ship res = null;
		if ( warmup ) {
			for (Ship s : temporaryFleet) {
				if (s.intersect(clicked)) {
					return s;
				}
			}
		} else {
			return null;
		}
		return res;
	}

	/**
	 * Returns true if the Ship given in parameter has
	 * a collision with at least one other ship
	 * @param ship the Ship to test
	 * @param pt the coordinates to validate or not
	 * @return if there is collision or not
	 */
	public boolean intersectOtherShips(Ship ship, Point pt) {
		Point lastPoint = ship.getPosition();
		int id = this.temporaryFleet.indexOf(ship);
		ship.setPosition(pt);
		
		for (Ship s : this.temporaryFleet) {
			if (this.temporaryFleet.indexOf(s) != id && s.intersect(ship)) {
				ship.setPosition(lastPoint);
				return true;
			}
		}

		ship.setPosition(lastPoint);
		return false;
	}

	public void setShipPosition(Ship selected, Point point) {
		selected.setPosition(point);

		this.setChanged();
		this.notifyObservers("BOARD");
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
						&& element.getX()+element.getWidth() <= this.getWidth()
						&& element.getY()+element.getHeight() <= this.getHeight() ) {
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

	public double getDistance(Point p1, Point p2) {
		return Math.sqrt(Math.pow((p2.y - p1.y),2) + Math.pow((p2.x - p1.x),2));
	}


	public void setStartScreen() {
		this.setChanged();
		this.notifyObservers("setStartScreen");
	}

	public void setConfigPartyScreen() {
		this.setChanged();
		this.notifyObservers("setConfigPartyScreen");
	}

	public void setPartyScreen() {
		System.err.println("Game.setConfigPartyScreen() : a completer");
		System.exit(0);

		this.setChanged();
		this.notifyObservers("setPartyScreen");
	}

}
