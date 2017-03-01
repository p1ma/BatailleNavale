/**
 * 
 */
package bateaux;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public abstract class Bateau {

	private int longueur;
	private int largeur;
	private BufferedImage image = null;
	private Point position;
	private Rectangle box;
	private int zoom;
	
	public Bateau(int nbCase, int lrgr, String path, Point pos) {
		longueur = nbCase;
		largeur = lrgr; 
		zoom = 1;
		
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}	
		
		position = new Point(pos);
		box = new Rectangle(position.x, position.y, zoom * longueur,  zoom * largeur);
	}
	
	public void setZoom(int zm) {
		zoom = zm;
		box = new Rectangle(position.x, position.y, zoom * longueur,  zoom * largeur);
	}
	
	public boolean intersect(Point p) {
		return box.contains(p);
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public Point position() {
		return position;
	}
	
	public void setPosition(Point p) {
		position.setLocation(p.getX(), p.getY());
		box.setBounds(position.x, position.y, zoom * longueur, zoom * largeur);
	}
	
	public int getX() {
		return position.x;
	}
	
	public int getY() {
		return position.y;
	}
	
	public void rotate() {
	    int width = image.getWidth();
	    int height = image.getHeight();
	    BufferedImage imageFlip = new BufferedImage(height, width, image.getType());
	    for(int i=0; i<width; i++)
	        for(int j=0; j<height; j++)
	            imageFlip.setRGB(height-1-j, width-1-i, image.getRGB(i, j));
	    image = imageFlip;
	    int tmp = longueur;
		longueur = largeur;
		largeur = tmp;
		box.setBounds(position.x, position.y, zoom * longueur, zoom * largeur);
	}
	
	public void descendre(int y) {
		position.y += y;
		box.setLocation(position.x, position.y);
	}
	
	public void monter(int y) {
		position.y -= y;
		box.setLocation(position.x, position.y);
	}
	
	public void gauche(int x) {
		position.x -= x;
		box.setLocation(position.x, position.y);
	}
	
	public void droite(int x) {
		position.x += x;
		box.setLocation(position.x, position.y);
	}
	public void tirer() {
		
	}
	
	public int longueur() {
		return zoom * longueur;
	}
	
	public int largeur() {
		return zoom * largeur;
	}

	public void verifierPosition(Point p) {
		if (position.x < p.getX()) {
			position.setLocation(p.getX(), position.y);
			box.setLocation(position.x, (int)p.getY());
		}
		if (position.y < p.getY()) {
			position.setLocation(position.x, p.getY());
			box.setLocation(position.x, (int)p.getY());
		}

		int a = (int) ((position.x - p.x) / zoom);
		int b = (int) ((position.y - p.y) / zoom);
		
		position.setLocation(a * zoom, b * zoom);
		position.translate(+p.x, +p.y);
		box.setLocation(position.x, position.y);
	}
}
