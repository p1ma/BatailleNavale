/**
 * 
 */
package element;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Ship extends Drawable{

	private int life;
	private int damage;
	private int range;
	private BufferedImage image;

	/**
	 * Constructor of Ship
	 * @param p Position
	 * @param w width
	 * @param h height
	 */
	public Ship(Point p, int w, int h) {
		super(p, w, h);

	}

	public Ship(Point p, int w, int h, BufferedImage img) {
		super(p, w, h);
		image = img;
		
		range = 0; // à voir
		damage = 10; // à voir
		life = 10; // à voir
	}

	/*
	 * 	METHODS
	 */

	public void damage(Ship s) {
		life -= s.getDamage();
	}

	public boolean isDead() {
		return (life <= 0);
	}

	/*
	 *			SETTERS
	 */


	/*
	 *			GETTERS
	 */

	public BufferedImage getImage() {
		return image;
	}

	public int getLife() {
		return life;
	}

	public int getDamage() {
		return damage;
	}

	public int getRange() {
		return range;
	}

}
