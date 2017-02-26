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
public class Rand implements IA {

	private Random random = new Random();
	
	@Override
	public Point executer(boolean[][] plateau2D) {
		int x,y;
		x = random.nextInt(plateau2D.length);
		y = random.nextInt(plateau2D[0].length);
		
		while ( plateau2D[x][y] != false ) {
			x = random.nextInt(plateau2D.length);
			y = random.nextInt(plateau2D[0].length);
		}
		
		return new Point(x,y);
	}

}
