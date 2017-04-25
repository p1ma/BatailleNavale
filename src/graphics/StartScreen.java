/**
 * 
 */
package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.GameIModel;
import graphics.listener.LoadController;
import graphics.listener.StartController;
import storage.XMLDAOFactory;
import storage.user.IUserDAO;
import user.IUser;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 29, 2017
 */
public class StartScreen extends JPanel{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Image to display as a background
	 */
	private Image background;
	
	/**
	 * Image to display as a title (different for every Game)
	 */
	private Image title;
	
	/**
	 * Dimension of the View
	 */
	private final Dimension dimension = new Dimension(800, 600);
	
	/**
	 * Point where all the components of this View
	 * refer to
	 */
	private final Point starterAxis = new Point(0, 0);
	
	/**
	 * Controllers assigned to this View
	 */
	private StartController startController;
	private LoadController loadController;
	
	/**
	 * Used to start a new Game
	 */
	private JButton start;
	
	/**
	 * Used to load a Game
	 */
	private JButton load;
	
	/**
	 * JLabel used to say "Hello"
	 */
	private JLabel hello;
	
	/**
	 * JComboBox containing users
	 */
	private JComboBox<IUser> users;
	
	/**
	 * 
	 * Constructs a StartScreen with the given parameter(s)
	 * @param game the Game to display
	 */
	public StartScreen(final GameIModel game) {
		super();	
		
		// Sets layout to null
		setLayout(null);
				
		// Initializes JLabel 
		hello = new JLabel("Bonjour");
		String fontName = hello.getFont().getFontName();
		Font current = new Font(fontName, Font.BOLD, 16);
		hello.setForeground(Color.WHITE);
		hello.setFont( current );
		int width = 75;
		int height = 50;
		int posX = (int) (dimension.getWidth() * 0.1) + (int)starterAxis.getX(),
				posY = (int) (dimension.getHeight() * 0.6)+ (int)starterAxis.getY();
		hello.setBounds(
				posX,
				posY,
				width,
				height);
		
		// Initializes JComboBox, needs 1 IUserDAO call
		IUserDAO userDAO = XMLDAOFactory.getInstance().getUserDAO();
		users = new JComboBox<IUser>(userDAO.getAllUsers());
		posX += hello.getWidth();
		width = 10 + (int)users.getPreferredSize().getWidth();
		users.setBounds(
				posX,
				posY,
				width,
				height);
		
		// Initializes JButton start and sets properties
		start = new JButton("Jouer");
		setComponentProperties(start);
		width = 75;
		height = 50;
		posX = (int) (dimension.getWidth() * 0.1) + (int)starterAxis.getX(); 
		posY = (int) (dimension.getHeight() * 0.7)+ (int)starterAxis.getY();
		start.setBounds(
				posX,
				posY, 
				width,
				height);
		
		// Initializes JButton load and sets properties
		load = new JButton("Charger");
		setComponentProperties(load);
		width = 100;
		posX += start.getWidth();
		load.setBounds(
				posX,
				posY, 
				width,
				height);
		
		// Adds Controllers
		startController = new StartController(game, users);
		start.addActionListener( startController );
		
		loadController = new LoadController(game, users);
		load.addActionListener(loadController);

		
		// Adds Components
		add(hello);
		add(users);
		add(start);
		add(load);
		
		// DAO calls for starter image and title
		XMLDAOFactory.getInstance().getImageDAO().setStartImage(game);
		XMLDAOFactory.getInstance().getImageDAO().setTitleImage(game);
		
		// Sets Image from Game
		title = game.getGameTitleImage();
		background = game.getGameStartImage();
		
		// Sets sizes
		setMinimumSize( getMinimumSize() );
		setPreferredSize( getPreferredSize() );
		setMaximumSize( getMaximumSize() );
	}

	/**
	 * Sets for the JComponent compo somes fixed properties
	 * @param compo a JComponent
	 */
	private void setComponentProperties(JComponent compo) {
		Font current = compo.getFont();
		compo.setFont( current.deriveFont(16.f) );
		compo.setBackground(Color.WHITE);
	}
	
	/**
	 * Main draw function
	 */
	@Override
	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
		
		// Draws background image
		g.drawImage(background, 
				(int) starterAxis.getX(), 
				(int) starterAxis.getY(), 
				(int) dimension.getWidth(),
				(int) dimension.getHeight(),
				this);
		
		// Draws title's image
		g.drawImage(this.title, 
				(int) (starterAxis.getX() + (dimension.getWidth() / 3)), 
				(int) starterAxis.getY(),
				(int) (dimension.getWidth() / 3),
				(int) (dimension.getHeight() / 4),
				null);
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

	@Override
	public void removeAll() {
		start.removeActionListener(startController);
		startController = null;
	}
	
}
