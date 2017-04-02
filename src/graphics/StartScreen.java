package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.Configuration;
import game.Game;

public class StartScreen extends JPanel {

	/**
	 * Current Game
	 */
	private Game game;
	
	/**
	 * Background of the app
	 */
	private final Image background = TextureFactory.getInstance().getBattleshipBackground();
	
	/**
	 * Title of the app
	 */
	private final Image title = TextureFactory.getInstance().getBattleshipTitle();

	
	
	
	
	/**
	 * Constructor
	 * @param g : Game
	 */
	public StartScreen(Game g) {
		super();
		game = g;
		
		JButton buttonNewGame = new JButton("New Game");
		buttonNewGame.addActionListener(e -> {
			this.game.setConfigPartyScreen();
		});
		
		this.add( Box.createRigidArea( new Dimension(0, this.title.getHeight(null)+100) ) );
		this.add(buttonNewGame);

		// Size
		this.setPreferredSize(
				new Dimension((Configuration.WIDTH * GameScreen.G_UNIT) *2 + GameScreen.G_UNIT, 
						Configuration.HEIGHT * GameScreen.G_UNIT + GameScreen.G_UNIT));
	}

	
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(this.background, 
				0, 0, 
				(int)(this.background.getWidth(null)/1.5), (int)(this.background.getHeight(null)/2), 
				null);

		g.drawImage(this.title, 
				(this.getWidth()/2) - (this.title.getWidth(null)/4), 0, 
				this.title.getWidth(null)/2, this.title.getHeight(null)/2, 
				null);
	}
}
