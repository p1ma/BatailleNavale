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

	/**
	 * Position, width and height of Drawable
	 */
	protected Rectangle boundingBox;
	
	/**
	 * 0 : horizontal orientation, 1 : vertical orientation
	 */
	protected int orientation;

	
	
	
	
	/**
	 * Constructor
	 * @param p : Point, position of Drawable
	 * @param w : int, width
	 * @param h : int, height
	 */
	public Drawable(Point p, int w, int h) {
		this.boundingBox = new Rectangle(p.x, p.y, w, h);
		this.orientation = 0;
	}

	
	
	
	
	/**
	 * Changes the orientation of the Drawable by placing it at the point p
	 * @param p : Point, new position
	 */
	public void rotate(Point p) {
		int lastWidth = this.boundingBox.width, lastHeight = this.boundingBox.height;
		this.boundingBox.height = lastWidth;
		this.boundingBox.width = lastHeight;
		this.setRotation();
		this.setPosition(p);
	}
	
	/**
	 * Return true if the point is in the bounding box
	 * False otherwise
	 * @param point : Point, position to be tested
	 * @return boolean
	 */
	public boolean intersect(Point point) {
		return (boundingBox.contains( point ));
	}

	/**
	 * Return true if the Drawable is in the bounding box
	 * False otherwise
	 * @param d : Drawable, Drawable to be tested
	 * @return boolean
	 */
	public boolean intersect(Drawable d) {
		return (boundingBox.intersects( d.getBoundingBox() ));
	}
	
	
	
	
	
	/**
	 * Returns the image of the Drawable
	 * @return Image
	 */
	public abstract Image getImage();
	
	/**
	 * Returns x coordinate of the Drawable
	 * @return int
	 */
	public int getX() {
		return (int) this.boundingBox.x;
	}

	/**
	 * Returns y coordinate of the Drawable
	 * @return int
	 */
	public int getY() {
		return (int) this.boundingBox.y;
	}
	

	/**
	 * Returns the width of the Drawable
	 * @return int
	 */
	public int getWidth() {
		return this.boundingBox.width;
	}

	/**
	 * Returns the heigth of the Drawable
	 * @return int
	 */
	public int getHeight() {
		return this.boundingBox.height;
	}

	/**
	 * Returns the bounding box of the Drawable
	 * @return Rectangle
	 */
	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	/**
	 * Returns the position of the Drawable
	 * @return Point
	 */
	public Point getPosition() {
		return this.boundingBox.getLocation();
	}

	/**
	 * Returns the orientation of the Drawable
	 * @return int
	 */
	public int getOrientation() {
		return orientation;
	}
	
	/**
	 * Changes the orientation of the Drawable
	 */
	public void setRotation() {
		this.orientation = 1 - this.orientation;
	}

	/**
	 * Changes the position of the Drawable
	 * @param point : Point, new position
	 */
	public void setPosition(Point point) {
		boundingBox.setLocation(point);
	}
	
}
