package app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AMethodInvoker implements IMethodInvoker {
	
	@Override
	public void callMethod(String methodName, Class<?> type1, Object input1, Class<?> type2, Object input2) {
		Method method = null;
		try {
			if (type1 == null) {
				method = this.getClass().getMethod(methodName);
			}
			else if (type2 == null) {
				method = this.getClass().getMethod(methodName, type1);
			}
			else {
				method = this.getClass().getMethod(methodName, type1, type2);
			}
		} catch (SecurityException e) {
		  // ...
		} catch (NoSuchMethodException e) {
		  // ...
		}
		
		// If the method is null, return now
		if (method == null) { return; }
		
		try {
				if (type1 == null) {
					method.invoke(this);
				}
				else if (type2 == null) {
					method.invoke(this, input1);
				}
				else {
					method.invoke(this, input1, input2);
				}
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
	}
	
	@Override
	public void callMethod(String methodName, Class<?> type, Object input) {		
		callMethod(methodName, type, input, null, null);
	}
	
	public void callMethod(String methodName) {
		callMethod(methodName, null, null);
	}
}
