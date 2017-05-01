/**
 * 
 */
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

import game.GameAbstractModel;
import graphics.menu.GameMenuBar;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class GameScreen extends JFrame implements Observer {

	/**
	 * serialVersionUID used to 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * JFrame's WELCOME title (used by the StartScreen)
	 */
	private final static String WELCOME = "Bienvenue";
	
	/**
	 * JFrame's PARAMETERS title (used by the ParametersScreen)
	 */
	private final static String PARAMETERS = "Paramètres";

	/**
	 * Zoom factor
	 */
	private final int g_unit = 38;
	
	/**
	 * Margins for portability reasons
	 */
	private final int W_MARGIN = 10;
	private final int H_MARGIN = 2;

	/**
	 * Model
	 */
	private GameAbstractModel game;

	/**
	 * This JPanel will be used as a welcome screen,
	 * it will let the user chooses to load a Game or the start a new one.
	 */
	private JPanel starterScreen;
	
	/**
	 * When the user had chosen to start a new Game, 
	 * the user will chose the parameters wanted
	 */
	private JPanel parametersScreen;
	
	/**
	 * Right Screen, it will contain the Opponent's elements and
	 * the user's tentatives
	 */
	private JPanel radarScreen;
	
	/**
	 * Left Screen, it will contain the user's elements and
	 * the computer's tentatives
	 */
	private JPanel boardScreen;
	
	/**
	 * Top screen, it will draw the current score, the number of
	 * missed and hit shots and it will also draw (in case) the winner
	 */
	private JPanel scoreScreen;

	/**
	 * 
	 * Constructs a GameScreen with the given parameter(s)
	 * @param gameAbstract the Model and Observable
	 */
	public GameScreen(GameAbstractModel gameAbstract) {
		super();

		game = gameAbstract;
		game.addObserver(this);

		this.setTitle( game.getGameName() );
		setStarterScreen();

		// DEFAULT OPERATIONS
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null); // display window in the middle of the screen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Changes | Loads the starterScreen
	 */
	private void setStarterScreen() {
		removeAll();
		starterScreen = new StartScreen(game);

		initStarterScreen();

		add(starterScreen);
		validate();
	}

	/**
	 * Sets the properties of the starterScreen
	 */
	private void initStarterScreen() {
		// TITLES
		this.setTitle(WELCOME);

		// SIZES
		this.setMinimumSize( starterScreen.getMinimumSize() );
		this.setPreferredSize( starterScreen.getPreferredSize() );
		this.setMaximumSize( starterScreen.getMaximumSize() );

		// RESIZABLE
		setResizable(false);
		starterScreen.setFocusable(false);
		pack();
	}

	/**
	 * Changes | Loads the parametersScreen
	 */
	private void setParametersScreen() {
		// Removes JComponent
		this.removeAll();
		
		this.setJMenuBar(null);

		parametersScreen = new ParametersScreen(game);

		initParametersScreen();

		add(parametersScreen);
		
		setLocationRelativeTo(null); // center frame
		revalidate();
	}

	/**
	 * Sets the properties of the parametersScreen
	 */
	private void initParametersScreen() {
		// TITLES
		this.setTitle(PARAMETERS);

		// SIZES
		this.setResizable(true);
		this.setMinimumSize( parametersScreen.getMinimumSize() );
		this.setPreferredSize( parametersScreen.getPreferredSize() );
		this.setMaximumSize( parametersScreen.getMaximumSize() );

		// RESIZABLE
		this.setResizable(false);

		// LAYOUT
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		pack();
	}

	/**
	 * Sets the properties of the GameScreen, it will load
	 * the radarScreen(right), boardScreen(left) and scoreScreen(top)
	 */
	private void setGameScreens() {
		// Removes parameters screen if exists
		removeAll();
		radarScreen = new RadarScreen(game, 
				g_unit);
		boardScreen = new BoardScreen(game, 
				g_unit);

		initGameScreens();

		scoreScreen = new ScoreScreen(game, 
				getPreferredSize(), 
				g_unit);
		
		add(scoreScreen);
		add(boardScreen);
		add( Box.createRigidArea( new Dimension(g_unit, 0) ) );
		add(radarScreen);
		
		setLocationRelativeTo(null); // center frame
		validate();
	}

	/**
	 * Sets the properties of the boardScreen, radarScreen, scoreScreen
	 */
	private void initGameScreens() {		
		// TITLES
		this.setTitle( game.getGameName() );

		// SETS JMENU
		setJMenuBar(new GameMenuBar(game) );

		// SIZE
		this.setResizable(true);
		Dimension dimension = computeSizes();
		this.setMinimumSize( dimension );
		this.setPreferredSize( dimension );
		this.setMaximumSize( dimension );
		
		// to use keyListener
		if ( boardScreen.isFocusable() ) {
			setFocusable(false);
			boardScreen.setFocusable(true);
		}

		// RESIZABLE
		this.setResizable(false);

		// LAYOUT
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		// COLOR
		this.getContentPane().setBackground(Color.WHITE);
		pack();
	}

	/**
	 * Updates the screen depending of the code(arg1) sent by 
	 * the Model
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		String input = (String) arg1;
		switch(input) {
		case GameAbstractModel.RADAR :
			radarScreen.repaint();
			scoreScreen.repaint();
			break;
		case GameAbstractModel.BOARD :
			boardScreen.repaint();
			scoreScreen.repaint();
			break;
		case GameAbstractModel.WARMUP_OVER :
			showWarmupOverMessage();
			break;
		case GameAbstractModel.WARMUP_PROBLEM :
			showWarmupProblemMessage();
			break;
		case GameAbstractModel.YOUR_TURN :
			showYourTurnMessage();
			break;
		case GameAbstractModel.PARAMETERS :
			setParametersScreen();
			break;
		case GameAbstractModel.WARMUP :
			setGameScreens();
			showWarmupBeginMessage();
			break;
		case GameAbstractModel.HUMAN_WIN :
			showGameOverScreen(GameAbstractModel.HUMAN_WIN);
			break;
		case GameAbstractModel.COMPUTER_WIN :
			showGameOverScreen(GameAbstractModel.COMPUTER_WIN);
			break;
		case GameAbstractModel.DRAW :
			showGameOverScreen(GameAbstractModel.DRAW);
			break;
		case GameAbstractModel.EXIT :
			exit();
		case GameAbstractModel.REPLAY :
			replay();
			break;
		case GameAbstractModel.LOAD_GAME :
			setGameScreens();
			break;
		}	
	}

	/**
	 * Displays a JOptionPane to alert the User that it is his turn
	 */
	private void showYourTurnMessage() {
		this.setFocusable(true);
		JOptionPane.showMessageDialog(this,
				"À vous de jouer !",
				"Changement de tour...",
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Displays a JOptionPane to alert the User that it is time for
	 * him to move (or not) his fleet
	 */
	private void showWarmupBeginMessage() {
		JOptionPane.showMessageDialog(this,
				"Vous pouvez placer maintenant vos bateaux !\n" 
						+"\tCliquez sur la bateau de votre choix,\n" 
						+"puis faites glisser votre souris vers la position souhaitée.\n\n" 
						+"Appuyez sur la touche [R] pour faire tourner le bateau\n"
						+"Appuyez sur la touche [Entrée] pour valider\n",
				"Échauffement !",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Displays a JOptionPane to alert the User that his fleets
	 * has problems
	 */
	private void showWarmupProblemMessage() {
		this.setFocusable(true);
		JOptionPane.showMessageDialog(this,
				"Vos bateaux sont mal placés.",
				"Une erreur est survenue !",
				JOptionPane.ERROR_MESSAGE);
		boardScreen.setFocusable(true);
	}

	/**
	 * Displays a JOptionPane to alert the User that the Game will soon
	 * start
	 */
	private void showWarmupOverMessage() {
		this.setFocusable(true);
		JOptionPane.showMessageDialog(this,
				"Vos bateaux sont bien placés,\n"
						+ "le jeu peut donc commencer.",
						"Échauffement terminé !",
						JOptionPane.PLAIN_MESSAGE);
		boardScreen.setFocusable(true);
	}

	/**
	 * Displays a JOptionPane to alert the User that the Game is over
	 * (depending of the state) it will say the winner or it will advert that
	 * there is no ammunition available
	 */
	private void showGameOverScreen(String state) {
		this.setFocusable(true);
		StringBuilder message = new StringBuilder("");
		String title = "PARTIE TERMINÉE";

		switch(state) {
		case GameAbstractModel.HUMAN_WIN :
			message.append("Vous avez gagné !");
			break;
		case GameAbstractModel.COMPUTER_WIN :
			message.append("Vous avez perdu !");
			break;
		case GameAbstractModel.DRAW :
			message.append("Vous n'avez plus de munition !");
			break;
		}

		message.append("\n");
		message.append("Voulez-vous rejouer ?");

		Object[] options = {"Rejouer", "Quitter"};

		// Displays Pop-up
		int replayOrNot = JOptionPane.showOptionDialog(null,
				message.toString(),
				title,
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]
				);

		// we checks the user's entry
		if ( replayOrNot != JOptionPane.CLOSED_OPTION ) {
			boolean replay;
			if ( replayOrNot == JOptionPane.YES_OPTION ) {
				replay = true;
			} else {
				replay = false;
			}

			// Asks model 
			game.setExitOrReplay( replay );
		}
	}

	/**
	 * Displays a JOptionPane to alert the User that the window will close.
	 */
	private void exit() {
		JOptionPane.showMessageDialog(this,
				"À bientôt !",
				"Fermeture",
				JOptionPane.INFORMATION_MESSAGE);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		System.exit(1);
	}

	/**
	 * Replays
	 */
	private void replay() {	
		/* we remove BoardScreen and Radar
		then ask the ParametersScreen to be seen.
		 */
		setParametersScreen();
	}

	/**
	 * Computes BoardScreen's and RadarScreen's and ScoreScreen's sizes
	 */
	private Dimension computeSizes() {
		Dimension dBoard = boardScreen.getPreferredSize();
		Dimension dRadar = radarScreen.getPreferredSize();
		Dimension result = new Dimension(0,0);
		result.setSize((dBoard.getWidth() + dRadar.getWidth() ) + g_unit + W_MARGIN,
				dBoard.getHeight() + 2 * g_unit + getJMenuBar().getHeight() + H_MARGIN);

		return result;
	}

	@Override
	public void removeAll() {
		if (starterScreen != null) {
			remove(starterScreen);
		}
		
		if (parametersScreen != null) {
			remove(parametersScreen);
		}
		
		if (boardScreen != null) {
			remove(boardScreen);
		}
		
		if (radarScreen != null) {
			remove(radarScreen);
		}
		
		if (scoreScreen != null) {
			remove(scoreScreen);
		}
		validate();
	}
}
