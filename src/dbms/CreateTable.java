package dbms;


import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateTable {

	public CreateTable(String TableName , String[]attributes , String[]type , String path) {
		// TODO Auto-generated constructor stub
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(TableName);// table name
																// (taken as
																// parameter)
			doc.appendChild(rootElement);

			// staff elements
			Element staff = doc.createElement("row");
			rootElement.appendChild(staff);

		
			for (int i = 0; i < attributes.length; i++) {
				Element firstname = doc.createElement(attributes[i]);
				firstname.setAttribute("type", type[i]);
				// firstname.appendChild(doc.createTextNode(values[i]));
				staff.appendChild(firstname);

			}

			String name = TableName;// table name taken as parameter
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));// taken as parameter
			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	 {

	 }	

}
