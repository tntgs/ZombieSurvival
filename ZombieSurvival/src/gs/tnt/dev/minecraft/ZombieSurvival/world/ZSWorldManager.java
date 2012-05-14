/**
 * 
 */
package gs.tnt.dev.minecraft.ZombieSurvival.world;

import gs.tnt.dev.minecraft.ZombieSurvival.ZombieSurvival;
import gs.tnt.dev.minecraft.ZombieSurvival.data.Datastore;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.World;

/**
 * @author ted
 *
 */
public class ZSWorldManager
{
	private List<ZSWorld> zsWorlds;
	private ZombieSurvival ourPlugin;
	private Server ourServer;
	private Datastore ourDS;
	private ZSWorldEntityListener ourWorldEntityListener;
	
	public ZSWorldManager(ZombieSurvival zsPlugin)
	{
		this.ourPlugin = zsPlugin;
		this.ourServer = zsPlugin.getServer();
		this.ourDS = zsPlugin.getDS();
		this.zsWorlds = new ArrayList<ZSWorld>();
		
		Logger log = ourPlugin.getLogger();
		
		log.info("Enumerating loaded worlds ...");
		
		World thisWorld;
		ZSWorld thisZSWorld;
		List<World> worlds = ourServer.getWorlds();
		for (int x = 1; x <= worlds.size(); x++)
		{
			thisWorld = worlds.get(x - 1);
			
			Boolean thisWorldEnabled = false;
			String thisWorldName = thisWorld.getName();
			if (this.ourDS.isWorldAdded(thisWorldName) == false)
			{
				thisWorldEnabled = false;
				this.ourDS.addWorld(thisWorldName, thisWorldEnabled);
			}
			else
			{
				thisWorldEnabled = this.ourDS.isWorldEnabled(thisWorldName);
			}
			
			thisZSWorld = new ZSWorld(thisWorld, thisWorldName, thisWorldEnabled);
			zsWorlds.add(thisZSWorld);
			
			log.info("world: <" + thisZSWorld.getWorldName() + "> type: " + thisWorld.getEnvironment().toString() + " monsters:<" + thisWorld.getAllowMonsters() + "> animals:<" + thisWorld.getAllowAnimals() + "> difficulty:<" + thisWorld.getDifficulty().toString() + "> maxY:<" + thisWorld.getMaxHeight() + "> PVP:<" + thisWorld.getPVP() + "> enabled:<" + thisZSWorld.getWorldEnabled() + "> zl:<" + thisZSWorld.getZombieLimit() + ">");
		}
		
		this.ourWorldEntityListener = new ZSWorldEntityListener(this);
		this.ourServer.getPluginManager().registerEvents(this.ourWorldEntityListener, this.ourPlugin);
	}
	
	/**
	 * 
	 * @param world
	 * @return
	 */
	public ZSWorld findZSWorld(World world)
	{
		ZSWorld thisWorld;
		for (int x = 1; x <= this.zsWorlds.size(); x++)
		{
			thisWorld = (ZSWorld) zsWorlds.get(x-1);
			if (thisWorld.isThisOurWorld(world) == true)
			{
				return thisWorld;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param worldName
	 * @return
	 */
	public ZSWorld findZSWorld(String worldName)
	{
		ZSWorld thisWorld;
		for (int x = 1; x <= this.zsWorlds.size(); x++)
		{
			thisWorld = (ZSWorld) zsWorlds.get(x-1);
			if (thisWorld.getWorldName().equalsIgnoreCase(worldName))
			{
				return thisWorld;
			}
		}
		return null;
	}
}
