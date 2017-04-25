/**
 * 
 */
package element;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public abstract class GameElement {

	/**
	 * BoundingBox, used to locate
	 * and get the widht et height of this
	 */
	protected Rectangle boundingBox;
	
	/**
	 * Orientation used to know if the
	 * GameElement has to be rotated or not
	 * 0 = no rotation
	 */
	protected int orientation;
	
	/**
	 * Constructor
	 * @param p position 
	 * @param w width
	 * @param h height
	 */
	public GameElement(Point p, int w, int h) {
		this.boundingBox = new Rectangle(w, h);
		this.boundingBox.setLocation(p);
		this.orientation = 0;
	}

	/**
	 * Constructs a GameElement with the given parameter(s)
	 * @param p position 
	 * @param w width
	 * @param h height
	 * @param o orientation
	 */
	public GameElement(Point p, int w, int h, int o) {
		this.boundingBox = new Rectangle(w, h);
		this.boundingBox.setLocation(p);
		this.orientation = o;
	}

	/**
	 * Returns the GameElement's Image
	 * @return an Image
	 */
	public abstract BufferedImage getImage();
	
	/**
	 * Rotates the Image
	 */
	public abstract void rotateImage();
	
	/**
	 * Indicates if the this GameElement is useful
	 * to the Strategy, like
	 * a hitBox is useful (it let us know that there is a
	 * Ship at the position)
	 * @return true if the GameElement can be used to the IA
	 */
	public abstract boolean isStrategicallyUseful();
	
	/**
	 * Rotates the GameElement,
	 * first it change the orientation value,
	 * then calls the method rotateImage
	 */
	public void rotate() {
		this.orientation = ( 1 - orientation);
		boundingBox.setSize(getHeight(), getWidth());
		rotateImage();
	}
	
	/**
	 * Returns x coordinate of the GameElement
	 * @return x, an integer
	 */
	public int getX() {
		return (int)boundingBox.getX();
	}
	
	/**
	 * Returns y coordinate of the GameElement
	 * @return y, an integer
	 */
	public int getY() {
		return (int)boundingBox.getY();
	}
	
	/**
	 * Returns the width of the GameElement
	 * @return width, an integer
	 */
	public int getWidth() {
		return (int)boundingBox.getWidth();
	}
	
	/**
	 * Returns the heigth of the GameElement
	 * @return height, an integer
	 */
	public int getHeight() {
		return (int)boundingBox.getHeight();
	}
	
	/**
	 * Returns the boundingBox of the GameElement
	 * @return the boundingBox
	 */
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	
	/**
	 * Returns the position of the GameElement
	 * @return a Point used to locate the GameElement
	 */
	public Point getPosition() {
		return boundingBox.getLocation();
	}
	
	/**
	 * Sets the new GameElement's position to point
	 * @param point the new Position of the GameElement
	 */
	public void setPosition(Point point) {
		boundingBox.setLocation(point);
	}
	
	/**
	 * Returns the orientation value
	 * 0 means "no rotation", 1 means "need rotation"
	 * @return orientation value
	 */
	public int getOrientation() {
		return orientation;
	}
	
	/**
	 * Indicates if the GameElement's boundingBox
	 * contains the Point point
	 * @param point a Point tested
	 * @return if there is a collision or not
	 */
	public boolean intersect(Point point) {
		return (boundingBox.contains( point ));
	}
	
	/**
	 * Indicates if the GameElement's boundingBox
	 * intersect the BoundingBox of the parameter d
	 * @param d GameElement tested for collision
	 * @return if there is a collision or not
	 */
	public boolean intersect(GameElement d) {
		return (boundingBox.intersects( d.getBoundingBox() ));
	}
	
	/**
	 * Equals function between 2 GameElements
	 */
	@Override
	public boolean equals(Object obj) {
		GameElement ge = (GameElement)obj;
		Rectangle bound = ge.getBoundingBox();
		Point pos = bound.getLocation();
		return (bound.getWidth() == getWidth() && 
				bound.getHeight() == getHeight() && 
				pos.getX() == getPosition().getX() &&
				pos.getY() == getPosition().getY() );
	}
}
