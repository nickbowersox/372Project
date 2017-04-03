import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connections
{
	private Connection conn = null; 
	
	public Connection getConnections()
	{
        try 
        {
 
            String dbURL = "jdbc:sqlserver://ICSsql-1;databaseName=AdventureWorksS1;";
            String user = "StudentS1";
            String pass = "SuperSecurePassword17";
            conn = DriverManager.getConnection(dbURL, user, pass);
            return conn;
 
        }//end try
        catch (SQLException ex) {
            ex.printStackTrace();
        }//end catch
		return conn;
	}//end getConnections
	
	public void closeConnection()
	{
		try 
		{
			conn.close();
		}//end try 
		catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//end closeConnection

}//end Connections
