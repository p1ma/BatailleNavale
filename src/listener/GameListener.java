/**
 * 
 */
package listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import graphique.vue.GameScreen;
import monde.World;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 26, 2017
 */
public class GameListener implements MouseListener {

	/** SpaceShip to control **/
	private World world;
	private GameScreen gameScreen;

	public GameListener(GameScreen gscreen, World monde) {
		this.gameScreen = gscreen;
	    this.world = monde;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Point pos = new Point(arg0.getX(), arg0.getY());
		System.out.println("Clic en " + pos);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
