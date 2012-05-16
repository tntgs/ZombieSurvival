/**
 * 
 */
package gs.tnt.dev.minecraft.ZombieSurvival.routine;

import gs.tnt.dev.minecraft.ZombieSurvival.world.ZSWorldManager;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author ted
 *
 */
public abstract class ZSTask implements Runnable
{
	protected JavaPlugin			ourPlugin;
	protected ZSWorldManager		worlds;
}
