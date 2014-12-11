package app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AMethodInvoker implements IMethodInvoker {
	
	@Override
	public void callMethod(String methodName, Class<?> type, String input) {
		
		Method method = null;
		try {
		  method = this.getClass().getMethod(methodName, type);
		} catch (SecurityException e) {
		  // ...
		} catch (NoSuchMethodException e) {
		  // ...
		}
		
		// If the method is null, return now
		if (method == null) { return; }
		
		try {
			  method.invoke(this, input);
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
	}
}
