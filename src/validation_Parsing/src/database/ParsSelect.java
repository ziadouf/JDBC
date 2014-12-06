package validation_Parsing.src.database;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsSelect {
	public String []  parsselect(String sqlsyntx){
		 String [] parssselect =  new String [9];
		 parssselect[0]=sqlsyntx;
		 parssselect[1]="SELECT";
		String regex = "(select)(\\s)+[^\\s]+";
		Pattern check = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher = check.matcher(sqlsyntx);
		if(regexmatcher.find()){
			
			
			int i = 6;
			while(regexmatcher.group().charAt(i)==' '){
				i++;
			}
			int j = i; 
			while(j<regexmatcher.group().length()&&regexmatcher.group().charAt(i)!=' '){
				j++;
			}
			String temp = regexmatcher.group().substring(i, j);

			if(temp.equals("*")){
				parssselect[3]="ALL";
			}else {
				parssselect[3]=temp;
			}
			
			
		}
		String regex2 = "(from)(\\s)+[^\\s]+";
		Pattern check2 = Pattern.compile(regex2,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher2 = check2.matcher(sqlsyntx);
		if(regexmatcher2.find()){
			
			int i = 4;
			while(regexmatcher2.group().charAt(i)==' '){
				i++;
			}
			int j = i; 
			while(j<regexmatcher2.group().length()&&regexmatcher2.group().charAt(i)!=' '){
				j++;
			}
			parssselect[2]=regexmatcher2.group().substring(i,j);
			}
		String where [] = new Parswhere().parswhere(sqlsyntx);
		parssselect[4]=where[0];
		parssselect[5]=where[1];
		parssselect[6]=where[2];
		
		String regex3 ="order(\\s)by((\\s)+(\\S)+)+";
			
		Pattern check3 = Pattern.compile(regex3,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher3 = check3.matcher(sqlsyntx);
		if(regexmatcher3.find()){
			String temp = regexmatcher3.group().substring(8, regexmatcher3.group().length());
			
			String splipt[]=temp.split(",");
			String order="";
			for(String s : splipt){
				
				int i = 0 ; 
				while(s.charAt(i)==' '){
					i++;
				}
				int j = i;
				while(j<s.length()&&(s.charAt(j)!=' '&&s.charAt(j)!=';')){
					j++;
				}
				String temp2 = s.substring(i, j);
				order=order+temp2+",";
			}
			order=order.substring(0, order.length()-1);
			parssselect[7]=order;
			String regex4 ="ASC|DESC";
			Pattern check4 = Pattern.compile(regex4,Pattern.CASE_INSENSITIVE);
			Matcher regexmatcher4 = check4.matcher(sqlsyntx);
			if(regexmatcher4.find()){
				parssselect[8]=regexmatcher4.group();
			}
		}
		
		return parssselect;
	}

}
