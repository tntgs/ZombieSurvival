/**
 * 
 */
package gs.tnt.dev.minecraft.ZombieSurvival.commands;

import org.bukkit.command.CommandSender;

/**
 * @author ted
 *
 */
public abstract class ZSCommand
{
	public abstract Boolean onCommand(CommandSender sender, String[] args);
}
