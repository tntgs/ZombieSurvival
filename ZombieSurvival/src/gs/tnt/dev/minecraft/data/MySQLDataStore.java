/**
 * 
 */
package gs.tnt.dev.minecraft.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.configuration.MemorySection;

/**
 * @author ted
 *
 * This class is meant to be a plugin-independent data source.
 */
public class MySQLDataStore extends DataStore
{
	private String ourDSN;
	private Connection sqlConnection;
	
	public byte startup(MemorySection settings)
	{
		ourDSN = "jdbc:mysql://" + settings.getString("host") + ":" + settings.getString("port")+ "/" + settings.getString("db");
		//tablePrefix = settings.getString("tablenameprefix");
		
		/*
		 * Establish (persistent) SQL connection to server
		 */
		try
		{
			this.sqlConnection = DriverManager.getConnection(ourDSN, settings.getString("user"), settings.getString("pass"));
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
  					`zaZombieLimit` smallint(6) NOT NULL DEFAULT '2000',
  					PRIMARY KEY (`worldName`)
				) ENGINE=MyISAM DEFAULT CHARSET=latin1;
			 *
		}		
		
		return -1;
		
		*/
	}
	
	/**
	 * 
	 * @return
	 */
	public int getTableCount()
	{
		int rowCount = -1;
		
		try
		{			
			ResultSet rsShowTables = executeQuery("SHOW TABLES");
			
			rowCount = this.getRowCountFromRS(rsShowTables);
			
			rsShowTables.close();
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			return -1; // Return code 2 indicates a database validation error
		}
		
		return rowCount;
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

		return rowCount;
	}
	
	/**
	 * 
	 * @param tableName The name of the table to check for
	 * @return 0 if table exists, 1 if it doesn't exist, less than 0 indicates an underlying error
	 */
	public byte checkIfTableExists(String tableName)
	{
		try
		{
			if (this.sqlConnection.isValid(1) == false)
			{
				// Connection is invalid for some reason or another
				return -1;
			}			
		}
		catch (SQLException e)
		{
			// SQLException was thrown when attempting to check SQL connection
			return -2;
		}
		
		PreparedStatement ourQuery;
		try
		{
			ourQuery = this.sqlConnection.prepareStatement("SHOW TABLES LIKE \"" + tableName + "\"");
		}
		catch (SQLException e)
		{
			System.out.println(e.toString());
			// SQLException was thrown when attempting to prepare a SQL statement
			return -3;
		}
		
		try
		{
			if (ourQuery.execute() == true)
			{
				// Success
				switch (this.getRowCountFromRS(ourQuery.getResultSet()))
				{
					case 0:
						try
						{
							ourQuery.close();
						}
						catch (SQLException e)
						{
							// We'll ignore this, although we will alert the user
							System.out.println(e.toString());
						}
						return 1;
						
					case 1:
						try
						{
							ourQuery.close();
						}
						catch (SQLException e)
						{
							// We'll ignore this, although we will alert the user
							System.out.println(e.toString());
						}
						return 0;
						
					default:
						try
						{
							ourQuery.close();
						}
						catch (SQLException ee)
						{
							// Fudgepacker!!
							System.out.println(ee.toString());
						}
							
						// We got an odd number of results
						return -5;
				}
			}
			else
			{
				// Non-Success
				try
				{
					ourQuery.close();
				}
				catch (SQLException e)
				{
					// We'll ignore this, although we will alert the user
					System.out.println(e.toString());
				}
				return -6;
			}
		}
		catch (SQLException e)
		{
			System.out.println(e.toString());
			
			try
			{
				ourQuery.close();
			}
			catch (SQLException ee)
			{
				// Fudgepacker!!
				System.out.println(ee.toString());
			}
			
			// SQLException thrown when executing our query
			return -4;
		}
	}
	
	/**
	 * 
	 * @param sqlQuery
	 * @return
	 */
	public byte execute(String sqlQuery)
	{
		try
		{
			if (this.sqlConnection.isValid(1) == false)
			{
				// Connection is invalid for some reason or another
				return 1;
			}			
		}
		catch (SQLException e)
		{
			// SQLException was thrown when attempting to check SQL connection
			return 2;
		}
		
		PreparedStatement ourQuery;
		try
		{
			ourQuery = this.sqlConnection.prepareStatement(sqlQuery);
		}
		catch (SQLException e)
		{
			System.out.println(e.toString());
			// SQLException was thrown when attempting to prepare a SQL statement
			return 3;			
		}
		
		try
		{
			if (ourQuery.execute() == true)
			{
				// Success
				try
				{
					ourQuery.close();
				}
				catch (SQLException e)
				{
					// We'll ignore this, although we will alert the user
					System.out.println(e.toString());
				}
				return 0;
			}
			else
			{
				// Non-Success
				try
				{
					ourQuery.close();
				}
				catch (SQLException e)
				{
					// We'll ignore this, although we will alert the user
					System.out.println(e.toString());
				}
				return -1;
			}
		}
		catch (SQLException e)
		{
			System.out.println(e.toString());
			
			try
			{
				ourQuery.close();
			}
			catch (SQLException ee)
			{
				// Fudgepacker!!
				System.out.println(ee.toString());
			}
			
			// SQLException thrown when executing our query
			return 4;
		}
	}
	
	/**
	 * 
	 * @param sqlQuery
	 * @return
	 */
	public int executeAndReturnRowCount(String sqlQuery)
	{
		int rowCount = -1;
		
		try
		{
			if (this.sqlConnection.isValid(1) == false)
			{
				// Connection is invalid for some reason or another
				return -1;
			}			
		}
		catch (SQLException e)
		{
			// SQLException was thrown when attempting to check SQL connection
			return -2;
		}
		
		PreparedStatement ourQuery;
		try
		{
			ourQuery = this.sqlConnection.prepareStatement(sqlQuery);
		}
		catch (SQLException e)
		{
			System.out.println(e.toString() + "\n" +  e.getStackTrace().toString());
			// SQLException was thrown when attempting to prepare a SQL statement
			return -3;
		}
		
		try
		{
			ourQuery.execute();
			
			rowCount = this.getRowCountFromRS(ourQuery.getResultSet());
				
			if (rowCount < 0)
			{
				try
				{
					ourQuery.close();
				}
				catch (SQLException e)
				{
					// We'll ignore this, although we will alert the user
					System.out.println(e.toString() + "\n" +  e.getStackTrace().toString());
				}
				return -4;
			}
			
			try
			{
				ourQuery.close();
			}
			catch (SQLException e)
			{
				// We'll ignore this, although we will alert the user
				System.out.println(e.toString() + "\n" +  e.getStackTrace().toString());
			}
			
			return rowCount;
		}
		catch (SQLException e)
		{
			System.out.println(e.toString() + "\n" +  e.getStackTrace().toString());
			
			try
			{
				ourQuery.close();
			}
			catch (SQLException ee)
			{
				// Fudgepacker!!
				System.out.println(ee.toString() + "\n" +  ee.getStackTrace().toString());
			}
			
			// SQLException thrown when executing our query
			return -5;
		}
	}
	
	public int fetchIntFromQuery(String sqlQuery) throws Exception
	{
		int rowCount = -1;
		int result = -1;
		
		try
		{
			if (this.sqlConnection.isValid(1) == false)
			{
				// Connection is invalid for some reason or another
				throw new InvalidStateException("SQL connection is not valid");
			}			
		}
		catch (SQLException e)
		{
			// SQLException was thrown when attempting to check SQL connection
			throw e;
		}
		
		PreparedStatement ourQuery;
		try
		{
			ourQuery = this.sqlConnection.prepareStatement(sqlQuery);
		}
		catch (SQLException e)
		{
			// SQLException was thrown when attempting to prepare a SQL statement
			throw e;
		}
		
		try
		{
			ourQuery.execute();
			ResultSet ourRS = ourQuery.getResultSet();
			rowCount = this.getRowCountFromRS(ourRS);

			switch (rowCount)
			{
				case 0:
					result = 0;
					break;
					
				case 1:
					ourRS.first();
					result = ourRS.getInt(1);
					break;
					
				default:
					break;
					
			}
			
			try
			{
				ourQuery.close();
			}
			catch (SQLException e)
			{
				// We'll ignore this, although we will alert the user
				System.out.println(e.toString() + "\n" +  e.getStackTrace().toString());
			}
			
			return result;
		}
		catch (SQLException e)
		{
			System.out.println(e.toString());
			
			try
			{
				ourQuery.close();
			}
			catch (SQLException ee)
			{
				// Fudgepacker!!
				System.out.println(ee.toString() + "\n" +  ee.getStackTrace().toString());
			}
			
			// SQLException thrown when executing our query
			throw e;
		}
		
		
		
	}
}
