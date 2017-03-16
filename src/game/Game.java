/**
 * 
 */
package game;

import java.awt.Point;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import element.Box;
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
	
	
	public Game() {
		super();
		human = new Human(this);
		computer = new Computer(this);
		configuration = new Configuration();
		
		// TEST
		computer.setShipPosition(new Ship(new Point(0,0),1,5), new Point(0,0));
		
		// FIN TEST
	}
	
	public int getWidth() {
		return configuration.getWidth();
	}
	
	public int getHeight() {
		return configuration.getHeight();
	}
	
	@Override
	public void notifyObservers() {
		super.notifyObservers();
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

}
