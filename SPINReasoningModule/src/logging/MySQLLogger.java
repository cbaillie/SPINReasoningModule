/**
 * Class for logging reasoning metrics to a MySQL database
 * 
 * @author Chris (c.baillie@abdn.ac.uk)
 */

package logging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySQLLogger {
	
	private Connection mysqlCon;
	
	/**
	 * Constructor
	 * 
	 * @param url The url of the MySQL database
	 * @param user Username for the database
	 * @param password Password for the database
	 */
	public MySQLLogger(String url, String user, String password)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			mysqlCon = DriverManager.getConnection(url, user, password);
			System.out.println("[MySQLLogger] Connection created");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * Perform an update query
	 * 
	 * @param updateQuery The query to be executed
	 * @return boolean indicating success
	 */
	public boolean doMySQLInsert(String updateQuery)
	{
		try
		{
			Statement stmt = mysqlCon.createStatement();
			if(stmt.executeUpdate(updateQuery) > 0)
				return true;
		}
		catch(Exception ex)
		{
			System.out.println("[MySQLLogger] doMySQLInsert : Update failed");
			ex.printStackTrace();
		}
		return false;
	}

}
