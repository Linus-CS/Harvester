package de.kargo.mc.hypixel.autoHarvest.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;

public class PlayerUtil {
	private static Minecraft mc = Minecraft.getMinecraft();
	private static GameSettings settings = mc.gameSettings;

	/**
	 * Sets the gameSettings to sprint and walk forward.
	 * 
	 * @param shouldWalk
	 */
	public static void walk(boolean shouldWalk) {
		KeyBinding.setKeyBindState(settings.keyBindForward.getKeyCode(), shouldWalk);
		KeyBinding.setKeyBindState(settings.keyBindSprint.getKeyCode(), shouldWalk);
	}

	/** Resets the gameSettings. */
	public static void reset() {
		walk(false);
		destroy(false);
	}

	public static void destroy(boolean shouldAttack) {
		KeyBinding.setKeyBindState(settings.keyBindAttack.getKeyCode(), shouldAttack);
	}

	public static boolean hasFullInventory(EntityPlayerSP player) {
		for (ItemStack stack : player.inventory.mainInventory) {
			if (stack == null)
				return false;
		}
		return true;
	}
}
