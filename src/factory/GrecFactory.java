/**
 * 
 */
package factory;

import java.awt.Point;
import java.util.List;

import bateaux.Bateau;
import bateaux.BateauGrec;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 28, 2017
 */
public class GrecFactory implements Factory{
	@Override
	public List<Bateau> getFlotte(List<Bateau> liste) {
		liste.add(new BateauGrec(5,5,"textures/ship.png", new Point(5,5)));
		liste.add(new BateauGrec(3,2,"textures/war.png", new Point(0,0)));
		return liste;
	}

}
