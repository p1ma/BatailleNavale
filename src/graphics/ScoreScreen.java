/**
 * 
 */
package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JPanel;

import game.GameIModel;
import player.IPlayer;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 6, 2017
 */
public class ScoreScreen extends JPanel {

	/**
	 * serialVersionUID used to 
	 */
	private static final long serialVersionUID = 1L;

	/***
	 * The Game
	 */
	private GameIModel game;
	
	/**
	 * The current Game's dimension
	 */
	private final Dimension dimension;
	
	/**
	 * Used as a space
	 */
	private Dimension dLabel;
	
	/**
	 * The Point where all the draws are suppose to start
	 */
	private Point starterAxis;

	/**
	 * 3 elements will be used here,
	 * 1rst the shot missed
	 * 2nd the successful shot
	 */ 
	private Image missedImage;
	private Image hitImage;

	/**
	 * Font
	 */
	private Font font;

	/**
	 * Color
	 */
	private final Color color = Color.WHITE;

	/**
	 * 
	 * Constructs a ScoreScreen with the given parameter(s)
	 * @param model the Game
	 * @param dim the Game's dimension
	 * @param unit the zoom factor
	 */
	public ScoreScreen(GameIModel model, final Dimension dim, final int unit) {
		super();
		game = model;
		dimension = new Dimension((int)dim.getWidth(), unit);
		starterAxis = new Point(dimension.width / 4, 0);
		font = new Font("Arial", Font.BOLD , unit / 3);
		dLabel = new Dimension(unit, unit);

		/**
		 * Images loading...
		 */
		missedImage = (game.getGameMissedImage());
		hitImage = (game.getGameTouchedImage());

		// Background color
		this.setBackground(color);

		// SIZE
		this.setPreferredSize( getPreferredSize() );
		this.setMinimumSize( getMinimumSize() );
		this.setMaximumSize( getMaximumSize() );
	}

	/**
	 * Main draw function
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g.create();

		g2d.setFont(font);

		if (game.isGameOver()) {
			drawWinner(g2d);
		} else {
			drawScore(g2d);
		}

		g2d.dispose();
	}

	/**
	 * Draws the name of the winner in g2d
	 * @param g2d the Graphics2D
	 */
	private void drawWinner(Graphics g2d) {
		String message = "";
		IPlayer winner = game.getWinner();
		if ( winner != null ) {
			if ( winner.isHuman() ) {
				message = "Félicitation " + winner.getName() + ", vous avez gagné !";
			} else {
				message = "Vous avez perdu !";
			}
		} else {
			message = "Plus de munition disponible !";
		}
		
		g2d.drawString(message,
				starterAxis.x,
				dLabel.height - font.getSize());
	}

	/**
	 * Draws the current score in g2d
	 * @param g2d the Graphics2D
	 */
	private void drawScore(Graphics g2d) {
		g2d.drawImage(hitImage, 
				starterAxis.x, 
				starterAxis.y, 
				dLabel.width, 
				dLabel.height,
				this);

		int x = starterAxis.x + dLabel.width;
		int y = dLabel.height - font.getSize();
		String sentence = "Tirs réussis : " + game.getHitShot();
		g2d.drawString(sentence,
				x, 
				y);

		x += ((sentence.length() * font.getSize()) / 2);

		g2d.drawImage(missedImage, 
				x, 
				starterAxis.y, 
				dLabel.width, 
				dLabel.height,
				this);

		x += dLabel.width;
		String sentence2 = "Tirs ratés : " + game.getMissedShot();
		g2d.drawString(sentence2,
				x, 
				y);

		x += ((sentence2.length() * font.getSize()) / 2) + dLabel.width;

		String sentence3 = "Tirs restants : " + game.getShotsRemaining();
		g2d.drawString(sentence3,
				x, 
				y);
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
