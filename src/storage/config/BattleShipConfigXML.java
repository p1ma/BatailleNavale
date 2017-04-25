/**
 * 
 */
package storage.config;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import element.Ship;
import game.GameIModel;
import game.parameter.Era;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Mar 13, 2017
 */
public class BattleShipConfigXML implements IConfigDAO {

	private final String file = "DAO/Game.xml";
	private Document doc;

	public BattleShipConfigXML() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
		} catch (SAXException | 
				ParserConfigurationException |
				IOException e) {
			System.err.println( e.getMessage() );
		}
	}

	@Override
	public Era[] getAllEras() {
		NodeList nList = doc.getElementsByTagName("Eras");
		Node node = nList.item(0);


		Element element = (Element) node;
		nList = element.getElementsByTagName("Era");

		// array's containing levels
		Era[] array = new Era[nList.getLength()];
		String eraName = null;
		Era era = null;
		for(int i = 0 ; i < nList.getLength() ; i++) {
			era = new Era();
			element = (Element)nList.item(i);
			eraName = element.getElementsByTagName("Name").item(0).getTextContent();

			// Set Era's id
			era.setIdentifiant(eraName);

			// Set Era's images
			String background = element.getElementsByTagName("Background").item(0).getTextContent();
			String touched = element.getElementsByTagName("TouchedImage").item(0).getTextContent();
			String missed = element.getElementsByTagName("MissedImage").item(0).getTextContent();

			era.loadImage(background, touched, missed);

			// Instanciates Ships
			NodeList ships = element.getElementsByTagName("Ship");
			Element el = null;
			Point start = new Point(0, 0);
			Ship ship = null;
			for(int j = 0 ; j < ships.getLength() ; j++) {
				el = (Element) ships.item(j);
				String name = el.getElementsByTagName("ShipName").item(0).getTextContent();

				// Width and Height
				int w, h;
				w = Integer.parseInt( el.getElementsByTagName("Width").item(0).getTextContent() );
				h = Integer.parseInt( el.getElementsByTagName("Height").item(0).getTextContent() );

				// Image
				String pathImage = el.getElementsByTagName("Image").item(0).getTextContent();

				// We can create the Ship now
				ship = new Ship(name, start, w, h, pathImage);
				era.addShip(ship);
				start.setLocation(0, start.getY() + 1);
			}
			array[i] = era;
		}	
		return array;
	}

	@Override
	public String[] getAllLevels() {
		NodeList nList = doc.getElementsByTagName("Levels");
		Node node = nList.item(0);

		Element element = (Element) node;
		nList = element.getElementsByTagName("Level");

		// array's containing levels
		String[] array = new String[nList.getLength()];
		for(int i = 0 ; i < nList.getLength() ; i++) {
			array[i] = nList.item(i).getTextContent();
		}		
		return array;
	}

	@Override
	public Dimension[] getAllDimensions() {
		NodeList nList = doc.getElementsByTagName("Dimensions");
		Node node = nList.item(0);

		Element element = (Element) node;
		nList = element.getElementsByTagName("Dimension");

		// array's containing levels
		Dimension[] array = new Dimension[nList.getLength()];
		int d = 0;
		for(int i = 0 ; i < nList.getLength() ; i++) {
			d = Integer.parseInt(nList.item(i).getTextContent());
			array[i] = new Dimension(d,d);
		}		
		return array;
	}

	@Override
	public int[] getAllAmmunitons() {
		NodeList nList = doc.getElementsByTagName("Shots");
		Node node = nList.item(0);

		Element element = (Element) node;
		nList = element.getElementsByTagName("MaxShot");

		// array's containing levels
		int[] array = new int[nList.getLength()];
		int d = 0;
		for(int i = 0 ; i < nList.getLength() ; i++) {
			d = Integer.parseInt(nList.item(i).getTextContent());
			array[i] = d;
		}		
		return array;
	}

	@Override
	public int getNbParams(GameIModel game) {
		NodeList nList = doc.getElementsByTagName(game.getGameName().replaceAll("\\s+", ""));
		Element element = (Element) nList.item(0);
		NodeList child = element.getChildNodes();
		int level = 0;
		for(int i = 0 ; i < child.getLength() ; i++) {
			int type = child.item(i).getNodeType();
			if ( type == Node.ELEMENT_NODE ) {
				level++;
			}
		}
		
		// All except images
		return (level - 2);
	}

}
