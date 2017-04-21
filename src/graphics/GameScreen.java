package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.Configuration;
import game.Game;
import graphics.listener.BoardController;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class GameScreen extends JFrame implements Observer {

	/**
	 * Current Game
	 */
	private Game game;

	/**
	 * Start Screen
	 */
	private JPanel startScreen;
	
	/**
	 * Configuration Game Screen
	 */
	private JPanel configPartyScreen;
	
	/**
	 * Board Screen (for a game)
	 */
	private JPanel boardScreen;
	
	/**
	 * Radar Screen (for a game)
	 */
	private JPanel radarScreen;

	/**
	 * Title of the window app
	 */
	private final static String TITLE = "Bataille Navale";
	
	/**
	 * Size of the window
	 */
	private final Dimension dimension = 
			new Dimension((Configuration.WIDTH * G_UNIT) *2 + G_UNIT, Configuration.HEIGHT * G_UNIT + G_UNIT);

	/**
	 * Size unit
	 */
	public final static int G_UNIT = 50;

	
	
	
	
	
	/**
	 * Constructor
	 */
	public GameScreen() {
		super(TITLE);

		game = new Game();
		game.addObserver(this);

		BoardController controller = new BoardController(game);
		this.addKeyListener(controller);

		this.startScreen = new StartScreen(game);
		this.configPartyScreen = new ConfigPartyScreen(game, controller);
		this.radarScreen = new RadarScreen(game);
		this.boardScreen = new BoardScreen(game);

		// to use keyListener
		this.setFocusable(true);

		initGameScreen();

		this.initStartScreen();
		//this.initPartyScreen();

		this.initGameScreen2();
	}

	/**
	 * Show the start screen
	 */
	private void initStartScreen() {
		this.removeAllContentScreen();

		this.add(this.startScreen);
		this.validate();
	}

	/**
	 * Show the configuration game screen
	 */
	private void initConfigPartyScreen() {
		this.removeAllContentScreen();

		this.add(this.configPartyScreen);
		this.validate();
	}

	/**
	 * Show the game screen
	 */
	private void initPartyScreen() {
		this.removeAllContentScreen();

		// Layout
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		add(boardScreen);
		add( Box.createRigidArea( new Dimension(G_UNIT, 0) ) );
		add(radarScreen);
		this.validate();
	}

	/**
	 * Hidden all screens
	 */
	private void removeAllContentScreen() {
		this.getContentPane().remove(this.startScreen);

		this.getContentPane().remove(this.configPartyScreen);

		this.getContentPane().remove(this.radarScreen);
		this.getContentPane().remove(this.boardScreen);
	}

	/**
	 * Initializes several attributes of the screen
	 */
	private void initGameScreen() {
		// Size
		this.setPreferredSize(dimension);

		// Resizable
		this.setResizable(false);

		// Color
		this.getContentPane().setBackground(Color.WHITE);
	}

	/**
	 * Initializes several attributes of the screen
	 * Needs to be called at the end of the display
	 */
	private void initGameScreen2() {
		// Default operations
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null); // display window in the middle of the screen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Display the player who must play
	 * @param s
	 */
	private void diplayPlayerTurn(String s) {
		JOptionPane.showMessageDialog(null, "Turn of the " + s, "Turn", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		String input = (String) arg1;
		switch(input) {
		case "RADAR" :
			radarScreen.repaint();
			break;
		case "BOARD" :
			boardScreen.repaint();
			this.configPartyScreen.repaint();
			break;

		case "setStartScreen" :
			this.initStartScreen();
			this.repaint();
			break;
		case "setConfigPartyScreen" :
			this.initConfigPartyScreen();
			this.repaint();
			break;
		case "setPartyScreen" :
			this.initPartyScreen();
			this.repaint();
			break;
			
		case "diplayPlayerTurn" :
			this.diplayPlayerTurn( ((Game) arg0).getCurrentPlayer() );
			break;
		}	
	}
}
