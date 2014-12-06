package dbms;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SelectColumn1Column2 {
	public String selectcolumn(String tablename,String path,String values,String columName,String requiredColumn,String input1){
		String result="";	
		try {
			String input=input1.toLowerCase();
			try{
				
				values=values.replaceAll("'", "");}
				catch(Exception e){
					
				}
			//String input = "SELECT * ";
			String[] RequieredColumn=new schema().spliter(requiredColumn);
			String[] attributes=new schema().getColumnNames(tablename);
		
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		//	String attribute = "", value = "";
		//	String[] attributes = { "firstname", "lastname", "nickname",
		//			"salary" };
			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

//			System.out.println("Root element :"
//					+ doc.getDocumentElement().getNodeName());
			if (!input.contains("where")) {
				NodeList nList = doc.getElementsByTagName("row");

				

				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;

						for (int i = 0; i < attributes.length; i++) {
							for(int j=0;j<RequieredColumn.length;j++)
							if (attributes[i]
									.equals(RequieredColumn[j]) )/* GIVEN COLUMN */
									{
									
																	 
//								System.out.print(eElement
//										.getElementsByTagName(attributes[i])
//										.item(0).getTextContent()
//										+ "  ");

								result=result+eElement.getElementsByTagName(attributes[i]).item(0).getTextContent()+ "  ";
									}
						}
						System.out.println();
						result=result+ "\n";
					}
				}

			} else if (input.contains("where")) {
				NodeList nList = doc.getElementsByTagName("row");

				
				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);
					Element eElement = (Element) nNode;
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						if (input.contains("=")) {

							if (eElement.getElementsByTagName(columName/*
																		 * given
																		 * column
																		 */)
									.item(0).getTextContent()
									.equals(values/* given column */)) {
								{

									for (int i = 0; i < attributes.length; i++) {
										for(int j=0;j<RequieredColumn.length;j++){
										if (attributes[i]
												.equals(RequieredColumn[j]/* GIVEN COLUMN */))
												
										{				
//											System.out.print(eElement
//													.getElementsByTagName(
//															attributes[i])
//													.item(0).getTextContent()
//													+ "  ");

											result=result+eElement.getElementsByTagName(attributes[i]).item(0).getTextContent()+ "  ";
										}
									}
									}
									
									}
								System.out.println();
								result=result+"\n";
									

								}
							}

						}
				}
					for (int temp = 0; temp < nList.getLength(); temp++) {

						Node nNode = nList.item(temp);
						Element eElement = (Element) nNode;

						if (input.contains(">")) { /* column1 *//* column 2 */
							
							if (Integer.parseInt(eElement
									.getElementsByTagName(columName).item(0)
									.getTextContent().toString()) > Integer.parseInt(values)) {
								for (int i = 0; i < attributes.length; i++)
								{
									for (int j=0;j<RequieredColumn.length;j++){
									if (attributes[i].equals(RequieredColumn[j]))
																		 
										{
										
//										System.out.print(eElement
//												.getElementsByTagName(
//														attributes[i]).item(0)
//												.getTextContent()
//												+ "  ");
										result=result+eElement.getElementsByTagName(attributes[i]).item(0).getTextContent()+ "  ";
									}
									}
								}
								System.out.println();
								result=result+"\n";
							}

						}
					}
						for (int temp = 0; temp < nList.getLength(); temp++) {

							Node nNode = nList.item(temp);
							Element eElement = (Element) nNode;

						if (input.contains("<")) { /* column1 *//* column 2 */
							if (Integer.parseInt(eElement
									.getElementsByTagName(columName).item(0)
									.getTextContent().toString()) < Integer.parseInt(values)/* VALUE */) {
								for (int i = 0; i < attributes.length; i++) {
									for (int j=0;j<RequieredColumn.length;j++){
									if (attributes[i].equals(RequieredColumn[j]))/*
																		 * GIVEN
																		 * COLUMN
																		 */
											 {
										System.out.print(eElement
												.getElementsByTagName(
														attributes[i]).item(0)
												.getTextContent()
												+ "  ");
									}
									}
								}
//								System.out.println();
								result=result+"\n";

							}

						
						}
						}
		
			
					
				}
			

		} catch (Exception e) {

		}
		String empty=result.replaceAll("  ", "");
		empty.replaceAll("\n", "");
		if(empty.equals("")){
			return "no row exists with this criteria";
		}
		char []array=result.toCharArray();
		char[] array2=new char[array.length-3];
		for (int i = 0; i < array2.length; i++) {
			array2[i]=array[i];
		}
		result=String.copyValueOf(array2);
		return result;
	}
}
