/**
 * 
 */
package player;

import java.awt.Point;
import java.util.List;

import element.GameElement;
import element.Ship;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 6, 2017
 */
public interface IPlayer {

	/**
	 * Returns true if the IPlayer
	 * is a Computer
	 * @return true if this. is a Computer
	 */
	public boolean isComputer();
	
	/**
	 * Returns true if the IPlayer
	 * is a Human
	 * @return true if this. is a Human
	 */
	public boolean isHuman();
	
	/**
	 * Returns true if the IPlayer
	 * is alive (it depend of the GameIModel chosen)
	 * see BattleShipPlayer
	 * @return true if alive
	 */
	public boolean isAlive();
	
	/**
	 * Returns true if the position at p has been
	 * played
	 * @param p the Point to check
	 * @return true if already played
	 */
	public boolean isAlreadyPlayed(Point p);
	
	/**
	 * Affects a randomly position to the IPlayer's board GameElements
	 * the List list
	 * @param list the List of GameElement to add
	 */
	public void setBoardElements(List<Ship> list);
	
	/**
	 * Randomly sets the GameElement inside the Game's board
	 */
	public void setBoardElements();
	
	/**
	 * Puts directly into the data structure the List of
	 * GameElement list
	 */
	public void putBoardElements(List<Ship> list);
	
	/**
	 * Affects to the IPlayer's radar GameElement list
	 * the List list
	 * @param list the List of GameElement to add
	 */
	public void setRadarElements(List<GameElement> list);
	
	/**
	 * Sets the IPlayer's name, if there is a IUser
	 * then it is more likely to have IUser's name = IPlayer's name
	 * @param name the IPlayer's name
	 */
	public void setName(String name);
	
	/**
	 * Method used to update the Map containing the IPlayer's GameElements
	 */
	public void updateMap();
	
	/**
	 * Lets the IPlayer play,
	 * if teh IPlayer is a Computer then this method will
	 * call the IA affected to the Computer (see Computer | IStrategy)
	 */
	public void play();
	
	/**
	 * Adds to the IPlayer's radar the GameElement box at the position pos
	 * @param pos the position where the GameElement box is suppose to be
	 * @param box the GameElement to add
	 */
	public void updateRadar(Point pos, GameElement box);
	
	/**
	 * Sets the number of succesful shots at shot
	 * @param shot the number of hit shot
	 */
	public void setHitShot(int shot);
	
	/**
	 * Sets the number of missed shots at shot
	 * @param shot the number of missed shot
	 */
	public void setMissedShot(int shot);
	
	/**
	 * Returns the number of succesful shots
	 * @return the number of hit shot
	 */
	public int getHitShot();
	
	/**
	 * Returns the number of missed shots
	 * @return the number of missed shot
	 */
	public int getMissedShot();
	
	/**
	 * Verify if there is a GameELement at the position p,
	 * if so then it return a HitBox, else a MissedBox
	 * @param p the position to check
	 * @return a Box
	 */
	public GameElement touchedAt(Point p);
	
	/**
	 * Returns the GameElements placed in the BoardScreen
	 * @return a List of Ship
	 */
	public List<Ship> getBoardElements();
	
	/**
	 * Returns the GameElements placed in the RadarScreen
	 * @return a List of GameElement
	 */
	public List<GameElement> getRadarElements();	
	
	/**
	 * Returns the name of the IPlayer
	 * @return IPlayer's name
	 */
	public String getName();
}
