package player;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import element.HitBox;
import element.MissedBox;
import element.Ship;
import game.Game;
import player.strategy.RandomStrategy;
import player.strategy.Strategy;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Computer extends Player {

	/**
	 * Computer strategy 
	 */
	private Strategy strategy;

	/**
	 * Current Game
	 */
	private Game game;





	/**
	 * Constructor
	 * @param strat : Strategy
	 * @param g : Game
	 */
	public Computer(Strategy strat, Game g) {
		super();
		game = g;
		strategy = strat;
		
		// choix strategie a changer (mettre dans la config)
		this.strategy = new RandomStrategy();
		
		// choix de base des bateaux pour le moment -> a mettre en alea apres
		List<Ship> ships = new LinkedList<Ship>();
		Image img = null;
		try {
			img = ImageIO.read(new File("textures/ship.png"));
		} catch (IOException e) {
			System.err.println("Error loading image");
			System.exit(0);
		}
		ships.add(new Ship(new Point(0,0), 5, 1, img));
		ships.add(new Ship(new Point(0,1), 4, 1, img));
		ships.add(new Ship(new Point(0,2), 3, 1, img));
		ships.add(new Ship(new Point(0,3), 3, 1, img));
		ships.add(new Ship(new Point(0,4), 2, 1, img));
		
		this.setFleet(ships);
	}





	@Override
	public void play(Ship s, Point p) {

	}
	
	public Point chooseShootingArea() {
		return this.strategy.execute( this.radar );
	}





	/**
	 * Set the computer strategy
	 * @param strat : Strategy
	 */
	public void setStrategy(Strategy strat) {
		strategy = strat;
	}

}
