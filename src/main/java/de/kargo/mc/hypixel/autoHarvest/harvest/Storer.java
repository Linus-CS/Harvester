package de.kargo.mc.hypixel.autoHarvest.harvest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;

public class Storer {
	
	private boolean backPackOpen;
	private int backPackIndex;
	
	private Minecraft mc = Minecraft.getMinecraft();
	private EntityPlayerSP player;
	private HarvestManager parent;
	
	public Storer(HarvestManager parent) {
		player = mc.thePlayer;
		this.parent = parent;
	}
	
	public void onUpdate() {
		if(!backPackOpen) {
			int skip = backPackIndex;
			for (int i = 36; i < player.inventory.mainInventory.length; i++) {
				if(player.inventory.mainInventory[i].getDisplayName().toLowerCase().contains("backpack")) {
					if(skip == 0) {
						player.inventory.currentItem = i;
						KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), true);
						backPackOpen = true;
						return;
					}else
						skip--;
				}
			}
			parent.setRunning(false);
		}else {
			KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
		}
	}
}
