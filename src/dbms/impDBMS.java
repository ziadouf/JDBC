package dbms;
import java.io.File;

import validation_Parsing.*;
import validation_Parsing.src.database.Validate;
import validation_Parsing.src.database.parsing;


public class impDBMS implements Dbms{
	static String path ;

	@Override
	public boolean CreateDataBase(String Name) {
 
		boolean created = true;
		String name = Name;
		//created = (new File("data bases/" + name)).mkdirs();
		created = (new File("../DatabaseManagementSystem/"+name)).mkdirs();
		this.path = "../DatabaseManagementSystem/"+name ;
		if (created == false) {
			System.out.println("not created !! repeated name");
			return false;
		}
		//String folder_path = "D:/2nd/MyDataBase/MySchema.xml";
		new schema().CreateSchema(path  , Name);
		return true;
		//String folder_path = "../DatabaseManagementSystem/" + name;
		//System.out.println(folder_path);

	}
	public String createTable(String TableName , String ColumName , String Type){
		if(this.path == null){
			String temp = new schema().getDataPath();
			if(temp.equals(Dbms.DB_NOT_FOUND)){
				return temp;
			}
			this.path = temp;
		}
		String TablePath = path + "/"+ (TableName+".xml");
		String[] colums = new schema().spliter(ColumName);
		String[]type  = new schema().spliter(Type);
		String msg =  new schema().InsertTable(TableName, colums ,type, TablePath);
		if(msg.equals(Con_Table)){
			new CreateTable(TableName, colums , type , TablePath);
		}
		
		return msg;
	}
	@Override
	public String input(String sql) {
		// TODO Auto-generated method stub
		if(!new Validate().validate(sql)){
			return PARSING_ERROR;
		}
		String[] pars = new parsing().pars(sql);
		for(String s : pars){
			System.out.println(s);
		}
		
		return new FunctionCall().functioncall(pars);
	}
	@Override
	public String delete(String filepath, String att, String value) {
		Delete del = new Delete();
		
		
		return del.delete(filepath, att, value);
	}
	@Override
	public String insert(String filepath, String inp, String newVal,
			String[] Original) {
		Insert ins = new Insert();
		
		return ins.insert(filepath, inp, newVal, Original);
	}
	@Override
	public String update(String filepath, String att, String value, String inp,
			String newVal, String[] Original) {
		Update up = new Update();
		return up.update(filepath, att, value, inp, newVal, Original);
	}

	@Override
	public String select(String tablename,String path,String values,String columName,String input,String order){
		SelectAll select=new SelectAll();
		//SelectColumn1Column2 selectCol=new SelectColumn1Column2();
		//selectCol.selectcolumn(tablename, path, values, columName, columName, input);
		System.out.println(select.selectAll(tablename, path, values, columName, input, order));
		return select.selectAll(tablename, path, values, columName, input, order);
	}
	@Override
	public String selectcolumn(String tablename,String path,String values,String columName,String requiredColumn,String input){
		SelectColumn1Column2 selectColumn=new SelectColumn1Column2();
		return selectColumn.selectcolumn(tablename, path, values, columName, requiredColumn, input);
		
	}
}
