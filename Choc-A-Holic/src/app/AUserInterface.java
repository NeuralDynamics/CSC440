package app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;

public abstract class AUserInterface implements IUserInterface {
	protected DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	protected IMethodInvoker methodInvoker = null;
	protected Queue<String> messages = new ArrayDeque<String>();
	
	protected String methodName = "";
	protected Class<?> cls;
	protected int maxCharCount = 0;
	
	@Override
	public void addMessageLine(String message) {
		messages.add(message);
	}

	@Override
	public void displayMsg(String methodName, Class<?> cls, int maxCharCount) {
		this.maxCharCount = maxCharCount;
		this.cls = cls;
		this.methodName = methodName;
	}

	@Override
	public void setMethodInvoker(IMethodInvoker methodInvoker) {
		this.methodInvoker = methodInvoker;
	}
	
	protected Object getDefaultValue() {
		if (cls == String.class) { return ""; }
		if (cls == Date.class) { return new Date(); }
		if (cls == long.class) { return -1; }
		if (cls == int.class) { return -1; }
		
		return null;
	}
	
	protected Object CastValue(String dataInput) {
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
	
	protected boolean testCharCount(int source, int maxCharCount) {
		if (source > maxCharCount) {
			addMessageLine("Too many characters entered. A maximum of " + maxCharCount + " characters is allowed");
			return false;
		}
		
		return true;
	}
	
	protected void displayInvalidEntry() {
		addMessageLine("Invalid Entry. Please try again.");
	}
}
