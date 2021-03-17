package de.kargo.mc.hypixel.autoHarvest.command;

import de.kargo.mc.hypixel.autoHarvest.AutoHarvestMod;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandHarvest extends CommandBase {

	private final String name = "harvest";
	private final String usage = "/harvest <start | stop>";

	@Override
	public String getCommandName() {
		return name;
	}

	@Override
	public String getCommandUsage(ICommandSender arg0) {
		return usage;
	}

	@Override
	public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
		if (arg1.length != 1) {
			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("\u00A7cUsage: " + usage));
		} else {
			if (arg1[0].equalsIgnoreCase("start")) {
				AutoHarvestMod.harvestManager.onCommand(true);
			} else if (arg1[0].equalsIgnoreCase("stop")) {
				AutoHarvestMod.harvestManager.onCommand(false);
			}
		}
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

}
