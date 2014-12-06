package validation_Parsing.src.database;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parswhere {
	
 public String[] parswhere (String sqlsyntex){
		String parsewhere [] = new String[3];
		String regex3 = "(WHERE)(\\s)+[A-za-z\\d]+((\\s)+[=><]((\\s)+((['][A-za-z\\d\\s]+['])|(\\d)+)|((['][A-za-z\\d\\s]+['])|(\\d))+)|[=><]((\\s)((['][A-za-z\\d\\s]+['])|(\\d)+)|((['][A-za-z\\d\\s]+['])|(\\d))+))";
		Pattern check3 = Pattern.compile(regex3,Pattern.CASE_INSENSITIVE);
		Matcher regexmatcher3 = check3.matcher(sqlsyntex);
		if(regexmatcher3.find()){
			
			//String split[] = regexmatcher3.group().split("[ =<>]");
			
			int j = 5;
			while(regexmatcher3.group().charAt(j)==' '){
				j++;
			}
			String temp1=regexmatcher3.group().substring(j,regexmatcher3.group().length());
			String split[] = temp1.split("[=>< ]");
			parsewhere [0]=split[0];
			
			String regex4 = "[=><]((\\s)+((['][A-za-z\\d\\s]+['])|(\\d)+)|((['][A-za-z\\d\\s]+['])|(\\d))+)";
			Pattern check4 = Pattern.compile(regex4,Pattern.CASE_INSENSITIVE);
			Matcher regexmatcher4 = check4.matcher(regexmatcher3.group());
			if (regexmatcher4.find()) {
				
				String split1[] = regexmatcher4.group().split("[=<>]");
				
				int i = 0 ;
				while(split1[1].charAt(i)==' '){
					i++;
				}
				String temp = split1[1].substring(i, split1[1].length());
				parsewhere[1]=temp;
				parsewhere [2]=regexmatcher4.group().substring(0,1);
				
				
				
			}
			}
		return parsewhere ;
 }
}
