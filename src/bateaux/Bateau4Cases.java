/**
 * 
 */
package bateaux;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public abstract class Bateau4Cases implements Bateau  {
	
	private final int CASES = 4;
	
	public int longueur() {
		return CASES;
	}
}
