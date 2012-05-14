/**
 * 
 */
package gs.tnt.dev.minecraft.ZombieSurvival.world;

import gs.tnt.dev.minecraft.ZombieSurvival.Variables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

/**
 * @author ted
 *
 */
public class ZSWorld
{
	private String		zsWorldName;
	private Boolean		zsWorldEnabled;
	private short		zsZombieLimit;
	private short		zsEntityLimit;
	private World		zsWorld;
	
	/**
	 * 
	 */
	public ZSWorld()
	{
		this.zsWorld = null;
		this.zsWorldName = "";
		this.zsWorldEnabled = false;
		this.zsZombieLimit = Variables.defaultPerWorldZombieLimit;
		this.zsEntityLimit = Variables.defaultPerWorldEntityLimit;
	}
	
	/**
	 * 
	 * @param thisWorld
	 */
	public ZSWorld(World thisWorld)
	{
		this.zsWorld = thisWorld;
		this.zsWorldName = thisWorld.getName();
		this.zsWorldEnabled = false;
		this.zsZombieLimit = Variables.defaultPerWorldZombieLimit;
		this.zsEntityLimit = Variables.defaultPerWorldEntityLimit;
	}
	
	/**
	 * 
	 * @param worldName
	 */
	public ZSWorld(String worldName)
	{
		this.zsWorldName = worldName;
		this.zsWorldEnabled = false;
		this.zsZombieLimit = Variables.defaultPerWorldZombieLimit;
		this.zsEntityLimit = Variables.defaultPerWorldEntityLimit;
	}
	
	/**
	 * 
	 * @param worldName
	 * @param bEnabled
	 */
	public ZSWorld(String worldName, Boolean bEnabled)
	{
		this.zsWorldName = worldName;
		this.zsWorldEnabled = bEnabled;
		this.zsZombieLimit = Variables.defaultPerWorldZombieLimit;
		this.zsEntityLimit = Variables.defaultPerWorldEntityLimit;
	}
	
	/**
	 * 
	 * @param worldName
	 * @param bEnabled
	 * @param iZombieLimit
	 */
	public ZSWorld(String worldName, Boolean bEnabled, short iZombieLimit)
	{
		this.zsWorldName = worldName;
		this.zsWorldEnabled = bEnabled;
		this.zsZombieLimit = iZombieLimit;
	}
	
	/**
	 * 
	 * @param worldName
	 * @param bEnabled
	 * @param iZombieLimit
	 * @param iEntityLimit
	 */
	public ZSWorld(String worldName, Boolean bEnabled, short iZombieLimit, short iEntityLimit)
	{
		this.zsWorldName = worldName;
		this.zsWorldEnabled = bEnabled;
		this.zsZombieLimit = iZombieLimit;
		this.zsEntityLimit = iEntityLimit;
	}
	
	/**
	 * 
	 * @param thisWorld
	 * @param worldName
	 * @param bEnabled
	 */
	public ZSWorld(World thisWorld, String worldName, Boolean bEnabled)
	{
		this.zsWorld = thisWorld;
		this.zsWorldName = worldName;
		this.zsWorldEnabled = bEnabled;
		this.zsZombieLimit = Variables.defaultPerWorldZombieLimit;
		this.zsEntityLimit = Variables.defaultPerWorldEntityLimit;
	}
	
	/**
	 * 
	 * @param thisWorld
	 * @param worldName
	 * @param bEnabled
	 * @param iZombieLimit
	 */
	public ZSWorld(World thisWorld, String worldName, Boolean bEnabled, short iZombieLimit)
	{
		this.zsWorld = thisWorld;
		this.zsWorldName = worldName;
		this.zsWorldEnabled = bEnabled;
		this.zsZombieLimit = iZombieLimit;
		this.zsEntityLimit = Variables.defaultPerWorldEntityLimit;
	}
	
	/**
	 * 
	 * @param worldName
	 */
	public void setWorldName(String worldName)
	{
		this.zsWorldName = worldName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getWorldName()
	{
		return this.zsWorldName;
	}
	
	/**
	 * 
	 * @param bEnabled
	 */
	public void setWorldEnabled(Boolean bEnabled)
	{
		this.zsWorldEnabled = bEnabled;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getWorldEnabled()
	{
		return this.zsWorldEnabled;
	}
	
	/**
	 * 
	 * @param iNewLimit
	 */
	public void setZombieLimit(short iNewLimit)
	{
		// Acceptable range for zombie limit is 0 <= x <= infinity
		if (iNewLimit < 0)
		{
			this.zsZombieLimit = 0;
		}
		else
		{
			this.zsZombieLimit = iNewLimit;	
		}
	}
	
	/**
	 * 
	 * @param iNewLimit
	 */
	public void setEntityLimit(short iNewLimit)
	{
		// Acceptable range for Entity limit is 0 <= x <= infinity
		if (iNewLimit < 0)
		{
			this.zsEntityLimit = 0;
		}
		else
		{
			this.zsEntityLimit = iNewLimit;	
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public short getZombieLimit()
	{
		return this.zsZombieLimit;
	}
	
	/**
	 * 
	 * @return
	 */
	public short getEntityLimit()
	{
		return this.zsEntityLimit;
	}
	
	/**
	 * 
	 * @param world
	 * @return
	 */
	public Boolean isThisOurWorld(World world)
	{
		return world.equals(this.zsWorld);
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getZombiesAllowed()
	{
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getCreepersAllowed()
	{
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getPigsAllowed()
	{
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getSpidersAllowed()
	{
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getSkeletonsAllowed()
	{
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getEndermanAllowed()
	{
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getSheepAllowed()
	{
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getPVPEnabled()
	{
		return this.zsWorld.getPVP();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getWorldType()
	{
		return this.zsWorld.getEnvironment().toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<EntityType, Integer> getEntityCount()
	{
		HashMap<EntityType, Integer> result = new HashMap<EntityType, Integer>();
		List<Entity> worldEntities = zsWorld.getEntities();
		Entity thisEntity;
		EntityType thisEntityType;
		int count = 0;
		
		for (int x = 1; x <= worldEntities.size(); x++)
		{
			thisEntity = worldEntities.get(x - 1);
			thisEntityType = thisEntity.getType();
			
			if (result.get(thisEntityType) == null)
			{
				count = 1;
				result.put(thisEntityType, count);
			}
			else
			{
				count = result.get(thisEntityType);
				count++;
				result.remove(thisEntityType);
				result.put(thisEntityType, count);
			}
		}
		
		return result;
	}
	
	//
	public int getEntityCount(Boolean countPlayerEntities)
	{
		List<Entity> worldEntities = zsWorld.getEntities();
		Entity thisEntity;
		int count = 0;
		
		for (int x = 1; x <= worldEntities.size(); x++)
		{
			thisEntity = worldEntities.get(x - 1);
			
			switch (thisEntity.getType())
			{
				case PLAYER:
					if (countPlayerEntities == true)
					{
						count++;
					}
					
				default:
					count++;
					break;
			}
			
			
		}
		
		return count;
	}
}

