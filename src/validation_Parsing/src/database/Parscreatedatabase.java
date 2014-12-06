package validation_Parsing.src.database;


public class Parscreatedatabase {
	public String[] parscreatdatabase(String sqlsyntax){
		String pars[] = new String[3];
		pars[0]=sqlsyntax;
		pars[1]="CREATEDATABASE";
		int i = 15;
		while(sqlsyntax.charAt(i)==' '){
			i++;
		}
		int j = i ;
		while(j<sqlsyntax.length()&&sqlsyntax.charAt(j)!=' '){
			j++;
		}
		pars[2]=(sqlsyntax.substring(i, j)).replace(";", "");
		
		return pars;
		
	}

}
