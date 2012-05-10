/**
 * 
 */
package gs.tnt.dev.minecraft.data;

import org.bukkit.configuration.MemorySection;

/**
 * @author ted
 *
 */
public abstract class DataStore
{
	public abstract byte startup(MemorySection settings);
	public abstract byte shutdown();
	public abstract String loadSetting(String szSetting);
	public abstract byte saveSetting(String szSetting, String szValue);
}
