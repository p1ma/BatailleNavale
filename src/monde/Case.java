/**
 * 
 */
package monde;

import java.awt.Image;
import java.awt.Point;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 28, 2017
 */
public class Case {

	private enum Etat { TOUCHE, RATE, VIDE };
	
	private Etat etat = Etat.VIDE;
	
	private Point pos;
	
	public Case(Point p) {
		pos = p;
	}
	
	public Image getImage() {
		switch(etat) {
		case TOUCHE:
			return null;
		case RATE :
			return null;
		default :
			return null;
		}
	}
}
