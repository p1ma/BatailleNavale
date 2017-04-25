/**
 * 
 */
package graphics.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import game.GameIModel;
import storage.XMLDAOFactory;
import user.IUser;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 17, 2017
 */
public class LoadController implements ActionListener {

	/**
	 * The Game
	 */
	private final GameIModel game;
	
	/**
	 * JComboBox containing the IUser
	 */
	private final JComboBox<IUser> users;
	
	/**
	 * The current IUser
	 */
	private IUser user;

	/**
	 * 
	 * Constructs a LoadController with the given parameter(s)
	 * @param g the Game
	 * @param usersBox the List of IUser (in StartScreen)
	 */
	public LoadController(final GameIModel g, final JComboBox<IUser> usersBox ) {
		game = g;
		users = usersBox;
		user = null;
	}

	/**
	 * 
	 * Constructs a LoadController with the given parameter(s)
	 * @param g the Game
	 * @param u the IUser selected (in the JMenuBar)
	 */
	public LoadController(final GameIModel g, IUser u) {
		game = g;
		users = null;
		user = u;
	}

	/**
	 * Responds to a click
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// if there is a list of IUser then
		if (users != null) {
			int selected = users.getSelectedIndex();
			user = users.getItemAt(selected);
		} 
		game.setUser(user);
		
		// DAO calls to load the files
		File[] files = XMLDAOFactory.getInstance().getGameDAO().listGame(game, user);

		if (files.length > 0) {
			String[] filesList = new String[files.length];
			int i = 0;
			for(File file : files) {
				filesList[i]= file.getName();
				i++;
			}
			String fileName = (String)JOptionPane.showInputDialog(
					null,
					"Sélectionner la partie de votre choix : \n",
					"Charger une partie",
					JOptionPane.PLAIN_MESSAGE,
					null,
					filesList,
					filesList[0]);

			if ((fileName != null) && (fileName.length() > 0)) {
				// we load the Game
				XMLDAOFactory.getInstance().getGameDAO().load(game, fileName);
			}
		} else {
			// No parties to load
			JOptionPane.showMessageDialog(null,
					"Aucune partie disponible. \n " +
							"Lancez une nouvelle partie (Jouer).",
							"Erreur",
							JOptionPane.WARNING_MESSAGE);
		}
	}

}
