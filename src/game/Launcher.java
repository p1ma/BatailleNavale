/**
 * 
 */
package game;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import graphics.GameScreen;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 14, 2017
 */
public class Launcher {

	/**
	 * Launches our Game
	 * @param args not needed
	 */
	public static void main(String[] args) {
		try {
			/*
			 * If the Nimbus Look and Feel is available
			 * then Nimbus is loaded
			 */
			 for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			        if ("Nimbus".equals(info.getName())) {
			            UIManager.setLookAndFeel(info.getClassName());
			            break;
			        }
			    }
		} catch (ClassNotFoundException  |
				InstantiationException |
				IllegalAccessException |
				UnsupportedLookAndFeelException e) {
			System.err.println(e.getMessage());
		}
		/*
		 *	Launches GameScreen for the specific game : BattleShip 
		 */
		new GameScreen( new BattleShipGame() );
	}

}
