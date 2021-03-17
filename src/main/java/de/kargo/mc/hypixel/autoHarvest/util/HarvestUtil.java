package de.kargo.mc.hypixel.autoHarvest.util;

import net.minecraft.block.BlockCarrot;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockPotato;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;

public class HarvestUtil {
	
	public static boolean isAtField(BlockPos playerPos) {
		BlockPos checkPos = null;
		for(int i = 1; i > -1; i--) {
			for(int j = 1; j > -1; j--) {
				checkPos = new BlockPos(playerPos.getX() + i, playerPos.getY(), playerPos.getZ() + j);
				if(isCrop(checkPos))
					return true;
			}
		}
		return false;
	}
	
	private static boolean isCrop(BlockPos pos) {
		Minecraft mc = Minecraft.getMinecraft();
		boolean flag1 = mc.theWorld.getBlockState(pos).getBlock() instanceof BlockCarrot;
		boolean flag2 = mc.theWorld.getBlockState(pos).getBlock() instanceof BlockPotato;
		boolean flag3 = mc.theWorld.getBlockState(pos).getBlock() instanceof BlockCrops;
		
		return flag1 || flag2 || flag3;
	}
}
