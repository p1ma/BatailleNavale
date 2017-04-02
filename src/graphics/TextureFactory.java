package graphics;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class TextureFactory {

	/**
	 * Singleton
	 */
	private static TextureFactory INSTANCE;
	
	/**
	 * Image of the missed box
	 */
	private Image missedBoxImage;
	
	/**
	 * Image of the hitted box
	 */
	private Image hitBoxImage;
	
	/**
	 * Background for the boards
	 */
	private Image background;
	
	/**
	 * background for the app
	 */
	private Image battleshipBackground;
	
	/**
	 * App title image
	 */
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
	
	/**
	 * Returns the missed box image
	 * @return Image
	 */
	public Image getMissedBoxImage() {
		return missedBoxImage;
	}
	
	/**
	 * Returns the hitted box image
	 * @return Image
	 */
	public Image getHitBoxImage() {
		return hitBoxImage;
	}
	
	/**
	 * Returns the board background
	 * @return Image
	 */
	public Image getBoardBackground() {
		return background;
	}
	
	/**
	 * Returns ths background of the app
	 * @return Image
	 */
	public Image getBattleshipBackground() {
		return this.battleshipBackground;
	}
	
	/**
	 * Returns the title app
	 * @return Image
	 */
	public Image getBattleshipTitle() {
		return this.battleshipTitle;
	}
}
