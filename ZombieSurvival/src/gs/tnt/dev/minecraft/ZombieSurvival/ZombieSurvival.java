/**
 * 
 */
package gs.tnt.dev.minecraft.ZombieSurvival;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author ted
 *
 */
public class ZombieSurvival extends JavaPlugin
{
	private Logger log;
	
	public void onEnable()
	{
		log = this.getLogger();
		log.info("onEnable()");
	}
	
	public void onDisable()
	{
		log.info("onDisable()");
	}
}
