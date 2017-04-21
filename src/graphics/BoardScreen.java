package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import element.Drawable;
import element.Ship;
import game.Game;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class BoardScreen extends JPanel {

	/**
	 * Current Game
	 */
	private Game game; 
	
	/**
	 * Background of the app
	 */
	private final Image background;

	
	
	
	
	/**
	 * Constructor
	 * @param g : Game
	 */
	public BoardScreen(Game g) {
		super();
		game = g;
		background = TextureFactory.getInstance().getBoardBackground();
		
		// Size
		this.setPreferredSize(
				new Dimension(GameScreen.G_UNIT * game.getWidth(), GameScreen.G_UNIT * game.getHeight()));	
	}

	
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawBackground(g);
		drawBoard(g);

		drawShip(g);
		drawRadar(g);
	}
	
	/**
	 * Draw the background
	 * @param g : Graphics
	 */
	private void drawBackground(Graphics g) {
		g.drawImage(background, 0, 0, game.getWidth() * GameScreen.G_UNIT, game.getHeight() * GameScreen.G_UNIT, null);
	}
	
	/**
	 * Draws the game board
	 * @param g : Graphics
	 */
	private void drawBoard(Graphics g) {
		int lgr = game.getWidth() * GameScreen.G_UNIT;
		int lrg = game.getHeight() * GameScreen.G_UNIT;
		Point axe = new Point(0,0);

		g.setColor(Color.WHITE);
		g.drawRect(0, 0, lgr, lrg);
		for(int i = 0 ; i < game.getWidth() ; i++) {
			for(int j = 0 ; j < game.getHeight() ; j++) {
				g.drawRect(axe.x + (j * GameScreen.G_UNIT), axe.y, GameScreen.G_UNIT, GameScreen.G_UNIT);
			}
			axe.y = axe.y + GameScreen.G_UNIT;
		}
	}

	/**
	 * Draws the ships of the human
	 * @param g : Graphics
	 */
	private void drawShip(Graphics g) {
		List<Ship> fleet = game.getHumanFleet();
		Graphics2D g2d = (Graphics2D)g;

		for( Ship s : fleet ) {
			if (s.getOrientation() == 0) {
				g2d.drawImage(s.getImage(), 
						s.getX() * GameScreen.G_UNIT,
						s.getY() * GameScreen.G_UNIT,
						s.getWidth() * GameScreen.G_UNIT,
						s.getHeight() * GameScreen.G_UNIT,
						this);
			} else {
				
				BufferedImage bi = (BufferedImage) s.getImage();
				AffineTransform tx = new AffineTransform();
				tx.rotate(Math.toRadians(90), 
						bi.getWidth()/13, 
						bi.getHeight()/2
						);
				AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
				bi = op.filter(bi, null);
				g2d.drawImage(bi, 
						s.getX() * GameScreen.G_UNIT,
						s.getY() * GameScreen.G_UNIT,
						s.getWidth() * GameScreen.G_UNIT,
						s.getHeight() * GameScreen.G_UNIT,
						this);
				
			}
		}
	}
	
	/**
	 * Draws the areas where the computer shoot
	 * @param g : Graphics
	 */
	private void drawRadar(Graphics g) {
		List<Drawable> elements = game.getComputerRadar();
		
		Graphics2D g2d = (Graphics2D)g;
		for( Drawable d : elements ) {
			g2d.drawImage(d.getImage(), 
					d.getX() * GameScreen.G_UNIT,
					d.getY() * GameScreen.G_UNIT,
					d.getHeight() * GameScreen.G_UNIT,
					d.getWidth() * GameScreen.G_UNIT,
					this);
		}
	}
	
}
