/**
 * 
 */
package monde;

import java.awt.Dimension;


/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 2, 2017
 */
public class Configuration {

	private Dimension dimension;
	
	public Configuration() {
		dimension = new Dimension(10,10);
	}
	
	public int height() {
		return dimension.height;
	}
	
	public int width() {
		return dimension.width;
	}
	
	
}
