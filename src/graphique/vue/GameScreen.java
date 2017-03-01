/**
 * 
 */
package graphique.vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import bateaux.Bateau;
import bateaux.BateauGrec;
import listener.ShootListener;
import monde.World;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 26, 2017
 */
public class GameScreen extends JPanel implements Observer, KeyListener, MouseMotionListener, MouseListener{

	private World world;
	private final Color defaut = Color.BLUE;
	private final Color back = Color.WHITE;
	private final Point axePlateau;
	private final String alphabet[] = {"A","B","C","D","E","F","G",
			"H","I","J","K","L","M","N","O","P","Q","R","S","T","U",
			"V","W","X","Y","Z"};

	private Bateau ship = null;
	private Bateau select = null;
	private JButton boutons[][] ;
	private JPanel sonar;
	private JPanel panneaux;

	private int facteur = 60;

	public GameScreen(World monde, int zoom, final Point axeP) {
		super();

		facteur = zoom;
		world = monde;
		boutons = new JButton[world.longueur()][world.largeur()];
		axePlateau = axeP;
		ship = new BateauGrec(5,1, "textures/ship.png",
				new Point(axePlateau.x + facteur*4, axePlateau.y + facteur*5));
		ship.setZoom(facteur);
		sonar = new JPanel();
		panneaux = new Plateau();
		setLayout(new GridLayout(1,2));
		initDimensions();
		initBoutons();
		add(panneaux);
		add(sonar);
	}

	public void initBoutons() {
		Dimension dim = new Dimension(facteur,facteur);
		sonar.setLayout(new GridLayout(world.longueur(), world.largeur()));
		for(int i = 0 ; i < world.longueur() ; i++) {
			for(int j = 0 ; j < world.largeur() ; j++) {
				boutons[i][j] = new JButton("");
				boutons[i][j].setBackground(defaut);
				boutons[i][j].setMaximumSize(dim);
				boutons[i][j].setMinimumSize(dim);
				boutons[i][j].setSize(dim);
				boutons[i][j].addActionListener(new ShootListener(world,new Point(i,j)));
				sonar.add(boutons[i][j]);
			}
		}
	}
	public void initDimensions() {
		this.setMinimumSize(new Dimension((2*world.longueur() + 2) * (facteur), (1*world.largeur() + 2) * (facteur)));
		this.setMaximumSize(new Dimension((2*world.longueur() + 2) * (facteur), (1*world.largeur() + 2) * (facteur)));
		sonar.setMinimumSize(new Dimension((world.longueur()) * (facteur), (world.largeur()) * (facteur)));
		sonar.setMaximumSize(new Dimension((world.longueur()) * (facteur), (world.largeur()) * (facteur)));
		panneaux.setMinimumSize(new Dimension((world.longueur()) * (facteur), (world.largeur()) * (facteur)));
		panneaux.setMaximumSize(new Dimension((world.longueur()) * (facteur), (world.largeur()) * (facteur)));
		this.setBackground(Color.WHITE);
	}

	public void dessinerPlateau(Graphics g) {
		int lgr = world.longueur() * facteur;
		int lrg = world.largeur() * facteur;
		Point axe = new Point(axePlateau);

		g.setColor(back);
		g.drawRect(axe.x, axe.y, lgr, lrg);
		g.setColor(defaut);
		g.fillRect(axe.x, axe.y, lgr, lrg);
		g.setColor(back);
		for(int i = 0 ; i < world.longueur() ; i++) {
			g.drawString(alphabet[i], axePlateau.x + i*facteur + (facteur/2), axePlateau.y - (facteur/10));
			for(int j = 0 ; j < world.largeur() ; j++) {
				g.drawRect(axe.x + (j * facteur), axe.y, facteur, facteur);
			}
			g.drawString("" + (1+i), axe.x - (facteur/3), axe.y + (facteur/2));
			axe.y = axe.y + facteur;
		}
		g.drawImage(ship.getImage(),ship.getX() , ship.getY(), ship.longueur(), ship.largeur(), null);
	}

	public void dessinerSonar(Graphics g) {
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("ok");
		switch(arg0.getKeyCode()) {
		case KeyEvent.VK_R :
			ship.rotate();
			repaint();
			break;
		case KeyEvent.VK_Z :
			ship.monter(facteur);
			repaint();
			break;	
		case KeyEvent.VK_S :
			ship.descendre(facteur);
			repaint();
			break;
		case KeyEvent.VK_Q :
			ship.gauche(facteur);
			repaint();
			break;
		case KeyEvent.VK_D :
			ship.droite(facteur);
			repaint();
			break;
		default :
			break;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if ( select != null ) {
			System.out.println("Pos.x = " + ((e.getX() - axePlateau.x) / facteur));
			System.out.println("Pos.y = " + ((e.getY() - axePlateau.y) / facteur));
			ship.setPosition(e.getPoint());
			ship.verifierPosition(axePlateau);
			repaint();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Pos : " + e.getPoint());
		System.out.println("Pos.x = " + ((e.getX() - axePlateau.x) / facteur));
		System.out.println("Pos.y = " + ((e.getY() - axePlateau.y) / facteur));

		if( ship.intersect(e.getPoint())) {
			select = ship;
		} else {
			select = null;
		}
		repaint();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if( ship.intersect(e.getPoint())) {
			select = ship;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		select = null;
		repaint();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if( ship.intersect(e.getPoint())) {
			select = ship;
			repaint();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private class Plateau extends JPanel {
		
		public Plateau() {
			
		}

		@Override
		public void paint(Graphics g) {
			int lgr = world.longueur() * facteur;
			int lrg = world.largeur() * facteur;
			Point axe = new Point(axePlateau);

			g.setColor(back);
			g.drawRect(axe.x, axe.y, lgr, lrg);
			g.setColor(defaut);
			g.fillRect(axe.x, axe.y, lgr, lrg);
			g.setColor(back);
			for(int i = 0 ; i < world.longueur() ; i++) {
				g.drawString(alphabet[i], axePlateau.x + i*facteur + (facteur/2), axePlateau.y - (facteur/10));
				for(int j = 0 ; j < world.largeur() ; j++) {
					g.drawRect(axe.x + (j * facteur), axe.y, facteur, facteur);
				}
				g.drawString("" + (1+i), axe.x - (facteur/3), axe.y + (facteur/2));
				axe.y = axe.y + facteur;
			}
			if ( select != null ) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(10));
				g2.setColor(Color.RED);
				g2.drawRect(select.getX(), select.getY(), ship.longueur(), ship.largeur());
				g2.setColor(defaut);
				System.out.println("draw");
			}
			g.drawImage(ship.getImage(),ship.getX() , ship.getY(), ship.longueur(), ship.largeur(), null);
		}
		
		
	}

	@Override
	public void paintComponents(Graphics g) {
		panneaux.repaint();
	}
}
