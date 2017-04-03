import java.sql.Connection;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Queries 
{

	/*
	  this method should search for a ticket but ticket ID number
	  and fill the fields in the GUI appropriately. I believe that
	  I passed in all the fields in the listener methods in the GUI
	  createTicketScreen() method, but I may have missed one.
	*/
	public void search(Connection conn, JTextField idText, JTextField customerText, JTextField summaryText, 
			JTextField resolveTimeText, JTextField minutesSpentText, JTextField resolvedByText, JTextField priorityText, 
			JCheckBox resolvedCheck, JTextArea resolutionTextArea, JTextArea descriptionTextArea, JTextArea notesTextArea) 
	{
		System.out.println("Search worked");
		idText.setText("It worked!");
	}//end search

	
	/*
	   The update method should search for the data by ticket ID number
	   and update each column with the new values in the GUI
	 */
	public void update(Connection conn, JTextField idText, JTextField customerText, JTextField summaryText, 
			JTextField resolveTimeText, JTextField minutesSpentText, JTextField resolvedByText, JTextField priorityText, 
			JCheckBox resolvedCheck, JTextArea resolutionTextArea, JTextArea descriptionTextArea, JTextArea notesTextArea) 
	{
		System.out.println("Update worked");
	}//end update

	
	/*
	   The resolve method should search for the ticket by ticket ID
	   and change the bit field to 1 for resolve. 0 can be for not 
	   resolved. This should also check the box in the GUI.
	 */
	public void resolve(Connection conn, JTextField idText, JTextField customerText, JTextField summaryText, 
			JTextField resolveTimeText, JTextField minutesSpentText, JTextField resolvedByText, JTextField priorityText, 
			JCheckBox resolvedCheck, JTextArea resolutionTextArea, JTextArea descriptionTextArea, JTextArea notesTextArea) 
	{
		System.out.println("Resolve worked");
	}//end resolve


	/*
	   The create method should add a new row to the ticket table
	   with the information provided. I would think we would need null
	   field checking so it doesn't break the database
	 */
	public void create(Connection conn, JTextField idText, JTextField customerText, JTextField summaryText, 
	JTextField resolveTimeText, JTextField minutesSpentText, JTextField resolvedByText, JTextField priorityText, 
	JCheckBox resolvedCheck, JTextArea resolutionTextArea, JTextArea descriptionTextArea, JTextArea notesTextArea) 
	{
		System.out.println("Create worked");
	}//end create


	public void report1(Connection conn, JTextField reportText,
			JTextArea reportTextArea) 
	{
		System.out.println("Reprt 1 worked");
	}//end report1
	
	public void report2(Connection conn, JTextField reportText,
			JTextArea reportTextArea) 
	{
		System.out.println("Reprt 2 worked");
	}//end report2
	
	public void report3(Connection conn, JTextField reportText,
			JTextArea reportTextArea) 
	{
		System.out.println("Reprt 3 worked");
	}//end report3
	
	public void report4(Connection conn, JTextField reportText,
			JTextArea reportTextArea) 
	{
		System.out.println("Reprt 4 worked");
	}//end report4
	
	public void report5(Connection conn, JTextField reportText,
			JTextArea reportTextArea) 
	{
		System.out.println("Reprt 5 worked");
	}//end report5

}//end Queries
