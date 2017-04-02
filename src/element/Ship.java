package element;

import java.awt.Image;
import java.awt.Point;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Ship extends Drawable {

	/**
	 * Life of the Ship
	 */
	private int life;
	
	/**
	 * Damages caused by the Ship
	 */
	private int damage;
	
	/**
	 * Range of the Ship
	 */
	private int range;
	
	/**
	 * Image of the Ship
	 */
	private Image image;

	
	
	
	
	/**
	 * Constructor
	 * @param p : Point, position of Ship
	 * @param w : int, width
	 * @param h : int height
	 */
	public Ship(Point p, int w, int h) {
		super(p, w, h);

	}

	/**
	 * Constructor
	 * @param p : Point, position of Ship
	 * @param w : int, width
	 * @param h : int, height
	 * @param img : Image
	 */
	public Ship(Point p, int w, int h, Image img) {
		super(p, w, h);
		image = img;
	}

	
	
	
	/**
	 * Take damage from Ship s
	 * @param s : Ship, ship that does the damage
	 */
	public void damage(Ship s) {
		life -= s.getDamage();
	}

	/**
	 * Return true if the Ship is dead
	 * False otherwise
	 * @return boolean
	 */
	public boolean isDead() {
		return (life <= 0);
	}
	
	
	
	
	
	
	public Image getImage() {
		return image;
	}

	/**
	 * Returns the life of the Ship
	 * @return int
	 */
	public int getLife() {
		return life;
	}

	/**
	 * Returns the damage of the Ship
	 * @return int
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Returns the range of the Ship
	 * @return int
	 */
	public int getRange() {
		return range;
	}

}
