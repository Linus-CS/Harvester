package de.kargo.mc.hypixel.autoHarvest;

import de.kargo.mc.hypixel.autoHarvest.command.CommandHarvest;
import de.kargo.mc.hypixel.autoHarvest.harvest.HarvestManager;
import de.kargo.mc.hypixel.autoHarvest.harvest.Harvester;
import de.kargo.mc.hypixel.autoHarvest.harvest.Storer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = AutoHarvestMod.MODID, version = AutoHarvestMod.VERSION)
public class AutoHarvestMod {
	public static final String MODID = "autoharvest";
	public static final String VERSION = "1.0";
	
	public static final HarvestManager harvestManager = new HarvestManager();
	private boolean chatOpen;

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		ClientCommandHandler.instance.registerCommand(new CommandHarvest());
	}
	
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) {
		if(event.entity.equals(Minecraft.getMinecraft().thePlayer) && harvestManager.isRunning() && !chatOpen) {
			harvestManager.onUpdate();
		}
	}
	
	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		if(event.gui instanceof GuiChat) {
			chatOpen = true;
		}else {
			chatOpen = false;
		}
	}
	
}
