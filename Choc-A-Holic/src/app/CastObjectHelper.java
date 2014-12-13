package app;

import date.conversion.DateChecker;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CastObjectHelper {
	private static final String DATE_REGEX = "(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])-((18|19|20|21)\\d\\d)";
	private static final String DATE_FORMAT = "MM-DD-YYYY";
	private static final DateChecker dateChecker = new DateChecker(DATE_REGEX);
	private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	
	private int numDays = -45;
	
	public String getDateFormat() {
		return DATE_FORMAT;
	}
	
	public int getNumDays() {
		return numDays;
	}
	
	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}
	
	public Object getDefaultValue(Class<?> cls) {
		if (cls == String.class) { return ""; }
		if (cls == Date.class) { return new Date(); }
		if (cls == long.class) { return -1; }
		if (cls == int.class) { return -1; }
		
		return null;
	}
	
	public Object CastValue(Class<?> cls, String dataInput) {
		// Cast String
		if (cls == String.class) { return dataInput; }
		
		// Cast Date
		if (cls == Date.class) {
			try { if (dateChecker.check(dataInput)) { return dateFormat.parse(dataInput); } } catch (Exception Ex) { }
		}
		
		// Cast Long
		if (cls == long.class) {
			try { long l = Long.parseLong(dataInput); return l; } catch (Exception Ex) { }
		}
		
		// Cast Int
		if (cls == int.class) {
			try { int l = Integer.parseInt(dataInput); return l; } catch (Exception Ex) { }
		}
		
		return null;
	}
}
