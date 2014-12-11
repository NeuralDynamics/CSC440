package app;

public interface IController extends IMethodInvoker {
	public void start();
	
	public void setUserInterface(IUserInterface userInterface);
	
	public IUserInterface getUserInterface();
}
