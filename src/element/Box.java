/**
 * 
 */
package element;

import java.awt.Point;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public abstract class Box extends Drawable{

	private final static int WIDTH = 1;
	private final static int HEIGHT = 1;
	/**
	 * Constructor of Box 
	 * @param p
	 * @param w
	 * @param h
	 */
	public Box(Point p) {
		super(p, WIDTH, HEIGHT);
		// TODO Auto-generated constructor stub
	}

}
