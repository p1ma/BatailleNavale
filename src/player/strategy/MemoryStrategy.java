/**
 * 
 */
package player.strategy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import element.GameElement;
import game.GameIModel;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 8, 2017
 */
public class MemoryStrategy implements IStrategy {

	/**
	 * Map which keeps in memory all the useful
	 * shots previously done
	 */
	private Set<GameElement> memory;
	
	/**
	 * Random
	 */
	private Random random;

	/**
	 * Constructs a MemoryStrategy
	 */
	public MemoryStrategy() {
		memory = new HashSet<GameElement>();
		random = new Random();
	}
	
	/**
	 * Executes a MemoryStrategy (DIFFICILE level)
	 * @param radar the Map where the IStrategy will search its infos
	 * @param game the GameIModel
	 * @return a best Point available
	 */
	@Override
	public Point execute(Map<Point, GameElement> radar, GameIModel game) {
		suceededShot(radar);
		/*
		 * If there is now information, we 
		 * execute a Random shot
		 */
		if (memory.size() == 0) {
			int w = game.getWidth();
			int h = game.getHeight();

			Point pos = null;
			boolean exist = true;

			while (exist) {
				pos = new Point(random.nextInt(w), 
						random.nextInt(h));
				if ( !radar.containsKey(pos) ) {
					exist = false;
				}
			}
			return pos;
		} else {
			// We know that there is potentially some useful GE here
			Point pos = null;
			Point tmp = null;
			List<Point> possibilities = new ArrayList<Point>();
			for( GameElement ge : memory ) {
				pos = ge.getPosition();

				/*
				 * We know that pos is the location of a Ship
				 * so we know try is the pos.x + 1 is possible
				 * pos.x - 1 etc...
				 * We return the first one available
				 */
				int width = (int)pos.getX() + 1;
				if( width < game.getWidth()) {
					tmp = new Point(width,
							(int)pos.getY());
					if ( !radar.containsKey(tmp) ) {
						possibilities.add(tmp);
					}
				}

				width = (int)pos.getX() - 1;
				if( width >= 0 ) {
					tmp = new Point(width,
							(int)pos.getY());
					if ( !radar.containsKey(tmp) ) {
						possibilities.add(tmp);
					}
				}

				int height = (int)pos.getY() + 1;
				if( height < game.getHeight()) {
					tmp = new Point((int)pos.getX(),
							height);
					if ( !radar.containsKey(tmp) ) {
						possibilities.add(tmp);
					}
				}

				height = (int)pos.getY() - 1;
				if( height >= 0) {
					tmp = new Point((int)pos.getX(),
							height);
					if ( !radar.containsKey(tmp) ) {
						possibilities.add(tmp);
					}
				}
			}

			// if none are available then random
			if (possibilities.size() == 0) {
				memory.clear();
				int w = game.getWidth();
				int h = game.getHeight();

				pos = null;
				boolean exist = true;

				while (exist) {
					pos = new Point(random.nextInt(w), 
							random.nextInt(h));
					if ( !radar.containsKey(pos) ) {
						exist = false;
					}
				}
				return pos;
			} else {
				return possibilities.get( random.nextInt(possibilities.size()));
			}
		}
	}

	/**
	 * Fills the memory Map with all the useful elements
	 * present in the Map radar
	 * @param radar a Map
	 */
	private void suceededShot(Map<Point, GameElement> radar) {
		for(Map.Entry<Point, GameElement> entry: radar.entrySet()) {
			GameElement element = entry.getValue();
			// If the GameElement is usefull to the IA then
			if ( element.isStrategicallyUseful() ) {
				memory.add(element);
			}
		}
	}
}
