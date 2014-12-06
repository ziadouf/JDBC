package dbms;
import java.io.File;
import java.io.IOException;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Delete {
     	public static final String NOT_MATCH_CRITERIA = "no row exists with this criteria";
		public static final String Con_Delete = "Row/s deleted";		
		public String delete(String path , String att , String value){
			if(value!=null){
			value = value.replaceAll("\'", "");
			}
			boolean flag = false;
		    try {
			File filepath = new File(path);
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			
			Element root = doc.getDocumentElement();
			NodeList staff = doc.getElementsByTagName("row");
			
			for (int j=0; j<staff.getLength(); j++){
				Node row = staff.item(j);
				
				if (att!=null){
					NodeList list = row.getChildNodes();
					for (int i =0; i<list.getLength(); i++) {
						
						Node node = list.item(i);
						
		                if (att.equalsIgnoreCase((node.getNodeName())) && value.equals(node.getTextContent())){
		                	flag = true ;
		                	root.removeChild(row);
		                	j--;//back step index
		                	
		                }
					}
				}
				else {
					root.removeChild(row);
					j--;//back step index
					flag=true;
				}
				
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(filepath);
			transformer.transform(source, result);
			
		   } catch (ParserConfigurationException pce) {
				pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
		    if (flag== true)
		    	return Con_Delete ;
		    else 
		    	return NOT_MATCH_CRITERIA;
		}
}
