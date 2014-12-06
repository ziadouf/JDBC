package dbms;
import java.io.File;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SelectAll {
	private String attribute;
	private String value;
	private String fileName;
	// private String input="SELECT *";
	

	public String selectAll(String tablename,String path,String values,String columName,String input1,String order) {// select all (file
																// path,string
																// input,string
																// attribute
																// ,string
		String result="";												// value,string
																// array of
																// attributes,table
																// name)
		try {
			
			String input=input1.toLowerCase(); 
			
			
			try{
			values=values.replaceAll("'", "");}
			catch(Exception e){
				
			}
			String[] attributes=new schema().getColumnNames(tablename);
		//	String input = "SELECT * ORDERED BY DESC";

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
			if (input.contains("select")  && !input.contains("where")) {
				if (!input.contains("order by")) {
					NodeList nList = doc.getElementsByTagName("row");

					

					

					for (int temp = 0; temp < nList.getLength(); temp++) {

						Node nNode = nList.item(temp);

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {

							Element eElement = (Element) nNode;

							{
								for (int i = 0; i < attributes.length; i++) {
//									System.out
//											.print(eElement
//													.getElementsByTagName(
//															attributes[i])
//													.item(0).getTextContent()
//													+ "  ");

									result=result+eElement.getElementsByTagName(attributes[i]).item(0).getTextContent()+ "  ";
								}
							//	System.out.println();
								result=result+"\n";
								
							}

						}

					}
				} else {
					ordering ordering = new ordering();

					NodeList nList = doc.getElementsByTagName("row");

					
					if (input.contains("asc")) {
						try {
							ordering.sortStrigAsc(nList, order); // given tag
																	// after
																	// ORDERED
																	// BY
						} catch (Exception e) {
							ordering.sortIntAsc(nList, order); // given tag
																	// after
																	// ORDERED
																	// BY
						}

						// ordering.sortStrigAsc(nList, "firstname");

					} else if (input.contains("desc")) {
						try {
							ordering.sortStrigDsc(nList, order); // given tag
																	// after
																	// ORDERED
																	// BY
						} catch (Exception e) {
							ordering.sortIntDsc(nList, order); // given tag
																	// after
																	// ORDERED
						}
					}

					// ordering.sortStrigAsc(nList, "lastname");

					for (int temp = 0; temp < nList.getLength(); temp++) {

						Node nNode = nList.item(temp);

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {

							Element eElement = (Element) nNode;

							{
								for (int i = 0; i < attributes.length; i++) {

//									System.out
//											.print(eElement
//													.getElementsByTagName(
//															attributes[i])
//													.item(0).getTextContent()
//													+ "    ");

									result=result+eElement.getElementsByTagName(attributes[i]).item(0).getTextContent()+ "  ";
								}
//								System.out.println();
								result=result+"\n";
							}

						}

					}

				}

			} else if (input.contains("where")) {
				NodeList nList = doc.getElementsByTagName("row");

				
				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);
					Element eElement = (Element) nNode;

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						// Element eElement = (Element) nNode;
						if (input.contains("=")) {
							//System.out.println(columName);
							if (eElement.getElementsByTagName(columName)
									
									.item(0).getTextContent().equals(values)) {
								{
									for (int i = 0; i < attributes.length; i++) {
//										System.out.print(eElement
//												.getElementsByTagName(
//														attributes[i]).item(0)
//												.getTextContent()
//												+ "  ");
										result=result+eElement.getElementsByTagName(attributes[i]).item(0).getTextContent()+ "  ";
									}
									//System.out.println();
									result=result+"\n";

								}
							}
						}
					}
				}
				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);
					Element eElement = (Element) nNode;

					if (input.contains(">")) {
						if (Integer.parseInt(eElement
								.getElementsByTagName(columName).item(0)
								.getTextContent().toString()) > Integer.parseInt(values)/* VALUE */) {
							for (int i = 0; i < attributes.length; i++) {
//								System.out.print(eElement
//										.getElementsByTagName(attributes[i])
//										.item(0).getTextContent()
//										+ "  ");
								result=result+eElement.getElementsByTagName(attributes[i]).item(0).getTextContent()+ "  ";
							}
							//System.out.println();
							result=result+"\n";
							
						}
					}
					}
				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);
					Element eElement = (Element) nNode;

					if (input.contains("<")) {
						if (Integer.parseInt(eElement
								.getElementsByTagName(columName).item(0)
								.getTextContent().toString()) < Integer.parseInt(values)) {
							for (int i = 0; i < attributes.length; i++) {
//								System.out.print(eElement
//										.getElementsByTagName(attributes[i])
//										.item(0).getTextContent()
//										+ "  ");
								result=result+eElement.getElementsByTagName(attributes[i]).item(0).getTextContent()+ "  ";
							}
							//System.out.println();
							result=result+"\n";
						}

					}

				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
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

