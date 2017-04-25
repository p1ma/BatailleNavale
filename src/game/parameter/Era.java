/**
 * 
 */
package game.parameter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import element.Ship;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Era {

	/**
	 * Name of the Era
	 * for example : "Moderne", "XII"
	 */
	private String identifiant;
	
	/**
	 * Path to the backgroundImage
	 * because some Eras can have
	 * different background's Image
	 */
	private String backgroundPath;
	
	/**
	 * Path to the touchedImage
	 * because some Eras can have
	 * different touched's Image
	 */
	private String touchedPath;
	
	/**
	 * Path to the missedImage
	 * because some Eras can have
	 * different missed's Image
	 */
	private String missedPath;
	
	/**
	 * Background Image
	 */
	private BufferedImage background;
	
	/**
	 * Touched Image (see HitBox)
	 */
	private BufferedImage touched;
	
	/**
	 * Missed Image (see MissedBox)
	 */
	private BufferedImage missed;

	/**
	 * The List of Ships
	 */
	private List<Ship> fleet;

	/**
	 * 
	 * Constructs a Era with the given parameter(s)
	 * @param id the Era's name
	 * @param ships the List of Ships
	 */
	public Era(String id, List<Ship> ships) {
		identifiant = id;
		fleet = ships;
	}
	
	/**
	 * 
	 * Constructs a Era with the given parameter(s)
	 * Instanciates the Era with no specific values
	 * used by DAOs,
	 * it's our job to fill it ;p
	 */
	public Era() {
		fleet = new LinkedList<Ship>();
	}

	/**
	 * Loads 3 images
	 * @param backgroundP The background Image to load
	 * @param touchedP The touched Image to load
	 * @param missedP The missed Image to load
	 */
	public void loadImage(String backgroundP, String touchedP, String missedP) {
		try {
			backgroundPath = backgroundP;
			touchedPath = touchedP;
			missedPath = missedP;
			
			File url = new File(backgroundPath);
			background = ImageIO.read(url);

			url = new File(touchedPath);
			touched = ImageIO.read(url);

			url = new File(missedPath);
			missed = ImageIO.read(url);
		} catch (IOException e) {
			System.err.println("ERREUR LOAD IMAGE ERA!");
			System.exit(1);
		}
	}

	/**
	 * Sets the Era's name
	 * @param id name
	 */
	public void setIdentifiant(String id) {
		identifiant = id;
	}

	/**
	 * Adds a Ship to the list 
	 * @param ship Sip to add
	 */
	public void addShip(Ship ship) {
		fleet.add(ship);
	}

	/**
	 * Returns the List of Ship available
	 * @return the Fleet
	 */
	public List<Ship> getFleet() {
		return fleet;
	}

	/**
	 *  Returns the List of Ship available
	 *  inside the List given in parameter
	 * @param list the List in result 
	 * @return the List of Ship 
	 */
	public List<Ship> getFleet(List<Ship> list) {
		for(Ship s : fleet) {
			list.add(new Ship(s));
		}
		return list;
	}

	/**
	 * Returns just the Era's name
	 * @return String version of an Era
	 */
	public String toString() {
		return identifiant;
	}
	
	/**
	 * Returns just Era's background image path
	 * @return the path to the background image
	 */
	public String getBackgroundPath() {
		return backgroundPath;
	}
	
	/**
	 * Returns just Era's missed image path
	 * @return the path to the missed image
	 */
	public String getMissedPath() {
		return missedPath;
	}
	
	/**
	 * Returns just Era's touched image path
	 * @return the path to the touched image
	 */
	public String getTouchedPath() {
		return touchedPath;
	}
	
	/**
	 * Returns the background Image
	 * @return the background Image
	 */
	public BufferedImage getBackground() {
		return background;
	}
	
	/**
	 * Returns the missed Image (see MissedBox)
	 * @return the missed Image
	 */
	public BufferedImage getMissedImage() {
		return missed;
	}
	
	/**
	 * Returns the touched Image (see HitBox)
	 * @return the touched Image
	 */
	public BufferedImage getHitImage() {
		return touched;
	}
}
