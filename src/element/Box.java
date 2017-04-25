/**
 * 
 */
package element;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public abstract class Box extends GameElement{

	/**
	 * Width of the BOX
	 */
	private final static int WIDTH = 1;
	
	/**
	 * Height of the BOX
	 */
	private final static int HEIGHT = 1;
	
	/**
	 * Background's Image
	 */
	protected BufferedImage background;
	
	/**
	 * Constructor of Box 
	 * @param p position of the Box
	 * @param w the width
	 * @param h the height
	 */
	public Box(Point p) {
		super(p, WIDTH, HEIGHT);
	}
	
	/**
	 * Rotate a Box, 
	 * no useful for the moment
	 */
	public void rotateImage() {
		// nothing to rotate atm...
	}

	/**
	 * Returns Box's Image
	 */
	@Override
	public BufferedImage getImage() {
		return background;
	}	
}
