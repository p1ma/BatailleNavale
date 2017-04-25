/**
 * 
 */
package graphics;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.List;

import element.GameElement;
import game.GameIModel;
import graphics.listener.RadarController;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class RadarScreen extends CommonScreen {

	/**
	 * serialVersionUID used to 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The controller attached to this Screen
	 */
	private MouseListener controller;

	/**
	 * 
	 * Constructs a RadarScreen with the given parameter(s)
	 * @param g the Game
	 * @param unit the zoom factor
	 */
	public RadarScreen(GameIModel g, int unit) {
		super(g, unit, Color.GREEN);

		controller = new RadarController(g, unit);
		addMouseListener( controller );
	}

	/**
	 * Returns the GameElements to draw
	 */
	public List<GameElement>elementsToDraw(GameIModel game) {
		return game.getOpponentElements();
	}
}
