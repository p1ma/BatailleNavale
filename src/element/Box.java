package element;

import java.awt.Point;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public abstract class Box extends Drawable {

	/**
	 * Box's width
	 */
	private final static int WIDTH = 1;
	
	/**
	 * Box's height
	 */
	private final static int HEIGHT = 1;
	
	
	
	
	
	/**
	 * Constructor of Box 
	 * @param p : Point, position of box
	 */
	public Box(Point p) {
		super(p, WIDTH, HEIGHT);
	}
	
}
