/**
 * 
 */
package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import element.GameElement;
import element.Ship;
import game.parameter.BattleShipConfiguration;
import game.parameter.IConfiguration;
import player.Computer;
import player.Human;
import player.IPlayer;
import player.strategy.IStrategy;
import player.strategy.StrategyFactory;
import storage.XMLDAOFactory;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class BattleShipGame extends GameAbstractModel {

	/**
	 * Game's name
	 */
	private final String name = "Bataille Navale";

	/**
	 * 
	 * Constructs a BattleShipGame
	 */
	public BattleShipGame() {
		super();
	}

	/**
	 * Confirms the GameElement's position,
	 * the GameIModel can begun.
	 * It also change the GameIModel state.
	 */
	public void confirmGameElementsPosition() {
		List<Ship> fleet = human.getBoardElements();

		boolean intersect = false;
		int f = 0, s = 1;
		Ship first = fleet.get(f),
				second = fleet.get(s);

		while( (!intersect) && (f != fleet.size()) ) {

			while( s < fleet.size() && !intersect ) {
				second = fleet.get(s);
				if( first.intersect(second) ) {
					intersect = true;
				}
				s++;
			}
			f++;
			s = f + 1;
		}
		state = (intersect == true) ?  State.WARMUP : State.PLAYING ;

		// initialize the Date
		setDate(Calendar.getInstance().getTime());

		// could have test intersect directly
		String message = "";
		if ( hasBegun() ) {
			human.updateMap();
			message = WARMUP_OVER;

			// DAO save
			XMLDAOFactory.getInstance().getGameDAO().save(this);

		} else {
			message = WARMUP_PROBLEM;
		}

		notify(message);	
	}

	/**
	 * Action called by the user's mouse click at the
	 * Point pos
	 * @param pos the Point clicked by the player
	 */
	public void play(Point pos) {

		// while the two players have their fleet then they play
		if ( !isGameOver() ) {
			GameElement box = null;
			// if it is the Human's turn then ...
			if ( isPlayerTurn() ) {
				//notify(YOUR_TURN);
				box = computer.touchedAt(pos);
				human.updateRadar(pos, box);

				notify(RADAR);
			} else {
				// Computer's turn
				box = human.touchedAt(pos);
				computer.updateRadar(pos, box);

				notify(BOARD);
			}

			// checks if games if over or not
			checkIfOver();

			if ( isGameOver() ) {
				// DAO remove
				XMLDAOFactory.getInstance().getGameDAO().remove(this);

				// the Game is over, so we notify who the winner is
				IPlayer winner = getWinner();
				setGameOver( winner );
				return;
			}

			// next player...
			setNextPlayer();

			// DAO save
			XMLDAOFactory.getInstance().getGameDAO().save(this);

			/*
			 * Now if it's the Computer's turn
			 * we have to call a specific function
			 */
			if ( isOpponentTurn() ) {
				computer.play();
			}
		} else {
			// the Game is over, so we notify who the winner is
			IPlayer winner = getWinner();
			setGameOver( winner );
		}
	}

	/**
	 * Returns the GameElements of the Player (Computer)
	 * @return the Opponent's GameElements
	 */
	public List<GameElement> getOpponentElements() {
		return human.getRadarElements();
	}

	/**
	 * Returns the GameElements of the Player (Human)
	 * @return the Human's GameElements
	 */
	public List<GameElement> getPlayerElements() {
		List<GameElement> result = new LinkedList<GameElement>();
		result.addAll(human.getBoardElements());
		result.addAll(computer.getRadarElements());
		return result;
	}

	/**
	 * Select a GameElement if there is a GameElement
	 * at the given position pos
	 * @param pos the Point selected
	 * @return the selected GameElement
	 */
	public GameElement selectGameElement(Point pos) {
		Ship res = null;
		List<Ship> humanFleet = human.getBoardElements();
		if ( state == State.WARMUP ) {
			for( Ship s : humanFleet) {
				if (s.intersect(pos)) {
					return s;
				}
			}
		} 
		return res;
	}

	/**
	 * Methods used to advert the View that the
	 * GameIModel has correctly load everything
	 * (so see DAO) and that the GameScreen can
	 * now change.
	 */
	@Override
	public void loadOver() {

		/*
		 *  We have to remove the case who have been used
		 *  like if there is a hitBox at (2,0) then we
		 *  remove the key (2,0) from the Ship's Map
		 */
		List<GameElement> oRadar = human.getRadarElements();
		int wS = 0;
		for ( GameElement ge : oRadar ) {
			if ( ge.isStrategicallyUseful() ) {
				wS++;
			}

			computer.touchedAt( ge.getPosition() );
		}
		computer.setHitShot(wS);
		computer.setMissedShot(oRadar.size() - wS);

		// same for the Human
		oRadar = computer.getRadarElements();
		wS = 0;
		for ( GameElement ge : oRadar ) {
			if ( ge.isStrategicallyUseful() ) {
				wS++;
			}
			human.touchedAt( ge.getPosition() );
		}
		human.setHitShot(wS);
		human.setMissedShot(oRadar.size() - wS);
		
		state = State.PLAYING;	
		notify(LOAD_GAME);
	}

	/**
	 * Returns true if the Ship given in parameter has
	 * a collision with at least one other ship
	 * @param ship the Ship to test
	 * @param pt the coordinates to validate or not
	 * @return if there is collision or not
	 */
	public boolean isPositionAvailable(GameElement ge, Point pos) {
		int num = 0;
		List<Ship> humanFleet = new LinkedList<Ship>(human.getBoardElements());
		int index = humanFleet.indexOf(ge);
		Point old = ge.getPosition();
		ge.setPosition(pos);
		boolean intersect = true;
		Ship s = null;

		while( num < humanFleet.size() && intersect ) {
			s = humanFleet.get(num);
			if ( num != index && s.intersect(ge) ) {
				intersect = false;
				ge.setPosition(old);
			}
			num++;
		}
		return intersect;
	}

	/**
	 * Returns the closest available position when the
	 * rotation render an incorrect position.
	 * @return the closest position available
	 */
	private Point getClosestPosition(GameElement ge) {
		List<Point> positions = new ArrayList<Point>();
		Point initialPosition = new Point(ge.getPosition());
		int maxW = this.getWidth() - ge.getWidth(),
				maxH = this.getHeight() - ge.getHeight();
		Point tmp = null;
		for (int x = 0; x < maxW; x++) {
			for (int y = 0; y < maxH; y++) {
				tmp = new Point(x, y);

				boolean correct = isPositionAvailable(ge, tmp);
				if ( correct ) {
					positions.add(tmp);
				}
			}
		}

		/*
		 * Iterates through the Point list
		 * and find the closest Point available
		 */
		tmp = null;
		for(Point point : positions) {
			if (tmp == null) {
				tmp = point;
			} else {
				if (this.getDistance(initialPosition, tmp) > this.getDistance(initialPosition, point)) {
					tmp = point;
				}
			}
		}

		// Then we return the closest one
		return tmp;
	}

	/**
	 * Returns the distance between
	 * the Point p1 and the Point p2.
	 * @return the distance between 2 Points
	 */
	private double getDistance(Point p1, Point p2) {
		return Math.sqrt(Math.pow((p2.y - p1.y), 2) + Math.pow((p2.x - p1.x), 2));
	}
	/**
	 * This function is called by the BoardController,
	 * that's mean that it is directly the Player's GameElement
	 * we move and the only GameElement moveable are the Ship
	 */
	public void setGameElementPosition(GameElement ge, Point pos) {
		ge.setPosition(pos);
		/*
		 * We check if there is a collision
		 * in case of incorrect rotation for example
		 */
		if ( !isPositionAvailable(ge, pos) ) {
			Point closest = getClosestPosition(ge);
			ge.setPosition(closest);
		}
		notify(BOARD);
	}

	/**
	 * Sets Human's GameElement at randoms places
	 */
	public void setGameElementsPosition() {
		human.setBoardElements();
		notify(BOARD);
	}

	/**
	 * Indicates if the Point at pos
	 * has been played or not
	 * @param pos the Point the check
	 * @return true if we can play this position
	 */
	public boolean isAlreadyPlayed(Point pos) {
		return (human.isAlreadyPlayed(pos));
	}

	/**
	 * Indicates if the Point pos is within
	 * the Game's dimension
	 * @param pos the Point to test
	 * @return true if correct
	 */
	private boolean isValid(Point pos) {
		if ( pos.getX() < 0 ) {
			return false;
		}

		if ( pos.getY() < 0 ) {
			return false;
		}

		if ( pos.getX() >= getWidth() ) {
			return false;
		}

		if ( pos.getY() >= getHeight() ) {
			return false;
		}
		return true;
	}

	/**
	 * Indicates if we can play at the pos position,
	 * this function makes more verifications than
	 * isAlreadyPlayed
	 * @param pos the Point the check
	 * @return true if we can play this position
	 */
	public boolean isPlayable(Point pos) {
		return isValid(pos) && hasBegun() && isPlayerTurn() && !isAlreadyPlayed(pos);
	}

	/**
	 * Checks if one or the two players has lost all
	 * his fleet or if there is still some ammunition available
	 */
	public void checkIfOver() {
		if (getShotsRemaining() <= 0 || 
				(!human.isAlive() || !computer.isAlive())) {
			state = State.OVER;
		}
	}

	/**
	 * Returns the winner
	 */
	@Override
	public IPlayer getWinner() {
		if (!computer.isAlive() ) {
			return human;
		} 
		if (!human.isAlive()){
			return computer;
		}
		return null;
	}

	/**
	 * Changes the GameIModel state to over,
	 * and affects winner as the GameIModel's game winner
	 * @param winner IPlayer winner
	 */
	@Override
	public void setGameOver(IPlayer winner) {
		state = State.OVER;
		if (winner != null) {
			if ( winner.isHuman() ) {
				notify(HUMAN_WIN);
			} else {
				notify(COMPUTER_WIN);
			}
		} else {
			notify(DRAW);
		}
	}

	/**
	 * Sets game's parameters :
	 * game's dimension width and height,
	 * game's difficulty used to set computer's IA,
	 * game's era, used to set Ship's textures and properties...
	 * using argument config
	 */ 
	@Override
	public void setGameConfiguration(IConfiguration config) {

		configuration = new BattleShipConfiguration(config);

		// Set the Human's fleet
		human.setBoardElements( configuration.getGameElements(new LinkedList<Ship>()) );

		// Sets computer's strategy
		IStrategy strategy = StrategyFactory.getInstance().getStrategy(config.getDifficulty());

		computer = new Computer(this, strategy);

		// Set the Computer's fleet
		computer.setBoardElements( configuration.getGameElements(new LinkedList<Ship>()) );

		// Now we can let the Human player set his fleet
		setWarmupScreen();
	}

	/**
	 * Sets the GameIModel to the IConfiguration config
	 * except Human's, Computer's GameElements
	 * (see GameXMLDAO)
	 * @param config the IConfiguration
	 */
	@Override
	public void loadGameConfiguration(IConfiguration config) {
		configuration = new BattleShipConfiguration(config);

		// Sets computer's strategy
		IStrategy strategy = StrategyFactory.getInstance().getStrategy(config.getDifficulty());
		computer = new Computer(this, strategy);
	}

	/**
	 * Removes IConfiguration, and others GameIModel's
	 * attributes as the Computer etc.
	 */
	@Override
	public void reset() {
		human = new Human(user.getUserName(), this);
		computer = null;
		configuration = null;
		state = State.WARMUP;
		humanTurn = true;
	}

	/**
	 * Returns the Game's name
	 * @return a name
	 */
	public String getGameName() {
		return name;
	}

	/**
	 * Returns the number of shot available
	 * @return an integer
	 */
	@Override
	public int getShotsRemaining() {
		int shots = human.getHitShot() + human.getMissedShot();
		return (configuration.getLimit() - shots);
	}
}
