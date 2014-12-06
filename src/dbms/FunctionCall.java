package dbms;

public class FunctionCall {
public String functioncall(String[]array){
	impDBMS function = new impDBMS();
	if(array[1].equals("CREATEDATABASE")){
		if(function.CreateDataBase(array[2])){
			return Dbms.Con_DB;
		}else{
			return null;
		}
	}
	else if (array[1].equals("CREATETABLE")) {
		
		return function.createTable(array[2],array[3],array[4]);
		
	}
	else if (array[1].equals("INSERT")) {
		String schema =new schema().ifTable(array[2], array[3],array[4]);
		if(schema.equals("Done")){
			
			return function.insert(new schema().getPath(array[2]),array[3],array[4], new schema().getColumnNames(array[2]));
		}
		else{
			return schema;
		}
		
	}
	else if (array[1].equals("UPDATE")) {
		String schema = new schema().ifTable(array[2], array[3], array[4]);
		if(schema.equals("Done")){
			schema=new schema().ifTable(array[2], array[5], array[6]);
			if(schema.equals("Done")){
				return function.update(new schema().getPath(array[2]), array[5], array[6],array[3], array[4],  new schema().getColumnNames(array[2]));
			}
		}
		return schema;
		
	}
	else if (array[1].equals("DELETE")) {
		String schema =  new schema().ifTable(array[2], array[3], array[4]);
		if(schema.equals("Done")||schema.equals("This Table already exists")){
		   return	function.delete(new schema().getPath(array[2]), array[3], array[4]);
		}
		else{
			return schema;
		}
	}
	else if (array[1].equals("SELECT")) {
		if(array[3].equals("ALL")){
			String schema =  new schema().ifTable(array[2],null , array[4]);
			System.out.println(schema);
			if(schema.equals("Done")||schema.equals("This Table already exists")){
				String result;
				return result=function.select(array[2], new schema().getPath(array[2]), array[5], array[4], array[0], array[7]);
			}
		}
		else{
			String schema =  new schema().ifTable(array[2],null, array[4]);
			
			if(schema.equals("Done")||schema.equals("This Table already exists")){
					return function.selectcolumn(array[2], new schema().getPath(array[2]) , array[5], array[4], array[3],array[0]);
			}
		}
	
	
		
		
	}
	return null;
}
}
