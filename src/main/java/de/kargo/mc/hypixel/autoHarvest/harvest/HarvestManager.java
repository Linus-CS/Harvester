package de.kargo.mc.hypixel.autoHarvest.harvest;

import de.kargo.mc.hypixel.autoHarvest.util.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;

public class HarvestManager {
	private boolean running;
	private final Harvester harvester = new Harvester();
	private final Storer storer = new Storer(this);
	
	public Minecraft mc = Minecraft.getMinecraft();
	public EntityPlayerSP player;
	
	private boolean fullInventory;
	
	public boolean isRunning() {
		return running;
	}

	public void onUpdate() {
		if (PlayerUtil.hasFullInventory(player)) {
			player.addChatComponentMessage(new ChatComponentText("Inventory full."));
			fullInventory = true;
			PlayerUtil.reset();
		}
		
		if(!fullInventory)
			harvester.onUpdate();
		else
			storer.onUpdate();
	}

	public void onCommand(boolean running) {
		player = mc.thePlayer;
		if (this.running = running) {
			player.addChatComponentMessage(new ChatComponentText("\u00A7aStart harvesting."));
			fullInventory = false;
			harvester.init();
		} else {
			player.addChatComponentMessage(new ChatComponentText("\u00A74Stop harvesting."));
			PlayerUtil.reset();
		}
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
