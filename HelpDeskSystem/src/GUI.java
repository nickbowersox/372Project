import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class GUI 
{
	
	////////////////////////////
	  //Create Ticket Screen//
	////////////////////////////
	public void createTicketScreen(Queries queries, Connections conn)
	{
		JFrame frame = new JFrame();
		
		///////////////////////
		//Create GUI Elements//
		///////////////////////
		
		//create labels
		JLabel idLabel = new JLabel("Ticket ID");
		JLabel customerLabel = new JLabel("Customer");
		JLabel summaryLabel = new JLabel("Summary");
		JLabel resolveTimeLabel = new JLabel("        Resolved at:");
		JLabel minutesSpentLabel = new JLabel("Minutes spent:");
		JLabel descriptionLabel = new JLabel("   Description of Issue");
		JLabel resolutionLabel = new JLabel("                   Resolution");
		JLabel notesLabel = new JLabel("                             Notes");
		JLabel resolvedLabel = new JLabel("                  Resolved?");
		JLabel resolvedByLabel = new JLabel("Resolved by:");
		JLabel priorityLabel = new JLabel("Priority");
		
		//create buttons
		JButton searchButton = new JButton("Search By Ticket ID");
		JButton updateButton = new JButton("Save Changes");
		JButton clearButton = new JButton("Clear");
		JButton createButton = new JButton("Create new Ticket");
		JButton resolveButton = new JButton("Resolve Ticket");
		JButton reportButton = new JButton("Report Screen");
		
		//create text fields
		JTextField idText = new JTextField(10);
		JTextField customerText = new JTextField(15);
		JTextField summaryText = new JTextField(25);
		JTextField resolveTimeText = new JTextField(10);
		JTextField minutesSpentText = new JTextField(5);
		JTextField resolvedByText = new JTextField(15);
		JTextField priorityText = new JTextField(1);
		
		//create scrolling text areas
		final JTextArea descriptionTextArea = new JTextArea(10,25);
		JScrollPane descriptionScroll = new JScrollPane(descriptionTextArea);
		final JTextArea resolutionTextArea = new JTextArea(10,25);
		JScrollPane resolutionScroll = new JScrollPane(resolutionTextArea);
		final JTextArea notesTextArea = new JTextArea(10,25);
		JScrollPane notesScroll = new JScrollPane(notesTextArea);		
		
		//create check box
		JCheckBox resolvedCheck = new JCheckBox();

		//create panel to add JItems to
		JPanel panel = new JPanel();
		
		//add JItems to panel
		panel.add(idLabel);
		panel.add(idText);
		panel.add(customerLabel);
		panel.add(customerText);
		panel.add(summaryLabel);
		panel.add(summaryText);
		panel.add(priorityLabel);
		panel.add(priorityText);
		panel.add(descriptionLabel);
		panel.add(descriptionScroll);
		panel.add(notesLabel);
		panel.add(notesScroll);
		
		panel.add(resolvedLabel);
		panel.add(resolvedCheck);
		panel.add(resolvedByLabel);
		panel.add(resolvedByText);
		panel.add(resolveTimeLabel);
		panel.add(resolveTimeText);
		panel.add(minutesSpentLabel);
		panel.add(minutesSpentText);
		panel.add(resolutionLabel);
		panel.add(resolutionScroll);
		
		panel.add(searchButton);
		panel.add(updateButton);
		panel.add(resolveButton);
		panel.add(createButton);
		panel.add(clearButton);
		panel.add(reportButton);
			
		//add panel to frame
		frame.add(panel);
		
		//edit frame
		frame.setSize(450, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		///////////////////////
		//implement listeners//
		///////////////////////
		
		//search listener
		class SearchListener implements ActionListener
    {
    	
    	public void actionPerformed(ActionEvent event)
    	   {
    			queries.search(conn.getConnections(), idText, customerText, summaryText, resolveTimeText, minutesSpentText, 
    					resolvedByText, priorityText, resolvedCheck, resolutionTextArea, descriptionTextArea, notesTextArea);
    			conn.closeConnection();
    	   }
    }
		
		ActionListener searchListener = new SearchListener();
		searchButton.addActionListener(searchListener);
		
		//update listener
		class UpdateListener implements ActionListener
    {
    	
    	public void actionPerformed(ActionEvent event)
    	   {
    			queries.update(conn.getConnections(), idText, customerText, summaryText, resolveTimeText, minutesSpentText, 
    					resolvedByText, priorityText, resolvedCheck, resolutionTextArea, descriptionTextArea, notesTextArea);
    			conn.closeConnection();
    	   }
    }
		
		ActionListener updateListener = new UpdateListener();
		updateButton.addActionListener(updateListener);
		
		//resolve listener
		class ResolveListener implements ActionListener
    {
    	
    	public void actionPerformed(ActionEvent event)
    	   {
    			queries.resolve(conn.getConnections(), idText, customerText, summaryText, resolveTimeText, minutesSpentText, 
    					resolvedByText, priorityText, resolvedCheck, resolutionTextArea, descriptionTextArea, notesTextArea);
    			conn.closeConnection();
    	   }
    }
		
		ActionListener resolveListener = new ResolveListener();
		resolveButton.addActionListener(resolveListener);
		
		//create listener
		class CreateListener implements ActionListener
    {
    	
    	public void actionPerformed(ActionEvent event)
    	   {
    			queries.create(conn.getConnections(), idText, customerText, summaryText, resolveTimeText, minutesSpentText, 
    					resolvedByText, priorityText, resolvedCheck, resolutionTextArea, descriptionTextArea, notesTextArea);
    			conn.closeConnection();
    	   }
    }
		
		ActionListener createListener = new CreateListener();
		createButton.addActionListener(createListener);
		
	  //clear listener
		class ClearListener implements ActionListener
	  {
	    	
	  	public void actionPerformed(ActionEvent event)
	  	{
	  			clear(idText, customerText, summaryText, resolveTimeText, minutesSpentText, 
    					resolvedByText, priorityText, resolvedCheck, resolutionTextArea, descriptionTextArea, notesTextArea);
	  	}
	  }
			
			ActionListener clearListener = new ClearListener();
			clearButton.addActionListener(clearListener);
			
		//report listener
		class ReportListener implements ActionListener
		{
		    	
		  public void actionPerformed(ActionEvent event)
		  {
		  		createReportScreen(queries, conn);
		  }
		}
				
				ActionListener reportListener = new ReportListener();
				reportButton.addActionListener(reportListener);
	}//end createTicketScreen
	
	////////////////////////////
	  //Create Report Screen//
	////////////////////////////
	public void createReportScreen(Queries queries, Connections conn)
	 {
		 ///////////////////////
		 //Create GUI Elements//
		 ///////////////////////
		 
		 JFrame reportFrame = new JFrame();
		 
		 //create labels
		 JLabel reportLabel = new JLabel("Report Name:");
		 
		 //create text fields
		 JTextField reportText = new JTextField(25);
		 
		 //create report text area
		 final JTextArea reportTextArea = new JTextArea(37,39);
		 JScrollPane reportScroll = new JScrollPane(reportTextArea);
		 
		 //create buttons
		 JButton report1Button = new JButton("Report 1");
		 JButton report2Button = new JButton("Report 2");
		 JButton report3Button = new JButton("Report 3");
		 JButton report4Button = new JButton("Report 4");
		 JButton report5Button = new JButton("Report 5");
		 JButton resetButton = new JButton("Clear");
		 
		 //add to frame
		 JPanel reportPanel = new JPanel();
		 reportPanel.add(reportLabel);
		 reportPanel.add(reportText);
		 reportPanel.add(reportScroll);
		 reportPanel.add(report1Button);
		 reportPanel.add(report2Button);
		 reportPanel.add(report3Button);
		 reportPanel.add(report4Button);
		 reportPanel.add(report5Button);
		 reportPanel.add(resetButton);
		 
		 reportFrame.add(reportPanel);
		 
		 //edit frame
			reportFrame.setSize(450, 720);
			reportFrame.setResizable(false);
			reportFrame.setLocation(100,100);
			reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			reportFrame.setVisible(true);
			
			
			////////////////////
			//Action Listeners//
			////////////////////
			
			//report1 listener
			class Report1Listener implements ActionListener
	    {
	    	
	    	public void actionPerformed(ActionEvent event)
	    	{
	    		queries.report1(conn.getConnections(), reportText, reportTextArea);
	    	}
	    	
	    }
			
			ActionListener report1Listener = new Report1Listener();
			report1Button.addActionListener(report1Listener);
			
	  	//report2 listener
			class Report2Listener implements ActionListener
	    {
	    	
	    	public void actionPerformed(ActionEvent event)
	    	{
	    		queries.report2(conn.getConnections(), reportText, reportTextArea);
	    	}
	    	
	    }
			
			ActionListener report2Listener = new Report2Listener();
			report2Button.addActionListener(report2Listener);
			
		  //report3 listener
			class Report3Listener implements ActionListener
	    {
	    	
	    	public void actionPerformed(ActionEvent event)
	    	{
	    		queries.report3(conn.getConnections(), reportText, reportTextArea);
	    	}
	    	
	    }
			
			ActionListener report3Listener = new Report3Listener();
			report3Button.addActionListener(report3Listener);
			
	  	//report4 listener
			class Report4Listener implements ActionListener
	    {
	    	
	    	public void actionPerformed(ActionEvent event)
	    	{
	    		queries.report4(conn.getConnections(), reportText, reportTextArea);
	    	}
	    	
	    }
			
			ActionListener report4Listener = new Report4Listener();
			report4Button.addActionListener(report4Listener);
			
		  //report5 listener
			class Report5Listener implements ActionListener
	    {
	    	
	    	public void actionPerformed(ActionEvent event)
	    	{
	    		queries.report5(conn.getConnections(), reportText, reportTextArea);
	    	}
	    	
	    }
			
			ActionListener report5Listener = new Report5Listener();
			report5Button.addActionListener(report5Listener);
			
		//reset listener
			class ResetListener implements ActionListener
	    {
	    	
	    	public void actionPerformed(ActionEvent event)
	    	{
	    		reset(reportTextArea);
	    	}
	    	
	    }
			
			ActionListener resetListener = new ResetListener();
			resetButton.addActionListener(resetListener);
			
	 }//end createReportScreen
	
	private void reset(JTextArea reportTextArea) 
	{
		System.out.println("Clear worked");
	}
	
	public static void clear(JTextField idText, JTextField customerText, JTextField summaryText, JTextField resolveTimeText, 
			JTextField minutesSpentText, JTextField resolvedByText, JTextField priorityText, JCheckBox resolvedCheck, 
			JTextArea resolutionTextArea, JTextArea descriptionTextArea, JTextArea notesTextArea) 
	{
		idText.setText("");
		customerText.setText("");
		summaryText.setText("");
		resolveTimeText.setText("");
		minutesSpentText.setText("");
		resolvedByText.setText("");
		priorityText.setText("");
		resolvedCheck.setSelected(false);
		resolutionTextArea.setText("");
		descriptionTextArea.setText("");
		notesTextArea.setText("");
	}//end clear
}//end GUI
