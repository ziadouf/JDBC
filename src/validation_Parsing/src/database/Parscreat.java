package validation_Parsing.src.database;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parscreat {
	public String[] parscreat(String sqlsyntex){
		
		
		String [] parscreat = new String[5];
		parscreat[0]=sqlsyntex;
		parscreat[1]="CREATETABLE";
		String regex = "create table [a-z\\d]+";
		Pattern check = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher regxmatcher = check.matcher(sqlsyntex);
		if(regxmatcher.find()){
			
			String [] split = regxmatcher.group().split(" ");
			parscreat[2]=split[2];
		}
		
		String regex2 = "[(][\\s\\S]+[;]";
		Pattern check2 = Pattern.compile(regex2,Pattern.CASE_INSENSITIVE);
		Matcher regxmatcher2 = check2.matcher(sqlsyntex);
		if(regxmatcher2.find()){
			
			String temp=regxmatcher2.group().substring(0,regxmatcher2.group().length()-2);
			temp=temp.replaceFirst("\\(", "");
			String split[] = temp.split(",");
			String column = "";
			String type ="";
			for(String s :split){
				int i = 0 ;
				while(s.charAt(i)==' '){
					i++;
				}
				String temp1= s.substring(i, s.length());
				String split2[] = temp1.split(" ");
				column=column+split2[0]+",";
//				if(split2[1].contains("varchar")){
					
//					type = type +"String,";
//				}
//				else{
					
					type = type +split2[1]+",";
//				}
				
				
			}
			parscreat[3]=column.substring(0, column.length()-1);
			type=type.substring(0, type.length()-1);
			parscreat[4]= type.toLowerCase();
			
		}
		return parscreat;
	}
	
}
