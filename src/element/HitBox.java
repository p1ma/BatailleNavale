/**
 * 
 */
package element;

import java.awt.Image;

import graphics.TextureFactory;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class HitBox extends Box{

	@Override
	public Image getImage() {
		return TextureFactory.getInstance().getMissedBoxImage();
	}

}
