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
public class GreekFactory implements EraFactory{
	@Override
	public List<Ship> getFlotte(List<Ship> liste) {
		liste.add(new GreekShip(5,5,"textures/ship.png", new Point(5,5)));
		liste.add(new GreekShip(3,2,"textures/war.png", new Point(0,0)));
		return liste;
	}

}
