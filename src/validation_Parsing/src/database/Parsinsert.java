package validation_Parsing.src.database;

import java.security.acl.LastOwnerException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parsinsert {
	public String[] parsinsert(String sqlsyntax){
		String parsinsert[] = new String[5];
		parsinsert[0]=sqlsyntax;
		parsinsert[1]="INSERT";
		
		
		String  regex = "(INTO|into)(\\s)+[A-za-z\\d]+";
		Pattern check = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher = check.matcher(sqlsyntax);
		
		if(regexmatcher.find()){
			
			int i = 4 ; 
			while(regexmatcher.group().charAt(i)==' '){
				i++;
			}
			String temp =regexmatcher.group().substring(i,regexmatcher.group().length() );
			
			parsinsert[2]=temp;
			}
		String  regex3 = "(INTO|into)(\\s)+[A-za-z\\d]+(\\s)+(\\()([^\\)])+";
		Pattern check3 = Pattern.compile(regex3,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher3 = check3.matcher(sqlsyntax);
		if(regexmatcher3.find()){
			
			String[] split = regexmatcher3.group().split("\\(");
			String temp=split[1].replaceAll(" ", "");
			
			parsinsert[3]=temp;
			
		}
		
		
		
		String  regex2 = "(VALUES|values)(\\s)+[(][^)]+";
		Pattern check2 = Pattern.compile(regex2,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher2 = check2.matcher(sqlsyntax);
		if(regexmatcher2.find()){
		String values="";
		String[] split = regexmatcher2.group().split("\\(");
		
		String split1[]=split[1].split("\\,");
		for(String s : split1){
			
			int i = 0 ; 
			while(s.charAt(i)==' '){
				i++;
			}
			int j = i;
			if(s.charAt(j)=='\''){
				int x = s.lastIndexOf("\'");
			while(j<s.length()&&j<x){
				j++;
			}
			}
			else{
				while(j<s.length()&&s.charAt(j)!=' '){
					j++;
				}
			}
			values =values+s.substring(i,j)+",";
			
			
		}
		
		
		parsinsert[4]=values.substring(0,values.length()-1);
		
		}
		
		
				
				
				
				
		if(parsinsert[3]==null){
			parsinsert[3]="all";
		}
		
		return parsinsert;
	}
}

