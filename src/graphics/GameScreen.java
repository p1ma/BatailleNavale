/**
 * 
 */
package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Configuration;
import game.Game;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class GameScreen extends JFrame implements Observer {

	private Game game;

	private JPanel radarScreen;
	private JPanel boardScreen;

	private final static String TITLE = "Bataille Navale";
	// pour la largeur : (nbCasesLargeur * tailleD'uneCase) *2 + colonneDeSeparation
	// 					 le *2 est la pour dire qu'il y a 2 grilles différentes
	private final Dimension dimension = 
			new Dimension((Configuration.WIDTH * G_UNIT) *2 + G_UNIT, Configuration.HEIGHT * G_UNIT + G_UNIT);

	private final static int G_UNIT = 50;

	// TESTS
	private int width, height;

	public GameScreen() {
		super(TITLE);
		game = new Game();
		game.addObserver(this);

		radarScreen = new RadarScreen(game, G_UNIT);
		boardScreen = new BoardScreen(game, G_UNIT);

		initGameScreen();

		add(boardScreen);
		add( Box.createRigidArea( new Dimension(G_UNIT, 0) ) );
		add(radarScreen);

		this.initGameScreen2();
	}

	private void initGameScreen() {
		// SIZE
		this.setPreferredSize(dimension);

		// RESIZABLE
		this.setResizable(false);

		// LAYOUT
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
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
			break;
		}	
	}
}
