/**
 * 
 */
package game;

import java.util.List;

import element.Ship;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Era {

	private String identifiant;
	private List<Ship> fleet;
	
	public Era(String id, List<Ship> ships) {
		identifiant = id;
		fleet = ships;
	}
	
	public List<Ship> getFleet() {
		return fleet;
	}
	
	public String getIdentifiant() {
		return identifiant;
	}
}
