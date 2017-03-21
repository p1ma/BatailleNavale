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
import graphics.GameScreen;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 14, 2017
 */
public class BoardController implements MouseListener, MouseMotionListener, KeyListener{

	private final Game game;
	private Ship selected;

	public BoardController(final Game g) {
		game = g;
		selected = null;
	}

	/**
	 * Used to move the Ship when the player
	 * dragged his mouse, then check if the 
	 * deplacement is correct, if not
	 * function checkCoordinates is called
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if ( selected != null) {
			Point p = new Point(e.getX()/GameScreen.G_UNIT, e.getY()/GameScreen.G_UNIT);
			if (!game.intersectOtherShips(selected, p)) {
				game.setShipPosition(selected, p);
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

		// checks if the x positions exceed (left border)
		if (position.x < 0) {
			position.x = 0;
		}

		// checks if the y positions exceed (top border)
		if (position.y < 0) {
			position.y = 0;
		}

		// checks if the x positions exceed (right border)
		int exceedBy = game.getWidth() - (position.x + s.getWidth());
		if ( exceedBy < 0) {
			// + because exceedBy is negative at this moment
			position.x += exceedBy;
		}

		// checks if the y positions exceed (bottom border)
		exceedBy = game.getHeight() - (position.y + s.getHeight());
		if ( exceedBy < 0) {
			// + because exceedBy is negative at this moment
			position.y += exceedBy;
		}

		// then we re-update the new position
		game.setShipPosition(selected, position);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

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
		int xClicked = (int)(e.getX() / GameScreen.G_UNIT);
		int yClicked = (int)(e.getY() / GameScreen.G_UNIT);
		Point clicked = new Point(xClicked, yClicked);

		// on enregistre le bateau sur lequel on maintient le click gauche
		selected = game.selectShip(clicked);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// on relache le bateau
		selected = null;
	}





	@Override
	public void keyPressed(KeyEvent e) {
		if ((selected != null) && (e.getKeyCode() == KeyEvent.VK_R)) {
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
