package it.cybsec.utils;

import java.io.IOException;
import java.text.*;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class Date2StringSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator generator, SerializerProvider provider) throws IOException {
		try {
			generator.writeObject(DateFormatter.format(value));
		} catch (ParseException e) {
			e.printStackTrace();
			generator.writeObject(null);
		}
	}

	
}