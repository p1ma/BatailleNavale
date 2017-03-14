/**
 * 
 */
package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
	
	private Image background;
	
	private final static String TITLE = "Bataille Navale";
	private final Dimension dimension = new Dimension(1024,768);
	
	private final static int G_UNIT = 50;
	
	// TESTS
	private int width, height;
	
	public GameScreen() {
		super(TITLE);
		game = new Game();
		game.addObserver(this);
		
		// TESTS
		width = game.getWidth();
		height = game.getHeight();
		// END TESTS
		
		radarScreen = new RadarScreen(game, G_UNIT);
		boardScreen = new BoardScreen(game, G_UNIT);
		
		initGameScreen();
		
		add(boardScreen, JPanel.LEFT_ALIGNMENT);
		
		add(radarScreen, JPanel.RIGHT_ALIGNMENT);
	}
	
	private void initGameScreen() {
		// SIZE
		this.setMinimumSize(dimension);
		this.setMaximumSize(dimension);
		this.setPreferredSize(dimension);
		
		// RESIZABLE
		this.setResizable(false);
		
		// LAYOUT
		this.setLayout(new GridLayout(1, 2, 10, 5));
		
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
