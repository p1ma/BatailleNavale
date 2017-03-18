/**
 * 
 */
package element;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public abstract class Drawable {

	protected Point position;
	protected int width;
	protected int height;
	protected Rectangle boundingBox;
	
	/**
	 * Constructor
	 * @param p position 
	 * @param w width
	 * @param h height
	 */
	public Drawable(Point p, int w, int h) {
		this.position = p;
		this.width = w;
		this.height = h;
		this.boundingBox = new Rectangle(h, w);
		this.boundingBox.setLocation(position);
	}
	
	public abstract Image getImage();
	
	/**
	 * Returns x coordinate of the Drawable
	 * @return x, and integer
	 */
	public int getX() {
		return (int)position.getX();
	}
	
	/**
	 * Returns y coordinate of the Drawable
	 * @return y, and integer
	 */
	public int getY() {
		return (int)position.getY();
	}
	
	/**
	 * Returns the width of the Drawable
	 * @return width, and integer
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns the heigth of the Drawable
	 * @return height, and integer
	 */
	public int getHeight() {
		return height;
	}
	
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public boolean intersect(Point point) {
		return (boundingBox.contains(point));
	}
	
	public void setPosition(Point point) {
		position = new Point(point);
		boundingBox.setLocation(position);
	}
}
