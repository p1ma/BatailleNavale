/**
 * 
 */
package graphics.listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.GameIModel;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 14, 2017
 */
public class RadarController implements MouseListener{

	/**
	 * Zoom factor
	 */
	private final int g_unit;
	
	/**
	 * Game
	 */
	private final GameIModel game;

	/**
	 * 
	 * Constructs a RadarController with the given parameter(s)
	 * @param g the Game
	 * @param unit the zoom factor
	 */
	public RadarController(final GameIModel g, final int unit) {
		g_unit = unit;
		game = g;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// empty
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// empty
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// empty
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// empty
	}

	/**
	 * If the position clicked on is playable
	 * then it will call the Game's play method
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		Point clicked = scalePoint(e.getPoint());
		
		if( game.isPlayable(clicked) ) {
			game.play(clicked);
		}
	}

	private Point scalePoint(Point p) {
		int xClicked = (int)(p.getX() / g_unit) ;
		int yClicked = (int)(p.getY() / g_unit);

		return new Point(xClicked, yClicked);
	}

}
