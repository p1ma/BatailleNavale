/**
 * 
 */
package game.parameter;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.List;

import element.Ship;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 5, 2017
 */
public interface IConfiguration {

	/**
	 * Returns the Era
	 * @return an Era
	 */
	public Era getEra();
	
	/**
	 * Returns the Dimension
	 * @returns current Dimension
	 */
	public Dimension getDimension();
	
	/**
	 * Returns the Game's width
	 * @return the width
	 */
	public int getWidth();
	
	/**
	 * Returns the Game's height
	 * @return the height
	 */
	public int getHeight();
	
	/**
	 * Returns the ammunition available
	 * @return the ammunition
	 */
	public int getLimit();
	
	/**
	 * Returns the Game's difficulty
	 * @return the difficulty
	 */
	public String getDifficulty();
	
	/**
	 * Returns the List of Ships
	 * used by Human and Computer
	 * @return the List of Ship
	 */
	public List<Ship> getGameElements();
	
	/**
	 * Returns the List of Ships 
	 * with the result List in injection.
	 * used by Human and Computer
	 * @return the List of Ship
	 */
	public List<Ship> getGameElements(List<Ship> injection);
	
	/**
	 * Sets the Game's difficulty
	 * @param the Difficulty 
	 */
	public void setDifficulty(String diff);
	
	/**
	 * Sets the ammunition to limit
	 * @param the ammunition available
	 */
	public void setLimit(int limit);
	
	/**
	 * Sets the Dimension
	 * @param dim, Dimension selected
	 */
	public void setDimension(Dimension dim);
	
	/**
	 * Sets the Era to era
	 * @param era the Era choosen
	 */
	public void setEra(Era era);

	/**
	 * Returns the Game's background Image
	 * used by the StartScreen
	 * @return the Game's background Image
	 */
	public BufferedImage getGameBackgroundImage();
	
	/**
	 * Returns the Game's missed Image
	 * used by the MissedBox
	 * @return the Game's missed Image
	 */
	public BufferedImage getGameMissedImage();
	
	/**
	 * Returns the Game's touched Image
	 * used by the HitBox
	 * @return the Game's touched Image
	 */
	public BufferedImage getGameTouchedImage();
}
