package element;

import java.awt.Image;
import java.awt.Point;

import graphics.TextureFactory;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class HitBox extends Box {

	/**
	 * Constructor
	 * @param p : Point, position of HitBox
	 */
	public HitBox(Point p) {
		super(p);
	}
	
	
	
	
	
	@Override
	public Image getImage() {
		return TextureFactory.getInstance().getHitBoxImage();
	}

}
