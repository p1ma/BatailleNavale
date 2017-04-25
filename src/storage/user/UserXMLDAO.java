/**
 * 
 */
package storage.user;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import user.IUser;
import user.User;

/**
 * @author JUNGES Pierre-Marie - M1 Informatique 2016/2017
 *
 * Apr 17, 2017
 */
public class UserXMLDAO implements IUserDAO{

	private final String file = "DAO/Users/users.xml";
	private Document doc;
	
	public UserXMLDAO() {
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
	public IUser[] getAllUsers() {
		NodeList nList = doc.getElementsByTagName("Users");
		Node node = nList.item(0);

		Element element = (Element) node;
		nList = element.getElementsByTagName("User");

		// array's containing IUsers
		IUser[] array = new IUser[nList.getLength()];
		
		// Variables used to store attribute's values
		String pseudo;
		for(int i = 0 ; i < nList.getLength() ; i++) {
			element = (Element)nList.item(i);
			pseudo  = element.getElementsByTagName("pseudo").item(0).getTextContent();
			array[i] = new User(pseudo);
		}		
		return array;
	}

}
