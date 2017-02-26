/**
 * 
 */
package graphique.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import monde.World;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 26, 2017
 */
public class GameScreen extends JPanel implements Observer{

	private World world;
	private final Color defaut = Color.BLUE;
	private final Color back = Color.WHITE;
	private final Point axePlateau = new Point(50,50);
	private final String alphabet[] = {"A","B","C","D","E","F","G",
			"H","I","J","K","L","M","N","O","P","Q","R","S","T","U",
					"V","W","X","Y","Z"};
	
	private int facteur = 60;
	
	public GameScreen(World monde, int zoom) {
		super();
		facteur = zoom;
		world = monde;
		this.setBackground(back);
	}
	
	public void initDimensions() {
        this.setPreferredSize(new Dimension((2*world.longueur() + 1) * (facteur), (2*world.largeur() + 1) * (facteur)));
        this.setBackground(Color.WHITE);
	}

	/**
	 * Most important graphic function,
	 * used here to draw every GameElements of the World
	 */
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		dessinerPlateau(g);
		dessinerSonar(g);
	}
	
	public void dessinerPlateau(Graphics g) {
		int lgr = world.longueur() * facteur;
		int lrg = world.largeur() * facteur;
		Point axe = new Point(axePlateau);
		
		g.setColor(defaut);
		g.drawRect(axe.x, axe.y, lgr, lrg);
		for(int i = 0 ; i < world.longueur() ; i++) {
			g.drawString(alphabet[i], axePlateau.x + i*facteur + (facteur/2), axePlateau.y - (facteur/10));
			for(int j = 0 ; j < world.largeur() ; j++) {
								g.drawRect(axe.x + (j * facteur), axe.y, facteur, facteur);
			}
			g.drawString("" + (1+i), axe.x - (facteur/3), axe.y + (facteur/2));
			axe.y = axe.y + facteur;
		}
		
	}
	
	public void dessinerSonar(Graphics g) {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}


}
