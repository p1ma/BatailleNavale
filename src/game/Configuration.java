/**
 * 
 */
package game;

import java.awt.Dimension;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Configuration {

	private Era era;
	
	/*
	 *	Techniquement d'apres notre diagramme de sequence c'est faux 
	 */
	public final static int WIDTH = 10;
	public final static int HEIGHT = 10;
	
	public Configuration() {

	}
	
	public Era getEra() {
		return era;
	}
}
