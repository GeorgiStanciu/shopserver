package Commands;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class JsonDateDeserializer implements JsonDeserializer<Date> {
	
	@Override	
	public Date deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
	 String s = json.getAsJsonPrimitive().getAsString();
    Locale locale;
	 if(s.contains("Jan") || s.contains("Feb") || s.contains("Mar") || s.contains("Apr") || s.contains("Jun") || s.contains("Jul") || s.contains("Aug") || s.contains("Sep") || s.contains("Oct") || s.contains("Nov") || s.contains("Dec"))
		locale = new Locale("gb");
	 else
		 locale = new Locale("gb");
    DateFormat df = new SimpleDateFormat("MMM dd, yyyy", locale);
      
       java.util.Date startDate;
       try {
           startDate = df.parse(s);
           Date d = new Date(startDate.getTime());
           return d;
       } catch (ParseException e) {
           e.printStackTrace();
  	     	
       }
       return null;
}

	

	

    
}
