/**
 * 
 */
package graphique;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import graphique.vue.GameScreen;
import listener.GameListener;
import monde.World;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 26, 2017
 */
public class Game extends JFrame {
    
    private World world;
    private GameScreen gameScreen;
    
	private int ZOOM = 60;
	private final Point axePlateau = new Point(50,50);
    
	private final static String TITLE = "Bataille Navale";
	
    public Game() {
    	super();
        this.world = new World();
        
        /**
         * Create and add the GameScreen
         */
		this.gameScreen = new GameScreen(this.world, ZOOM, axePlateau);
		this.add(gameScreen);
		
		/** 
		 * Create and add the KeyListener
		 **/
		addKeyListener(gameScreen);
		addMouseListener(gameScreen);
		
		initGraphics();
    }
    
    /**
     * Initializes JFrame's options such as
     * - The title
     * - The Size
     * - The Visibility
     *  ...
     */
    private void initGraphics() {
        this.setTitle(TITLE);
        this.setPreferredSize(new Dimension((2*world.longueur() + 1) * (ZOOM), (2*world.largeur() + 1) * (ZOOM)));
        this.setResizable(false);
        
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); // display window in the middle of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}  
