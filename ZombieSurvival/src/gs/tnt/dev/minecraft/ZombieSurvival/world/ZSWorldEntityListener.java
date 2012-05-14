/**
 * 
 */
package gs.tnt.dev.minecraft.ZombieSurvival.world;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * @author ted
 *
 */
public class ZSWorldEntityListener implements Listener
{
	private ZSWorldManager ourWorldManager;
	
	public ZSWorldEntityListener(ZSWorldManager worldManager)
	{
		this.ourWorldManager = worldManager;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onCreatureSpawn(CreatureSpawnEvent evt)
	{
		Location loc = evt.getLocation();
		Entity entity = evt.getEntity();
		World world = entity.getWorld();
		ZSWorld zsWorld = this.ourWorldManager.findZSWorld(world);
		
		/*
		 * Check if this world is ZombieSurvival enabled in configuration
		 */
		if (zsWorld.getWorldEnabled() == false)
		{
			// This world is disabled as far as ZombieSurvival is concerned
			// We really don't care what happens here.
			return;
		}
		
		/*
		 * Enumerate the entity type
		 */
		EntityType thisEntityType = evt.getEntity().getType();
		switch (thisEntityType)
		{
			case BLAZE:
				break;
				
			case CAVE_SPIDER:
				break;
				
			case COW:
				//if (zsWorld.getCowsAllowed() == true)
				//{
					/*
					 * Cows are allowed
					 */
					//return;
				//}
				break;
				
			case CHICKEN:
				break;
				
			case CREEPER:
				if (zsWorld.getCreepersAllowed() == true)
				{
					/*
					 * Creepers are allowed
					 */
					return;
				}
				else
				{
					/*
					 * Creepers are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
				}
				break;
				
			case ENDERMAN:
				if (zsWorld.getEndermanAllowed() == true)
				{
					/*
					 * Enderman are allowed
					 */
					return;
				}
				else
				{
					/*
					 * Enderman are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
				}
				break;
				
			case ENDER_DRAGON:
				break;
				
			case GHAST:
				break;
				
			case GIANT:
				break;
				
			case IRON_GOLEM:
				break;
				
			case PIG:
				if (zsWorld.getSkeletonsAllowed() == true)
				{
					/*
					 * Skeletons are allowed
					 */
					return;
				}
				else
				{
					/*
					 * Skeletons are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
				}
				break;
				
			case PIG_ZOMBIE:
				break;
				
			case SHEEP:
				if (zsWorld.getSheepAllowed() == true)
				{
					/*
					 * Sheep are allowed
					 */
					return;
				}
				else
				{
					/*
					 * Sheep are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
				}
				break;
				
			case SILVERFISH:
				break;
				
			case SKELETON:
				if (zsWorld.getSkeletonsAllowed() == true)
				{
					/*
					 * Skeletons are allowed
					 */
					return;
				}
				else
				{
					/*
					 * Skeletons are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
				}
				break;
				
			case SLIME:
				break;
				
			case SPIDER:
				if (zsWorld.getSpidersAllowed() == true)
				{
					/*
					 * Spiders are allowed
					 */
					return;
				}
				else
				{
					/*
					 * Spiders are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
				}
				break;
				
			case SQUID:
				break;
				
			case VILLAGER:
				break;
				
			case WOLF:
				break;
				
			case ZOMBIE:
				if (zsWorld.getZombiesAllowed() == true)
				{
					/*
					 * Zombies are allowed
					 */
					return;
				}
				else
				{
					/*
					 * Zombies are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
				}
				break;
				
			default:
				System.out.println("Unhandled creature spawn w:<" + world.getName() + "> X:" + loc.getX() + " Y:" +
					loc.getY() + " Z:" + loc.getZ() + " - " + evt.getSpawnReason() + " - " +
					entity.toString() + " - " + evt.getEntityType().getName() + " - e?:" + zsWorld.getWorldEnabled());
				break;
		}
	}
}


