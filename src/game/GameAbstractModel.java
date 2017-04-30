/**
 * 
 */
package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import javax.imageio.ImageIO;

import game.parameter.Era;
import game.parameter.IConfiguration;
import player.Human;
import player.IPlayer;
import user.IUser;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 5, 2017
 */
public abstract class GameAbstractModel extends Observable implements GameIModel{

	/**
	 * Different Game's state
	 */
	public enum State { WARMUP, OVER, PLAYING};

	/**
	 * Different Game's update arguments :
	 */
	public static final String PARAMETERS = "PARAMETERS";
	public static final String RADAR = "RADAR";
	public static final String BOARD = "BOARD";
	public static final String WARMUP = "WARMUP";
	public static final String WARMUP_PROBLEM = "WARMUP_PROBLEM";
	public static final String WARMUP_OVER = "WARMUP_OVER";
	public static final String YOUR_TURN = "YOUR_TURN";
	public static final String HUMAN_WIN = "HUMAN_WIN";
	public static final String COMPUTER_WIN = "COMPUTER_WIN";
	public static final String DRAW = "DRAW";
	public static final String REPLAY = "REPLAY";
	public static final String EXIT = "EXIT";
	public static final String LOAD_GAME = "LOAD_GAME";
	
	/**
	 * Game's date of start
	 */
	private String startDate;
	
	/**
	 * The Game is owned by 1 IUser
	 */
	protected IUser user;
	
	/**
	 * The game has 1 configuration
	 */
	protected IConfiguration configuration;
	
	/**
	 * A Game's Human player
	 */
	protected IPlayer human;
	
	/**
	 * A Game's Computer player
	 */
	protected IPlayer computer;
	
	/**
	 * State attribute
	 */
	protected State state;

	/**
	 * Attribute used to manage players turn
	 */
	protected boolean humanTurn;
	
	/**
	 * Background of the Game (used in StarterScreen)
	 */
	private BufferedImage background;
	
	/**
	 * Title's image of the Game (used in StarterScreen)
	 */
	private BufferedImage title;
	
	/**
	 * Constructs a GameAbstractModel
	 * Initializes everything at null except
	 * Human the rest wil be fill by the ParameterController
	 */
	public GameAbstractModel() {
		super();
		human = new Human(this);
		computer = null;
		humanTurn = true;
		
		background = null;
		title = null;
		state = State.WARMUP;
	}
	
	/**
	 * Returns the GameIModel's Date
	 * used to correctly save a Game
	 * (see DAO)
	 * @return the Game's beginning date
	 */
	@Override
	public String getDate() {
		return startDate;
	}
	
	/**
	 * Returns the list of IPlayer
	 * @return the IPLayers
	 */
	@Override
	public List<IPlayer> getPlayers() {
		List<IPlayer> players = new LinkedList<IPlayer>();
		players.add(human);
		players.add(computer);
		return players;
	}
	
	/**
	 * Returns the IPlayer human
	 * @return the Human
	 */
	@Override
	public IPlayer getHuman() {
		return human;
	}
	
	/**
	 * Returns the Computer computer
	 * @return the Computer
	 */
	@Override
	public IPlayer getComputer() {
		return computer;
	}
	
	/**
	 * Returns the Game's width
	 * @return the width
	 */
	@Override
	public int getWidth() {
		return configuration.getWidth();
	}

	/**
	 * Returns the Game's height
	 * @return the height
	 */
	@Override
	public int getHeight() {
		return configuration.getHeight();
	}

	/**
	 * Returns the number of missed shot
	 * @return an integer
	 */
	public int getMissedShot()	{
		return computer.getMissedShot();
	}

	/**
	 * Returns the number of hit shot
	 * @return an integer
	 */
	public int getHitShot()	{
		return computer.getHitShot();
	}
	
	/**
	 * Returns the Game's difficulty
	 * @return a string representing the difficulty
	 */
	public String getDifficulty() {
		return configuration.getDifficulty();
	}
	
	/**
	 * Returns the Era
	 * @return the Era
	 */
	public Era getEra() {
		return configuration.getEra();
	}
	
	/**
	 * Returns the IUser who owns the current
	 * Game
	 * @return the IUser
	 */
	public IUser getUser() {
		return user;
	}

	/**
	 * Indicates if the GameIModel has begun
	 * @return true if the GameIModel has begun
	 */
	@Override
	public boolean hasBegun() {
		return (State.PLAYING == state);
	}

	/**
	 * Indicates if the GameIModel is over or not
	 * @return true if the GameIModel's party is finished
	 */
	@Override
	public boolean isGameOver() {
		return (state == State.OVER);
	}

