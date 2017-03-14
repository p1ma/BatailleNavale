/**
 * 
 */
package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import game.Game;
import graphics.listener.BoardController;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class BoardScreen extends JPanel {

	private Game game; // <- pas necessaire imho
	private final int g_unit;
	
	public BoardScreen(Game g, final int unit) {
		super();
		game = g;
		g_unit = unit;
		
		addMouseListener(new BoardController(g_unit));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		drawBoard(g);
	}
	
	private void drawBoard(Graphics g) {
		int lgr = game.getWidth() * g_unit;
		int lrg = game.getHeight() * g_unit;
		Point axe = new Point(0,0);
		
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, lgr, lrg);
		for(int i = 0 ; i < game.getWidth() ; i++) {
			for(int j = 0 ; j < game.getHeight() ; j++) {
				g.drawRect(axe.x + (j * g_unit), axe.y, g_unit, g_unit);
			}
			axe.y = axe.y + g_unit;
		}
	}
	
	
}
