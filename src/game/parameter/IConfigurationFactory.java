/**
 * 
 */
package game.parameter;

import game.GameIModel;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 17, 2017
 */
public class IConfigurationFactory {

	/**
	 * Instance attribute
	 */
	private static IConfigurationFactory INSTANCE = new IConfigurationFactory();
	
	/**
	 * Constructs a IConfigurationFactory
	 * empty constructor
	 */
	private IConfigurationFactory() {	
	}
	
	/**
	 * Returns the Factory instance
	 * @return the INSTANCE
	 */
	public static IConfigurationFactory getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Returns the correct IConfiguration according to
	 * the GameIModel given in parameter
	 * @param game the GameIModel
	 * @return the correct IConfiguration
	 */
	public IConfiguration getConfiguration(GameIModel game) {
		String name = game.getGameName();
		
		switch(name) {
		case "Bataille Navale" :
				return new BattleShipConfiguration();
		default :
				return new BattleShipConfiguration();
		}
	}
}