	/**
	 * Indicates if it's the Human's turn
	 * @return true if the Human has to play
	 */
	@Override
	public boolean isPlayerTurn() {
		return humanTurn;
	}

	/**
	 * Indicates if it's the Opponent's turn
	 * @return true if the Opponent has to play
	 */
	@Override
	public boolean isOpponentTurn() {
		return !humanTurn;
	}

	/**
	 * Sets a IUser to the GameIModel
	 * @param user a IUser
	 */
	@Override
	public void setUser(IUser user) {
		this.user = user;
		human.setName( this.user.getUserName() );
	}
	
	/**
	 * Sets a Date to the GameIModel,
	 * a Date is set when the IUser confirms the
	 * Ship's position
	 * @param date
	 */
	@Override
	public void setDate(Date date) {
		startDate = date.toString();
	}
	
	/**
	 * Changes the current IPlayers
	 */
	@Override
	public void setNextPlayer() {
		humanTurn = !humanTurn;
	}

	/**
	 * Sets the turn, if true then that's
	 * the Human's turn, else the Computer's turn
	 * @param turn true if Human false otherwise
	 */
	@Override
	public void setTurn(boolean turn) {
		humanTurn = turn;
	}
	
	/**
	 * Option diplays when the Game is over,
	 * and if userChoice is true then the call
	 * function replay() otherwise the function
	 * exit() is called.
	 * @param userChoice a boolean
	 */
	@Override
	public void setExitOrReplay(boolean userChoice) {
		// user choice between replay and exit
		if ( userChoice ) {
			replay();
		} else {
			exit();
		}
	}

	/**
	 * Notify the View (Observer) with the
	 * given parameter code.
	 * code can be REPLAY etc. (see GameAbstractModel)
	 * @param code a String code
	 */
	@Override
	public void notify(String code) {
		this.setChanged();
		this.notifyObservers(code);
	}

	/**
	 * Sets the current Screen to the
	 * ParamtersScreen
	 */
	@Override
	public void setParametersScreen() {
		notify(PARAMETERS);
	}

	/**
	 * Sets the current Screen to the
	 * WarmupScreen, actually warmup is
	 * a state and the Screen will be the normal
	 * GameScreen
	 */
	@Override
	public void setWarmupScreen() {
		notify(WARMUP);
	}

	/**
	 * Replays (means : new party)
	 */
	@Override
	public void replay() {
		reset();
		notify(REPLAY);
	}

	/**
	 * Notify the user that the Game's window
	 * will be close any time soon.
	 */
	@Override
	public void exit() {
		notify(EXIT);
	}

	/**
	 * Returns the GameIModel start Image,
	 * used by the StartScreen
	 * @return the start Image
	 */
	@Override
	public BufferedImage getGameStartImage() {
		return background;
	}

	/**
	 * Returns the GameIModel title Image,
	 * used by the StartScreen
	 * @return the title Image
	 */
	@Override
	public BufferedImage getGameTitleImage() {
		return title;
	}

	/**
	 * Loads the title Image located at titlePath
	 * @param titlePath the path to the Image
	 */
	@Override
	public void setTitleImage(String titlePath) {
		try {
			File url = new File(titlePath);
			title = ImageIO.read(url);
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}

	/**
	 * Loads the start Image located at startPath
	 * @param titlePath the path to the Image
	 */
	@Override
	public void setStartImage(String startPath) {
		try {
			File url = new File(startPath);
			background = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the GameIModel background Image,
	 * used by the Board and RadarScreen
	 * @return the background Image
	 */
	@Override
	public BufferedImage getGameBackgroundImage() {
		return configuration.getGameBackgroundImage();
	}

	/**
	 * Returns the GameIModel missed Image,
	 * used by the MissedBox
	 * @return the missed Image
	 */
	@Override
	public BufferedImage getGameMissedImage() {
		return configuration.getGameMissedImage();
	}

	/**
	 * Returns the GameIModel touched Image,
	 * used by the HitBox
	 * @return the touched Image
	 */
	@Override
	public BufferedImage getGameTouchedImage() {
		return configuration.getGameTouchedImage();
	}
	
	/**
     * Returns the GameIModel sunk Image,
     * used by the SunkBox
     * @return the sunk Image
     */
	@Override
	public BufferedImage getGameSunkImage() {
	    return configuration.getGameSunkImage();
	};

	/**
	 * Returns the IConfiguration of the Game 
	 * @return the IConfiguration
	 */
	@Override
	public IConfiguration getConfig() {
		return configuration;
	}
}
