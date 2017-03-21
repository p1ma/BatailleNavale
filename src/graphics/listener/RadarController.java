/**
 * 
 */
package graphics.listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.Game;
import graphics.GameScreen;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 14, 2017
 */
public class RadarController implements MouseListener{

	private final Game game;

	public RadarController(final Game g) {
		game = g;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int xClicked = (e.getX() / GameScreen.G_UNIT);
		int yClicked = (e.getY() / GameScreen.G_UNIT);
		Point clicked = new Point(xClicked, yClicked);

		if ( !game.checkRadar(clicked) ) {
			game.shootAt(clicked, null);
		} else {
			System.err.println("(RDRCONTROLLER) ALREADY PLAYED");
		}
	}

}
