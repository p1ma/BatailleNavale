/**
 * 
 */
package storage.game;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import element.GameElement;
import element.HitBox;
import element.MissedBox;
import element.Ship;
import game.GameIModel;
import game.parameter.BattleShipConfiguration;
import game.parameter.Era;
import game.parameter.IConfiguration;
import player.IPlayer;
import user.IUser;
import user.User;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 9, 2017
 */
public class BattleShipGameXML implements GameDAO{

	private final String path = "DAO/Games/";

	public BattleShipGameXML() {

		// If the directory does not exist, we create it
		File file = new File(path);

		if ( !file.exists() ) {
			file.mkdir();
		}
	}

	/**
	 * Saves a Game from scratch
	 */
	@Override
	public void save(GameIModel game) {

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// root element
			Document doc = docBuilder.newDocument();
			
			// removes spaces (forbidden in xml)
			String gameName = game.getGameName().replaceAll("\\s+","");
			Element rootElement = doc.createElement(gameName);
			doc.appendChild(rootElement);

			// adds Date
			Element date = doc.createElement("StartDate");
			date.appendChild(doc.createTextNode(game.getDate()));
			rootElement.appendChild(date);
			
			// adds Configuration's infos
			Element configuration = doc.createElement("Configuration");
			rootElement.appendChild(configuration);

			Element dimension = doc.createElement("Width");
			dimension.appendChild(doc.createTextNode(game.getWidth() + ""));
			configuration.appendChild(dimension);

			dimension = doc.createElement("Height");
			dimension.appendChild(doc.createTextNode(game.getHeight() + ""));
			configuration.appendChild(dimension);

			Element difficulty = doc.createElement("Difficulty");
			difficulty.appendChild(doc.createTextNode(game.getDifficulty()));
			configuration.appendChild(difficulty);
			
			Element maxShot = doc.createElement("MaxShot");
			maxShot.appendChild(doc.createTextNode(game.getConfig().getLimit() + ""));
			configuration.appendChild(maxShot);

			Element eraXML = doc.createElement("Era");
			configuration.appendChild(eraXML);

			Element nameXML = doc.createElement("Name");
			nameXML.appendChild(doc.createTextNode(game.getEra().toString()));
			eraXML.appendChild(nameXML);
			
			Element backgroundXML = doc.createElement("Background");
			backgroundXML.appendChild(doc.createTextNode(game.getEra().getBackgroundPath()));
			eraXML.appendChild(backgroundXML);
			
			Element missedXML = doc.createElement("MissedImage");
			missedXML.appendChild(doc.createTextNode(game.getEra().getMissedPath()));
			eraXML.appendChild(missedXML);
			
			Element touchedXML = doc.createElement("TouchedImage");
			touchedXML.appendChild(doc.createTextNode(game.getEra().getTouchedPath()));
			eraXML.appendChild(touchedXML);

			Element shipsXML = doc.createElement("Ships");
			Era era = game.getEra();
			Element shipXML;
			for( Ship ship : era.getFleet() ) {
				shipXML = shipToXML(ship, doc);			

				// Adds the Ship
				shipsXML.appendChild(shipXML);
			}
			eraXML.appendChild(shipsXML);

			// Who's turn
			Element turnXML = doc.createElement("HumanTurn");
			boolean turn = game.isPlayerTurn();
			turnXML.appendChild(doc.createTextNode(turn + ""));
			configuration.appendChild(turnXML);

			// Now we need to get the Players informations
			Element playersXML = doc.createElement("Players");
			for( IPlayer player : game.getPlayers() ) {
				Element playerXML = doc.createElement("Player");

				Element playerNameXML = doc.createElement("PlayerName");
				playerNameXML.appendChild(doc.createTextNode(player.getName()));

				playerXML.appendChild(playerNameXML);

				shipsXML = doc.createElement("Ships");
				for( Ship s : player.getBoardElements() ) {
					shipXML = shipToXML(s, doc);

					// Adds the Ship
					shipsXML.appendChild(shipXML);
				}
				playerXML.appendChild(shipsXML);

				// Gets Missed and Hit shot now
				List<GameElement> radar = player.getRadarElements();

				Element radarXML = doc.createElement("Radar");
				for( GameElement ge : radar ) {
					Element Box = doc.createElement("Box");
					Point p = ge.getPosition();
					Element posX = doc.createElement("PosX");
					posX.appendChild(doc.createTextNode((int)p.getX() + ""));

					Element posY = doc.createElement("PosY");
					posY.appendChild(doc.createTextNode((int)p.getY() + ""));

					Element missedOrHit = doc.createElement("Type");
					boolean mOH = ge.isStrategicallyUseful();
					if ( mOH ) {
						missedOrHit.appendChild(doc.createTextNode("HIT"));
					} else {
						missedOrHit.appendChild(doc.createTextNode("MISSED"));
					}

					Box.appendChild(posX);
					Box.appendChild(posY);
					Box.appendChild(missedOrHit);

					radarXML.appendChild(Box);
				}
				playerXML.appendChild(radarXML);
				playersXML.appendChild(playerXML);
			}
			rootElement.appendChild(playersXML);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			File file = new File(path.concat( formatName( game ) ));
			StreamResult result = new StreamResult(file);

			transformer.transform(source, result);

		} catch (ParserConfigurationException | TransformerException e) {
			System.err.println("ERREUR SAUVEGARDER GAME");
			System.exit(1);
		}

	}

	/**
	 * Ship.java -> XML representation of a Ship
	 * @param ship the Ship to trad
	 * @param doc the global Document
	 * @return the Ship tag
	 */
	private Element shipToXML(Ship ship, Document doc) {
		Element shipXML = doc.createElement("Ship");

		// Gets Ship's name
		Element shipNameXML = doc.createElement("ShipName");
		shipNameXML.appendChild(doc.createTextNode(ship.getIdentifiant()));

		// Adds name
		shipXML.appendChild(shipNameXML);

		// Gets Ship's position
		Point p = ship.getPosition();

		// X coordinate
		Element shipXXML = doc.createElement("ShipXPosition");
		shipXXML.appendChild(doc.createTextNode((int)p.getX() + ""));

		// Y coordinate
		Element shipYXML = doc.createElement("ShipYPosition");
		shipYXML.appendChild(doc.createTextNode((int)p.getY() + ""));

		// Adds position
		shipXML.appendChild(shipXXML);
		shipXML.appendChild(shipYXML);

		//Gets Ship's orientation
		Element orientationXML = doc.createElement("ShipOrientation");
		orientationXML.appendChild(doc.createTextNode(ship.getOrientation() + ""));

		// Adds orientation
		shipXML.appendChild(orientationXML);

		//Gets Ship's width and height
		Element widthXML = doc.createElement("ShipWidth");
		widthXML.appendChild(doc.createTextNode(ship.getWidth() + ""));

		Element heightXML = doc.createElement("ShipHeight");
		heightXML.appendChild(doc.createTextNode(ship.getHeight() + ""));

		// Adds width and height
		shipXML.appendChild(widthXML);
		shipXML.appendChild(heightXML);

		// Get Ship's image path
		Element imageXML = doc.createElement("ShipImage");
		imageXML.appendChild(doc.createTextNode(ship.getPath()));

		// Adds Ship's image path
		shipXML.appendChild(imageXML);

		return shipXML;
	}

	/**
	 * Trads a XML element el to a Ship.java
	 * @param el the XML element
	 * @return a Ship
	 */
	private Ship XMLtoShip(Element el) {
		Ship ship = null;

		String name = el.getElementsByTagName("ShipName").item(0).getTextContent();

		// Width and Height
		int w, h;
		w = Integer.parseInt( el.getElementsByTagName("ShipWidth").item(0).getTextContent() );
		h = Integer.parseInt( el.getElementsByTagName("ShipHeight").item(0).getTextContent() );

		// Image
		String pathImage = el.getElementsByTagName("ShipImage").item(0).getTextContent();

		// Orientation
		int o = Integer.parseInt( el.getElementsByTagName("ShipOrientation").item(0).getTextContent() );

		// Position
		int x, y;
		x =  Integer.parseInt( el.getElementsByTagName("ShipXPosition").item(0).getTextContent() );
		y =  Integer.parseInt( el.getElementsByTagName("ShipYPosition").item(0).getTextContent() );

		// We can create the Ship now
		Point pos = new Point(x, y);
		ship = new Ship(name, pos, w, h, o, pathImage);

		return ship;
	}

	/**
	 * Load a GameIModel game with as file's name fileName.
	 * It will fill game attributes and then call game.loadOver()
	 */
	@Override
	public void load(GameIModel game, String fileName) {
		String nameFile = path.concat(fileName);
		File file = new File(nameFile);

		// If the file exists then we load the Game
		if ( file.exists() ) {
			game.reset();
			try {

				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder;
				dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(file);
				doc.getDocumentElement().normalize();

				// loads date
				NodeList nList = doc.getElementsByTagName("StartDate");
				
				String stringDate = nList.item(0).getTextContent();
				
				// format date
				DateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
				Date date = format.parse(stringDate);
				game.setDate(date);
				
				nList = doc.getElementsByTagName("Configuration");
				Node node = nList.item(0);


				Element element = (Element) node;
				int width = Integer.parseInt(element.getElementsByTagName("Width").
						item(0).
						getTextContent());

				int height = Integer.parseInt(element.getElementsByTagName("Height").
						item(0).
						getTextContent());

				Dimension dimension = new Dimension(width, height);

				String difficulty = element.getElementsByTagName("Difficulty").
						item(0).
						getTextContent();

				int maxShot = Integer.parseInt(element.getElementsByTagName("MaxShot").
						item(0).
						getTextContent());
				
				boolean turn = Boolean.parseBoolean(element.getElementsByTagName("HumanTurn").
						item(0).
						getTextContent());

				game.setTurn(turn);

				element = (Element) node;
				nList = element.getElementsByTagName("Era");

				Era era = new Era();
				element = (Element)nList.item(0);
				String eraName = element.getElementsByTagName("Name").item(0).getTextContent();
				
				String backgroundPath = element.getElementsByTagName("Background").item(0).getTextContent();
				String touchedPath = element.getElementsByTagName("TouchedImage").item(0).getTextContent();
				String missedPath = element.getElementsByTagName("MissedImage").item(0).getTextContent();			

				era.loadImage(backgroundPath, touchedPath, missedPath);
				
				// Set Era's id
				era.setIdentifiant(eraName);

				// Instanciates Ships
				NodeList ships = element.getElementsByTagName("Ship");
				Element el = null;
				Ship ship = null;
				for(int j = 0 ; j < ships.getLength() ; j++) {
					el = (Element) ships.item(j);
					ship = XMLtoShip(el);
					era.addShip(ship);
				}

				// We can instanciate a Configuration
				IConfiguration config = new BattleShipConfiguration(dimension, era, difficulty, maxShot);

				// Game's settings are loaded
				game.loadGameConfiguration(config);

				// Load PLayers infos
				nList = doc.getElementsByTagName("Player");

				for(int j = 0 ; j < nList.getLength() ; j++) {
					node = nList.item(j);
					element = (Element) node;
					IPlayer player = null;
					String name = element.getElementsByTagName("PlayerName").item(0).getTextContent();
					
					if( !name.equals("COMPUTER") ) {
						player = game.getHuman();
						player.setName(name);
						game.setUser( new User(name) );
					} else {
						player = game.getComputer();
						player.setName(name);
					}

					// Instanciates Ships
					ships = element.getElementsByTagName("Ship");
					el = null;
					ship = null;
					List<Ship> fleet = new LinkedList<Ship>();
					for(int i = 0 ; i < ships.getLength() ; i++) {
						el = (Element) ships.item(i);
						ship = XMLtoShip(el);
						fleet.add(ship);
					}
					player.putBoardElements(fleet);

					// Gets Radar's infos
					List<GameElement> radar = new LinkedList<GameElement>();

					NodeList radarXML =  element.getElementsByTagName("Box");
					el = null;
					GameElement box = null;
					for(int i = 0 ; i < radarXML.getLength() ; i++) {
						el = (Element) radarXML.item(i);
						String type = el.getElementsByTagName("Type").item(0).getTextContent();

						// Position
						int x,y;
						x =  Integer.parseInt( el.getElementsByTagName("PosX").item(0).getTextContent() );
						y =  Integer.parseInt( el.getElementsByTagName("PosY").item(0).getTextContent() );
						if ( type.equals("HIT") ) {
							box = new HitBox(new Point(x,y), game);
						} else {
							box = new MissedBox(new Point(x,y), game);
						}
						radar.add(box);
					}
					player.setRadarElements(radar);
				}

				game.loadOver();

			} catch (ParserConfigurationException | 
					SAXException | 
					IOException | DOMException | ParseException e) {
				System.err.println("ERREUR LOAD");
				System.exit(1);
			}
		}
	}

	/**
	 * Returns all the games of the IUser user
	 */
	public File[] listGame(GameIModel game, IUser user) {
		final String gameName = game.getGameName();
		final String userName = user.getUserName();
		
		File dir = new File(path);
		
		File[] files = dir.listFiles( new FilenameFilter() {
			public boolean accept(File dir, String name) {
				boolean ok = name.startsWith(gameName) &&
						name.contains(userName);	
		        return ok;
		    }
		});
		
		return files;
	}
	
	/**
	 * Once the Game is over, there is no
	 * interest to keep it in memory,
	 * so we delete it
	 */
	@Override
	public void remove(GameIModel game) {
		File file = new File(path.concat( formatName( game ) ));
		file.delete();
	}

	private String formatName(GameIModel game) {
		return game.getGameName() + " " + game.getUser() + " " + game.getDate();
	}

}
