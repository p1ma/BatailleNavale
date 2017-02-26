/**
 * 
 */
package bateaux;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Feb 24, 2017
 */
public abstract class Bateau3Cases implements Bateau  {

	private final int CASES = 3;
	
	public int longueur() {
		return CASES;
	}
}
