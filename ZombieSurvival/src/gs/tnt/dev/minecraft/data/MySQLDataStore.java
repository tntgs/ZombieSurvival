/**
 * 
 */
package gs.tnt.dev.minecraft.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.configuration.MemorySection;

/**
 * @author ted
 *
 */
public class MySQLDataStore extends DataStore
{
	private String ourDSN;
	private String tablePrefix;
	private Connection sqlConnection;
	
	public byte startup(MemorySection settings)
	{
		ourDSN = "jdbc:mysql://" + settings.getString("host") + ":" + settings.getString("port")+ "/" + settings.getString("db");
		tablePrefix = settings.getString("tablenameprefix");
		
		/*
		 * Establish (persistent) SQL connection to server
		 */
		try
		{
			sqlConnection = DriverManager.getConnection(ourDSN, settings.getString("user"), settings.getString("pass"));
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			return 1; // Return code 1 indicates a connection related error  
		}
		
		return 0;
		
		/*
		 * Get the number of tables that our selected database contains
		 * Enumerate each of the table names, and compare them to see if they 
		 * match the ones we are need.
		 */

		/*
		
		Boolean bFoundTableWorlds = false;
		int rowCount = -1;
		
		try
		{			
			ResultSet rsShowTables = executeQuery("SHOW TABLES");
			
			rowCount = this.getRowCountFromRS(rsShowTables);
			
			switch (rowCount)
			{
				case -1:
					break;
					
				case 0:
					break;
					
				default:
					while (rsShowTables.next())
					{
						String tableName = rsShowTables.getString(1);
						if (tableName.compareToIgnoreCase(tablePrefix + "worlds") == 0)
						{
							bFoundTableWorlds = true;
						}
						else
						{
							System.out.println(rsShowTables.getString(1));
						}
					}
					break;
			}
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			return 2; // Return code 2 indicates a database validation error
		}
		
		 *
		 * If _worlds table is missing, create it
		 *
		 *
		if (bFoundTableWorlds == false)
		{
			*
			 	CREATE TABLE `za_worlds` (
  					`worldName` varchar(255) NOT NULL,
  					`zaEnabled` tinyint(1) NOT NULL DEFAULT '0',
  					PRIMARY KEY (`worldName`)
				) ENGINE=MyISAM DEFAULT CHARSET=latin1;
			 *
		}		
		
		return -1;
		
		*/
	}
	
	public byte shutdown()
	{
		try
		{
			sqlConnection.close();
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			return 1;
		}
		
		return 0;
	}
	
	public String loadSetting(String szSetting)
	{
		return "";
	}
	
	public byte saveSetting(String szSetting, String szValue)
	{
		return 0;
	}
	
	/**
	 * 
	 * @param szQuery The SQL query to execute
	 * @return the ResultSet containing the results
	 */
	@SuppressWarnings("unused")
	private ResultSet executeQuery(String szQuery)
	{
		try
		{
			PreparedStatement ps = sqlConnection.prepareStatement(szQuery);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			
			return rs;
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			return null;
		}
	}
	
	/**
	 * 
	 * @param rs The ResultSet that was returned from an execute() call
	 * @return The number of rows that the result contains
	 */
	@SuppressWarnings({ "finally", "unused" })
	private int getRowCountFromRS(ResultSet rs)
	{
		int rowCount = -1;
		int curRow;
		
		try
		{
			curRow = rs.getRow();
			
			if (rs.last() == false)
			{
				rowCount = 0;
			}
			else
			{
				rowCount = rs.getRow();
				
				rs.first();
				rs.relative(curRow-1);
			}
			
			return rowCount;
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			return rowCount;
		}
	}
}
