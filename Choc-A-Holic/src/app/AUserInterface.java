package app;

import java.util.ArrayDeque;
import java.util.Queue;

public abstract class AUserInterface implements IUserInterface {
	protected IMethodInvoker methodInvoker = null;
	protected Queue<String> messages = new ArrayDeque<String>();
	
	protected String methodName = "";
	protected Class<?> cls;
	protected int maxCharCount = 0;
	
	protected CastObjectHelper castObjHelper = new CastObjectHelper();
	
	@Override
	public String getDateFormat() {
		return castObjHelper.getDateFormat();
	}
	
	@Override
	public int getNumDays() {
		return castObjHelper.getNumDays();
	}
	
	@Override
	public void setNumDays(int numDays) {
		castObjHelper.setNumDays(numDays);
	}
	
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
