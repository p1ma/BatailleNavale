/**
 * 
 */
package graphics.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import game.GameIModel;
import user.IUser;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 29, 2017
 */
public class StartController implements ActionListener{

	/**
	 * The Game
	 */
	private final GameIModel game;
	
	/**
	 * The List of IUser
	 */
	private final JComboBox<IUser> users;
	
	/**
	 * The current IUser
	 */
	private IUser user;

	/**
	 * 
	 * Constructs a StartController with the given parameter(s)
	 * @param g the Game
	 * @param usersBox the List of IUser( used in StartScreen )
	 */
	public StartController(final GameIModel g, final JComboBox<IUser> usersBox ) {
		game = g;
		users = usersBox;
		user = null;
	}

	/**
	 * 
	 * Constructs a StartController with the given parameter(s)
	 * @param g the Game
	 * @param usr IUser currently connected( used when the Game has already begun )
	 */
	public StartController(final GameIModel g, IUser usr) {
		game = g;
		users = null;
		user = usr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// if there is no user, then nothing happen

		if ( (users != null) || (user != null)) {

			/*
			 * If a List of IUser is available,
			 * then we select the IUser
			 */
			if (users != null) {
				if (users.getItemCount() > 0) {
					int selected = users.getSelectedIndex();
					user = users.getItemAt(selected);
					
					game.setUser( user );
					game.setParametersScreen();
				}
			} else {
				/*
				 * If no List is available, then we use
				 * the current IUser user
				 */
				game.setUser( user );
				game.setParametersScreen();
			}
		}
	}

}
