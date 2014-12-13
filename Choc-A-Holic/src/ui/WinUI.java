package ui;

import java.awt.Button;
import java.awt.Frame;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Queue;

import app.IMethodInvoker;
import app.IUserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

public class WinUI extends Frame implements ActionListener, IUserInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	protected IMethodInvoker methodInvoker = null;
	protected Queue<String> messages = new ArrayDeque<String>();
	
	protected String methodName = "";
	protected Class<?> cls;
	protected int maxCharCount = 0;
	
	public WinUI() {
        
        //call the superclass constructor
        super();
       
        //set window title using setTitle method
        this.setTitle("Choc-A-Holics Anonymous");
       
        //add window event adapter
        addWindowListener(new MyWindowAdapter(this));
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(this);
        btnSubmit.setSize(50, 28);
        
        this.add(btnSubmit);
       
        //set window size using setSize method
        this.setSize(300,500);
       
        //show window using setVisible method
        this.setVisible(true);
	}
	
	//extend WindowAdapter
    class MyWindowAdapter extends WindowAdapter{
           
    	WinUI myWindow = null;
           
            MyWindowAdapter(WinUI myWindow){
                    this.myWindow = myWindow;
            }
           
            //implement windowClosing method
            public void windowClosing(WindowEvent e) {
                    //hide the window when window's close button is clicked
                    myWindow.setVisible(false); 
                    
                    // Exit the program
                    System.exit(0);
            }
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
		
		// Display the text
		while (messages.isEmpty() == false)
		{ System.out.println(messages.poll()); }
		
		// Read the User Input
		//readUserInput();
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

	@Override
	public boolean getIsEventBased() {
		// This is always event based
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDateFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumDays() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNumDays(int numDays) {
		// TODO Auto-generated method stub
		
	}
}
