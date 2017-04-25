/**
 * 
 */
package player.strategy;

import storage.XMLDAOFactory;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 7, 2017
 */
public class StrategyFactory {

	/**
	 * INSTANCE attribute
	 */
	private final static StrategyFactory INSTANCE = new StrategyFactory();
	
	/**
	 * String[] containing all the GameIModel levels,
	 * will be filled with a DAO call
	 */
	private String[] levels;
	
	/**
	 * 
	 * Constructs a StrategyFactory
	 */
	private StrategyFactory() {
		levels = XMLDAOFactory.getInstance().getConfigDAO().getAllLevels();
	}
	
	/**
	 * Returns the INSTANCE
	 * @return a StrategyFactory instance
	 */
	public static StrategyFactory getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Returns the correct Strategy according to the
	 * level given in parameter and the levels[] in attribute
	 * @param level the wished level
	 * @return the IStrategy according to level
	 */
	public IStrategy getStrategy (String level) {
		if (level.equals(levels[0])) {
			return new RandomStrategy();
		} else {
			if (level.equals(levels[1])) {
				return new CrossStrategy();
			} else {
				return new MemoryStrategy();
			}
		}
	}
}
