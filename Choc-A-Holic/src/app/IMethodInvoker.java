package app;

public interface IMethodInvoker {
	public void callMethod(String methodName, Class<?> type, String input);
}
