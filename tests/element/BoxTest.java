/**
 * 
 */
package element;

import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 29, 2017
 */
public class BoxTest {

	private Box box;
	private Point p = new Point(0,0);
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		box = new HitBox(p);
	}
	
	/**
	 * Tests if the setPosition methods works
	 */
	@Test
	public void setPosition() {
		int x = 5, y  = 5;
		Point obj = new Point(x,y);
		box.setPosition(obj);

		assertTrue(box.getPosition().x == x && box.getPosition().y == y);
	}
	
	/**
	 * Tests if the rotation operation works
	 */
	@Test
	public void rotate() {
		box.rotate();
		assertTrue(box.getOrientation() == 1);
	}


}
