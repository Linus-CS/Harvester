package de.kargo.mc.hypixel.autoHarvest.harvest;

import de.kargo.mc.hypixel.autoHarvest.util.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;

public class Harvester {
	private Minecraft mc = Minecraft.getMinecraft();
	private EntityPlayerSP player;

	private boolean running;
	private LineHarvester lineHarvester;

	public Harvester() {
		lineHarvester = new LineHarvester(mc, player);
	}
	
	public void init() {
		lineHarvester.init();
	}

	public void onUpdate() {
		if (!lineHarvester.isComplete()) {
			lineHarvester.onUpdate();
		} else if (!lineHarvester.hasFailed()) {
			lineHarvester.turn();
		} else {
			player.addChatComponentMessage(new ChatComponentText("\u00A7cHarvesting failed! Go to a valid field."));
			setRunning(false);
		}
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		if (!running) {
			PlayerUtil.reset();
		}
		this.running = running;
	}
}
