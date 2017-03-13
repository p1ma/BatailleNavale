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
	private String imagePath;
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

	@Override
	public Image getImage() {
		return null;
	}

}
