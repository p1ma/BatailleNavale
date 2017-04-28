/**
 * 
 */
package game;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

import element.GameElement;
import game.parameter.Era;
import game.parameter.IConfiguration;
import player.IPlayer;
import user.IUser;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 4, 2017
 */
public interface GameIModel {
	
	/**
	 * Returns the GameIModel's Date
	 * used to correctly save a Game
	 * (see DAO)
	 * @return the Game's beginning date
	 */
	public String getDate();
	
	/**
	 * Returns the GameElements of the Player (Human)
	 * @return the Human's GameElements
	 */
	public List<GameElement> getPlayerElements();
	
	/**
	 * Returns the GameElements of the Player (Computer)
	 * @return the Opponent's GameElements
	 */
	public List<GameElement> getOpponentElements();
	
	/**
	 * Select a GameElement if there is a GameElement
	 * at the given position pos
	 * @param pos the Point selected
	 * @return the selected GameElement
	 */
	public GameElement selectGameElement(Point pos);
	
	/**
	 * Returns the GameIModel start Image,
	 * used by the StartScreen
	 * @return the start Image
	 */
	public BufferedImage getGameStartImage();
	
	/**
	 * Returns the GameIModel title Image,
	 * used by the StartScreen
	 * @return the title Image
	 */
	public BufferedImage getGameTitleImage();
	
	/**
	 * Returns the GameIModel background Image,
	 * used by the Board and RadarScreen
	 * @return the background Image
	 */
	public BufferedImage getGameBackgroundImage();
	
	/**
	 * Returns the GameIModel missed Image,
	 * used by the MissedBox
	 * @return the missed Image
	 */
	public BufferedImage getGameMissedImage();
	
	/**
	 * Returns the GameIModel touched Image,
	 * used by the HitBox
	 * @return the touched Image
	 */
	public BufferedImage getGameTouchedImage();
	
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
	 * Returns the number of missed shot
	 * @return an integer
	 */
	public int getMissedShot();	
	
	/**
	 * Returns the number of hit shot
	 * @return an integer
	 */
	public int getHitShot();
	
	/**
	 * Returns the number of shot available
	 * @return an integer
	 */
	public int getShotsRemaining();

	/**
	 * Returns the Game's name
	 * @return a name
	 */
	public String getGameName();
	
	/**
	 * Returns the Game's difficulty
	 * @return a string representing the difficulty
	 */
	public String getDifficulty();
	
	/**
	 * Returns the Era
	 * @return the Era
	 */
	public Era getEra();
	
	/**
	 * Returns the Game's winner
	 * @return the winner
	 */
	public IPlayer getWinner();
	
	/**
	 * Returns the list of IPlayer
	 * @return the IPLayers
	 */
	public List<IPlayer> getPlayers();
	
	/**
	 * Returns the IPlayer human
	 * @return the Human
	 */
	public IPlayer getHuman();
	
	/**
	 * Returns the IPlayer computer
	 * @return the Computer
	 */
	public IPlayer getComputer();
	
	/**
	 * Returns the IUser who owns the current
	 * Game
	 * @return the IUser
	 */
	public IUser getUser();
	
	/**
	 * Returns the IConfiguration of the Game 
	 * @return the IConfiguration
	 */
	public IConfiguration getConfig();
	
	/**
	 * Indicates if the GameElement ge can be moved
	 * the the position pos
	 * @param ge a GameElement
	 * @param pos the wished new Position
	 * @return if the movement is possible or not
	 */
	public boolean isPositionAvailable(GameElement ge, Point pos);
	
	/**
	 * Indicates if the GameIModel has begun
	 * @return true if the GameIModel has begun
	 */
	public boolean hasBegun();
	
	/**
	 * Indicates if the GameIModel is over or not
	 * @return true if the GameIModel's party is finished
	 */
	public boolean isGameOver();
	
	/**
	 * Indicates if it's the Human's turn
	 * @return true if the Human has to play
	 */
	public boolean isPlayerTurn();
	
	/**
	 * Indicates if it's the Opponent's turn
	 * @return true if the Opponent has to play
	 */
	public boolean isOpponentTurn();
	
