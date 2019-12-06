package it.cybsec.utils;

import java.util.Date;
import java.text.*;
import java.util.Locale;
import java.util.TimeZone;


public class DateFormatter {

	private static SimpleDateFormat dateFormat;
	
	static {
		dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ITALY);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
	
	static public Date parse(String stringDate) throws ParseException {
		return dateFormat.parse(stringDate);
	}
	
	static public String format(Date date) throws ParseException {
		return dateFormat.format(date);
	}
	
}
