/**
 * 
 */
package gs.tnt.dev.minecraft.ZombieSurvival.commands;

import gs.tnt.dev.minecraft.ZombieSurvival.world.ZSWorld;
import gs.tnt.dev.minecraft.ZombieSurvival.world.ZSWorldManager;
import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

/**
 * @author ted
 *
 */
public class ZSWorldInfoCommand extends ZSCommand
{
	private ZSWorldManager worlds;
	
	/**
	 * 
	 * @param worldMgr
	 */
	public ZSWorldInfoCommand(ZSWorldManager worldMgr)
	{
		this.worlds = worldMgr;
	}
	
	/**
	 * 
	 * @param sender
	 * @param args
	 * @return
	 */
	public Boolean onCommand(CommandSender sender, String[] args)
	{
		boolean bPermitted = false;
		ZSWorld worldInfoQuery = null;
		switch (args.length)
		{
			case 1:
				worldInfoQuery = this.worlds.findZSWorld(args[0]);
				if (worldInfoQuery == null)
				{
					System.out.println("World \"" + args[0] + "\" not found.");
					return true;
				}
				
				Map<EntityType, Integer> currentWorldEntities = worldInfoQuery.getEntityCount();
				
				if (sender.isOp() == false)
				{
					
				}
				else
				{
					bPermitted = true;
				}
				
				if (bPermitted == true)
				{
					sender.sendMessage("\"" + worldInfoQuery.getWorldName() + "\" (" + worldInfoQuery.getWorldType() + ") PVP:<" + worldInfoQuery.getPVPEnabled() + ">");
					sender.sendMessage("ZombieSurvival enabled: <" + worldInfoQuery.getWorldEnabled() + ">");
					sender.sendMessage("# entities: <" + worldInfoQuery.getEntityCount(false) + ">");
					sender.sendMessage("  " + currentWorldEntities.toString());
				}
				else
				{
					
				}
				
				break;
				
			default:
				return false;	
		}
		
		return true;
	}
}
