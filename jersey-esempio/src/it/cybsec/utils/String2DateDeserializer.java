package it.cybsec.utils;

import java.util.Date;
import java.io.IOException;
import java.text.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class String2DateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		
		final String date = parser.getText();
		
		try {
			
			if (date == null || date == "")
				return null;
			return DateFormatter.parse(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	
}