/**
 * 
 */
package monde.element.cases;

import java.awt.Point;

import monde.element.GameElement;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 2, 2017
 */
public abstract class Case extends GameElement{

	public Case(int nbCase, int lrgr, String path, Point pos) {
		super(nbCase, lrgr, path, pos);
	}

	
}
