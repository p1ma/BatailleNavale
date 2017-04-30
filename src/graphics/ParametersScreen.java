package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
	private final Dimension dimension = new Dimension(500,600);
	
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
	 * A JLabel[] used to explain the meaning of the parameters
	 */
	private JLabel descriptions[];
	
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
	 * Main JPanel, contains the parameters plus the confirm button
	 */
	private JPanel main;
	
	/**
	 * Parameters JPanel, contains all the parameters + their descriptions
	 * will be add to the JPanel main 
	 */
	private JPanel parametersDescriptions;
	
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
		descriptions = new JLabel[nbParams];
		
		// Sets layout
		parametersDescriptions = new JPanel(new GridLayout(nbParams + 1, 2));
		
		// Sets the size to 80% of the all Dimension available
		Dimension compute = new Dimension((int)(dimension.getWidth() * 0.9),
				(int)(dimension.getHeight() * 0.75));
		
		parametersDescriptions.setMinimumSize(compute);
		parametersDescriptions.setPreferredSize(compute);
		parametersDescriptions.setMaximumSize(compute);
		
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
		// adds dimensions' description
		descriptions[0] = new JLabel("Taille de la carte : ");
		
		
		// adds levels' items
		params[1] = new JComboBox<String>();
		for(int i = 0 ; i < levelsArray.length ; i++) {
			params[1].addItem(levelsArray[i]);
		}
		// adds levels' description
		descriptions[1] = new JLabel("Difficulté : ");
		
		// adds eras' items
		params[2] = new JComboBox<String>();
		for(int i = 0 ; i < erasArray.length ; i++) {
			params[2].addItem(erasArray[i] + "");
		}	
		// adds eras's description
		descriptions[2] = new JLabel("Époque souhaitée : ");
		
		// adds ammunitions' items
		params[3] = new JComboBox<String>();
		for(int i = 0 ; i < ammunitionsArray.length ; i++) {
			params[3].addItem(" " + ammunitionsArray[i] + "% du nombre de case total");
		}
		// adds ammunitions' description
		descriptions[3] = new JLabel("Nombre de munition maximum : ");
		
		// Adds button
		confirm = new JButton("Confirmer");
		
		// Sets the button size
		compute = new Dimension((int)(dimension.getWidth() * 0.9),
				(int)(dimension.getHeight() * 0.15));
		confirm.setMinimumSize( compute );
		confirm.setPreferredSize( compute );
		confirm.setMaximumSize( compute );
		
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
			parametersDescriptions.add(descriptions[i]);
			parametersDescriptions.add(params[i]);
		}
		
		// creates the main JPanel
		main = new JPanel();
		
		// sets size according the this.getXSize()
		compute = new Dimension((int)(dimension.getWidth() * 0.95),
				(int)(dimension.getHeight() * 0.95));
		
		main.setMinimumSize( compute );
		main.setPreferredSize( compute );
		main.setMaximumSize( compute );
		
		main.add(parametersDescriptions, JPanel.TOP_ALIGNMENT);
		main.add(confirm, JPanel.BOTTOM_ALIGNMENT);
		
		// Adds the JPanel to the this
		add(main);
		
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
