package de.kargo.mc.hypixel.autoHarvest.harvest;

import de.kargo.mc.hypixel.autoHarvest.util.HarvestUtil;
import de.kargo.mc.hypixel.autoHarvest.util.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.Vec3i;

public class LineHarvester {

	private Minecraft mc;
	private EntityPlayerSP player;

	private boolean complete;
	private boolean failed;

	private int facing;
	private Vec3i sideVec;
	private final int NORTH = 180;
	private final int SOUTH = 0;
	private final int WEST = 90;
	private final int EAST = 270;

	private int tickBuffer;

	public LineHarvester(Minecraft mc, EntityPlayerSP player) {
		this.mc = mc;
		this.player = player;
	}

	/** Fires at beginning of new line to harvest */
	public void init() {
		initValues();
		findFacing();
		if (!HarvestUtil.isAtField(player.getPosition())) {
			complete = true;
			this.failed = true;
		} else {
			this.player.setPosition((int) player.posX + 0.5D, player.getPosition().getY(), (int) player.posZ + 0.5D);
			tickBuffer = 10;
		}
	}

	public void turn() {
		player.setPositionAndRotation(player.posX + sideVec.getX(), player.posY, player.posZ + sideVec.getZ(), facing,
				90);

		initValues();
		facing += facing >= 180 ? -180 : 180;
		tickBuffer = 0;
	}

	private void initValues() {
		this.complete = false;
	}

	/** Gets the direction the player is facing */
	private void findFacing() {
		double x = player.getLookVec().xCoord;
		double z = player.getLookVec().zCoord;

		if (Math.abs(x) > Math.abs(z)) {
			if (x < 0) {
				facing = WEST;
				sideVec = new Vec3i(0, 0, -1);
			} else {
				facing = EAST;
				sideVec = new Vec3i(0, 0, 1);
			}

		} else {
			if (z < 0) {
				facing = NORTH;
				sideVec = new Vec3i(1, 0, 0);
			} else {
				facing = SOUTH;
				sideVec = new Vec3i(-1, 0, 0);
			}
		}
	}

	private Vec3i flipVec(Vec3i vec) {
		int newX = vec.getX() != 0 ? vec.getX() * -1 : vec.getX();
		int newY = vec.getY() != 0 ? vec.getY() * -1 : vec.getY();
		int newZ = vec.getZ() != 0 ? vec.getZ() * -1 : vec.getZ();
		return new Vec3i(newX, newY, newZ);
	}

	/** Fires at every update of the player if this.isRunning(). */
	public void onUpdate() {
		/* Checks if player has reached end. */
		if (player.isCollidedHorizontally && 10 < tickBuffer) {
			complete = true;
			return;
		}

		/* Continues to walk */
		PlayerUtil.walk(!complete);
//		PlayerUtil.destroy(!complete);
		player.setPositionAndRotation(player.posX, player.posY, player.posZ, facing, 50);
		tickBuffer++;
	}

	public boolean isComplete() {
		return complete;
	}

	public boolean hasFailed() {
		return failed;
	}

	public Vec3i getSideVec() {
		return sideVec;
	}
}
