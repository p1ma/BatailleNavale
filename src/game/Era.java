package game;

import java.util.List;

import element.Ship;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class Era {

	/**
	 * identifiant of the Era
	 */
	private String identifiant;
	
	/**
	 * List of the corresponding Ships at the chosen Era
	 */
	private List<Ship> fleet;
	
	
	
	
	
	/**
	 * Constructor
	 * @param id : String
	 * @param ships : List<Ship>
	 */
	public Era(String id, List<Ship> ships) {
		identifiant = id;
		fleet = ships;
	}
	
	
	
	
	
	/**
	 * Returns the list of Ships of the Era
	 * @return List<Ship>
	 */
	public List<Ship> getFleet() {
		return fleet;
	}
	
	/**
	 * Returns the identifiant of the Era
	 * @return String
	 */
	public String getIdentifiant() {
		return identifiant;
	}
}
