package validation_Parsing.src.database;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
	public boolean validate (String sqlsyntax){
		
		String regex[] = new String [12];
		 regex[0]= "create(\\s)table(\\s)[a-z\\d]+((\\s)+\\(|\\()((((\\s)+[a-z\\d]+|[a-z\\d]+)(\\s)((int)|(varchar\\(255\\))))((\\s)+,|,))+((((\\s)+[a-z\\d]+|[a-z\\d]+)(\\s)((int)|(varchar\\(255\\))))((\\s)+\\)|\\)))(((\\s)+;)|(;))";
		 regex[1]="(delete(\\s)+(\\*)(\\s)+from(\\s)+[a-z\\d]+(((\\s)+;)|;))|(delete(\\s)+from(\\s)+[a-z\\d]+(((\\s)+;)|;))";
		 regex[2]="delete(\\s)+from(\\s)+[a-z\\d]+(\\s)+where(\\s)+[a-z\\d]+((\\s)+[=><]|[=><])(((\\s)+(((\\')[a-z\\d\\s]+(\\'))|(\\d)+))|(((\\')[a-z\\d\\s]+(\\'))|(\\d)+))((\\s)+;|;)";
		 regex[3]="insert(\\s)+into(\\s)+[a-z\\d]+(\\s)+values(\\s)+(\\()((((\\s)+((\\'[a-z\\d\\s]+\\')|(\\d)+))|((\\'[a-z\\d\\s]+\\')|(\\d)+))(((\\s)+\\,)|(\\,)))+((((\\s)+((\\'[a-z\\d\\s]+\\')|(\\d)+))|((\\'[a-z\\d\\s]+\\')|(\\d)+))(((\\s)+\\))|(\\))))((\\s)+;|(;))";
		 regex[4]="insert(\\s)+into(\\s)+[a-z\\d]+(\\s)+(\\()(((\\s)+[a-z\\d]+|[a-z\\d]+)((\\s)+,|,))+(((\\s)+[a-z\\d]+|[a-z\\d]+)((\\s)+\\)|\\)))((values(\\s)+(\\()((((\\s)+((\\'[a-z\\d\\s]+\\')|(\\d)+))|((\\'[a-z\\d\\s]+\\')|(\\d)+))(((\\s)+\\,)|(\\,)))++((((\\s)+((\\'[a-z\\d\\s]+\\')|(\\d)+))|((\\'[a-z\\d\\s]+\\')|(\\d)+))(((\\s)+\\))|(\\))))((\\s)+;|(;)))|((\\s)+values(\\s)+(\\()((((\\s)+((\\'[a-z\\d\\s]+\\')|(\\d)+))|((\\'[a-z\\d\\s]+\\')|(\\d)+))(((\\s)+\\,)|(\\,)))++((((\\s)+((\\'[a-z\\d\\s]+\\')|(\\d)+))|((\\'[a-z\\d\\s]+\\')|(\\d)+))(((\\s)+\\))|(\\))))((\\s)+;|(;))))";
		 regex[5]=
				 "update(\\s)+[a-z\\d]+(\\s)+set(\\s)+(([a-z\\d]+((\\s)+=|=)((\\s)+(\\'[a-z\\d\\s]+\\'|\\d+)|(\\'[a-z\\d\\s]+\\'|\\d+))((\\s)+,|,))|((\\s)[a-z\\d]+((\\s)+=|=)((\\s)+(\\'[a-z\\d\\s]+\\'|\\d+)|(\\'[a-z\\d\\s]+\\'|\\d+))+(\\s)+where(\\s)+[a-z\\d]+((\\s)+[=><]|[=><])(((\\s)+(((\\')[a-z\\d\\s]+(\\'))|(\\d)+))|(((\\')[a-z\\d\\s]+(\\'))|(\\d)+))((\\s)+;|;))|([a-z\\d]+((\\s)+=|=)((\\s)+(\\'[a-z\\d\\s]+\\'|\\d+)|(\\'[a-z\\d\\s]+\\'|\\d+))(\\s)+where(\\s)+[a-z\\d]+((\\s)+[=><]|[=><])(((\\s)+(((\\')[a-z\\d\\s]+(\\'))|(\\d)+))|(((\\')[a-z\\d\\s]+(\\'))|(\\d)+))((\\s)+;|;)))";
		 regex[6]=
		 "update(\\s)+[a-z\\d]+(\\s)+set(\\s)+(([a-z\\d]+((\\s)+=|=)((\\s)+(\\'[a-z\\d\\s]+\\'|\\d+)|(\\'[a-z\\d\\s]+\\'|\\d+))(((\\s)+,|,))|([a-z\\d]+((\\s)+=|=)((\\s)+(\\'[a-z\\d\\s]+\\'|\\d+)|(\\'[a-z\\d\\s]+\\'|\\d+))((\\s)+;|;))|((\\s)+([a-z\\d]+((\\s)+=|=)((\\s)+(\\'[a-z\\d\\s]+\\'|\\d+)|(\\'[a-z\\d\\s]+\\'|\\d+)))((\\s)+;|;))))+";
		 regex[7]="select(\\s)+([*]|[a-z\\d]+)(\\s)+from(\\s)+[a-z\\d]+(;|(\\s)+;)";
		 regex[8]="select(\\s)+([*]|[a-z\\d]+)(\\s)+from(\\s)+[a-z\\d]+(\\s)+where(\\s)+[a-z\\d]+((\\s)+[=><]|[=><])(((\\s)+(((\\')[a-z\\d\\s]+(\\'))|(\\d)+))|(((\\')[a-z\\d\\s]+(\\'))|(\\d)+))((\\s)+;|;)";
		 regex[9]="select(\\s)+([*]|[a-z\\d]+)(\\s)+from(\\s)+[a-z\\d]+(\\s)+where(\\s)+[a-z\\d]+((\\s)+[=><]|[=><])(((\\s)+(((\\')[a-z\\d\\s]+(\\'))|(\\d)+))|(((\\')[a-z\\d\\s]+(\\'))|(\\d)+))(\\s)+order(\\s)by(\\s)+(((\\s)+((([a-z\\d]+)(((ASC|DESC)|((\\s)+(ASC|DESC)))|(;|(\\s)+;)))|((([a-z\\d]+((\\s)+,|,)))|([a-z\\d]+((\\s)+,|,)))))|((([a-z\\d]+)(((ASC|DESC)|((\\s)+(ASC|DESC)))|(;|(\\s)+;)))|((([a-z\\d]+((\\s)+,|,)))|([a-z\\d]+((\\s)+,|,)))))+((\\s)+;|;)";
		 regex[10]="select(\\s)+([*]|[a-z\\d]+)(\\s)+from(\\s)+[a-z\\d]+(\\s)+order(\\s)by(\\s)+(((\\s)+((([a-z\\d]+)(((ASC|DESC)|((\\s)+(ASC|DESC)))|(;|(\\s)+;)))|((([a-z\\d]+((\\s)+,|,)))|([a-z\\d]+((\\s)+,|,)))))|((([a-z\\d]+)(((ASC|DESC)|((\\s)+(ASC|DESC)))|(;|(\\s)+;)))|((([a-z\\d]+((\\s)+,|,)))|([a-z\\d]+((\\s)+,|,)))))+((\\s)+;|;)";
		 regex[11]="create(\\s)database(\\s)+[a-z\\d]+((\\s)+;|;)";
		 for(int i = 0 ; i <= 11 ; i ++){
			 Pattern check = Pattern.compile(regex[i],Pattern.CASE_INSENSITIVE);
			 Matcher regexcheck = check.matcher(sqlsyntax);
			 if(regexcheck.find()){
				 return true;
			 }
		 }
		return false;
		
	}

}
