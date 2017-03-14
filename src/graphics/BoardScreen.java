/**
 * 
 */
package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import game.Game;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class BoardScreen extends JPanel {

	private Game game; // <- pas necessaire imho
	
	public BoardScreen(Game g) {
		super();
		game = g;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		System.out.println("BOARD REFRESHED");
	}
	
	
}
