package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import game.GameIModel;
import game.parameter.Era;
import graphics.listener.ParametersController;
import storage.XMLDAOFactory;
import storage.config.IConfigDAO;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 29, 2017
 */
public class ParametersScreen extends JPanel {
	
	/**
	 * serialVersionUID used to 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The ParameterScreen dimensions
	 */
	private final Dimension dimension = new Dimension(300,400);
	
	/**
	 * The Game
	 */
	private final GameIModel game;
	
	/**
	 * A JComboBox[] used to contain all the Game's possible
	 * parameters
	 */
	private JComboBox<String> params[];
	
	/**
	 * Confirm button
	 */
	private JButton confirm;
	
	/**
	 * Controller attached to this Screen
	 */
	private ParametersController controller;
	
	/**
	 * background color
	 */
	private final Color color = Color.WHITE;
	
	/**
	 * 
	 * Constructs a ParametersScreen with the given parameter(s)
	 * @param g the Game
	 */
	public ParametersScreen(final GameIModel g) {
		super();
		game = g;
		
		// DAO calls
		IConfigDAO DAO = XMLDAOFactory.getInstance().getConfigDAO();
		
		int nbParams = DAO.getNbParams(game);
		params = new JComboBox[nbParams];
		
		// Sets layout
		setLayout(new GridLayout(nbParams + 1, 1));
		
		// DAO calls
		Dimension[] dimensionsArray = DAO.getAllDimensions();
		Era[] erasArray = DAO.getAllEras();
		String[] levelsArray = DAO.getAllLevels();
		int[] ammunitionsArray = DAO.getAllAmmunitons();
		
		// Adds components
		
		// adds dimensions' items
		params[0] = new JComboBox<String>();
		for(int i = 0 ; i < dimensionsArray.length ; i++) {
			params[0].addItem("[ " + dimensionsArray[i].width + " x " + dimensionsArray[i].height + " ]");
		}
		
		// adds levels' items
		params[1] = new JComboBox<String>();
		for(int i = 0 ; i < levelsArray.length ; i++) {
			params[1].addItem(levelsArray[i]);
		}
		
		// adds eras' items
		params[2] = new JComboBox<String>();
		for(int i = 0 ; i < erasArray.length ; i++) {
			params[2].addItem("Epoque " + erasArray[i]);
		}	
		
		// adds ammunitions' items
		params[3] = new JComboBox<String>();
		for(int i = 0 ; i < ammunitionsArray.length ; i++) {
			params[3].addItem(" " + ammunitionsArray[i] + "% du nombre de case total");
		}

		// Adds button
		confirm = new JButton("Confirmer");
		
		// Adds controller
		controller = new ParametersController(game, 
				params[0], 
				params[1], 
				params[2],
				params[3],
				dimensionsArray,
				erasArray,
				levelsArray,
				ammunitionsArray);
		confirm.addActionListener(controller);
		
		Font current = confirm.getFont();
		confirm.setFont( current.deriveFont(16.f) );
		
		// Set background
		setBackground(color);
		confirm.setBackground(color);
		for (int i = 0 ; i < nbParams ; i++) {
			params[i].setSelectedIndex(0);
			params[i].setBackground(color);
		}

		// Adds elements to JPanel
		for (int i = 0 ; i < nbParams ; i++) {
			add(params[i]);
		}
		add(confirm);
		
		// Sets sizes
		setMinimumSize( getMinimumSize() );
		setPreferredSize( getPreferredSize() );
		setMaximumSize( getMaximumSize() );
	}

	@Override
	public void removeAll() {
		confirm.removeActionListener( controller );
		controller = null;
	}
	
	@Override
	public Dimension getMaximumSize() {
		return dimension; 
	}

	@Override
	public Dimension getMinimumSize() {
		return dimension; 
	}

	@Override
	public Dimension getPreferredSize() {
		return dimension; 
	}

}
