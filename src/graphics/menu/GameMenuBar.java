/**
 * 
 */
package graphics.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import game.GameIModel;
import graphics.listener.LoadController;
import graphics.listener.StartController;
import graphics.menu.listener.LevelMenuListener;
import storage.XMLDAOFactory;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 19, 2017
 */
public class GameMenuBar extends JMenuBar {

	/**
	 * serialVersionUID used to 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Height of the JMenuBar
	 */
	private final static int HEIGHT = 25;
	
	/**
	 * JMenu file, contains exit and load and newG
	 */
	private JMenu file;
	
	/**
	 * JMenuItem exit, used to quit the Game
	 */
	private JMenuItem exit;
	
	/**
	 * JMenuItem load, used to load a Game
	 */
	private JMenuItem load;
	
	/**
	 * JMenuItem newG, used to renew a Game
	 */
	private JMenuItem newG;
	
	/**
	 * JMenu options, contains difficulties and 
	 * position
	 */
	private JMenu options;
	
	/**
	 * JMenu difficulties, will contained
	 * all the difficulties availables
	 */
	private JMenu difficulties;
	
	/**
	 * JMenu positions, used to changed the
	 * GameElements positions 
	 */
	private JMenu positions;
	
	/**
	 * JMenuItem used to moved all the GameElements
	 * to a randoms positions
	 */
	private JMenuItem random;
	
	/**
	 * JMenu help, will contains helps to the user
	 */
	private JMenu help;
	
	/**
	 * GameIModel 
	 */
	private final GameIModel model;
	
	/**
	 * 
	 * Constructs a GameMenuBar with the given parameter(s)
	 * @param game the GameIModel 
	 */
	public GameMenuBar(final GameIModel game) {
		super();
		model = game;
		
		// Instanciates exit Menu
		file = new JMenu("Fichier");
		file.setVisible(true);
		
		// subs jmenu items
		load = new JMenuItem("Charger une partie");
		exit = new JMenuItem("Quitter");
		newG = new JMenuItem("Nouvelle partie");
		
		// Adds newG's listener
		newG.addActionListener(new StartController(model, model.getUser()));

		// Adds exit's listener
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.exit();
			}
			
		});
		
		load.addActionListener(new LoadController(model, model.getUser()));
		
		file.add(newG);
		file.add(load);
		file.add(exit);
		
		// Instanciates options Menu
		options = new JMenu("Options");
		options.setVisible(true);
		
		// subs JMenu items
		difficulties = new JMenu("Difficultés");
		
		// sub sub JMenu items
		
		// DAO calls
		String[] levels = XMLDAOFactory.getInstance().getConfigDAO().getAllLevels();
		
		// create sub-difficulties 
		JMenuItem level = null;
		
		// to regroup all the JMenuItem level
		ButtonGroup bg = new ButtonGroup();
		String difficulty = game.getDifficulty();
		for(int i = 0 ; i < levels.length ; i++) {
			level = new JRadioButtonMenuItem(levels[i]);
			level.addActionListener(new LevelMenuListener(game, levels[i]));
			difficulties.add(level);
			bg.add(level);
			
			// we select the correct level
			if (difficulty.equals(levels[i])) {
				level.setSelected(true);
			}
		}
		
		// creates Listener on difficulties
		options.add(difficulties);
		
		positions = new JMenu("Placement");
		random = new JMenuItem("Aléatoire");
		positions.add(random);
		
		random.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ( !model.hasBegun() ) {
					model.setGameElementsPosition();
				}
			}
			
		});
		options.add(positions);
		
		// Instanciates help Menu
		help = new JMenu("Aide");
		help.setVisible(true);

		// Adds JMenu
		add(file);
		add(options);
		add(help);
		
		this.setVisible(true);
	}

	/**
	 * Returns the JMenuBar's height
	 */
	@Override
	public int getHeight() {
		return HEIGHT;
	}
	
	
}
