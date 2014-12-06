package dbms;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class schema {
	private String path ="../DatabaseManagementSystem/" + "MySchema.xml";
	public void CreateSchema(String Path , String DataBaseName){	
		//this.path = path ;
			try {
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document schema = docBuilder.newDocument();
				Element xsSchema = schema.createElement("schema");
				schema.appendChild(xsSchema);
				Attr l = schema.createAttribute("DataBase");
				l.setValue(DataBaseName);
				xsSchema.setAttributeNode(l);
				l = schema.createAttribute("DataBasePath");
				l.setValue(Path);
				xsSchema.setAttributeNode(l);
				Transform(schema);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}	
	}
   public void Transform(Document schema){
	   try{
		   TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(schema);
			StreamResult result = new StreamResult(new File(path));
			//transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
	   }catch(Exception e){
			
		}
	   
   }

   public Document readSchema() throws IOException{
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document schema = docBuilder.parse(path);
			schema.getDocumentElement().normalize();	
			return schema;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
		} catch (SAXException e) {	
			System.out.println("error");
		} catch (IOException e) {
			throw new IOException();
		}
		return null;
		
   }
   public String InsertTable(String tableName , String[]columNames,String[]type,String path){
	   Document schema ;
	       try{
		    schema = readSchema();
	       }catch(IOException e){
		    	return DB_NOT_FOUND;
		    }
			if (schema != null) {
				if(getTable(tableName)!= null){
					return TABLE_ALREADY_EXISTS;
				}
				Node parent = schema.getFirstChild();
				Element table = schema.createElement("xs:element");
				parent.appendChild(table);
				Attr l = schema.createAttribute("xmlns:xs");
				l.setValue(path);
				table.setAttributeNode(l);
			    l = schema.createAttribute("Table");
				l.setValue(tableName);
				table.setAttributeNode(l);
				Element complex = schema.createElement("xs:complexType");
				table.appendChild(complex);
				Element sequence = schema.createElement("xs:sequence");
				complex.appendChild(sequence);
				for (int i = 0; i < type.length; i++) {
					Element column = schema.createElement("xs:element");
					sequence.appendChild(column);
					Attr t = schema.createAttribute("Column");
					t.setValue(columNames[i]);
					column.setAttributeNode(t);
					t = schema.createAttribute("Type");
					t.setValue(type[i]);
					column.setAttributeNode(t);
				}
				Transform(schema);
			}	
			return Con_Table;
   }
   public String ifTable(String tableName , String ColumName , String Values){   
	   Node table = getTable(tableName);
	   String v = Values ;
       if(table== null){
    	   return TABLE_NOT_FOUND;
       }else{
    	   if(ColumName != null && ColumName.equals("all")){
    		   return checkAll(table.getFirstChild().getFirstChild(), Values);
    	   }
           else if(ColumName != null){
    		   String[]colums = ColumName.split(",");
    		   String[]value = new String[colums.length];
    		   if(Values != null){
    			   value = Values.split(",");
    			   if(colums.length != value.length){
    				   return COLUMN_TYPE_MISMATCH;
    			   }
    		   }
    		   for (int i = 0; i < colums.length; i++) {
    			   v = value[i];
    			   String msg = getColum(table.getFirstChild().getFirstChild(), colums[i],v);
        		   if(msg.equals(COLUMN_NOT_FOUND)|| msg.equals(COLUMN_TYPE_MISMATCH)){
        			   return msg ; 
        		   }
			}
    		   return correct;
    	   }
    	   return TABLE_ALREADY_EXISTS;
       }
   }
   public Node getTable(String tableName){
	   Document schema ;
       try{
	    schema = readSchema();
       }catch(IOException e){
	    	return null;
	    }
	   Node parent = schema.getFirstChild();
       return (getNode(parent,"Table",tableName));
   }
   public String getColum(Node table , String ColumName , String value ) {
	   Node colum = getNode(table, "Column",ColumName);
	   if(colum == null){
		   return COLUMN_NOT_FOUND;
	   }else if(value != null){
		   NamedNodeMap attr = colum.getAttributes();
		   Node nodeAttr = attr.getNamedItem("Type");
		   if(!checkType(value, nodeAttr.getNodeValue())){
			   return COLUMN_TYPE_MISMATCH;
		   }
		   
	   }
	   return correct;
  }
   public Node getNode(Node firstNode , String attrName,String attrValue ){
	   Node temp = firstNode.getFirstChild();
	   if(temp != null){
		   do{
			   NamedNodeMap attr = temp.getAttributes();
			   Node nodeAttr = attr.getNamedItem(attrName);
			   if(nodeAttr.getNodeValue().equalsIgnoreCase(attrValue)){
				   return temp ;
			   }
			   temp = temp.getNextSibling();
		   }while(temp != null);
	   }
	   return null ;    
   }
   public String getPath(String TableName) {
	   Node table = getTable(TableName);
	   if (table != null) {
		NamedNodeMap attr = table.getAttributes();
		Node nodeAttr = attr.getNamedItem("xmlns:xs");
		return (nodeAttr.getNodeValue());
	}
	   return null;
	
}
   public String[] spliter(String text){
	   String [] strings = text.split(",") ;
	   for (int i = 0; i < strings.length; i++) {
	}
	   return strings;
   }
   public String checkAll(Node table , String values ){
	   if(values == null){
		   return COLUMN_TYPE_MISMATCH;
	   }
	   String[]value = values.split(",");
	   Node colum = table.getFirstChild();
	   for (int i = 0; i < value.length; i++) {
		   if(colum == null){
			   return COLUMN_TYPE_MISMATCH;
		   }
		   NamedNodeMap attr = colum.getAttributes();
		   Node nodeAttr = attr.getNamedItem("Type");
		   if(!checkType(value[i],nodeAttr.getNodeValue())){
			   return COLUMN_TYPE_MISMATCH;
		   }
		   colum = colum.getNextSibling();
	}if(colum != null){
		return COLUMN_TYPE_MISMATCH;
	}
	return correct;
   }
   public String[] getColumnNames(String TableName){
	   Node table = getTable(TableName);
	   int index = 0;
	   //String[]columNames = new String[];
	   String colum = new String();
	   if (table != null) {
		Node column = table.getFirstChild().getFirstChild().getFirstChild();
		while(column!= null){
			NamedNodeMap attr = column.getAttributes();
			 Node nodeAttr = attr.getNamedItem("Column");
			 colum = colum+nodeAttr.getNodeValue()+",";
	         column = column.getNextSibling();
		}
	}
	return colum.split(",");
   }
   public boolean checkType(String value , String type){
	   if(type.equals("int")){
		   return !(value.contains("'"));
	   }else if(type.contains("varchar")){
		   return (value.contains("'"));
	   }
	   return false;
			   
   }
   public String getDataPath(){
	   Document schema;
	   try{
		   schema = readSchema();
		   Node sc = schema.getFirstChild();
		   NamedNodeMap attr = sc.getAttributes();
		   Node nodeAttr = attr.getNamedItem("DataBasePath");
		   return nodeAttr.getNodeValue();
		   
		   
	   }catch(IOException e){
		   return Dbms.DB_NOT_FOUND;
	   }
	 
   }
   public static final String correct = "Done";
   
   public static final String TABLE_NOT_FOUND = "This Table doesn't exists in database";
	public static final String COLUMN_NOT_FOUND = "This column doesn't exists in this table";
	public static final String TABLE_ALREADY_EXISTS = "This Table already exists";
	public static final String PARSING_ERROR = "bad formated input";
	public static final String DB_NOT_FOUND = "No database exists";
	public static final String COLUMN_TYPE_MISMATCH = "Entered value doesn't match column type";
	public static final String Con_DB = "DB created";
	public static final String Con_Table = "Table created";
	public static final String Con_insert = "insertion Complete";
	public static final String Con_Delete = "Row/s deleted";
	public static final String Con_Update = "Row/s Updated";
	public static final String NOT_MATCH_CRITERIA = "no row exists with this criteria";

}
