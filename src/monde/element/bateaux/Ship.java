/**
 * 
 */
package monde.element.bateaux;

import java.awt.Point;
import monde.element.GameElement;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public abstract class Ship extends GameElement {
	
	public Ship(int nbCase, int lrgr, String path, Point pos) {
		super(nbCase,lrgr,path,pos);
	}
	
	public void tirer() {
		
	}

}
