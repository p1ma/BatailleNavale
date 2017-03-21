/**
 * 
 */
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
import graphics.listener.BoardController;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class BoardScreen extends JPanel {

	private Game game; 
	private final Image background;

	public BoardScreen(Game g) {
		super();
		game = g;
		background = TextureFactory.getInstance().getBoardBackground();

		BoardController controller = new BoardController(game);
		this.addKeyListener(controller);
		this.addMouseListener(controller);
		this.addMouseMotionListener(controller);

		// SIZE
		this.setPreferredSize(
				new Dimension(GameScreen.G_UNIT * game.getWidth(), GameScreen.G_UNIT * game.getHeight()));	
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawBackground(g);
		drawBoard(g);
		drawElements(g);
		drawShipStarterPosition(g);
	}

	private void drawElements(Graphics g) {
		List<Drawable> elements = game.getBoardElements();

		Graphics2D g2d = (Graphics2D)g;
		for( Drawable d : elements ) {
			/*g2d.rotate(d.getOrientation(),
					(int)d.getX() * g_unit,
					(int)d.getY() * g_unit);*/
			g2d.drawImage(d.getImage(), 
					d.getX() * GameScreen.G_UNIT,
					d.getY() * GameScreen.G_UNIT,
					d.getHeight() * GameScreen.G_UNIT,
					d.getWidth() * GameScreen.G_UNIT,
					this);
			/*g2d.rotate(-d.getOrientation(),
					(int)d.getX() * g_unit,
					(int)d.getY() * g_unit);*/
			System.out.println("X : " + d.getX() * GameScreen.G_UNIT);
			System.out.println("Y : " + d.getY() * GameScreen.G_UNIT);
		}
	}

	private void drawBackground(Graphics g) {
		g.drawImage(background, 0, 0, game.getWidth() * GameScreen.G_UNIT, game.getHeight() * GameScreen.G_UNIT, null);
	}

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

	/*
	 * PAS SUR DE CETTE SOLUTION, A DISCUTER
	 * NECESSITE PAS MAL DE BIDOUILLAGE IMHO
	 * + DUPLICATION DE CODE AVEC DRAW ELEMENTS
	 */
	private void drawShipStarterPosition(Graphics g) {
		List<Ship> fleet = game.getFleet();
		Graphics2D g2d = (Graphics2D)g;
		/*
		private Image image;
		AffineTransform identity = new AffineTransform();

		Graphics2D g2d = (Graphics2D)g;
		AffineTransform trans = new AffineTransform();
		trans.setTransform(identity);
		trans.rotate( Math.toRadians(45) );
		g2d.drawImage(image, trans, this);
		 */

		for( Ship s : fleet ) {
			if (s.getOrientation() == 0) {
				g2d.drawImage(s.getImage(), 
						s.getX() * GameScreen.G_UNIT,
						s.getY() * GameScreen.G_UNIT,
						s.getWidth() * GameScreen.G_UNIT,
						s.getHeight() * GameScreen.G_UNIT,
						this);
			} else {
				// Rotation information
				double rotationRequired = Math.toRadians (90);
				double locationY = GameScreen.G_UNIT;
				double locationX = GameScreen.G_UNIT;
				AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
				AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

				// Drawing the rotated image at the required drawing locations
				g2d.drawImage(op.filter((BufferedImage) s.getImage(), null), 
						s.getX() * GameScreen.G_UNIT,
						s.getY() * GameScreen.G_UNIT,
						s.getWidth() * GameScreen.G_UNIT,
						s.getHeight() * GameScreen.G_UNIT,
						this);
			}
		}
	}
}
