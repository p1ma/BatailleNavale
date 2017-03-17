/**
 * 
 */
package game;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
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
	
	private List<Ship> temporaryFleet;
	
	private boolean warmup;
	
	public Game() {
		super();
		human = new Human(this);
		computer = new Computer(this);
		configuration = new Configuration();
		warmup = true;
		temporaryFleet = new LinkedList<Ship>();
		
		// TESTS
		// on simule notre liste de Ship
		
		Point point = new Point(0,0);
		Image img = null;
		try {
			img = ImageIO.read(new File("textures/ship.png"));
		} catch (IOException e) {}
		
		// bidouillage des 50 et 250, normalement c'est 1 et 5
		temporaryFleet.add(new Ship(point,50,250, img));
		
		try {
			img = ImageIO.read(new File("textures/war.png"));
		} catch (IOException e) {}
		point = new Point(point.x,  point.y + 50);
		temporaryFleet.add(new Ship(point,50,200, img));
		
		computer.setShipPosition(new Ship(new Point(0,0),1,5), new Point(0,0));
		
		// FIN TEST
	}
	
	public boolean getWarmup() {
		return warmup;
	}
	
	public int getWidth() {
		return configuration.getWidth();
	}
	
	public int getHeight() {
		return configuration.getHeight();
	}
	
	public List<Ship> getFleet() {
		return temporaryFleet;
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
				for( Ship s : temporaryFleet) {
					if (s.intersect(clicked)) {
						return s;
					}
				}
		} else {
			return null;
		}
		return res;
	}

	public void setShipPosition(Ship selected, Point point) {
		selected.setPosition(point);
		this.setChanged();
		this.notifyObservers("BOARD");
	}

}
