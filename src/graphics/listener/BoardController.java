/**
 * 
 */
package graphics.listener;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import element.Ship;
import game.Game;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 14, 2017
 */
public class BoardController implements MouseListener, MouseMotionListener, KeyListener{

	private final Game game;
	private Ship selected;
	private final int g_unit;

	// key 'r' used to rotate a ship
	private int rotate;

	public BoardController(final Game g, final int unit) {
		game = g;
		selected = null;
		g_unit = unit;
		rotate = KeyEvent.VK_R;
	}

	/**
	 * Used to move the Ship when the player
	 * dragged his mouse, then check if the 
	 * deplacement is correct, if not
	 * function checkCoordinates is called
	 */
	@Override
	public void mouseDragged(MouseEvent arg) {
		if ( selected != null) {
			if (!game.intersectOtherShips(selected, arg.getPoint())) {
				game.setShipPosition(selected, arg.getPoint());
				checkCoordinates( selected );
			}
		}
	}

	/**
	 * In order to keep the Ship inside the BoardScreen
	 * we have to check if the new coordinates corrects.
	 */
	private void checkCoordinates(Ship s) {
		Point position = s.getPosition();

		// calc coordinates
		int x = 0, y = 0;
		x = (int)(position.x / g_unit);
		y = (int)(position.y / g_unit);
		x *= g_unit;
		y *= g_unit;
		position = new Point(x,y);

		// checks if the x positions exceed (left border)
		if (position.x < 0) {
			position.x = 0;
		}

		// checks if the y positions exceed (top border)
		if (position.y < 0) {
			position.y = 0;
		}

		// checks if the x positions exceed (right border)
		int exceedBy = (game.getHeight() * g_unit) - (position.x + s.getHeight());
		if ( exceedBy < 0) {
			// + because exceedBy is negative at this moment
			position.x += exceedBy;
		}

		// checks if the y positions exceed (bottom border)
		exceedBy = (game.getWidth() * g_unit) - (position.y + s.getWidth());
		if ( exceedBy < 0) {
			// + because exceedBy is negative at this moment
			position.y += exceedBy;
		}

		// then we re-update the new position
		game.setShipPosition(selected, position);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// see function mouseDragged
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// see function mousePressed
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// see function mousePressed
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// see function mouseReleased
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int xClicked = (int)(e.getX());
		int yClicked = (int)(e.getY());
		Point clicked = new Point(xClicked, yClicked);

		// TEST
		selected = game.selectShip(clicked);
		// END TEST
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		selected = null;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		// ca deconne mais je sais pq
		if ( (code == rotate) && (selected != null) ) {
				game.rotate(selected);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
