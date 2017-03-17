/**
 * 
 */
package graphics;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class TextureFactory {

	private static TextureFactory INSTANCE;
	
	private Image missedBoxImage;
	private Image hitBoxImage;
	
	private Image background;
	
	/**
	 * Constructor of TextureFactory
	 */
	private TextureFactory() {
		try {
			/* examples */
		    File url = new File("textures/missedBox.png");
		    missedBoxImage = ImageIO.read(url);
		    
		    url = new File("textures/hitBox.png");
		    hitBoxImage = ImageIO.read(url);
		    
		    url = new File("textures/ocean.jpg");
		    background = ImageIO.read(url);
		   
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * @return instance
	 */
	public static TextureFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TextureFactory();
		}
		return INSTANCE;
	}
	
	public Image getMissedBoxImage() {
		return missedBoxImage;
	}
	
	public Image getHitBoxImage() {
		return hitBoxImage;
	}
	
	public Image getBoardBackground() {
		return background;
	}

}
