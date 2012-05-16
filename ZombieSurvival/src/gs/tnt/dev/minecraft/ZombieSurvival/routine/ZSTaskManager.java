/**
 * 
 */
package gs.tnt.dev.minecraft.ZombieSurvival.routine;

import gs.tnt.dev.minecraft.ZombieSurvival.ZombieSurvival;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * @author ted
 *
 */
public class ZSTaskManager
{
	private JavaPlugin					ourPlugin;
	private Server						ourServer;
	private BukkitScheduler				ourScheduler;
	
	private ZSTaskMonsterTargeting		monsterTargetingRoutine;
	private int							monsterTargetingRoutineID;
	
	public ZSTaskManager(JavaPlugin plugin)
	{
		this.ourPlugin = plugin;
		ZombieSurvival zsPlugin = (ZombieSurvival) plugin;
		
		this.monsterTargetingRoutine = new ZSTaskMonsterTargeting(plugin, zsPlugin.getWorldManager());
		this.ourServer = plugin.getServer();
		this.ourScheduler = ourServer.getScheduler();
	}
	
	public Boolean startup()
	{
		this.monsterTargetingRoutineID = this.ourScheduler.scheduleSyncRepeatingTask(ourPlugin,
				monsterTargetingRoutine, 120, 30);
		
		if (this.monsterTargetingRoutineID == -1)
		{
			return false;
		}
		
		return true;
	}
	
	public Boolean stop()
	{
		this.ourScheduler.cancelTask(monsterTargetingRoutineID);
		return true;
	}
}
