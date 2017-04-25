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

import element.GameElement;
import game.GameIModel;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 14, 2017
 */
public class BoardController implements MouseListener, MouseMotionListener, KeyListener{

	/**
	 * The GameIModel
	 */
	private final GameIModel game;

	/**
	 * GameElement currently selected, default = null
	 */
	private GameElement selected;

	/**
	 * Zoom factor
	 */
	private final int g_unit;

	/**
	 *  key 'r' used to rotate a ship
	 */
	private final int rotate = KeyEvent.VK_R;

	/**
	 *  key 'enter' used to validate ships positions
	 */
	private final int validate = KeyEvent.VK_ENTER;

	/**
	 * 
	 * Constructs a BoardController with the given parameter(s)
	 * @param g the Game
	 * @param unit the zoom factor
	 */
	public BoardController(final GameIModel g, final int unit) {
		game = g;
		selected = null;
		g_unit = unit;
	}

	/**
	 * Used to move the Ship when the player
	 * dragged his mouse, then check if the 
	 * deplacement is correct, if not
	 * function checkCoordinates is called
	 */
	@Override
	public void mouseDragged(MouseEvent arg) {
		Point scaledPoint = scalePoint( arg.getPoint() );
		if ( selected != null) {
			boolean correct = game.isPositionAvailable(selected, scaledPoint);
			if ( correct ) {
				game.setGameElementPosition(selected, scaledPoint);
				checkCoordinates( selected );
			}
		} 
	}

	/**
	 * In order to keep the Ship inside the BoardScreen
	 * we have to check if the new coordinates corrects.
	 */
	private void checkCoordinates(GameElement s) {
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
		int exceedBy = (game.getHeight() - (position.x + s.getWidth()));
		if ( exceedBy < 0) {
			// + because exceedBy is negative at this moment
			position.x += exceedBy;
		}

		// checks if the y positions exceed (bottom border)
		exceedBy = (game.getWidth() - (position.y + s.getHeight()));
		if ( exceedBy < 0) {
			// + because exceedBy is negative at this moment
			position.y += exceedBy;
		}

		// then we re-update the new position
		game.setGameElementPosition(selected, position);
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
		selected = null;
	}

	/**
	 * Selects the GameElements located at the position
	 * where the mouse was pressed
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		selected = game.selectGameElement( scalePoint(e.getPoint()) );
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// see function mouseExited
	}

	/**
	 * If the User (during the warmup)
	 * clicks on r then it will rotates the selected GameElement
	 * and if He clicks on ENTER then it confirms the GameElements positions
	 * and launch the Game
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if ( selected != null ){
			switch( code ) {
			case rotate :
				selected.rotate();
				checkCoordinates(selected);
				break;
			case validate :
				game.confirmGameElementsPosition();
				break;
			}
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	private Point scalePoint(Point p) {
		int xClicked = (int)(p.getX() / g_unit) ;
		int yClicked = (int)(p.getY() / g_unit);

		return new Point(xClicked, yClicked);
	}
}
