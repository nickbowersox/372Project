import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JCheckBox;
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
			JTextArea notesTextArea)
	{
		// try to connect and execute query
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
					+ "JOIN AdventureWorksS1.HelpDesk.Support on AdventureWorksS1.HelpDesk.Ticket.ResolvedBy = AdventureWorksS1.HelpDesk.Support.SupportID "
					+ "JOIN AdventureWorksS1.HelpDesk.Customers on AdventureWorksS1.HelpDesk.Ticket.CustomerID = AdventureWorksS1.HelpDesk.Customers.CustomerID "
					+ "where AdventureWorksS1.HelpDesk.Ticket.TicketID = " + idText.getText();

			// create the query and execute it
			stmt = conn.createStatement();
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
				String resolver = rs.getString(13) + " " + rs.getString(14);
				resolvedByText.setText(resolver);
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
			JTextArea notesTextArea)
	{
		try {
            String connectionUrl = "jdbc:sqlserver://ICSsql-1;databaseName=AdventureWorksS1;";
            String user = "StudentS1";
            String pass = "SuperSecurePassword17";
            
            conn = DriverManager.getConnection(connectionUrl, user, pass);
            
            String ticketID = idText.getText();
            String customerID = customerText.getText();
            String summary = summaryText.getText();
            String description = descriptionTextArea.getText();
            String priority = priorityText.getText();
            String resolveTime = resolveTimeText.getText();
            String minutesSpent = minutesSpentText.getText();
            String resolvedBy = resolvedByText.getText();
            String isResolved  = resolvedCheck.isSelected() ? "1" : "0";
            String resolution = resolutionTextArea.getText();
            String notes = notesTextArea.getText();
            
          //sql code to create ticket in database
            String SQL = 
            		   "UPDATE AdventureWorksS1.HelpDesk.Ticket SET CustomerID = " + customerID + "," + "Summary = " + "'" + summary + "'"
            		   + "," + "Description="  +  "'" + description + "'" + "," +  "Resolution=" + "'" + resolution + "'" + ","  + "ResolutionTime =" + "'" +resolveTime +  "'" + "," + "Resolved=" + isResolved + "," + "ResolvedBy=" + "'" +  resolvedBy + "'" + "," + "Priority=" + priority + "," + "MinutesSpent=" + "'" + minutesSpent + "'" + "," + "Notes=" + "'" + notes + "'" + 
            		   "WHERE TicketID = " + ticketID;
            		   

     		PreparedStatement pstmt = conn.prepareStatement(SQL);
     		pstmt.executeUpdate();
		
		
		
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
			JTextArea notesTextArea)
	{
		// try to connect and execute query
		try
		{
			String connectionUrl = "jdbc:sqlserver://ICSsql-1;databaseName=AdventureWorksS1;";
			String user = "StudentS1";
			String pass = "SuperSecurePassword17";

			conn = DriverManager.getConnection(connectionUrl, user, pass);

			// sql code to update resolved field data in database
			String SQL = "UPDATE AdventureWorksS1.HelpDesk.Ticket " + "  SET Resolved = 1 " + "WHERE TicketID = "
					+ idText.getText();

			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.executeUpdate();
			resolvedCheck.setSelected(true);
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
	JCheckBox resolvedCheck, JTextArea resolutionTextArea, JTextArea descriptionTextArea, JTextArea notesTextArea) 
	{
		try {
            String connectionUrl = "jdbc:sqlserver://ICSsql-1;databaseName=AdventureWorksS1;";
            String user = "StudentS1";
            String pass = "SuperSecurePassword17";
            
            conn = DriverManager.getConnection(connectionUrl, user, pass);
            
            String ticketID = idText.getText();
            String customerID = customerText.getText();
            String summary = summaryText.getText();
            String description = descriptionTextArea.getText();
            String priority = priorityText.getText();
            
          //sql code to create ticket in database
            String SQL = 
            		   "INSERT INTO AdventureWorksS1.HelpDesk.Ticket (CustomerID, Summary,  Description, Priority)" + 
            		   "  VALUES (" + customerID + "," + "'" + summary + "'" + "," +  "'"  + description + "'"
            		   + "," + priority + ")";
            		   

     		PreparedStatement pstmt = conn.prepareStatement(SQL);
     		pstmt.executeUpdate();
		
		
		
		System.out.println("Create worked");
	}catch (Exception e)
		{

		e.printStackTrace();
	}//end create
	}

	public void report1(Connection conn, JTextField reportText, JTextArea reportTextArea)
	{
		System.out.println("Reprt 1 worked");
	}// end report1

	public void report2(Connection conn, JTextField reportText, JTextArea reportTextArea)
	{
		System.out.println("Reprt 2 worked");
	}// end report2

	public void report3(Connection conn, JTextField reportText, JTextArea reportTextArea)
	{
		System.out.println("Reprt 3 worked");
	}// end report3

	public void report4(Connection conn, JTextField reportText, JTextArea reportTextArea)
	{
		System.out.println("Reprt 4 worked");
	}// end report4

	public void report5(Connection conn, JTextField reportText, JTextArea reportTextArea)
	{
		System.out.println("Reprt 5 worked");
	}// end report5

}// end Queries
