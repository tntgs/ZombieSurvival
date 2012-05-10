/**
 * 
 */
package gs.tnt.dev.minecraft.ZombieSurvival;

import java.util.logging.Logger;
import java.util.List;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
 * @author ted
 *
 */
public class ZombieSurvival extends JavaPlugin
{
	private Logger log;
	private Server server;
	
	/**
	 * 
	 */
	public void onEnable()
	{
		server = this.getServer();
		log = this.getLogger();
		
		log.info("onEnable()");
		
		log.info("Enumerating loaded worlds ...");
		
		World thisWorld;
		List<World> worlds = server.getWorlds();
		for (int x = 1; x <= worlds.size(); x++)
		{
			thisWorld = worlds.get(x-1);
			log.info("world: <" + thisWorld.getName() + "> type: " + thisWorld.getEnvironment().toString() + " monsters:<" + thisWorld.getAllowMonsters() + "> animals:<" + thisWorld.getAllowAnimals() + "> difficulty:<" + thisWorld.getDifficulty().toString() + "> maxY:<" + thisWorld.getMaxHeight() + "> PVP:<" + thisWorld.getPVP() + ">");
		}
	}
	
	/**
	 * 
	 */
	public void onDisable()
	{
		log.info("onDisable()");
	}
}
