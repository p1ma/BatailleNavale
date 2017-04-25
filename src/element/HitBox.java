/**
 * 
 */
package element;

import java.awt.Point;

import game.GameIModel;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class HitBox extends Box{

	/**
	 * 
	 * Constructs a HitBox with the given parameter(s)
	 * @param p the Position
	 * @param model the GameIModel (used to load the image)
	 */
	public HitBox(Point p, GameIModel model) {
		super(p);
		background = model.getGameTouchedImage();
	}

	/**
	 * Indicates if the this GameElement is useful
	 * to the Strategy, like
	 * a hitBox is useful (it let us know that there is a
	 * Ship at the position)
	 * @return true if the GameElement can be used to the IA
	 */
	@Override
	public boolean isStrategicallyUseful() {
		return true;
	}
	
	

}
