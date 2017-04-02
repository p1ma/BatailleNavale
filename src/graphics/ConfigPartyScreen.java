package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import element.Ship;
import game.Configuration;
import game.Game;
import graphics.listener.BoardController;

public class ConfigPartyScreen extends JPanel {

	/**
	 * Current Game
	 */
	private Game game;

	/**
	 * Background of the app
	 */
	private final Image background = TextureFactory.getInstance().getBattleshipBackground();





	/**
	 * Constructor
	 * @param g : Game
	 * @param controller : BoardController
	 */
	public ConfigPartyScreen(Game g, BoardController controller) {
		super();
		game = g;

		// Layout
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		this.initLeftPart();
		this.add( Box.createRigidArea( new Dimension(GameScreen.G_UNIT, 0) ) );
		this.initRightPart(controller);

		// Size
		this.setPreferredSize(
				new Dimension((Configuration.WIDTH * GameScreen.G_UNIT) *2 + GameScreen.G_UNIT, 
						Configuration.HEIGHT * GameScreen.G_UNIT + GameScreen.G_UNIT));
	}

	/**
	 * part who contain some parametres of the party
	 */
	private void initLeftPart() {
		JPanel leftPanel = new JPanel();
		int width = GameScreen.G_UNIT * game.getWidth();
		int height = GameScreen.G_UNIT * game.getHeight();
		leftPanel.setPreferredSize(new Dimension(width, height));
		leftPanel.setBackground(Color.WHITE);

		// title
		JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(width, 50));
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.setBackground(Color.WHITE);

		JLabel title = new JLabel("Configuration new party : ");
		title.setFont(title.getFont().deriveFont(20f));

		p1.add(title);

		// era
		JPanel p2 = new JPanel();
		p2.setPreferredSize(new Dimension(width, 30));
		p2.setBackground(Color.WHITE);

		JLabel labelEra = new JLabel("Era : ");
		Object[] elements = new Object[]{"Element 1", "Element 2", "Element 3", "Element 4", "Element 5"};
		JComboBox comboBoxEra = new JComboBox(elements);

		p2.add(labelEra);
		p2.add(comboBoxEra);

		// config ok
		JPanel p3 = new JPanel();
		p3.setPreferredSize(new Dimension(width, 30));
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.setBackground(Color.WHITE);

		JButton buttonOk = new JButton("Validate configuration");
		buttonOk.addActionListener(e -> {
			this.game.setPartyScreen();
		});

		p3.add(buttonOk);



		leftPanel.add(p1);
		leftPanel.add(p2);
		leftPanel.add(p3);

		this.add(leftPanel);
	}

	/**
	 * part who contain the grid for positionning the ships
	 * @param controller : BoardController
	 */
	private void initRightPart(BoardController controller) {
		JPanel rightPanel = new rightPanel(game, controller);

		this.add(rightPanel);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(this.background, 
				0, 0, 
				(int)(this.background.getWidth(null)/1.5), (int)(this.background.getHeight(null)/2), 
				null);
	}




	
	private class rightPanel extends JPanel {

		/**
		 * Current Game
		 */
		private Game game;

		/**
		 * Background of the board
		 */
		private final Image background = TextureFactory.getInstance().getBoardBackground();





		/**
		 * Constructor
		 * @param g : Game
		 * @param controller : BoardController
		 */
		public rightPanel(Game g, BoardController controller) {
			super();
			game = g;

			List<Ship> ships = new LinkedList<Ship>();
			Image img = null;
			try {
				img = ImageIO.read(new File("textures/ship.png"));
			} catch (IOException e) {
				System.err.println("Error loading image");
				System.exit(0);
			}
			ships.add(new Ship(new Point(0,0), 5, 1, img));
			ships.add(new Ship(new Point(0,1), 4, 1, img));
			ships.add(new Ship(new Point(0,2), 3, 1, img));
			ships.add(new Ship(new Point(0,3), 3, 1, img));
			ships.add(new Ship(new Point(0,4), 2, 1, img));

			this.game.setHumanFleet(ships);

			this.addMouseListener(controller);
			this.addMouseMotionListener(controller);

			// SIZE
			this.setPreferredSize(
					new Dimension(GameScreen.G_UNIT * game.getWidth(), GameScreen.G_UNIT * game.getHeight()));	
		}

		
		
		
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(background, 0, 0, 
					game.getWidth() * GameScreen.G_UNIT, game.getHeight() * GameScreen.G_UNIT, null);

			this.drawBoard(g);
			this.drawShips(g);
		}

		/**
		 * Draws the game board
		 * @param g : Graphics
		 */
		private void drawBoard(Graphics g) {
			int lgr = game.getWidth() * GameScreen.G_UNIT;
			int lrg = game.getHeight() * GameScreen.G_UNIT;
			Point axe = new Point(0,0);

			g.setColor(Color.WHITE);
			g.drawRect(0, 0, lgr, lrg);
			for(int i = 0 ; i < game.getWidth() ; i++) {
				for(int j = 0 ; j < game.getHeight() ; j++) {
					g.drawRect(axe.x + (j * GameScreen.G_UNIT), axe.y, GameScreen.G_UNIT, GameScreen.G_UNIT);
				}
				axe.y = axe.y + GameScreen.G_UNIT;
			}
		}

		/**
		 * Draws the ships of the human
		 * @param g : Graphics
		 */
		private void drawShips(Graphics g) {
			List<Ship> fleet = game.getHumanFleet();
			Graphics2D g2d = (Graphics2D)g;

			for( Ship s : fleet ) {
				if (s.getOrientation() == 0) {
					g2d.drawImage(s.getImage(), 
							s.getX() * GameScreen.G_UNIT,
							s.getY() * GameScreen.G_UNIT,
							s.getWidth() * GameScreen.G_UNIT,
							s.getHeight() * GameScreen.G_UNIT,
							this);
				} else {

					BufferedImage bi = (BufferedImage) s.getImage();
					AffineTransform tx = new AffineTransform();
					tx.rotate(Math.toRadians(90), 
							bi.getWidth()/13, 
							bi.getHeight()/2
							);
					AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					bi = op.filter(bi, null);
					g2d.drawImage(bi, 
							s.getX() * GameScreen.G_UNIT,
							s.getY() * GameScreen.G_UNIT,
							s.getWidth() * GameScreen.G_UNIT,
							s.getHeight() * GameScreen.G_UNIT,
							this);

				}
			}
		}
	}

}
