package app;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	
		/*IUserInterface userInterface = new CmdLnInterface();
		Controller ctrl = new Controller();
		
		userInterface.setMethodInvoker(ctrl);
		ctrl.setUserInterface(userInterface);
		*/
		MemberManager mbrMgr = new MemberManager();
		mbrMgr.load();
		/*Member m = new Member(345678912, "Mary Jane Smith", "578 Main St.", "Maidson", "WI", 345678, "123-123-7890", "MJS@yahoo.com");
		m.setIsSuspended(true);
		mbrMgr.addMember(m);
		//mbrMgr.addMember(new Member(234567891, "Joe Jim Smith", "321 Main St.", "Milwaukee", "WI", 23456, "098-765-4321", "JJS@yahoo.com"));
		mbrMgr.save();*/
		
		mbrMgr.load();
		mbrMgr.display();
		
		
		
		/*ctrl.test();
		
		
		IUserInterface ui = new CmdLnInterface();
		IController ctrl2 = new CmdLnController();
		
		ui.setMethodInvoker(ctrl2);
		ctrl2.setUserInterface(ui);
		
		ctrl2.start();
		
		
		//ctrl.initialize();*/
	}
}