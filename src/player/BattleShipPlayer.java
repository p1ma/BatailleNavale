/**
 * 
 */
package player;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import element.GameElement;
import element.HitBox;
import element.MissedBox;
import element.Ship;
import game.GameIModel;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public abstract class BattleShipPlayer implements IPlayer{

	/**
	 * Player's id
	 */
	private String identifiant;
	
	/**
	 * Number of missed shots
	 */
	private int losingShot;
	
	/**
	 * Number of succesful shots
	 */
	private int winningShot;

	/**
	 * Map containing the Ships (BoardScreen)
	 */
	protected Map<Point, Ship> fleet;
	
	/**
	 * Map containing the Box (RadarScreen)
	 */
	protected Map<Point, GameElement> radar;

	/**
	 * Game
	 */
	protected GameIModel game;
	
	/**
	 * 
	 * Constructs a BattleShipPlayer with the given parameter(s)
	 * @param id tHithe IPlayer's name
	 * @param model the GameIModel associate
	 */
	public BattleShipPlayer(String id, GameIModel model) {
		identifiant = id;
		game = model;
		losingShot = 0;
		winningShot = 0;

		fleet = new HashMap<Point, Ship>();
		radar = new HashMap<Point, GameElement>();
	}
	
	/**
	 * Affects to the IPlayer's radar GameElement list
	 * the List list
	 * @param list the List of GameElement to add
	 */
	@Override
	public void setRadarElements(List<GameElement> list) {
		for(GameElement ge : list) {
			radar.put(ge.getPosition(), ge);
		}
	}
	
	/**
	 * Sets the number of succesful shots at shot
	 * @param shot the number of hit shot
	 */
	@Override
	public void setHitShot(int shot) {
		winningShot = shot;
	}
	
	/**
	 * Sets the number of missed shots at shot
	 * @param shot the number of missed shot
	 */
	@Override
	public void setMissedShot(int shot) {
		losingShot = shot;
	}
	
	/**
	 * Returns the number of succesful shots
	 * @return the number of hit shot
	 */
	@Override
	public int getHitShot() {
		return winningShot;
	}

	/**
	 * Returns the number of missed shots
	 * @return the number of missed shot
	 */
	@Override
	public int getMissedShot() {
		return losingShot;
	}

	/**
	 * Returns a Set of Ship, used by the method
	 * getBoardElements().
	 * We used a Set because the Map has multiple keys for one Ship
	 * so with a Set we can add multiple times the same value and it won't
	 * actually add it multiple times in the Set.
	 * @return a Set of Ship
	 */
	private Set<Ship> getSetFleet() {
		Set<Ship> set = new HashSet<Ship>();
		set.addAll(fleet.values());
		return set;
	}
	
	/**
	 * Returns a Set of GameElement, used by the method
	 * getRadarElements().
	 * We used a Set because the Map has multiple keys for one Box
	 * so with a Set we can add multiple times the same value and it won't
	 * actually add it multiple times in the Set.
	 * @return a Set of GameElement
	 */
	private Set<GameElement> getSetRadar() {
		Set<GameElement> set = new HashSet<GameElement>();
		set.addAll(radar.values());
		return set;
	}
	
	/**
	 * Returns the GameElements placed in the BoardScreen
	 * @return a List of Ship
	 */
	@Override
	public List<Ship> getBoardElements() {
		return new LinkedList<Ship>( getSetFleet() );
	}

	/**
	 * Returns the GameElements placed in the RadarScreen
	 * @return a List of GameElement
	 */
	@Override
	public List<GameElement> getRadarElements() {
		return new LinkedList<GameElement>( getSetRadar() );
	}

	/**
	 * Verify if the Map fleet contains a
	 * GameElement at the position p
	 * @param p the Point to check
	 * @return true if there is a GameElement
	 */
	private boolean isTouched(Point p) {
		return (fleet.containsKey(p));
	}

	/**
	 * Returns true if the position at p has been
	 * played
	 * @param p the Point to check
	 * @return true if already played
	 */
	@Override
	public boolean isAlreadyPlayed(Point p) {
		return (radar.containsKey(p));
	}
	
	/**
	 * Returns true if the IPlayer
	 * is alive (it depend of the GameIModel chosen)
	 * see BattleShipPlayer
	 * @return true if alive
	 */
	@Override
	public boolean isAlive() {
		return !( getBoardElements().isEmpty() );
	}
	
	/**
	 * Verify if there is a GameELement at the position p,
	 * if so then it return a HitBox, else a MissedBox
	 * @param p the position to check
	 * @return a Box
	 */
	@Override
	public GameElement touchedAt(Point p) {
		if ( isTouched(p) ) {
			fleet.remove(p);
			winningShot++;
			return new HitBox(p, game);
		} else {
			losingShot++;
			return new MissedBox(p, game);
		}
	}

	/**
	 * Adds to the IPlayer's radar the GameElement box at the position pos
	 * @param pos the position where the GameElement box is suppose to be
	 * @param box the GameElement to add
	 */
	@Override
	public void updateRadar(Point pos, GameElement box) {
		radar.put(pos, box);
	}

	/**
	 * Method used to update the Map containing the IPlayer's GameElements
	 */
	@Override
	public void updateMap() {
		List<Ship> ships = getBoardElements();
		fleet.clear();
		putBoardElements(ships);
	}
	
	/**
	 * Randomly sets the GameElement inside the Game's board
	 */
	@Override
	public void setBoardElements() {
		setBoardElements( getBoardElements() );
	}
	
	/**
	 * Affects to the IPlayer's board GameElement list
	 * the List list
	 * @param list the List of GameElement to add
	 */
	@Override
	public void setBoardElements(List<Ship> list) {
		fleet.clear();
		Random random = new Random();
		int w = game.getWidth(), h = game.getHeight();
		int randX = 0, randY = 0;
		Point p = null;
		boolean rotate = false;
		/*
		 *	Brute force version 
		 */
		int i = 0;
		while(i < list.size()) {
			Ship s = list.get(i);
			
			rotate = random.nextBoolean();
			if (rotate) {
				randY = random.nextInt(w - s.getWidth());
				randX = random.nextInt(h - s.getHeight());
				p = new Point(randX, randY);
				s.setPosition(p);
				s.rotate();
			} else {
				randX = random.nextInt(w - s.getWidth());
				randY = random.nextInt(h - s.getHeight());
				p = new Point(randX, randY);
				s.setPosition(p);
			}
			
			int j = 0;
			boolean correct = true;
			while (j < list.size() && correct) {
				if ( j != i ) {
					Ship second = list.get(j);
					if ( s.intersect(second) ) {
						correct = false;
					}
				}
				j++;
			}
			
			if (correct) {
				i++;
			}
		}
		
		putBoardElements(list);
	}
	
	/**
	 * Puts directly into the data structure the List of
	 * GameElement list
	 */
	public void putBoardElements(List<Ship> list) {
		for ( Ship s : list ) {
			Point pos = s.getPosition();
			for (int i = 0 ; i < s.getWidth() ; i++) {
				for (int j = 0 ; j < s.getHeight(); j++ ) {
					fleet.put(new Point(pos.x + i, pos.y + j), s);
				}
			}
		}
	}

	/**
	 * Sets the IPlayer's name, if there is a IUser
	 * then it is more likely to have IUser's name = IPlayer's name
	 * @param name the IPlayer's name
	 */
	@Override
	public void setName(String name) {
		identifiant = name;
	}
	
	/**
	 * Returns the name of the IPlayer
	 * @return IPlayer's name
	 */
	@Override
	public String getName() {
		return identifiant;
	}
}
