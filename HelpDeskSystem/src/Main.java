
public class Main 
{
	public static void main(String[] args)
	{
		GUI gui = new GUI();
		Queries	queries = new Queries();
		Connections conn = new Connections();
		gui.createTicketScreen(queries, conn);
		
	}//end main
}//end Main