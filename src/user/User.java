/**
 * 
 */
package user;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 13, 2017
 */
public class User implements IUser{

	/**
	 * User's pseudo
	 */
	private String pseudo;
	
	/**
	 * 
	 * Constructs a User with the given parameter(s)
	 * @param name the wished pseudonym
	 */
	public User(String name) {
		pseudo = name;
	}
	
	/**
	 * Returns the IUser's name
	 * @return the name
	 */
	@Override
	public String getUserName() {
		return pseudo;
	}
	
	/**
	 * Returns the IUser's name
	 */
	public String toString() {
		return pseudo;
	}

}
