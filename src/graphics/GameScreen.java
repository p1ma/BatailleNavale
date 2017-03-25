/**
 * 
 */
package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JFrame;
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

	private Game game;

	private JPanel startScreen;
	private JPanel configPartyScreen;
	private JPanel radarScreen;
	private JPanel boardScreen;

	private final static String TITLE = "Bataille Navale";
	// pour la largeur : (nbCasesLargeur * tailleD'uneCase) *2 + colonneDeSeparation
	// 					 le *2 est la pour dire qu'il y a 2 grilles différentes
	private final Dimension dimension = 
			new Dimension((Configuration.WIDTH * G_UNIT) *2 + G_UNIT, Configuration.HEIGHT * G_UNIT + G_UNIT);

	public final static int G_UNIT = 50;

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

	private void initStartScreen() {
		this.removeAllContentScreen();

		this.add(this.startScreen);
		this.validate();
	}

	private void initConfigPartyScreen() {
		this.removeAllContentScreen();

		this.add(this.configPartyScreen);
		this.validate();
	}

	private void initPartyScreen() {
		this.removeAllContentScreen();

		// LAYOUT
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		add(boardScreen);
		add( Box.createRigidArea( new Dimension(G_UNIT, 0) ) );
		add(radarScreen);
		this.validate();
	}

	private void removeAllContentScreen() {
		this.getContentPane().remove(this.startScreen);

		this.getContentPane().remove(this.configPartyScreen);

		this.getContentPane().remove(this.radarScreen);
		this.getContentPane().remove(this.boardScreen);
	}

	private void initGameScreen() {
		// SIZE
		this.setPreferredSize(dimension);

		// RESIZABLE
		this.setResizable(false);

		// COLOR
		this.getContentPane().setBackground(Color.WHITE);
	}

	private void initGameScreen2() {
		// DEFAULT OPERATIONS
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null); // display window in the middle of the screen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		}	
	}
}
