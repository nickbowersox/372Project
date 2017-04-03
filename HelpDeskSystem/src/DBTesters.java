import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//This class is only used to test if the program
//properly connects to the database. If you get the
//database metadata, it is working. The actual driver
//will be in the Main class
public class DBTesters {

	public static void main(String[] args) 
	{
		 Connection conn = null;
		 
	        try {
	 
	            String dbURL = "jdbc:sqlserver://ICSsql-1;databaseName=AdventureWorksS1;";
	            String user = "StudentS1";
	            String pass = "SuperSecurePassword17";
	            conn = DriverManager.getConnection(dbURL, user, pass);
	            if (conn != null) {
	                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
	                System.out.println("Driver name: " + dm.getDriverName());
	                System.out.println("Driver version: " + dm.getDriverVersion());
	                System.out.println("Product name: " + dm.getDatabaseProductName());
	                System.out.println("Product version: " + dm.getDatabaseProductVersion());
	                //Query1(conn);
	            }
	 
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (conn != null && !conn.isClosed()) {
	                    conn.close();
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }	// TODO Auto-generated method stub

	        }
	        
	       
	}
	
}
