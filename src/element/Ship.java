/**
 * 
 */
package element;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Ship extends GameElement{

	/**
	 * Image's of the Ship,
	 * maximum 2, 
	 * the normal one [0],
	 * and the rotate version [1]
	 */
	private BufferedImage[] image;
	
	/**
	 * Name of the Ship
	 */
	private String identifiant;
	
	/**
	 * Image's path
	 */
	private final String path;

	/**
	 * Constructor of Ship with the given parameter(s)
	 * @param p position
	 * @param w width
	 * @param h height
	 * @param img path to the Image
	 */
	public Ship(String id, Point p, int w, int h, String img) {
		super(p, w, h);
		identifiant = id;
		image = new BufferedImage[2];
		path = img;
		loadImage(img);
	}	
	
	/**
	 * 
	 * Constructs a Ship with the given parameter(s)
	 * @param id name
	 * @param p position
	 * @param w width
	 * @param h height
	 * @param o orientation
	 * @param img path to the Image
	 */
	public Ship(String id, Point p, int w, int h, int o, String img) {
		super(p, w, h, o);
		identifiant = id;
		image = new BufferedImage[2];
		path = img;
		loadImage(img);
		
		if (o != 0) {
			rotateImage();
		}
	}
	
	/**
	 * 
	 * Constructs a Ship with the given parameter(s)
	 * copy constructor
	 * @param s the Ship to copy
	 */
	public Ship(Ship s) {
		super(s.getPosition(),
				s.getWidth(),
				s.getHeight(),
				s.getOrientation());
		path = s.getPath();
		image = new BufferedImage[2];
		image[0] = s.getImage();
		
		identifiant = s.getIdentifiant();
		
		if (s.getOrientation() != 0) {
			rotate();
		}
	}
	
	/**
	 * Rotates the Image if that's
	 * was not already done.
	 */
	public void rotateImage() {
		/* 
		 * if the rotated image does not exist
		 * then we create it
		 */		
		if (image[1] == null) {
			int width = image[0].getWidth();
		    int height = image[0].getHeight();
			image[1] = new BufferedImage(height, 
					width, 
					image[0].getType());
			for(int i=0; i<width; i++) {
		        for(int j=0; j<height; j++) {
		        	image[1].setRGB(height - 1 - j,
		        			width - 1 - i,
		        			image[0].getRGB(i, j));
		        }
			}
		}
	}

	/**
	 * Loads the Ship's Image from the path
	 * given in parameter
	 * @param path the Path to load
	 */
	private void loadImage(String path) {
		try {
			image[0] = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.err.println("CHEMIN DE L'IMAGE INCORRECT");
		}
	}
	
	/**
	 * Sets the identifiants of the Ship
	 * @param id the id to sett
	 */
	public void setIdentifiant(String id) {
		identifiant = id;
	}

	/**
	 * Returns the Ship's name
	 * @return a identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}
	
	/**
	 * Returns the path of the Ship's image
	 * @return a path 
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Returns the correct Image
	 * according to the Ship's orientation
	 * @return a Image
	 */
	public BufferedImage getImage() {
		return image[getOrientation()];
	}
	
	/**
	 * Indicates if the this GameElement is useful
	 * to the Strategy, like
	 * a hitBox is useful (it let us know that there is a
	 * Ship at the position)
	 * @return true if the GameElement can be used to the IA
	 */
	@Override
	public boolean isStrategicallyUseful() {
		return false;
	}
}
