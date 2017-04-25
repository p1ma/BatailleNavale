/**
 * 
 */
package storage.image;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import game.GameIModel;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 10, 2017
 */
public class BattleShipImageXML implements ImageDAO{

	private final String file = "DAO/Game.xml";
	private Document doc;

	public BattleShipImageXML() {
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
	public void setStartImage(GameIModel game) {
		NodeList nList = doc.getElementsByTagName(game.getGameName().replaceAll("\\s+", ""));
		Node node = nList.item(0);
		Element element = (Element) node;
		nList = element.getElementsByTagName("StartImage");
		
		node = nList.item(0);
		game.setStartImage(node.getTextContent());
	}

	@Override
	public void setTitleImage(GameIModel game) {
		NodeList nList = doc.getElementsByTagName(game.getGameName().replaceAll("\\s+", ""));
		Node node = nList.item(0);
		Element element = (Element) node;
		nList = element.getElementsByTagName("TitleImage");
		
		node = nList.item(0);
		game.setTitleImage(node.getTextContent());
	}

}
