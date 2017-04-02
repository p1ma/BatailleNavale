package game;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Configuration {

	/**
	 * Chosen during the configuration of the party
	 */
	private Era era;
	
	/**
	 * Width of the world
	 */
	public final static int WIDTH = 10;
	/**
	 * height of the world
	 */
	public final static int HEIGHT = 10;
	
	
	
	
	
	/**
	 * Constructor
	 */
	public Configuration() {

	}
	
	
	
	
	
	/**
	 * Returns the Era of the party
	 * @return Era
	 */
	public Era getEra() {
		return era;
	}
}
