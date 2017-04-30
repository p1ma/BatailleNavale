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

import element.GameElement;
import game.GameIModel;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 22, 2017
 */
public abstract class CommonScreen extends JPanel{
	
	/**
	 * serialVersionUID used to 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Axis starter Point
	 */
	private final Point starterAxis = new Point(0,0);
	
	/***
	 * Zoom factor
	 */
	private int factor;
	
	/**
	 * The Game
	 */
	private GameIModel game;
	
	/**
	 * The background color
	 */
	private Color color;
	
	/**
	 * The background Image
	 */
	private Image background;
	
	/**
	 * 
	 * Constructs a CommonScreen with the given parameter(s)
	 * @param g the Game
	 * @param x the zoom factor
	 * @param c the Color of the future lines
	 */
	public CommonScreen(GameIModel g, int x, Color c) {
		super();
		
		game = g;
		factor = x;
		color = c;
		background = g.getGameBackgroundImage();
		
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
		Graphics2D g2d = (Graphics2D)g.create();
		
		drawBackground(g2d);
		drawBoard(g2d);
		drawElements(g2d);
		
		g2d.dispose();
	}
	
	/**
	 * Used to get the correct GameElements to draw
	 * @param game the Game
	 * @return List of GameElements
	 */
	public abstract List<GameElement> elementsToDraw(GameIModel game);
	
	/**
	 * Draws the GameElements in g
	 * @param g the Graphics2D
	 */
	private void drawElements(Graphics2D g) {
		List<GameElement> elements = elementsToDraw(game);

		for( GameElement d : elements ) {
			g.drawImage(d.getImage(), 
					getZoomedInteger((int)d.getX()),
					getZoomedInteger((int)d.getY()),
					getZoomedInteger(d.getWidth()),
					getZoomedInteger(d.getHeight()),
					this);
		}
	}
	
	/**
	 * Draws the background in g
	 * @param g the Graphics2D
	 */
	private void drawBackground(Graphics g) {
		g.drawImage(background, 
				getZoomedInteger( (int)starterAxis.getX()),
				getZoomedInteger( (int)starterAxis.getY()),
				getZoomedInteger(game.getWidth()),
				getZoomedInteger(game.getHeight()),
				this);
	}
	
	/**
	 * Draw the board in g with the selected color color
	 * @param g the Graphics2D
	 */
	private void drawBoard(Graphics g) {
		int width = getZoomedInteger(game.getWidth());
		int height = getZoomedInteger(game.getHeight());
		Point axis = new Point(0,0);

		g.setColor(color);
		g.drawRect(axis.x, axis.y, width, height);
		for(int i = 0 ; i < game.getWidth() ; i++) {
			for(int j = 0 ; j < game.getHeight() ; j++) {
				g.drawRect(axis.x + getZoomedInteger(j),
						axis.y, 
						getZoomedInteger(1),
						getZoomedInteger(1));
			}
			axis.y += getZoomedInteger(1);
		}
	}
	
	/**
	 * Changes the zoom factor to x
	 * @param x the futur zoom factor
	 */
	public void setZoomedFactor(int x) {
		factor = x;
		revalidate();
		repaint();
	}
	
	/**
	 * Returns the correct value of x after the zoom operation
	 * @param x the value to compute
	 * @return the computed value
	 */
	private int getZoomedInteger(int x) {
		return (x * factor);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getZoomedInteger(game.getWidth()), 
				getZoomedInteger(game.getHeight()));
	}
	
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(getZoomedInteger(game.getWidth()), 
				getZoomedInteger(game.getHeight()));
	}
	
	@Override
	public Dimension getMaximumSize() {
		return new Dimension(getZoomedInteger(game.getWidth()), 
				getZoomedInteger(game.getHeight()));
	}
}
