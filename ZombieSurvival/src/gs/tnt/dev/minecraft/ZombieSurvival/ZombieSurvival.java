/**
 * 
 */
package gs.tnt.dev.minecraft.ZombieSurvival;

import gs.tnt.dev.minecraft.ZombieSurvival.data.Datastore;
import gs.tnt.dev.minecraft.ZombieSurvival.data.MysqlDatastore;
//import gs.tnt.dev.minecraft.data.MySQLDataStore;

import java.util.logging.Logger;
import java.util.List;
import org.bukkit.Server;
import org.bukkit.World;
//import org.bukkit.configuration.MemorySection;
//import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
 * @author ted
 *
 */
public class ZombieSurvival extends JavaPlugin
{
	private Boolean isEnabled = false;
	private Logger log;
	private Server server;
	private Datastore ds;
	
	/**
	 * 
	 */
	public void onEnable()
	{
		//byte result = 0;
		
		/*
		 * These variables will be used persistently throughout the lifetime of this plug-in.
		 */
		log = this.getLogger();
		server = this.getServer();
		
		/*
		 * Populate configuration with some default settings
		 */
		this.getConfig().addDefault("datastore.format", "mysql");
		this.getConfig().addDefault("mysql.host", "127.0.0.1");
		this.getConfig().addDefault("mysql.port", "3306");
		this.getConfig().addDefault("mysql.user", "user");
		this.getConfig().addDefault("mysql.pass", "pass");
		this.getConfig().addDefault("mysql.db", "db");
		this.getConfig().addDefault("mysql.tablenameprefix", "zs_");
		this.getConfig().addDefault("flatfile.path", "data.yml");
		this.getConfig().addDefault("sqlite.path", "data.db");
		
		/*
		 * This code below will create a default config.yml file is one hasn't been created yet
		 * Hopefully it doesn't override an existing one, unless it's FUBAR'ed
		 */
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		
		/*
		 * Load desired datastore format from config.yml file
		 *   and then initialize that data connection ...
		 */
		String dsFormat = this.getConfig().getString("datastore.format");
		if (dsFormat.compareToIgnoreCase("MYSQL") == 0) 
		{
			try
			{
				ds = new MysqlDatastore(this);
			}
			catch (Exception e)
			{
				this.getLogger().severe(e.toString());
				return;
			}
		}
		else if (dsFormat.compareToIgnoreCase("FLAT") == 0|| dsFormat.compareToIgnoreCase("FLATFILE") == 0)
		{
			log.severe("Flatfile data storage is not supported at this time.");
			return;
		}
		else if (dsFormat.compareToIgnoreCase("SQLITE") == 0)
		{
				log.severe("SQLite file-based data storage is not supported at this time.");
				return;
		}
		else
		{
			log.severe("An invalid data storage format was specified");
			return;
		}
		
		/*
		 * Testing code
		 */
		log.info("Enumerating loaded worlds ...");
		
		World thisWorld;
		List<World> worlds = server.getWorlds();
		for (int x = 1; x <= worlds.size(); x++)
		{
			thisWorld = worlds.get(x - 1);
			
			Boolean thisWorldEnabled = false;
			String thisWorldName = thisWorld.getName();
			if (this.ds.isWorldAdded(thisWorldName) == false)
			{
				thisWorldEnabled = false;
				this.ds.addWorld(thisWorldName, thisWorldEnabled);
			}
			else
			{
				thisWorldEnabled = this.ds.isWorldEnabled(thisWorldName);
			}
			
			log.info("world: <" + thisWorldName + "> type: " + thisWorld.getEnvironment().toString() + " monsters:<" + thisWorld.getAllowMonsters() + "> animals:<" + thisWorld.getAllowAnimals() + "> difficulty:<" + thisWorld.getDifficulty().toString() + "> maxY:<" + thisWorld.getMaxHeight() + "> PVP:<" + thisWorld.getPVP() + "> enabled:<" + thisWorldEnabled + ">");
		}
		
		/*
		 * Enable our plugin (allow all other functions to work)
		 */
		this.isEnabled = true;
	}
	
	/**
	 * 
	 */
	public void onDisable()
	{
		log.info("onDisable()");
		this.isEnabled = false;
	}
}
