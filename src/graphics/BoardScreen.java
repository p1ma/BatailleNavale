/**
 * 
 */
package graphics;

import java.awt.Color;
import java.util.List;

import element.GameElement;
import game.GameIModel;
import graphics.listener.BoardController;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class BoardScreen extends CommonScreen {

	/**
	 * serialVersionUID used to 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The controller attached to this Screen
	 */
	private BoardController controller ;
	
	/**
	 * 
	 * Constructs a BoardScreen with the given parameter(s)
	 * @param g the Game
	 * @param unit the zoom factor
	 */
	public BoardScreen(GameIModel g, int unit) {
		super(g, unit, Color.WHITE);

		controller = new BoardController(g, unit);
		
		this.addKeyListener( controller );
		this.addMouseListener( controller );
		this.addMouseMotionListener( controller );	
	}

	/**
	 * Returns the GameElements to draw
	 */
	public List<GameElement>elementsToDraw(GameIModel game) {
		return game.getPlayerElements();
	}

	/**
	 * Removes MouseMotionListener
	 */
	public void removeListeners() {
		this.removeMouseMotionListener(controller);
		this.removeKeyListener(controller);
	}
}
