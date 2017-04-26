/**
 * 
 */
package graphics.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.GameIModel;
import game.parameter.IConfiguration;
import player.Computer;
import player.strategy.IStrategy;
import player.strategy.StrategyFactory;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 20, 2017
 */
public class LevelMenuListener implements ActionListener {

	/**
	 * the GameIModel
	 */
	private final GameIModel model;
	
	/**
	 * the level, so the String representation,
	 * of the JMenuItem attached to this Listener
	 */
	private final String level;	
	
	/**
	 * 
	 * Constructs a LevelMenuListener with the given parameter(s)
	 * @param game the GameIModel
	 * @param lvl the level
	 */
	public LevelMenuListener(final GameIModel game, final String lvl) {
		model = game;
		level = lvl;
	}
		
	/**
	 * If clicked then the model will change its
	 * difficulty to the value linked to the attribute level
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// updates config
		IConfiguration config = model.getConfig();
		config.setDifficulty(level);
		
		// gets Computer
		Computer computer = model.getComputer();
		
		// get Strategy corresponding to the selected level
		IStrategy strat = StrategyFactory.getInstance().getStrategy(level);
		
		// updates all
		computer.setStrategy(strat);
	}
}
