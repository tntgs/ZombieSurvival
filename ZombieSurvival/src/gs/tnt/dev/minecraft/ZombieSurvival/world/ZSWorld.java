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
	private short		zsSpiderLimit;
	private short		zsCowLimit;
	private short		zsVillagerLimit;
	private short		zsSheepLimit;
	private short		zsOcelotLimit;
	private short		zsSquidLimit;
	private short		zsWolfLimit;
	private short		zsSkeletonLimit;
	private short		zsPigZombieLimit;
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
		this.zsCowLimit = Variables.defaultPerWorldCowLimit;
		this.zsEntityLimit = Variables.defaultPerWorldEntityLimit;
		this.zsOcelotLimit = Variables.defaultPerWorldOcelotLimit;
		this.zsPigZombieLimit = Variables.defaultPerWorldPigZombieLimit;
		this.zsSheepLimit = Variables.defaultPerWorldSheepLimit;
		this.zsSkeletonLimit = Variables.defaultPerWorldSkeletonLimit;
		this.zsSpiderLimit = Variables.defaultPerWorldSpiderLimit;
		this.zsSquidLimit = Variables.defaultPerWorldSquidLimit;
		this.zsVillagerLimit = Variables.defaultPerWorldVillagerLimit;
		this.zsWolfLimit = Variables.defaultPerWorldWolfLimit;
		this.zsZombieLimit = Variables.defaultPerWorldZombieLimit;
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
		this.zsCowLimit = Variables.defaultPerWorldCowLimit;
		this.zsEntityLimit = Variables.defaultPerWorldEntityLimit;
		this.zsOcelotLimit = Variables.defaultPerWorldOcelotLimit;
		this.zsPigZombieLimit = Variables.defaultPerWorldPigZombieLimit;
		this.zsSheepLimit = Variables.defaultPerWorldSheepLimit;
		this.zsSkeletonLimit = Variables.defaultPerWorldSkeletonLimit;
		this.zsSpiderLimit = Variables.defaultPerWorldSpiderLimit;
		this.zsSquidLimit = Variables.defaultPerWorldSquidLimit;
		this.zsVillagerLimit = Variables.defaultPerWorldVillagerLimit;
		this.zsWolfLimit = Variables.defaultPerWorldWolfLimit;
		this.zsZombieLimit = Variables.defaultPerWorldZombieLimit;
	}
	
	/**
	 * 
	 * @param worldName
	 */
	public ZSWorld(String worldName)
	{
		this.zsWorldName = worldName;
		this.zsWorldEnabled = false;
		this.zsCowLimit = Variables.defaultPerWorldCowLimit;
		this.zsEntityLimit = Variables.defaultPerWorldEntityLimit;
		this.zsOcelotLimit = Variables.defaultPerWorldOcelotLimit;
		this.zsPigZombieLimit = Variables.defaultPerWorldPigZombieLimit;
		this.zsSheepLimit = Variables.defaultPerWorldSheepLimit;
		this.zsSkeletonLimit = Variables.defaultPerWorldSkeletonLimit;
		this.zsSpiderLimit = Variables.defaultPerWorldSpiderLimit;
		this.zsSquidLimit = Variables.defaultPerWorldSquidLimit;
		this.zsVillagerLimit = Variables.defaultPerWorldVillagerLimit;
		this.zsWolfLimit = Variables.defaultPerWorldWolfLimit;
		this.zsZombieLimit = Variables.defaultPerWorldZombieLimit;
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
		this.zsCowLimit = Variables.defaultPerWorldCowLimit;
		this.zsEntityLimit = Variables.defaultPerWorldEntityLimit;
		this.zsOcelotLimit = Variables.defaultPerWorldOcelotLimit;
		this.zsPigZombieLimit = Variables.defaultPerWorldPigZombieLimit;
		this.zsSheepLimit = Variables.defaultPerWorldSheepLimit;
		this.zsSkeletonLimit = Variables.defaultPerWorldSkeletonLimit;
		this.zsSpiderLimit = Variables.defaultPerWorldSpiderLimit;
		this.zsSquidLimit = Variables.defaultPerWorldSquidLimit;
		this.zsVillagerLimit = Variables.defaultPerWorldVillagerLimit;
		this.zsWolfLimit = Variables.defaultPerWorldWolfLimit;
		this.zsZombieLimit = Variables.defaultPerWorldZombieLimit;
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
		this.zsCowLimit = Variables.defaultPerWorldCowLimit;
		this.zsEntityLimit = Variables.defaultPerWorldEntityLimit;
		this.zsOcelotLimit = Variables.defaultPerWorldOcelotLimit;
		this.zsPigZombieLimit = Variables.defaultPerWorldPigZombieLimit;
		this.zsSheepLimit = Variables.defaultPerWorldSheepLimit;
		this.zsSkeletonLimit = Variables.defaultPerWorldSkeletonLimit;
		this.zsSpiderLimit = Variables.defaultPerWorldSpiderLimit;
		this.zsSquidLimit = Variables.defaultPerWorldSquidLimit;
		this.zsVillagerLimit = Variables.defaultPerWorldVillagerLimit;
		this.zsWolfLimit = Variables.defaultPerWorldWolfLimit;
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
		this.zsCowLimit = Variables.defaultPerWorldCowLimit;
		this.zsEntityLimit = Variables.defaultPerWorldEntityLimit;
		this.zsOcelotLimit = Variables.defaultPerWorldOcelotLimit;
		this.zsPigZombieLimit = Variables.defaultPerWorldPigZombieLimit;
		this.zsSheepLimit = Variables.defaultPerWorldSheepLimit;
		this.zsSkeletonLimit = Variables.defaultPerWorldSkeletonLimit;
		this.zsSpiderLimit = Variables.defaultPerWorldSpiderLimit;
		this.zsSquidLimit = Variables.defaultPerWorldSquidLimit;
		this.zsVillagerLimit = Variables.defaultPerWorldVillagerLimit;
		this.zsWolfLimit = Variables.defaultPerWorldWolfLimit;
		this.zsZombieLimit = Variables.defaultPerWorldZombieLimit;
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
		this.zsCowLimit = Variables.defaultPerWorldCowLimit;
		this.zsEntityLimit = Variables.defaultPerWorldEntityLimit;
		this.zsOcelotLimit = Variables.defaultPerWorldOcelotLimit;
		this.zsPigZombieLimit = Variables.defaultPerWorldPigZombieLimit;
		this.zsSheepLimit = Variables.defaultPerWorldSheepLimit;
		this.zsSkeletonLimit = Variables.defaultPerWorldSkeletonLimit;
		this.zsSpiderLimit = Variables.defaultPerWorldSpiderLimit;
		this.zsSquidLimit = Variables.defaultPerWorldSquidLimit;
		this.zsVillagerLimit = Variables.defaultPerWorldVillagerLimit;
		this.zsWolfLimit = Variables.defaultPerWorldWolfLimit;
		this.zsZombieLimit = Variables.defaultPerWorldZombieLimit;
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
		this.zsCowLimit = Variables.defaultPerWorldCowLimit;
		this.zsEntityLimit = Variables.defaultPerWorldEntityLimit;
		this.zsOcelotLimit = Variables.defaultPerWorldOcelotLimit;
		this.zsPigZombieLimit = Variables.defaultPerWorldPigZombieLimit;
		this.zsSheepLimit = Variables.defaultPerWorldSheepLimit;
		this.zsSkeletonLimit = Variables.defaultPerWorldSkeletonLimit;
		this.zsSpiderLimit = Variables.defaultPerWorldSpiderLimit;
		this.zsSquidLimit = Variables.defaultPerWorldSquidLimit;
		this.zsVillagerLimit = Variables.defaultPerWorldVillagerLimit;
		this.zsWolfLimit = Variables.defaultPerWorldWolfLimit;
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
	public short getSpiderLimit()
	{
		return this.zsSpiderLimit;
	}
	
	/**
	 * 
	 * @return
	 */
	public short getCowLimit()
	{
		return this.zsCowLimit;
	}
	
	/**
	 * 
	 * @param iNewLimit
	 */
	public void setCowLimit(short iNewLimit)
	{
		// Acceptable range for Cow limit is 0 <= x <= infinity
		if (iNewLimit < 0)
		{
			this.zsCowLimit = 0;
		}
		else
		{
			this.zsCowLimit = iNewLimit;	
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public short getPigZombieLimit()
	{
		return this.zsPigZombieLimit;
	}
	
	/**
	 * 
	 * @param iNewLimit
	 */
	public void setPigZombieLimit(short iNewLimit)
	{
		// Acceptable range for Pig Zombie limit is 0 <= x <= infinity
		if (iNewLimit < 0)
		{
			this.zsPigZombieLimit = 0;
		}
		else
		{
			this.zsPigZombieLimit = iNewLimit;	
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public short getSheepLimit()
	{
		return this.zsSheepLimit;
	}
	
	/**
	 * 
	 * @param iNewLimit
	 */
	public void setSheepLimit(short iNewLimit)
	{
		// Acceptable range for Sheep limit is 0 <= x <= infinity
		if (iNewLimit < 0)
		{
			this.zsSheepLimit = 0;
		}
		else
		{
			this.zsSheepLimit = iNewLimit;	
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public short getSquidLimit()
	{
		return this.zsSquidLimit;
	}
	
	public void setSquidLimit(short iNewLimit)
	{
		// Acceptable range for Squid limit is 0 <= x <= infinity
		if (iNewLimit < 0)
		{
			this.zsSquidLimit = 0;
		}
		else
		{
			this.zsSquidLimit = iNewLimit;	
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public short getSkeletonLimit()
	{
		return this.zsSkeletonLimit;
	}
	
	public void setSkeletonLimit(short iNewLimit)
	{
		// Acceptable range for Skeleton limit is 0 <= x <= infinity
		if (iNewLimit < 0)
		{
			this.zsSkeletonLimit = 0;
		}
		else
		{
			this.zsSkeletonLimit = iNewLimit;	
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public short getVillagerLimit()
	{
		return this.zsVillagerLimit;
	}
	
	public void setVillagerLimit(short iNewLimit)
	{
		// Acceptable range for Villager limit is 0 <= x <= infinity
		if (iNewLimit < 0)
		{
			this.zsVillagerLimit = 0;
		}
		else
		{
			this.zsVillagerLimit = iNewLimit;	
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public short getWolfLimit()
	{
		return this.zsWolfLimit;
	}
	
	public void setWolfLimit(short iNewLimit)
	{
		// Acceptable range for Wolf limit is 0 <= x <= infinity
		if (iNewLimit < 0)
		{
			this.zsWolfLimit = 0;
		}
		else
		{
			this.zsWolfLimit = iNewLimit;	
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public short getOcelotLimit()
	{
		return this.zsOcelotLimit;
	}
	
	public void setOcelotLimit(short iNewLimit)
	{
		// Acceptable range for Ocelot limit is 0 <= x <= infinity
		if (iNewLimit < 0)
		{
			this.zsOcelotLimit = 0;
		}
		else
		{
			this.zsOcelotLimit = iNewLimit;	
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getOcelotAllowed()
	{
		return true;
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
	public Boolean getCowsAllowed()
	{
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getWolvesAllowed()
	{
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getVillagersAllowed()
	{
		return true;
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
	public Boolean getPigZombiesAllowed()
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
	public Boolean getZombiesBurnInDaylight()
	{
		return true;
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
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public short getEntityCount(EntityType entity)
	{
		List<Entity> worldEntities = zsWorld.getEntities();
		Entity thisEntity;
		EntityType thisEntityType;
		short count = 0;
		
		for (int x = 1; x <= worldEntities.size(); x++)
		{
			thisEntity = worldEntities.get(x - 1);
			thisEntityType = thisEntity.getType();
			
			if (thisEntityType.equals(entity))
			{
				count++;
			}
		}
		return count;
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