	/**
	 * Indicates if the Point at pos
	 * has been played or not
	 * @param pos the Point the check
	 * @return true if we can play this position
	 */
	public boolean isAlreadyPlayed(Point pos);
	
	/**
	 * Indicates if we can play at the pos position,
	 * this function makes more verifications than
	 * isAlreadyPlayed
	 * @param pos the Point the check
	 * @return true if we can play this position
	 */
	public boolean isPlayable(Point pos);
	
	/**
	 * Sets a IUser to the GameIModel
	 * @param user a IUser
	 */
	public void setUser(IUser user);
	
	/**
	 * Sets a Date to the GameIModel,
	 * a Date is set when the IUser confirms the
	 * Ship's position
	 * @param date
	 */
	public void setDate(Date date);
	
	/**
	 * Moves the GameElement ge to the positon pos.
	 * If possible it move the GameElement, else it do nothing.
	 * @param ge the GameElement selected
	 * @param pos the wished position
	 */
	public void setGameElementPosition(GameElement ge, Point pos);
	
	/**
	 * Moves all the GameElements available to random positions
	 */
	public void setGameElementsPosition();
	
	/**
	 * Sets the GameIModel to the IConfiguration config
	 * and Human's, Computer's GameElements
	 * @param config the IConfiguration
	 */
	public void setGameConfiguration(IConfiguration config);
	
	/**
	 * Sets the GameIModel to the IConfiguration config
	 * except Human's, Computer's GameElements
	 * (see GameXMLDAO)
	 * @param config the IConfiguration
	 */
	public void loadGameConfiguration(IConfiguration config);
	
	/**
	 * Changes the current IPlayers
	 */
	public void setNextPlayer();
	
	/**
	 * Changes the GameIModel state to over,
	 * and affects winner as the GameIModel's game winner
	 * @param winner IPlayer winner
	 */
	public void setGameOver(IPlayer winner);
	
	/**
	 * Sets the turn, if true then that's
	 * the Human's turn, else the Computer's turn
	 * @param turn true if Human false otherwise
	 */
	public void setTurn(boolean turn);
	
	/**
	 * Confirms the GameElement's position,
	 * the GameIModel can begun.
	 * It also change the GameIModel state.
	 */
	public void confirmGameElementsPosition();
	
	/**
	 * Action called by the user's mouse click at the
	 * Point pos
	 * @param pos the Point clicked by the player
	 */
	public void play(Point pos);
	
	/**
	 * Replays (means : new party)
	 */
	public void replay();
	
	/**
	 * Removes IConfiguration, and others GameIModel's
	 * attributes as the Computer etc.
	 */
	public void reset();
	
	/**
	 * Notify the user that the Game's window
	 * will be close any time soon.
	 */
	public void exit();
	
	/**
	 * Verify if the GameIModel is over,
	 * like if one of the two Players lost all
	 * its fleets etc.
	 */
	public void checkIfOver();
	
	/**
	 * Option diplays when the Game is over,
	 * and if userChoice is true then the call
	 * function replay() otherwise the function
	 * exit() is called.
	 * @param userChoice a boolean
	 */
	public void setExitOrReplay(boolean userChoice);
	
	/**
	 * Methods used to advert the View that the
	 * GameIModel has correctly load everything
	 * (so see DAO) and that the GameScreen can
	 * now change.
	 */
	public void loadOver();
	
	/**
	 * Notify the View (Observer) with the
	 * given parameter code.
	 * code can be REPLAY etc. (see GameAbstractModel)
	 * @param code a String code
	 */
	public void notify(final String code);
	
	/**
	 * Sets the current Screen to the
	 * ParamtersScreen
	 */
	public void setParametersScreen();
	
	/**
	 * Sets the current Screen to the
	 * WarmupScreen, actually warmup is
	 * a state and the Screen will be the normal
	 * GameScreen
	 */
	public void setWarmupScreen();

	/**
	 * Loads the title Image located at titlePath
	 * @param titlePath the path to the Image
	 */
	public void setTitleImage(String titlePath);
	
	/**
	 * Loads the start Image located at startPath
	 * @param titlePath the path to the Image
	 */
	public void setStartImage(String startPath);

	/**
	 * Sets the game difficulty
	 * @param strat the name of the wished IStrategy
	 */
	public void setDifficulty(String strat);
}
