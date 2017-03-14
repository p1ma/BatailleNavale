/**
 * 
 */
package game;

import java.awt.Point;
import java.util.Observable;

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
		human = new Human(this);
		computer = new Computer(this);
	}
	
	public int getWidth() {
		return configuration.getWidth();
	}
	
	public int getHeight() {
		return configuration.getHeight();
	}
	
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		super.notifyObservers();
	}
	
	public void shootAt(Point pos, Ship ship) {
		boolean touched = computer.isTouched(pos);
		
		if ( touched ) {
			computer.damage(ship, pos);
			human.updateRadar(pos, new HitBox(pos));
		} else {
			human.updateRadar(pos, new MissedBox(pos));
		}
	}

}
