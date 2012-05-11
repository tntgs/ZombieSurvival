/**
 * 
 */
package gs.tnt.dev.minecraft.ZombieSurvival.data;

//import org.bukkit.configuration.MemorySection;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author ted
 *
 */
public abstract class Datastore
{
	protected JavaPlugin ourPlugin;
	
	//public abstract byte startup(MemorySection settings);
	public abstract byte shutdown();
	public abstract Boolean isWorldEnabled(String worldName);
	public abstract Boolean isWorldAdded(String worldName);
	public abstract Boolean addWorld(String worldName);
	public abstract Boolean addWorld(String worldName, Boolean isEnabled);
	public abstract Boolean removeWorld(String worldName);
	public abstract Boolean enableWorld(String worldName);
	public abstract Boolean disableWorld(String worldName);
}
