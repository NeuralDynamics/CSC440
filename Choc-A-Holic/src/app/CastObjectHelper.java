package app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CastObjectHelper {
	protected DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
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
			try { Date d = dateFormat.parse(dataInput); return d; } catch (Exception Ex) { }
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
