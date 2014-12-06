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
import org.xml.sax.SAXException;
public class Insert {
	
		public static final String Con_insert = "insertion Complete";
		public String insert(String path, String inp ,String newVal ,String [] Original){
			schema split = new schema();
			try {
			File filepath = new File(path);
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			String newvalue = newVal.replaceAll("\'", "");
			String [] input = split.spliter(inp);
		    String [] Mine = split.spliter(newvalue);
		    String [] newValue = new String[Original.length];
		    
		    if (!inp.equals("all")){
		   
			    for (int y=0; y<input.length; y++){
				    for (int i=0; i<Original.length; i++){
						if (Original[i].compareToIgnoreCase(input[y])==0){
							newValue[i]= Mine[y];
							break;
						}
				    }
				    
			    }
			    for (int l=0; l<Original.length; l++){
			    	if (newValue[l]==null)
			    		newValue[l]="null";
			    }
		    }
		    else {
		    	for (int i=0; i<Original.length; i++){
		    		newValue[i] = Mine[i];
		    	}
		    }
		   
		    
		    Element root = doc.getDocumentElement();
		    Element row = doc.createElement("row");
		    root.appendChild(row);
		   
		    for (int i=0; i<Original.length; i++){
		    	Element add = doc.createElement(Original[i]);
		    	if (newValue[i]==null){
		    		add.appendChild(doc.createTextNode("null"));
		    	}
		    	else{
			    	add.appendChild(doc.createTextNode(newValue[i]));
		    	}
		    	row.appendChild(add);
		    }
		       
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
			return Con_insert;
	}
}
