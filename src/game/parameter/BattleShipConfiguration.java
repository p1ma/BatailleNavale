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
 * Mar 13, 2017
 */
public class BattleShipConfiguration implements IConfiguration{

	/**
	 * Era of the BattleShipGame
	 */
	private Era era;
	
	/**
	 * Dimension of the World
	 * [10 x 10] per example
	 */
	private Dimension dimension;
	
	/**
	 * Difficulty
	 */
	private String difficulty;
	
	/**
	 * Ammunition available in pourcent,
	 * per example :
	 * 75% on a [10 x 10] map means
	 * 75 shots availables.
	 */
	private int ammunition;

	/**
	 * Constructs a BattleShipConfiguration with the given parameter(s)
	 * @param cpy the IConfiguration to copy
	 */
	public BattleShipConfiguration(IConfiguration cpy) {
		era = cpy.getEra();
		dimension = cpy.getDimension();
		difficulty = cpy.getDifficulty();
		ammunition = cpy.getLimit();
	}
	
	/**
	 * Constructs a BattleShipConfiguration
	 * Initializes everything to null or 0.
	 * Used in DAOs classes.
	 * Our job is to fill it ;p
	 */
	public BattleShipConfiguration() {
		era = null;
		dimension = null;
		difficulty = "";
		ammunition = 0;
	}
	
	/**
	 * 
	 * Constructs a BattleShipConfiguration with the given parameter(s)
	 * @param dim the Dimension
	 * @param er the Era
	 * @param level the selected level
	 * @param ammu the ammunition available
	 */
	public BattleShipConfiguration(Dimension dim, Era er, String level, int ammu) {
		era = er;
		dimension = dim;
		difficulty = level;
		ammunition = ammu;
	}

	/**
	 * Returns the Era
	 * @return an Era
	 */
	public Era getEra() {
		return era;
	}
	
	/**
	 * Sets the Era to era
	 * @param era the Era choosen
	 */
	public void setEra(Era era) {
		this.era = era;
	}

	/**
	 * Returns the Dimension
	 * @returns current Dimension
	 */
	public Dimension getDimension() {
		return dimension;
	}
	
	/**
	 * Sets the Dimension
	 * @param dim, Dimension selected
	 */
	public void setDimension(Dimension dim) {
		dimension = dim;
	}
	
	/**
	 * Returns the Game's width
	 * @return the width
	 */
	public int getWidth() {
		return (int)dimension.getWidth();
	}

	/**
	 * Returns the Game's height
	 * @return the height
	 */
	public int getHeight() {
		return (int)dimension.getHeight();
	}

	/**
	 * Returns the List of Ships
	 * used by Human and Computer
	 * @return the List of Ship
	 */
	public List<Ship> getGameElements() {
		return era.getFleet();
	}

	/**
	 * Returns the List of Ships 
	 * with the result List in injection.
	 * used by Human and Computer
	 * @return the List of Ship
	 */
	public List<Ship> getGameElements(List<Ship> injection) {
		return era.getFleet(injection);
	}

	/**
	 * Sets the Game's difficulty
	 * @param the Difficulty 
	 */
	public void setDifficulty(String diff) {
		difficulty = diff;
	}

	/**
	 * Returns the Game's difficulty
	 * @return the difficulty
	 */
	@Override
	public String getDifficulty() {
		return difficulty;
	}

	/**
	 * Returns the Game's background Image
	 * used by the StartScreen
	 * @return the Game's background Image
	 */
	@Override
	public BufferedImage getGameBackgroundImage() {
		return era.getBackground();
	}

	/**
	 * Returns the Game's missed Image
	 * used by the MissedBox
	 * @return the Game's missed Image
	 */
	@Override
	public BufferedImage getGameMissedImage() {
		return era.getMissedImage();
	}

	/**
	 * Returns the Game's touched Image
	 * used by the HitBox
	 * @return the Game's touched Image
	 */
	@Override
	public BufferedImage getGameTouchedImage() {
		return era.getHitImage();
	}

	/**
	 * Returns the ammunition available
	 * @return the ammunition
	 */
	@Override
	public int getLimit() {
		return ammunition;
	}

	/**
	 * Sets the ammunition to limit
	 * @param the ammunition available
	 */
	@Override
	public void setLimit(int limit) {
		if (dimension != null) {
			ammunition = (int) (( dimension.getHeight() * dimension.getWidth() * limit ) / 100);
		} else {
			ammunition = limit;
		}
	}
}
