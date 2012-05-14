/**
 * 
 */
package gs.tnt.dev.minecraft.ZombieSurvival;

import gs.tnt.dev.minecraft.ZombieSurvival.commands.ZSWorldInfoCommand;
import gs.tnt.dev.minecraft.ZombieSurvival.data.Datastore;
import gs.tnt.dev.minecraft.ZombieSurvival.data.MysqlDatastore;
//import gs.tnt.dev.minecraft.ZombieSurvival.world.ZSWorld;
import gs.tnt.dev.minecraft.ZombieSurvival.world.ZSWorldManager;
//import gs.tnt.dev.minecraft.data.MySQLDataStore;
//import java.util.HashMap;
//import java.util.Map;
import java.util.logging.Logger;
//import java.util.List;
//import org.bukkit.Server;
//import org.bukkit.World;
//import org.bukkit.configuration.MemorySection;
//import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
//import org.bukkit.entity.EntityType;
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
	//private Server server;
	private Datastore ds;
	private ZSWorldManager worlds;
	
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
		//server = this.getServer();
		
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
		 * Load the ZombieSurvival World manager to manage the worlds
		 */
		worlds = new ZSWorldManager(this);
		
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
		if (this.isEnabled == false)
		{
			log.info("onDisable() -- we were never enabled?");
		}
		else
		{
			log.info("onDisable()");
			
			this.isEnabled = false;
		}
	}
	
	/**
	 * 
	 * @param sender
	 * @param cmd
	 * @param commandLabel
	 * @param args
	 * @return
	 */
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("zs"))
		{
			/*
			 *    /zs <...>
			 */
			if (args.length < 1)
			{
				/*
				 *  Missing some parameters here ...
				 */
				return false;
			}
			
			if (args[0].equalsIgnoreCase("world"))
			{
				/*
				 *    /zs world <...>
				 */
				
				if (args.length < 2)
				{
					/*
					 * We are expecting more parameters at this point ...
					 */
					return false;
				}
				
				if (args[1].equalsIgnoreCase("info"))
				{
					/*
					 *    /zs world info <...>
					 *      This command finds a world matching that name,
					 *      and prints out information about that world to the console. 
					 */
					
					if (args.length < 3)
					{
						/*
						 * We are expecting EVEN more parameters at this point ...
						 */
						return false;
					}
					
					/* Remove the first two elements in the String[] array */
					String[] newArgs = new String[args.length - 2];
					for (int x = 0; x < (args.length - 2); x++) { newArgs[x] = args[x + 2]; }
					
					/* Spawn another memory hogging object to run this command */
					ZSWorldInfoCommand zsWIC = new ZSWorldInfoCommand(this.worlds);
					return zsWIC.onCommand(sender, newArgs);
				}
			}
			
			return false;
		}
		else if (cmd.getName().equalsIgnoreCase("zswi"))
		{
			/*
			 *   /zswi <world>
			 *   This command finds a world matching that name, and prints out information about that
			 *     world to the console. 
			 */
			
			/* Spawn another memory hogging object to run this command */
			ZSWorldInfoCommand zsWIC = new ZSWorldInfoCommand(this.worlds);
			return zsWIC.onCommand(sender, args);
		}
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public Datastore getDS()
	{
		return this.ds;
	}
}
