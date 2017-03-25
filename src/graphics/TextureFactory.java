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
	private Image battleshipBackground;
	private Image battleshipTitle;
	
	/**
	 * Constructor of TextureFactory
	 */
	private TextureFactory() {
		try {
		    missedBoxImage = ImageIO.read(new File("textures/missedBox.png"));
		    
		    hitBoxImage = ImageIO.read(new File("textures/hitBox.png"));
		    
		    background = ImageIO.read(new File("textures/ocean.jpg"));
		    
		    this.battleshipBackground = ImageIO.read(new File("textures/fondBattleShip.jpg"));

		    this.battleshipTitle = ImageIO.read(new File("textures/BattleshipTitle.png"));
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
	
	public Image getBattleshipBackground() {
		return this.battleshipBackground;
	}
	
	public Image getBattleshipTitle() {
		return this.battleshipTitle;
	}
}
