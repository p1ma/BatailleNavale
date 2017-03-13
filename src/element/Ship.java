/**
 * 
 */
package element;

import java.awt.Image;
import java.awt.Point;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Ship extends Drawable{

	private int life;
	private int damage;
	private int range;
	private Image image;
	private int orientation;
	
	/**
	 * Constructor of Ship
	 * @param p Position
	 * @param w width
	 * @param h height
	 */
	public Ship(Point p, int w, int h) {
		super(p, w, h);
		
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
	
	public void setOrientation(int o) {
		orientation = o;
	}
	
	/*
	 *			GETTERS
	 */
	
	public Image getImage() {
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
	
	public int getOrientation() {
		return orientation;
	}

}
