/**
 * 
 */
package graphics.listener;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import game.GameIModel;
import game.parameter.Era;
import game.parameter.IConfiguration;
import game.parameter.IConfigurationFactory;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 29, 2017
 */
public class ParametersController implements ActionListener {

	/**
	 * The Game
	 */
	private final GameIModel game;
	
	/**
	 * Contains all the Dimension availables 
	 * and loaded by the DAO
	 */
	private JComboBox<String> dimensions;
	
	/**
	 * Contains all the levels availables 
	 * and loaded by the DAO
	 */
	private JComboBox<String> levels;
	
	/**
	 * Contains all the Eras availables 
	 * and loaded by the DAO
	 */
	private JComboBox<String> eras;
	
	/**
	 * Contains all the ammunitions availables 
	 * and loaded by the DAO
	 */
	private JComboBox<String> ammos;
	
	/**
	 * Contains all the Dimension availables 
	 * used to make a correspondance between the String value
	 * in the attribute dimensions and the real value contained
	 * in this attribute
	 */
	private Dimension[] dimensionsArray;
	
	/**
	 * Contains all the Eras availables 
	 * used to make a correspondance between the String value
	 * in the attribute eras and the real value contained
	 * in this attribute
	 */
	private Era[] erasArray;
	
	/**
	 * Contains all the levels availables 
	 * used to make a correspondance between the String value
	 * in the attribute levels and the real value contained
	 * in this attribute
	 */
	private String[] levelsArray;
	
	/**
	 * Contains all the ammunitions availables 
	 * used to make a correspondance between the String value
	 * in the attribute ammos and the real value contained
	 * in this attribute
	 */
	private int[] ammosArray;
	
	/**
	 * 
	 * Constructs a ParametersController with the given parameter(s)
	 * @param g the Game
	 * @param dim the String JComboBox of Dimension
	 * @param lvl the String JComboBox of Level
	 * @param e the String JComboBox of Era
	 * @param amm the String JComboBox of ammunition
	 * @param dimensionsA the Dimensions array
	 * @param erasA the Eras array
	 * @param levelsA the level array
	 * @param ammosA the ammunition array
	 */
	public ParametersController(final GameIModel g, 
			JComboBox<String> dim,
			JComboBox<String> lvl,
			JComboBox<String> e,
			JComboBox<String> amm,
			Dimension[] dimensionsA,
			Era[] erasA,
			String[] levelsA,
			int[] ammosA) {
		game = g;
		dimensions = dim;
		levels = lvl;
		eras = e;
		ammos = amm;
		dimensionsArray = dimensionsA;
		erasArray = erasA;
		levelsArray = levelsA;
		ammosArray= ammosA;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg) {
		game.reset();
		
		/*
		 * Gets all the selected indexes
		 */
		int selectedIndexDimension = dimensions.getSelectedIndex();
		int selectedIndexEra = eras.getSelectedIndex();
		int selectedIndexLevel = levels.getSelectedIndex();
		int selectedIndexAmmo = ammos.getSelectedIndex();

		IConfiguration config = IConfigurationFactory.getInstance().getConfiguration(game);
		
		/*
		 *	Sets the config 
		 */
		config.setDifficulty(levelsArray[selectedIndexLevel]);
		config.setLimit(ammosArray[selectedIndexAmmo]);
		config.setEra(erasArray[selectedIndexEra]);
		config.setDimension(dimensionsArray[selectedIndexDimension]);
		
		/*
		 * Affects the configuration to the Game
		 */
		game.setGameConfiguration ( config );
		
	}
}
