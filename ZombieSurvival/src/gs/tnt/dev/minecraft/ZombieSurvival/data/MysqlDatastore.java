/**
 * 
 */
package gs.tnt.dev.minecraft.ZombieSurvival.data;

import gs.tnt.dev.minecraft.data.MySQLDataStore;

import java.util.logging.Logger;

import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.SQLException;

/**
 * @author ted
 *
 */
public class MysqlDatastore extends Datastore
{
	private MySQLDataStore sqlDS;
	private String sqlTablePrefix;
	
	public MysqlDatastore(JavaPlugin lpPlugin) throws SQLException
	{
		/*
		 * Create under-lying MySQL data connection
		 * Initialize class variables
		 */
		super.ourPlugin = lpPlugin;
		this.sqlDS = new MySQLDataStore();
		
		// as well as constructor-scope variables
		FileConfiguration ourConfig;
		Logger ourLogger;
		
		ourConfig = super.ourPlugin.getConfig();
		ourLogger = super.ourPlugin.getLogger();
		
		/*
		 * Initialize that data connection...
		 */
		int result = sqlDS.startup((MemorySection) ourConfig.get("mysql"));
		switch (result)
		{
			case 0:		// Successful initialization
				ourLogger.info("Datastore connection established");
				break;
				
			default:	// Other than 0 indicates an unsuccessful initialization
				ourLogger.severe("Could not initialize datastore connection !!");
				throw new SQLException();
		}
		
		/*
		 * Store table prefix for all future queries
		 */
		this.sqlTablePrefix = ourConfig.getString("mysql.tablenameprefix");
		
		/*
		 * Check for zs_worlds table
		 *   and create it if it doesn't exist
		 */
		switch (this.sqlDS.checkIfTableExists(this.sqlTablePrefix + "worlds"))
		{
			case 0:
				break;
				
			case 1:
				result = this.sqlDS.execute("CREATE TABLE `zs_worlds` (\n`worldName` varchar(255) NOT NULL,\n`zsEnabled` tinyint(1) NOT NULL DEFAULT '0',\n`zaZombieLimit` smallint(6) NOT NULL DEFAULT '2000',\nPRIMARY KEY (`worldName`)\n) ENGINE=MyISAM DEFAULT CHARSET=latin1;");
				switch (result)
				{
					case -1:
					case 0:
						if (this.sqlDS.checkIfTableExists(this.sqlTablePrefix + "worlds") != 0)
						{
							ourLogger.severe("Could not verify creation of worlds table in SQL");
							throw new SQLException();
						}
						break;
						
					default:
						ourLogger.severe("Could not create the worlds table in SQL");
						throw new SQLException();
				}
				
			default:
				ourLogger.severe("Could not verify the worlds table in SQL");
				throw new SQLException();
		}
	}
	
	public byte shutdown()
	{
		return this.sqlDS.shutdown();
	}
	
	public Boolean addWorld(String worldName)
	{
		return this.addWorld(worldName, false);
	}
	
	public Boolean addWorld(String worldName, Boolean isEnabled)
	{
		String bEnabled;
		byte result;
		if (isEnabled == true) { bEnabled = "1"; } else { bEnabled = "0"; }
		
		result = this.sqlDS.execute("INSERT INTO `" + this.sqlTablePrefix + "worlds` (`worldName`, `zsEnabled`, `zsZombieLimit`) VALUES ('" + worldName + "', '" + bEnabled + "', '2000')");
		
		System.out.println("fdas:" + result);
		return false;
	}
	
	public Boolean removeWorld(String worldName)
	{
		return false;
	}
	
	public Boolean enableWorld(String worldName)
	{
		return false;
	}
	
	public Boolean disableWorld(String worldName)
	{
		return false;
	}
	
	public Boolean isWorldAdded(String worldName)
	{
		int rowCount = this.sqlDS.executeAndReturnRowCount("SELECT * FROM `" + this.sqlTablePrefix + "worlds` WHERE `worldName` = '" + worldName + "'");
		if (rowCount <= 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public Boolean isWorldEnabled(String worldName)
	{
		try
		{
			int result = this.sqlDS.fetchIntFromQuery("SELECT `zsEnabled` FROM `" + this.sqlTablePrefix + "worlds` WHERE `" + this.sqlTablePrefix + "worlds`.`worldName` = '" + worldName + "'");
			switch (result)
			{
				case 0:
					return false;
				
				case 1:
					return true;
				
				default:
					return false;
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception at isWorldEnabled(\"" + worldName + "\"): " + e.toString());
			return false;
		}
	}
	
	public int getZombieLimitByWorld(String worldName)
	{
		return 2000;
	}
	
	public Boolean setZombieLimitForWorld(String worldName, int iNewLimit)
	{
		return false;
	}
}
