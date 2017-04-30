import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Queries
{

	/*
	 * this method searches for a ticket based on ticket ID number and fills the
	 * fields in the GUI appropriately.
	 */
	public void search(Connection conn, JTextField idText, JTextField customerText, JTextField summaryText,
			JTextField resolveTimeText, JTextField minutesSpentText, JTextField resolvedByText, JTextField priorityText,
			JCheckBox resolvedCheck, JTextArea resolutionTextArea, JTextArea descriptionTextArea,
			JTextArea notesTextArea, JFrame frame)
	{
		// try to connect and execute query
		try
		{
			if (idText.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(frame, "Please enter a number for the ticket ID.");
				return;
			}
			
			try 
			{
		    int id = Integer.parseInt(idText.getText());
			} 
			catch (NumberFormatException e) 
			{
				JOptionPane.showMessageDialog(frame, "Please enter a number for the ticket ID.");
		    return;
			}
			
			String connectionUrl = "jdbc:sqlserver://ICSsql-1;databaseName=AdventureWorksS1;";
			String user = "StudentS1";
			String pass = "SuperSecurePassword17";

			Statement stmt = null;
			ResultSet rs = null;

			conn = DriverManager.getConnection(connectionUrl, user, pass);

			// sql code to retrieve data from database
			String SQL = "select * from AdventureWorksS1.HelpDesk.Ticket "
					+ "JOIN AdventureWorksS1.HelpDesk.Support on AdventureWorksS1.HelpDesk.Ticket.ResolvedBy = AdventureWorksS1.HelpDesk.Support.SupportID "
					+ "JOIN AdventureWorksS1.HelpDesk.Customers on AdventureWorksS1.HelpDesk.Ticket.CustomerID = AdventureWorksS1.HelpDesk.Customers.CustomerID "
					+ "where AdventureWorksS1.HelpDesk.Ticket.TicketID = " + idText.getText();

			// create the query and execute it
			stmt = conn.createStatement();
			
			ResultSet rs2 = stmt.executeQuery(SQL);
			if (!rs2.next())
			{
					JOptionPane.showMessageDialog(frame, "No ticket with that ID.");
					return;
			}
			
			rs = stmt.executeQuery(SQL);
			
			// set the GUI fields based on the fields returned from query
			while (rs.next())
			{
				idText.setText(rs.getString("TicketID"));
				String customer = rs.getString(19) + " " + rs.getString(20);
				customerText.setText(customer);
				summaryText.setText(rs.getString("Summary"));
				resolveTimeText.setText(rs.getString("ResolutionTime"));
				minutesSpentText.setText(rs.getString("MinutesSpent"));
				//String resolver = rs.getString(13) + " " + rs.getString(14);
				//resolvedByText.setText(resolver);
				if (rs.getString("ResolvedBy") == null)
				{
					resolvedByText.setText("");
				}
				else
				{
					String resolver = rs.getString(13) + " " + rs.getString(14);
					resolvedByText.setText(resolver);
				}
				priorityText.setText(rs.getString("Priority"));
				String resolved = rs.getString("Resolved");
				if (resolved.equals("1"))
				{
					resolvedCheck.setSelected(true);
				} else
				{
					resolvedCheck.setSelected(false);
				}
				resolutionTextArea.setText(rs.getString("Resolution"));
				descriptionTextArea.setText(rs.getString("Description"));
				notesTextArea.setText(rs.getString("Notes"));
			}
			// close the result set
			rs.close();
			idText.setEditable(false);
		}
		// if not able to execute query, print stack trace
		catch (Exception e)
		{

			e.printStackTrace();
		}
	}// end search

	/*
	 * The update method should search for the data by ticket ID number and
	 * update each column with the new values in the GUI
	 */
	public void update(Connection conn, JTextField idText, JTextField customerText, JTextField summaryText,
			JTextField resolveTimeText, JTextField minutesSpentText, JTextField resolvedByText, JTextField priorityText,
			JCheckBox resolvedCheck, JTextArea resolutionTextArea, JTextArea descriptionTextArea,
			JTextArea notesTextArea, JFrame frame)
	{
		try {
						if (idText.isEditable() == true)
						{
								JOptionPane.showMessageDialog(frame, "Please search for a ticket in order to edit it.");
								return;
						}
						
            String connectionUrl = "jdbc:sqlserver://ICSsql-1;databaseName=AdventureWorksS1;";
            String user = "StudentS1";
            String pass = "SuperSecurePassword17";
            
            conn = DriverManager.getConnection(connectionUrl, user, pass);
            String customerID, summary, description, priority, resolveTime, minutesSpent, resolvedBy, isResolved, resolution, notes = null;
            
            String ticketID = idText.getText();
            if (customerText.getText().equals(""))
            {
            	JOptionPane.showMessageDialog(frame, "Cannot update ticket without a customer.");
            	return;
            }
            else
            {
            	customerID = customerText.getText();
            }
            
            if (summaryText.getText().equals(""))
            {
            	JOptionPane.showMessageDialog(frame, "Cannot update ticket without a summary.");
            	return;
            }
            else
            {
            	summary = summaryText.getText();
            }
            
            if (descriptionTextArea.getText().equals(""))
            {
            	JOptionPane.showMessageDialog(frame, "Cannot update ticket without a description.");
            	return;
            }
            else
            {
            	description = descriptionTextArea.getText();
            }
            
            if (notesTextArea.getText().equals(""))
            {
            	notes = " ";
            }
            else
            {
            	notes = notesTextArea.getText();
            }
            
            if (priorityText.getText().equals(""))
            {
            		JOptionPane.showMessageDialog(frame, "Cannot update if the priority field is not a 1, 2, or 3.");
            		return;
            }
            else if (priorityText.getText().equals("1") || priorityText.getText().equals("2") || priorityText.getText().equals("3"))
            {
            		priority = priorityText.getText();
            }
            else
            {
            		JOptionPane.showMessageDialog(frame, "Cannot update if the priority field is not a 1, 2, or 3.");
            		return;
            }
         
            int id = 0;
            
            String SQL = 
         		   "SELECT CustomerID"
         		   + " FROM AdventureWorksS1.HelpDesk.Customers"
         		   + " WHERE FirstName =" + "'" + customerID + "'";
            
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
              //Retrieve by column name
              id  = rs.getInt("CustomerID");
          }
          if (id == 0)
          {
          	JOptionPane.showMessageDialog(frame, "No such customer.");
          	return;
          }
          else
          {
          	System.out.println("found customer id: "+ id);
          }
          
            
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
          LocalDateTime now = LocalDateTime.now();
          resolveTime = dtf.format(now); 
            
          String SQL2;
            
        if (resolvedCheck.isSelected())
        {
        	if (minutesSpentText.getText().equals(""))
          {
          		JOptionPane.showMessageDialog(frame, "The minutes spent field cannot be empty.");
          		resolvedCheck.setSelected(false);
          		return;
          }
          else 
          {
          	try 
  					{
  				    int minutesSpentInt = Integer.parseInt(minutesSpentText.getText());
  					} 
  					catch (NumberFormatException e) 
  					{
  						JOptionPane.showMessageDialog(frame, "Please enter a number for the minutes spent field.");
  						resolvedCheck.setSelected(false);
  				    return;
  					}
            minutesSpent = minutesSpentText.getText();
          }
          
          if (resolvedByText.getText().equals(""))
          {
          	JOptionPane.showMessageDialog(frame, "Cannot update ticket without a resolved by person.");
          	resolvedCheck.setSelected(false);
          	return;
          }
          else
          {
          	resolvedBy = resolvedByText.getText();
          }      
          
          isResolved  = "1";
          
          if (resolutionTextArea.getText().equals(""))
          {
          	JOptionPane.showMessageDialog(frame, "Cannot update ticket without a resolution.");
          	return;
          }
          else
          {
          	resolution = resolutionTextArea.getText();
          }
          //sql code to create ticket in database
        					 SQL2 = 
            		   "UPDATE AdventureWorksS1.HelpDesk.Ticket SET CustomerID = " + id + "," + "Summary = " + "'" + summary + "'"
            		   + "," + "Description="  +  "'" + description + "'" + "," +  "Resolution=" + "'" + resolution + "'" + ","  + "ResolutionTime =" + "'" +resolveTime +  "'" + "," + "Resolved=" + isResolved + "," + "ResolvedBy=" + "'" +  resolvedBy + "'" + "," + "Priority=" + priority + "," + "MinutesSpent=" + "'" + minutesSpent + "'" + "," + "Notes=" + "'" + notes + "'" + 
            		   "WHERE TicketID = " + ticketID;
        }
        else
        {
			             SQL2 = 
			       		   "UPDATE AdventureWorksS1.HelpDesk.Ticket SET CustomerID = " + id + "," + "Summary = " + "'" + summary + "'"
			       		   + "," + "Description="  +  "'" + description + "'" + "," +  "'" + "," + "Priority=" + priority + "," + "Notes=" + "'" + notes + "'" + 
			       		   "WHERE TicketID = " + ticketID;
        }

     		PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
      	pstmt2.executeUpdate();
		
		
		
		System.out.println("Update worked");
	}catch (Exception e)
		{

		e.printStackTrace();
	}//end create
	}// end update

	/*
	 * The resolve method searches for the ticket by ticket ID and changes the
	 * bit field to 1 for resolve. 0 is for not resolved. This also checks the
	 * box in the GUI.
	 */
	public void resolve(Connection conn, JTextField idText, JTextField customerText, JTextField summaryText,
			JTextField resolveTimeText, JTextField minutesSpentText, JTextField resolvedByText, JTextField priorityText,
			JCheckBox resolvedCheck, JTextArea resolutionTextArea, JTextArea descriptionTextArea,
			JTextArea notesTextArea, JFrame frame)
	{
		// try to connect and execute query
		try
		{
			if (resolvedCheck.isSelected())
			{
			//	JOptionPane.showMessageDialog(frame, "The ticket is already resolved.");
			}
			if (minutesSpentText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(frame, "Please do not leave the minutes spent empty.");
				return;
			}
			else
			{
					try 
					{
				    int minutesSpent = Integer.parseInt(minutesSpentText.getText());
					} 
					catch (NumberFormatException e) 
					{
						JOptionPane.showMessageDialog(frame, "Please enter a number for the minutes spent field.");
				    return;
					}
			}
			if (resolvedByText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(frame, "Please do not leave the resolved by field empty.");
				return;
			}
			else
			{
				String resolvedBy = resolvedByText.getText();
			}
			if (resolutionTextArea.getText().equals(""))
			{
				JOptionPane.showMessageDialog(frame, "Please do not leave the resolution field empty.");
				return;
			}
			else
			{
				String resolution = resolutionTextArea.getText();
			}
			
			resolvedCheck.setSelected(true);
			this.update(conn, idText, customerText, summaryText, resolveTimeText, minutesSpentText, 
					resolvedByText, priorityText, resolvedCheck, resolutionTextArea, descriptionTextArea, notesTextArea, frame);
			
/*			
			String connectionUrl = "jdbc:sqlserver://ICSsql-1;databaseName=AdventureWorksS1;";
			String user = "StudentS1";
			String pass = "SuperSecurePassword17";

			conn = DriverManager.getConnection(connectionUrl, user, pass);

Add to this query to also update:
    minutes spent using the minutesSpent variable
    the resolved by field using the resolvedBy variable
    the resolution using the resolution variables

			// sql code to update resolved field data in database
			String SQL = "UPDATE AdventureWorksS1.HelpDesk.Ticket " + "  SET Resolved = 1 " + "WHERE TicketID = "
					+ idText.getText();

			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.executeUpdate();
			resolvedCheck.setSelected(true); */
		}
		// if not able to execute query, print stack trace 
		catch (Exception e)
		{

			e.printStackTrace();
		}
	}// end resolve

	/*
	 * The create method should add a new row to the ticket table with the
	 * information provided. I would think we would need null field checking so
	 * it doesn't break the database
	 */
	public void create(Connection conn, JTextField idText, JTextField customerText, JTextField summaryText, 
	JTextField resolveTimeText, JTextField minutesSpentText, JTextField resolvedByText, JTextField priorityText, 
	JCheckBox resolvedCheck, JTextArea resolutionTextArea, JTextArea descriptionTextArea, JTextArea notesTextArea, JFrame frame) 
	{
		try {
            String connectionUrl = "jdbc:sqlserver://ICSsql-1;databaseName=AdventureWorksS1;";
            String user = "StudentS1";
            String pass = "SuperSecurePassword17";
            
            conn = DriverManager.getConnection(connectionUrl, user, pass);
            
            //String ticketID = idText.getText();
            String customerID = null;
            String summary = null;
            String description = null;
            String priority = null;
            
            if (!idText.getText().equals(""))
            {
            	JOptionPane.showMessageDialog(frame, "Please do not manually enter a ticket number. It will be added automatically.");
          		return;
            }
            
            if (customerText.getText().equals(""))
            {
            		JOptionPane.showMessageDialog(frame, "Please fill out the customer field.");
            		return;
            }
            else
            {
            		customerID = customerText.getText();
            }
            
            if (summaryText.getText().equals(""))
            {
            		JOptionPane.showMessageDialog(frame, "Please fill out the summary field.");
            		return;
            }
            else
            {
            		summary = summaryText.getText();
            }
            
            if (descriptionTextArea.getText().equals(""))
            {
            		JOptionPane.showMessageDialog(frame, "Please fill out the description field.");
            		return;
            }
            else
            {
            		description = descriptionTextArea.getText();
            }
            
            if (priorityText.getText().equals(""))
            {
            		JOptionPane.showMessageDialog(frame, "Please fill out the priority field with a 1, 2, or 3.");
            		return;
            }
            else if (priorityText.getText().equals("1") || priorityText.getText().equals("2") || priorityText.getText().equals("3"))
            {
            		priority = priorityText.getText();
            }
            else
            {
            		JOptionPane.showMessageDialog(frame, "Please fill out the priority field with a 1, 2, or 3.");
            		return;
            }
            
            int id = 0;
            
            String SQL = 
         		   "SELECT CustomerID"
         		   + " FROM AdventureWorksS1.HelpDesk.Customers"
         		   + " WHERE FirstName =" + "'" + customerID + "'";
            
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                //Retrieve by column name
                id  = rs.getInt("CustomerID");
            }
            if (id == 0)
            {
            	JOptionPane.showMessageDialog(frame, "No such customer.");
            	return;
            }
            else
            {
            	System.out.println("found customer id: "+ id);
            }
            
            
          //sql code to create ticket in database
     		String SQL2 = 
            		   "INSERT INTO AdventureWorksS1.HelpDesk.Ticket (CustomerID, Summary,  Description, Priority, ResolvedBy)" + 
            		   "  VALUES (" + "'" + id + "'" + "," + "'" + summary + "'" + "," +  "'"  + description + "'"
            		   + "," + "'" + priority + "'" + "," + "'" + "4" + "'" + ")";
            		   

     		PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
     		pstmt2.executeUpdate();
		
     		JOptionPane.showMessageDialog(frame, "Ticket has been created");
		
	}catch (Exception e)
		{
		JOptionPane.showMessageDialog(frame, "Unknown error.");
		e.printStackTrace();
	}//end create
	}

	public void report1(Connection conn, JTextField reportText, JTextArea reportTextArea)
	{
		try
		{
			String connectionUrl = "jdbc:sqlserver://ICSsql-1;databaseName=AdventureWorksS1;";
			String user = "StudentS1";
			String pass = "SuperSecurePassword17";

			Statement stmt = null;
			ResultSet rs = null;

			conn = DriverManager.getConnection(connectionUrl, user, pass);

			// sql code to retrieve data from database
			String SQL = "select * from AdventureWorksS1.HelpDesk.Ticket "
					+ "JOIN AdventureWorksS1.HelpDesk.Customers on AdventureWorksS1.HelpDesk.Ticket.CustomerID = AdventureWorksS1.HelpDesk.Customers.CustomerID";

			// create the query and execute it
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			String ticketIds = "";
			String description = "";
			String resolved = "";
			String displayData = "";
			
			// set the GUI fields based on the fields returned from query
			while (rs.next())
			{
				ticketIds = rs.getString("TicketID");
				description = rs.getString("Description");
				if(rs.getString("Resolved").equals("1"))
				{
					resolved = "Resolved";
				}
				else
				{
					resolved = "Open";
				}
				displayData += "Ticket ID: " + ticketIds + "\nDescription: " + description + "\nStatus: " + resolved + "\n\n";
			}
			reportTextArea.setText(displayData);
			reportText.setText("All Tickets");
			// close the result set
			rs.close();
		}
		// if not able to execute query, print stack trace
		catch (Exception e)
		{

			e.printStackTrace();
		}
	}// end report1

	public void report2(Connection conn, JTextField reportText, JTextArea reportTextArea)
	{
		try
		{
			String connectionUrl = "jdbc:sqlserver://ICSsql-1;databaseName=AdventureWorksS1;";
			String user = "StudentS1";
			String pass = "SuperSecurePassword17";

			Statement stmt = null;
			ResultSet rs = null;

			conn = DriverManager.getConnection(connectionUrl, user, pass);

			// sql code to retrieve data from database
			String SQL = "select TicketID, Description, Resolved from AdventureWorksS1.HelpDesk.Ticket "
					+ "JOIN AdventureWorksS1.HelpDesk.Customers on AdventureWorksS1.HelpDesk.Ticket.CustomerID = AdventureWorksS1.HelpDesk.Customers.CustomerID "
					+ "WHERE AdventureWorksS1.HelpDesk.Ticket.Resolved = '0'";

			// create the query and execute it
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			String ticketIds = "";
			String description = "";
			String resolved = "";
			String displayData = "";
			
			// set the GUI fields based on the fields returned from query
			while (rs.next())
			{
				ticketIds = rs.getString("TicketID");
				description = rs.getString("Description");
				if(rs.getString("Resolved").equals("1"))
				{
					resolved = "Resolved";
				}
				else
				{
					resolved = "Open";
				}
				displayData += "Ticket ID: " + ticketIds + "\nDescription: " + description + "\nStatus: " + resolved + "\n\n";
			}
			reportTextArea.setText(displayData);
			reportText.setText("Open Tickets");
			// close the result set
			rs.close();
		}
		// if not able to execute query, print stack trace
		catch (Exception e)
		{

			e.printStackTrace();
		}
	}// end report2

	public void report3(Connection conn, JTextField reportText, JTextArea reportTextArea)
	{
		try
		{
			String connectionUrl = "jdbc:sqlserver://ICSsql-1;databaseName=AdventureWorksS1;";
			String user = "StudentS1";
			String pass = "SuperSecurePassword17";

			Statement stmt = null;
			ResultSet rs = null;

			conn = DriverManager.getConnection(connectionUrl, user, pass);

			// sql code to retrieve data from database
			String SQL = "select TicketID, Description, Resolved from AdventureWorksS1.HelpDesk.Ticket "
					+ "JOIN AdventureWorksS1.HelpDesk.Customers on AdventureWorksS1.HelpDesk.Ticket.CustomerID = AdventureWorksS1.HelpDesk.Customers.CustomerID "
					+ "WHERE AdventureWorksS1.HelpDesk.Ticket.Resolved = '1'";

			// create the query and execute it
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			String ticketIds = "";
			String description = "";
			String resolved = "";
			String displayData = "";
			
			// set the GUI fields based on the fields returned from query
			while (rs.next())
			{
				ticketIds = rs.getString("TicketID");
				description = rs.getString("Description");
				if(rs.getString("Resolved").equals("1"))
				{
					resolved = "Resolved";
				}
				else
				{
					resolved = "Open";
				}
				displayData += "Ticket ID: " + ticketIds + "\nDescription: " + description + "\nStatus: " + resolved + "\n\n";
			}
			reportTextArea.setText(displayData);
			reportText.setText("Resolved Tickets");
			// close the result set
			rs.close();
		}
		// if not able to execute query, print stack trace
		catch (Exception e)
		{

			e.printStackTrace();
		}
	}// end report3

	public void report4(Connection conn, JTextField reportText, JTextArea reportTextArea)
	{
		try
		{
			String connectionUrl = "jdbc:sqlserver://ICSsql-1;databaseName=AdventureWorksS1;";
			String user = "StudentS1";
			String pass = "SuperSecurePassword17";

			Statement stmt = null;
			ResultSet rs = null;

			conn = DriverManager.getConnection(connectionUrl, user, pass);

			// sql code to retrieve data from database
			String SQL = "select Count(SupportID) as Support, Support.FirstName as FirstName, Support.LastName as LastName, Support.SupportTeam as Team from AdventureWorksS1.HelpDesk.Ticket "
					+ "JOIN AdventureWorksS1.HelpDesk.Customers on AdventureWorksS1.HelpDesk.Ticket.CustomerID = AdventureWorksS1.HelpDesk.Customers.CustomerID "
					+ "JOIN AdventureWorksS1.HelpDesk.Support on AdventureWorksS1.HelpDesk.Ticket.ResolvedBy =  AdventureWorksS1.HelpDesk.Support.SupportID "
					+ "WHERE AdventureWorksS1.HelpDesk.Ticket.Resolved = '1' "
					+ "GROUP BY Support.FirstName, Support.LastName, Support.SupportTeam";
					
			// create the query and execute it
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			String count = "";
			String firstName = "";
			String lastName = "";
			String team = "";
			String displayData = "";
			
			// set the GUI fields based on the fields returned from query
			while (rs.next())
			{
				count = rs.getString("Support");
				firstName = rs.getString("FirstName");
				lastName = rs.getString("LastName");
				team = rs.getString("Team");
				displayData += "Employee: " + firstName + " " + lastName + "\nTeam: " + team + "\nNumber of Tickets Resolved: " + count + "\n\n";
			}
			reportTextArea.setText(displayData);
			reportText.setText("Resolved by Employee");
			// close the result set
			rs.close();
		}
		// if not able to execute query, print stack trace
		catch (Exception e)
		{

			e.printStackTrace();
		}
	}// end report4

	public void report5(Connection conn, JTextField reportText, JTextArea reportTextArea)
	{
		try
		{
			String connectionUrl = "jdbc:sqlserver://ICSsql-1;databaseName=AdventureWorksS1;";
			String user = "StudentS1";
			String pass = "SuperSecurePassword17";

			Statement stmt = null;
			ResultSet rs = null;

			conn = DriverManager.getConnection(connectionUrl, user, pass);

			// sql code to retrieve data from database
			String SQL = "select Count(SupportID) as Support, Support.SupportTeam as Team from AdventureWorksS1.HelpDesk.Ticket "
					+ "JOIN AdventureWorksS1.HelpDesk.Customers on AdventureWorksS1.HelpDesk.Ticket.CustomerID = AdventureWorksS1.HelpDesk.Customers.CustomerID "
					+ "JOIN AdventureWorksS1.HelpDesk.Support on AdventureWorksS1.HelpDesk.Ticket.ResolvedBy =  AdventureWorksS1.HelpDesk.Support.SupportID "
					+ "WHERE AdventureWorksS1.HelpDesk.Ticket.Resolved = '1' "
					+ "GROUP BY Support.SupportTeam";
					
			// create the query and execute it
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			String count = "";
			String team = "";
			String displayData = "";
			
			// set the GUI fields based on the fields returned from query
			while (rs.next())
			{
				count = rs.getString("Support");
				team = rs.getString("Team");
				displayData += "Team: " + team + "\nNumber of Tickets Resolved: " + count + "\n\n";
			}
			reportTextArea.setText(displayData);
			reportText.setText("Resolved by Team");
			// close the result set
			rs.close();
		}
		// if not able to execute query, print stack trace
		catch (Exception e)
		{

			e.printStackTrace();
		}
	}// end report5

}// end Queries
