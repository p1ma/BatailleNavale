/**
 * 
 */
package strategie;

import java.awt.Point;
import java.util.Scanner;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 26, 2017
 */
public class Input implements IA{

	private Scanner scanner = new Scanner(System.in);

	@Override
	public Point executer(boolean[][] plateau2D) {
		int x = 0;
		int y = 0;
		do{
			System.out.println("Coordonnée x ?");
			x = scanner.nextInt() % 10;
			System.out.println("Coordonnée y ?");
			y = scanner.nextInt() % 10;
			System.out.flush();
		} while(plateau2D[x][y] != false);
		return new Point(x,y);
	}

}
