/**
 * 
 */
package game;

import java.awt.Dimension;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Configuration {

	private Era era;
	private Dimension size;
	
	public Configuration() {
		size = new Dimension(10,10);
	}
	
	public Era getEra() {
		return era;
	}
	
	public Dimension getSize() {
		return size;
	}
	
	public int getWidth() {
		return (int)size.getWidth();
	}
	
	public int getHeight() {
		return (int)size.getHeight();
	}
}
