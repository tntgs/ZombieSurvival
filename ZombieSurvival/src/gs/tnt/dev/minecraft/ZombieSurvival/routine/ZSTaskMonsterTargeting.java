/**
 * 
 */
package gs.tnt.dev.minecraft.ZombieSurvival.routine;

import gs.tnt.dev.minecraft.ZombieSurvival.world.ZSWorldManager;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class represents a routine that is run on the server periodically... <p><p>
 * 
 * The routine enumerates all current entities in all ZombieSurvival-enabled worlds, narrows the scope of
 * the enumeration to mobs (Zombies, Pig Zombies, etc.), determines their current target, and if not
 * targeting anything, will search for the nearest non-hostile combatant (Player, Villager, Iron Golem, etc.)
 * and target them. <p>
 * 
 * 
 * @author Ted
 */
public class ZSTaskMonsterTargeting extends ZSTask implements Runnable
{
	public ZSTaskMonsterTargeting(JavaPlugin plugin, ZSWorldManager ourWorldMgr)
	{
		super.ourPlugin = plugin;
		super.worlds = ourWorldMgr;
	}
	
	public void run()
	{
		//System.out.println(worlds.toString());
	}
}
