package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class WinUI extends AUserInterface implements ActionListener {
    
    final static String TEXT = "Text Field";
    final static String DATE = "Date Field";
    final static String QUIT = "Quit";
    final static String SUBMIT = "Submit";
    
    private JPanel cards; //a panel that uses CardLayout
    private JLabel lbl;
    private JDatePickerImpl datePicker;
    private JTextFieldLimit txt;
    
    private List<String> currentMessages = new ArrayList<String>();
    
    private final static int FRAME_WIDTH = 300;
    private final static int FRAME_HEIGHT = 500;
    
    private final static int CONTROL_WIDTH = 350;
    private final static int CONTROL_HEIGHT = 150;
	
	public WinUI() {
		JFrame frame = new JFrame("CardLayoutDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		//set up the content pane.
        addComponentToPane(frame.getContentPane());
         
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}

     
    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
    	
    	// Output Pane
    	JPanel labelPane = new JPanel(new FlowLayout()); // use FlowLayout
    	labelPane.setSize(500, 350);
    	lbl = new JLabel();
    	lbl.setMinimumSize(new Dimension(CONTROL_WIDTH, CONTROL_HEIGHT));
    	lbl.setPreferredSize(new Dimension(CONTROL_WIDTH, CONTROL_HEIGHT));
    	lbl.setMaximumSize(new Dimension(CONTROL_WIDTH, CONTROL_HEIGHT));
    	lbl.setText("[Display]");
    	lbl.setVerticalAlignment(JLabel.TOP);
    	lbl.setVerticalTextPosition(JLabel.TOP);
    	labelPane.add(lbl);
    	
    	// Input Pane
    	JPanel cardText = new JPanel();
    	txt = new JTextFieldLimit();
    	txt.setMinimumSize(new Dimension(CONTROL_WIDTH, 25));
    	txt.setPreferredSize(new Dimension(CONTROL_WIDTH, 25));
    	txt.setMaximumSize(new Dimension(CONTROL_WIDTH, 25));
    	txt.setActionCommand(SUBMIT);
    	txt.addActionListener(this);
    	cardText.add(txt);
    	
    	JPanel cardDate = new JPanel();
    	Properties p = new Properties();
    	p.put("text.today", "Today");
    	p.put("text.month", "Month");
    	p.put("text.year", "Year");
    	UtilDateModel model = new UtilDateModel();
    	JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
    	datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    	cardDate.add(datePicker);
    	
    	// Action Pane
    	JPanel buttonPane = new JPanel(); // use FlowLayout
    	
    	JButton btnSubmit = new JButton("Submit");
    	btnSubmit.setSize(100, 50);
    	btnSubmit.addActionListener(this);
    	btnSubmit.setActionCommand(SUBMIT);
    	buttonPane.add(btnSubmit);
    	
    	JButton btnQuit = new JButton("Quit");
    	btnQuit.setSize(50,50);
    	btnQuit.addActionListener(this);
    	btnQuit.setActionCommand(QUIT);
    	buttonPane.add(btnQuit);
    	
    	//Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(cardText, TEXT);
        cards.add(cardDate, DATE);
        
        // Add the panes to the frame
        pane.add(labelPane, BorderLayout.NORTH);
        pane.add(cards, BorderLayout.CENTER);
        pane.add(buttonPane, BorderLayout.SOUTH);
    }
	
	@Override
	public void displayMsg(String methodName, Class<?> cls, int maxCharCount) {
		this.maxCharCount = maxCharCount;
		this.cls = cls;
		this.methodName = methodName;
		
		// Configure the input control that the user will use
		configureInput();
		
		// Display the text
		String Text = "";
		while (messages.isEmpty() == false)
		{
			currentMessages.add(messages.peek());
			Text += messages.poll() + "<br>"; 
		}
		displayOutput(Text);
	}
	
	private void configureInput() {
		// Set our default to be Text Entry
		String controlName = TEXT;
		
		if (cls == Date.class) {
			controlName = DATE;	// Configure for Date Picker
		}
		else {
			txt.setLimit(maxCharCount);	// Set the maximum amount of characters that can be entered
		}
		
		// Display the proper card
		CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, controlName);
	}
	
	private void displayOutput(String Message) {
		// Format the message with HTML so we get line breaks
		String html1 = "<html><body style='width: ";
        String html2 = "px'>";
        String html3 = "</html>";
        lbl.setText(html1 + lbl.getWidth() + html2 + Message + html3);
	}

	@Override
	public boolean getIsEventBased() {
		// This is always event based
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Object objData = null;
		boolean quit = false;
		
		
		// First check to see if the user used the 'Quit' button
		if (arg0.getActionCommand().equals(QUIT)) {
			quit = true;
		}
		else {
			if (cls == Date.class) {
				// Date Parameter
				if (datePicker.getModel() != null && datePicker.getModel().getValue() != null) {
					objData = (Date) datePicker.getModel().getValue();
				}
			}
			else {
				/* Everything else parameter (String, Int, Long, etc.) */
				
				// Get the Text entered
				String dataInput = txt.getText();
				
				// Did the user quit?
				if (dataInput.toLowerCase().equals("q")) { quit = true; }
				
				// Cast the value and return a valid object
				objData = castObjHelper.CastValue(cls, dataInput);
			}
		}
		
		// If the object is not valid, we failed or we quit... ARG!
		if (objData == null) {
			objData = castObjHelper.getDefaultValue(cls);
			
			if (quit == false) {
				// We didn't quit, but there was an error parsing...
				displayInvalidEntry();
			}
		}
		
		// Call the assigned method
		methodInvoker.callMethod(this.methodName, this.cls, objData, boolean.class, quit);
		
		// Tell the Controller to process
		methodInvoker.run();
	}
}
