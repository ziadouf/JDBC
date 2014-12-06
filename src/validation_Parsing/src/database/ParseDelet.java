package validation_Parsing.src.database;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseDelet {
	public String[] parsdelete(String sqlsyntax){
		String deleteparse[] = new String[6];
			deleteparse[0]=sqlsyntax;
			deleteparse[1]="DELETE";
		
		String  regex = "(FROM)(\\s)[A-za-z\\d\\s]+";
		Pattern check = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher = check.matcher(sqlsyntax);
		
		if(regexmatcher.find()){
			String [] split = regexmatcher.group().split(" ");
			deleteparse[2]=split[1];
			
		}
		
		String where [] = new Parswhere().parswhere(sqlsyntax);
		deleteparse[3]=where[0];
		deleteparse[4]=where[1];
		deleteparse[5]=where[2];
		return deleteparse;
			
		
	}

}
