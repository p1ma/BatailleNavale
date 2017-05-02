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
public class ShipTest {
	
	private Ship ge;
	private String id = "NOM";
	private Point p = new Point(0,0);
	private int w = 1, h = 5, o = 0;
	private String img = "textures/Moderne/2cases.png";
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ge = new Ship(id, p, w, h, o, img);
	}

	/**
	 * Tests if the setPosition methods works
	 */
	@Test
	public void setPosition() {
		int x = 5, y  = 5;
		Point obj = new Point(x,y);
		ge.setPosition(obj);

		assertTrue(ge.getPosition().x == x && ge.getPosition().y == y);
	}

	/**
	 * Tests if the rotation operation works
	 */
	@Test
	public void rotate() {
		ge.rotate();
		assertTrue(ge.getOrientation() == (1-o));
		
		assertTrue(ge.getWidth() == h);
		assertTrue(ge.getHeight() == w);
		assertTrue(ge.getPosition().equals(p));
	}

	/**
	 * Tests if the overriden equals method works
	 */
	@Test
	public void equals() {
		Ship copy = new Ship(ge);
		assertTrue(copy.equals(ge));
	}

}
