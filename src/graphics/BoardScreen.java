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
import java.util.List;

import javax.swing.JPanel;

import element.Drawable;
import element.Ship;
import game.Configuration;
import game.Game;
import graphics.listener.BoardController;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class BoardScreen extends JPanel {

	private Game game; 
	private final int g_unit;
	private final Image background;

	public BoardScreen(Game g, final int unit) {
		super();
		game = g;
		g_unit = unit;
		background = TextureFactory.getInstance().getBoardBackground();

		BoardController controller = new BoardController(game, g_unit);
		this.addKeyListener(controller);
		this.addMouseListener(controller);
		this.addMouseMotionListener(controller);
		
		// SIZE
		this.setPreferredSize(new Dimension(this.g_unit * game.getWidth(), this.g_unit * game.getHeight()));	
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
					(int)d.getX() * g_unit,
					(int)d.getY() * g_unit,
					d.getHeight() * g_unit,
					d.getWidth() * g_unit,
					this);
			/*g2d.rotate(-d.getOrientation(),
					(int)d.getX() * g_unit,
					(int)d.getY() * g_unit);*/
			System.out.println("X : " + (int)d.getX() * g_unit);
			System.out.println("Y : " + (int)d.getY() * g_unit);
		}
	}

	private void drawBackground(Graphics g) {
		g.drawImage(background, 0, 0, game.getWidth() * g_unit, game.getHeight() * g_unit, null);
	}

	private void drawBoard(Graphics g) {
		int lgr = game.getWidth() * g_unit;
		int lrg = game.getHeight() * g_unit;
		Point axe = new Point(0,0);

		g.setColor(Color.WHITE);
		g.drawRect(0, 0, lgr, lrg);
		for(int i = 0 ; i < game.getWidth() ; i++) {
			for(int j = 0 ; j < game.getHeight() ; j++) {
				g.drawRect(axe.x + (j * g_unit), axe.y, g_unit, g_unit);
			}
			axe.y = axe.y + g_unit;
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
		for( Ship s : fleet ) {
			/*g2d.rotate(s.getOrientation(),
					(int)s.getX(),
					(int)s.getY());*/
			g2d.drawImage(s.getImage(), 
					(int)s.getX(),
					(int)s.getY(),
					s.getHeight(),
					s.getWidth(),
					this);
			/*g2d.rotate(-s.getOrientation(),
					(int)s.getX(),
					(int)s.getY());*/
		}
	}
}
