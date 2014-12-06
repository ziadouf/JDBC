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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
public class Update {
 
	public static final String Con_Update = "Row/s Updated";
	
	public String update(String path,String att, String value, String inp ,String newVal ,String [] Original) {
	
		schema split = new schema();
		try {
		File filepath = new File(path);
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);
		
		String temp = newVal.replaceAll("\'", "");
	    String [] input = split.spliter(inp);
	    String [] Mine = split.spliter(temp);
	    String [] newValue = new String[Original.length];
	    
	    
	    for (int y=0; y<input.length; y++){
		    for (int i=0; i<Original.length; i++){
				if (Original[i].compareToIgnoreCase((input[y]))==0){
					newValue[i]= Mine[y];
					break;
				}
		    }
	    }
	    
//	    for (int l=0; l<Original.length; l++){
//	    	if (newValue[l]==null)
//	    		newValue[l]="null";
//	    }
	  
		int position =0, size =0;
		NodeList staff = doc.getElementsByTagName("row");
		for (int j=0; j<staff.getLength(); j++){
			
			Node row = staff.item(j);
			size = staff.getLength();
			NodeList list = row.getChildNodes();
			
			for (int i =0; i<list.getLength(); i++) {
				
				Node node = list.item(i);
                if (att.equalsIgnoreCase((node.getNodeName())) && value.equals(node.getTextContent())){
                	position = j;
                }
			}
		}
		Node n = staff.item(position);
		
		for (int j=0; j<newValue.length; j++){
			NodeList l = n.getChildNodes();
			for (int i=0; i<l.getLength(); i++){
				Node node = l.item(i);

				if (newValue[j]!=null && Original[j].equalsIgnoreCase((node.getNodeName()))){
					node.setTextContent(newValue[j]);
 
				}
				
			}
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(filepath);
		transformer.transform(source, result);
 
		System.out.println("Done");
 
	   } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	   } catch (TransformerException tfe) {
		tfe.printStackTrace();
	   } catch (IOException ioe) {
		ioe.printStackTrace();
	   } catch (SAXException sae) {
		sae.printStackTrace();
	   }
		return Con_Update;
	}
	
	
}
