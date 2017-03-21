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
	private final Image background;

	public RadarScreen(Game g) {
		super();
		game = g;
		background = TextureFactory.getInstance().getBoardBackground();

		addMouseListener(new RadarController(game));
		
		// SIZE
		this.setPreferredSize(
				new Dimension(GameScreen.G_UNIT * game.getWidth(), GameScreen.G_UNIT * game.getHeight()));
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
					d.getX() * GameScreen.G_UNIT,
					d.getY() * GameScreen.G_UNIT,
					d.getHeight() * GameScreen.G_UNIT,
					d.getWidth() * GameScreen.G_UNIT,
					null);
		}
	}

	private void drawBackground(Graphics g) {
		g.drawImage(background, 0, 0, game.getWidth() * GameScreen.G_UNIT, game.getHeight() * GameScreen.G_UNIT, null);
	}

	private void drawRadar(Graphics g) {
		int lgr = game.getWidth() * GameScreen.G_UNIT;
		int lrg = game.getHeight() * GameScreen.G_UNIT;
		Point axe = new Point(0,0);

		g.setColor(Color.GREEN);
		g.drawRect(0, 0, lgr, lrg);
		for(int i = 0 ; i < game.getWidth() ; i++) {
			//g.drawString(alphabet[i], axePlateau.x + i*facteur + (facteur/2), axePlateau.y - (facteur/10));
			for(int j = 0 ; j < game.getHeight() ; j++) {
				g.drawRect(axe.x + (j * GameScreen.G_UNIT), axe.y, GameScreen.G_UNIT, GameScreen.G_UNIT);
			}
			//g.drawString("" + (1+i), axe.x - (facteur/3), axe.y + (facteur/2));
			axe.y = axe.y + GameScreen.G_UNIT;
		}
	}
}
