package app;

public interface IMethodInvoker {
	public void callMethod(String methodName, Class<?> type1, Object input1, Class<?> type2, Object input2);
	public void callMethod(String methodName, Class<?> type, Object input);
	public void callMethod(String methodName);
}
