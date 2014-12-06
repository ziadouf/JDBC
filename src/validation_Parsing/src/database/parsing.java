package validation_Parsing.src.database;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class parsing {

	public String [] pars(String sqlsyntax){
		
		String  regex = "insert(\\s)";
		Pattern check = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher = check.matcher(sqlsyntax);
		if(regexmatcher.find()){
			return new Parsinsert().parsinsert(sqlsyntax);
			
		}
		String  regex2 = "delete(\\s)";
		Pattern check2 = Pattern.compile(regex2,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher2 = check2.matcher(sqlsyntax);
		if(regexmatcher2.find()){
			return new ParseDelet().parsdelete(sqlsyntax);
		}
		String  regex3 = "create(\\s)table";
		Pattern check3 = Pattern.compile(regex3,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher3 = check3.matcher(sqlsyntax);
		if(regexmatcher3.find()){
			return new Parscreat().parscreat(sqlsyntax);
		}
		String  regex4 = "update(\\s)";
		Pattern check4 = Pattern.compile(regex4,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher4 = check4.matcher(sqlsyntax);
		if(regexmatcher4.find()){
			return new ParsUpdate().parsupdate(sqlsyntax);
		}
		String  regex5 = "select(\\s)";
		Pattern check5 = Pattern.compile(regex5,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher5 = check5.matcher(sqlsyntax);
		if(regexmatcher5.find()){
			return new ParsSelect().parsselect(sqlsyntax);
			
		}
		String  regex6 = "create(\\s)database";
		Pattern check6 = Pattern.compile(regex6,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher6 = check6.matcher(sqlsyntax);
		if(regexmatcher6.find()){
			return new Parscreatedatabase().parscreatdatabase(sqlsyntax);
			
		}
		return null;
	}
}
