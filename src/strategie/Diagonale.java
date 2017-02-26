/**
 * 
 */
package strategie;

import java.awt.Point;
import java.util.Random;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public class Diagonale implements IA {

	private Random random = new Random();
	private Point precedent = null;

	@Override
	public Point executer(boolean[][] plateau2D) {
		int x,y;
		if ( precedent == null ) {
			x = random.nextInt(plateau2D.length);
			y = random.nextInt(plateau2D[0].length);

			while ( plateau2D[x][y] != false ) {
				x = random.nextInt(plateau2D.length);
				y = random.nextInt(plateau2D[0].length);
			}
		} else {
			x = precedent.x;
			y = precedent.y;
			int index = -1;
			while ( plateau2D[x][y] != true ) {
				x = precedent.x + index;
				y = precedent.y + index;
				
				if ( x == 0 && y == 0) {
					index = 1;
					x = precedent.x + index;
					y = precedent.y + index;;
				}
				
				// cas extreme
				if ( x == plateau2D.length && 
						y == plateau2D[0].length) {
					while ( plateau2D[x][y] != false ) {
						x = random.nextInt(plateau2D.length);
						y = random.nextInt(plateau2D[0].length);
					}
					precedent = new Point(x,y);
					return new Point(x,y);
				}
			}
			precedent = new Point(x,y);
			
		}
		return new Point(x,y);
	}

}
