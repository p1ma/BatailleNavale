/**
 * 
 */
package element;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import game.Game;
import graphics.GameScreen;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public abstract class Drawable {

	//protected Point position;
	//protected int width;
	//protected int height;
	protected Rectangle boundingBox;
	protected double orientation;

	/**
	 * Constructor
	 * @param p position 
	 * @param w width
	 * @param h height
	 */
	public Drawable(Point p, int w, int h) {
		/*this.position = p;
		this.width = w;
		this.height = h;
*/
		
		this.boundingBox = new Rectangle(p.x, p.y, w, h);
		
		this.orientation = 0.;
	}

	public abstract Image getImage();

	/*
	 * J'ai commencé le code de la rotation, 
	 * je prefere le push tout de suite
	 * mais 
	 */
	public void rotate(Point p) {
		int lastWidth = this.boundingBox.width, lastHeight = this.boundingBox.height;
		this.boundingBox.height = lastWidth;
		this.boundingBox.width = lastHeight;
		this.setRotation();
		this.setPosition(p);
	}
	
	public void setRotation() {
		this.orientation = 1 - this.orientation;
	}

	/**
	 * Returns x coordinate of the Drawable
	 * @return x, and integer
	 */
	public int getX() {
		return (int) this.boundingBox.x;
	}

	/**
	 * Returns y coordinate of the Drawable
	 * @return y, and integer
	 */
	public int getY() {
		return (int) this.boundingBox.y;
	}

	/**
	 * Returns the width of the Drawable
	 * @return width, and integer
	 */
	public int getWidth() {
		return this.boundingBox.width;
	}

	/**
	 * Returns the heigth of the Drawable
	 * @return height, and integer
	 */
	public int getHeight() {
		return this.boundingBox.height;
	}

	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	public Point getPosition() {
		return this.boundingBox.getLocation();
	}

	public double getOrientation() {
		return orientation;
	}

	public boolean intersect(Point point) {
		return (boundingBox.contains( point ));
	}

	public boolean intersect(Drawable d) {
		return (boundingBox.intersects( d.getBoundingBox() ));
	}

	public void setPosition(Point point) {
		boundingBox.setLocation(point);
	}
}
