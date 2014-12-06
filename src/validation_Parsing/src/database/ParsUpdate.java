package validation_Parsing.src.database;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsUpdate {

	public String [] parsupdate(String sqlsyntex){
		String [] parsupdate = new String[8];
		parsupdate[0]=sqlsyntex;
		parsupdate[1]="UPDATE";
		String regex = "update(\\s)+[a-zA-Z\\d]+";
		Pattern check = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher = check.matcher(sqlsyntex);
		if(regexmatcher.find()){
			 String split[] = regexmatcher.group().split(" ");
			 parsupdate[2]=split[1]; 
		}
		String regex2 = "set(\\s)+[\\s\\S]+([w]|[;])";
		Pattern check2 = Pattern.compile(regex2,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher2 = check2.matcher(sqlsyntex);
		if(regexmatcher2.find()){
			
			String temp =regexmatcher2.group().substring(0,regexmatcher2.group().length()-1);
			
			String columns="";
			String values="";
			 temp = temp.replaceFirst(" ", ":");
			 
			String split1[]=temp.split(":");
			String split2[] = split1[1].split(",");
			for(String s : split2){
				
				String split3[] = s.split("=");
				int i = 0 ; 
				while(split3[0].charAt(i)==' '){
					i++;
				}
				int j = i ; 
				while(j<split3[0].length()&& split3[0].charAt(j)!=' '){
					j++;
				}
				String temp3 = split3[0].substring(i,j);
				
				 
				int x = 0 ; 
				while(split3[1].charAt(x)==' '){
					x++;
				}
				int y = x;
				if(split3[1].charAt(y)=='\''){
					int z = split3[1].lastIndexOf("\'");
				while(y<split3[1].length()&&y<=z){
					y++;
				}
				}
				else{
					while(y<split3[1].length()&&split3[1].charAt(y)!=' '){
						y++;
					}
				}
				String temp4 = split3[1].substring(x,y);
				columns=columns+temp3+",";
				values=values+temp4+",";
			}
			columns=columns.substring(0,columns.length()-1);
			parsupdate[3]=columns;
			values = values.substring(0,values.length()-1);
			//values=values+"\'";
			parsupdate[4]=values;
			}
		 String where [] = new Parswhere().parswhere(sqlsyntex);
		 parsupdate[5]=where[0];
		 parsupdate[6]=where[1];
		 parsupdate[7]=where[2];
				 
			
		if(parsupdate[3]==null)	{
			parsupdate[3]="all";
		}
		
		return parsupdate;
	}
}
