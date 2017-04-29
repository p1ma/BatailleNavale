/**
 * 
 */
package storage.image;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.BeforeClass;
import org.junit.Test;

import game.BattleShipGame;
import game.GameIModel;
import storage.XMLDAOFactory;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 29, 2017
 */
public class BattleShipImageTest {

	/**
	 * TEST FILE IS GAME.XML ( see DAO/Game.xml )
	 */
	private static ImageDAO config;
	private static GameIModel game;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		config = XMLDAOFactory.getInstance().getImageDAO();
		game = new BattleShipGame();
	}


	@Test
	public void setStartImage() throws IOException {
		config.setStartImage(game);

		BufferedImage start = game.getGameStartImage();

		String path = "textures/backgroundStartScreen.jpg";

		BufferedImage image = ImageIO.read(new File(path));

		int width = start.getWidth(),
				height = start.getHeight();
		
		int width2 = image.getWidth(),
				height2 = image.getHeight();
		
		assertTrue(width == width2);
		assertTrue(height == height2);
		
		int rgb1, rgb2;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				rgb1 = start.getRGB(i, j);
				rgb2 = image.getRGB(i, j);
				
				assertTrue(rgb1 == rgb2);
			}
		} 
	}
	
	
	@Test
	public void setTitleImage() throws IOException {
		config.setTitleImage(game);

		BufferedImage start = game.getGameTitleImage();

		String path = "textures/title.png";

		BufferedImage image = ImageIO.read(new File(path));

		int width = start.getWidth(),
				height = start.getHeight();
		
		int width2 = image.getWidth(),
				height2 = image.getHeight();
		
		assertTrue(width == width2);
		assertTrue(height == height2);
		
		int rgb1, rgb2;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				rgb1 = start.getRGB(i, j);
				rgb2 = image.getRGB(i, j);
				
				assertTrue(rgb1 == rgb2);
			}
		} 
	}

}
