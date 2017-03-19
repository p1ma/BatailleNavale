/**
 * 
 */
package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;

import element.Drawable;
import game.Configuration;
import game.Game;
import graphics.listener.RadarController;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class RadarScreen extends JPanel {

	private Game game; // <- pas necessaire imho
	private final int g_unit;
	private final Image background;

	public RadarScreen(Game g, final int unit) {
		super();
		game = g;
		g_unit = unit;
		background = TextureFactory.getInstance().getBoardBackground();

		addMouseListener(new RadarController(g_unit, game));
		
		// SIZE
		this.setPreferredSize(new Dimension(this.g_unit * game.getWidth(), this.g_unit * game.getHeight()));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawBackground(g);
		drawRadar(g);
		drawElements(g);
	}

	private void drawElements(Graphics g) {
		List<Drawable> elements = game.getRadarElements();

		for( Drawable d : elements ) {
			g.drawImage(d.getImage(), 
					d.getX() * g_unit,
					d.getY() * g_unit,
					d.getHeight() * g_unit,
					d.getWidth() * g_unit,
					null);
		}
	}

	private void drawBackground(Graphics g) {
		g.drawImage(background, 0, 0, game.getWidth() * g_unit, game.getHeight() * g_unit, null);
	}

	private void drawRadar(Graphics g) {
		int lgr = game.getWidth() * g_unit;
		int lrg = game.getHeight() * g_unit;
		Point axe = new Point(0,0);

		g.setColor(Color.GREEN);
		g.drawRect(0, 0, lgr, lrg);
		for(int i = 0 ; i < game.getWidth() ; i++) {
			//g.drawString(alphabet[i], axePlateau.x + i*facteur + (facteur/2), axePlateau.y - (facteur/10));
			for(int j = 0 ; j < game.getHeight() ; j++) {
				g.drawRect(axe.x + (j * g_unit), axe.y, g_unit, g_unit);
			}
			//g.drawString("" + (1+i), axe.x - (facteur/3), axe.y + (facteur/2));
			axe.y = axe.y + g_unit;
		}
	}
}
