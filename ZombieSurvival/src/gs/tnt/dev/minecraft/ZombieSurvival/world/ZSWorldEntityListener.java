/**
 * 
 */
package gs.tnt.dev.minecraft.ZombieSurvival.world;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.CreatureSpawnEvent;
//import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.SpawnEgg;

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
	public void onPlayerInteract(PlayerInteractEvent evt)
	{
		Player player = evt.getPlayer();
		World world = player.getWorld();
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
		 * Does the player have an item in their hand?
		 */
		if (evt.hasItem() == true)
		{
			ItemStack itemInPlayerHand = evt.getItem();
			switch (itemInPlayerHand.getType())
			{
				case MONSTER_EGG:
					/*
					 * This player is attempting to spawn a [MOB] using a spawning egg item ...
					 * Check if the [MOB] is permitted in the player's world,
					 *   and whether or not we reached our [MOB] limit for the world
					 */
					SpawnEgg spawnEggInPlayerHand = (SpawnEgg) itemInPlayerHand.getData();
					EntityType entitySpawnedFromEgg = spawnEggInPlayerHand.getSpawnedType();
					
					switch (entitySpawnedFromEgg)
					{
						case SPIDER:
							/* This player is attempting to spawn a spider using a spawning egg item */
							if (zsWorld.getSpidersAllowed() == false)
							{
								player.sendMessage(ChatColor.DARK_RED + "Spider spawning is prohibited on this world.");
								return;
							}
							break;
							
						case ZOMBIE:
							/* This player is attempting to spawn a zombie using a spawning egg item */
							if (zsWorld.getZombiesAllowed() == false)
							{
								player.sendMessage(ChatColor.RED + "Zombie spawning is prohibited on this world.");
								return;
							}
							
							/* Zombies permitted on world, check if limit has been reached */
							Map<EntityType, Integer> entityCountMap = zsWorld.getEntityCount();
							if (entityCountMap.containsKey(EntityType.ZOMBIE) == true)
							{
								String zombieCount = "0";
								zombieCount = (String) Integer.toString(entityCountMap.get(EntityType.ZOMBIE));
								short iZombieCount = Short.parseShort(zombieCount);
							
								if (iZombieCount >= zsWorld.getZombieLimit())
								{
									/* Inform user that their spawn action will fail */
									player.sendMessage(ChatColor.GOLD + "Zombie limit of " + zsWorld.getZombieLimit() + " for this world has been reached.");
									return;
								}
							}
							break;
					}
					break;
					
				default:
					player.sendMessage("<" + itemInPlayerHand + "> <" + itemInPlayerHand.getType() + "> <" + itemInPlayerHand.getTypeId() + ">");
					break;
			}
			
			
		}
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
				if (zsWorld.getCowsAllowed() == true)
				{
					/*
					 * Cows are allowed
					 */
				}
				else
				{
					/*
					 * Cows are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
					return;
				}
				break;
				
			case CHICKEN:
				break;
				
			case CREEPER:
				if (zsWorld.getCreepersAllowed() == true)
				{
					/*
					 * Creepers are allowed
					 */
				}
				else
				{
					/*
					 * Creepers are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
					return;
				}
				break;
				
			case ENDERMAN:
				if (zsWorld.getEndermanAllowed() == true)
				{
					/*
					 * Enderman are allowed
					 */
				}
				else
				{
					/*
					 * Enderman are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
					return;
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
				}
				else
				{
					/*
					 * Skeletons are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
					return;
				}
				break;
				
			case PIG_ZOMBIE:
				if (zsWorld.getPigZombiesAllowed() == true)
				{
					/*
					 * Pig zombies are allowed
					 */
				}
				else
				{
					/*
					 * Pig zombies are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
					return;
				}
				break;
				
			case SHEEP:
				if (zsWorld.getSheepAllowed() == true)
				{
					/*
					 * Sheep are allowed
					 */
				}
				else
				{
					/*
					 * Sheep are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
					return;
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
				}
				else
				{
					/*
					 * Skeletons are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
					return;
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
					/* Zombies permitted on world, check if limit has been reached */
					Map<EntityType, Integer> entityCountMap = zsWorld.getEntityCount();
					if (entityCountMap.containsKey(EntityType.SPIDER) == true)
					{
						String spiderCount = "0";
						spiderCount = (String) Integer.toString(entityCountMap.get(EntityType.SPIDER));
						short iSpiderCount = Short.parseShort(spiderCount);
					
						if (iSpiderCount >= zsWorld.getSpiderLimit())
						{
							/*
							 * Deny this spawn event because we have reached our per-world spider limit.
							 */
							evt.setCancelled(true);
							return;
						}
					}
				}
				else
				{
					/*
					 * Spiders are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
					return;
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
					Map<EntityType, Integer> entityCountMap = zsWorld.getEntityCount();
					
					if (entityCountMap.containsKey(EntityType.ZOMBIE) == true)
					{
						String zombieCount = "0";
						zombieCount = (String) Integer.toString(entityCountMap.get(EntityType.ZOMBIE));
						short iZombieCount = Short.parseShort(zombieCount);
					
						if (iZombieCount >= zsWorld.getZombieLimit())
						{
							/*
							 * Deny this spawn event because we have reached our per-world zombie limit.
							 */
							evt.setCancelled(true);
							return;
						}
					}
				}
				else
				{
					/*
					 * Zombies are not allowed, block this spawn event.
					 */
					evt.setCancelled(true);
					return;
				}
				break;
				
			default:
				System.out.println("Unhandled creature spawn w:<" + world.getName() + "> X:" + loc.getX() + " Y:" +
					loc.getY() + " Z:" + loc.getZ() + " - " + evt.getSpawnReason() + " - " +
					entity.toString() + " - " + evt.getEntityType().getName() + " - e?:" + zsWorld.getWorldEnabled());
				break;
		}
		
		short entityCount = (short) zsWorld.getEntityCount(false);
		if (entityCount >= zsWorld.getEntityLimit())
		{
			/*
			 * Deny this spawn event because we have reached our per-world entity limit.
			 */
			evt.setCancelled(true);
			return;
		}
	}
}


