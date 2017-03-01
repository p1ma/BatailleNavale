/**
 * 
 */
package listener;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import monde.World;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 28, 2017
 */
public class ShootListener implements ActionListener{

	private World monde;
	private Point point;
	
	public ShootListener(World world, Point p) {
		monde = world;
		point = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Tire en : " + point);
		monde.tirer(point);
	}

}
