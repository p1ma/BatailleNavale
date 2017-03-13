/**
 * 
 */
package game;

import java.util.Observable;

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
	private final int WIDTH = 10;
	private final int HEIGHT = 10;
	
	public Game() {
		human = new Human();
		computer = new Computer(this);
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		super.notifyObservers();
	}

}
