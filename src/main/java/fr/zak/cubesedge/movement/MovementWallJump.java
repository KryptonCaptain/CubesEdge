package fr.zak.cubesedge.movement;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import fr.zak.cubesedge.Movement;
import fr.zak.cubesedge.Util;
import fr.zak.cubesedge.entity.EntityPlayerCustom;

public class MovementWallJump extends Movement {

	@Override
	public void control(EntityPlayerCustom playerCustom, EntityPlayer player, Side side) {
		int x = MathHelper.floor_double(player.posX);
		int y = MathHelper.floor_double(player.posY);
		int z = MathHelper.floor_double(player.posZ);
		int heading = MathHelper
				.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		if (!player.capabilities.isFlying && !playerCustom.isSneaking) {
			if (!player.onGround && player.motionY <= 0) {
				if ((Util.isCube(getBlock(player.worldObj,
						x + 1,
						y,
						z)) || Util.isCube(getBlock(player.worldObj,
								x + 1,
								y - 1,
								z)
						))
						&& ((heading == 0) || (heading == 2))) {
					playerCustom.isOnWall = true;
					if (player.moveForward > 0) {
						if (playerCustom.rotationYaw == 0) {
							playerCustom.rotationYaw = player.rotationYaw;
							playerCustom.rotationPitch = player.rotationPitch;
							playerCustom.prevRotationPitch = player.prevRotationPitch;
							playerCustom.prevRotationYaw = player.prevRotationYaw;
						}
						if (player instanceof EntityPlayerSP) {
							if (((EntityPlayerSP) player).movementInput.jump
									&& !playerCustom.wallJump) {
								playerCustom.animRight = false;
								playerCustom.animLeft = false;
								if (heading == 0) {
									player.motionZ = 0.7D;
									player.motionX = -0.2D;
								}
								if (heading == 2) {
									player.motionZ = -0.7D;
									player.motionX = -0.2D;
								}
								player.motionY = 0.41999998688697815D;
								playerCustom.wallJump = true;
							}
						}
						if (heading == 2) {
							playerCustom.animRight = true;
						}
						if (heading == 0) {
							playerCustom.animLeft = true;

						}
					}
				} else if ((Util.isCube(getBlock(player.worldObj,
						x - 1,
						y,
						z)) || Util.isCube(getBlock(player.worldObj,
								x - 1,
								y - 1,
								z)
						))
						&& ((heading == 0) || (heading == 2))) {
					playerCustom.isOnWall = true;
					if (player.moveForward > 0) {
						if (playerCustom.rotationYaw == 0) {
							playerCustom.rotationYaw = player.rotationYaw;
							playerCustom.rotationPitch = player.rotationPitch;
							playerCustom.prevRotationPitch = player.prevRotationPitch;
							playerCustom.prevRotationYaw = player.prevRotationYaw;
						}
						if (player instanceof EntityPlayerSP) {
							if (((EntityPlayerSP) player).movementInput.jump
									&& !playerCustom.wallJump) {
								playerCustom.animRight = false;
								playerCustom.animLeft = false;
								if (heading == 0) {
									player.motionZ = 0.7D;
									player.motionX = 0.2D;
								}
								if (heading == 2) {
									player.motionZ = -0.7D;
									player.motionX = 0.2D;
								}
								player.motionY = 0.41999998688697815D;
								playerCustom.wallJump = true;
							}
						}
						if (heading == 2) {
							playerCustom.animLeft = true;
						}
						if (heading == 0) {
							playerCustom.animRight = true;
						}
					}
				} else if ((Util.isCube(getBlock(player.worldObj,
						x,
						y,
						z + 1)
						) || Util.isCube(getBlock(player.worldObj,
						x,
						y - 1,
						z + 1)
						))
						&& ((heading == 3) || (heading == 1))) {
					playerCustom.isOnWall = true;
					if (player.moveForward > 0) {
						if (playerCustom.rotationYaw == 0) {
							playerCustom.rotationYaw = player.rotationYaw;
							playerCustom.rotationPitch = player.rotationPitch;
							playerCustom.prevRotationPitch = player.prevRotationPitch;
							playerCustom.prevRotationYaw = player.prevRotationYaw;
						}
						if (player instanceof EntityPlayerSP) {
							if (((EntityPlayerSP) player).movementInput.jump
									&& !playerCustom.wallJump) {
								playerCustom.animRight = false;
								playerCustom.animLeft = false;
								if (heading == 3) {
									player.motionX = 0.7D;
									player.motionZ = -0.2D;
								}
								if (heading == 1) {
									player.motionX = -0.7D;
									player.motionZ = -0.2D;
								}
								player.motionY = 0.41999998688697815D;
								playerCustom.wallJump = true;
							}
						}
						if (heading == 3) {
							playerCustom.animRight = true;
						}
						if (heading == 1) {
							playerCustom.animLeft = true;
						}
					}
				} else if ((Util.isCube(getBlock(player.worldObj,
						x,
						y,
						z - 1)
						) || Util.isCube(getBlock(player.worldObj,
						x,
						y - 1,
						z - 1)
						))
						&& ((heading == 3) || (heading == 1))) {
					playerCustom.isOnWall = true;
					if (player.moveForward > 0) {
						if (playerCustom.rotationYaw == 0) {
							playerCustom.rotationYaw = player.rotationYaw;
							playerCustom.rotationPitch = player.rotationPitch;
							playerCustom.prevRotationPitch = player.prevRotationPitch;
							playerCustom.prevRotationYaw = player.prevRotationYaw;
						}
						if (player instanceof EntityPlayerSP) {
							if (((EntityPlayerSP) player).movementInput.jump
									&& !playerCustom.wallJump) {
								playerCustom.animRight = false;
								playerCustom.animLeft = false;
								if (heading == 3) {
									player.motionX = 0.7D;
									player.motionZ = 0.2D;
								}
								if (heading == 1) {
									player.motionX = -0.7D;
									player.motionZ = 0.2D;
								}
								player.motionY = 0.41999998688697815D;
								playerCustom.wallJump = true;
							}
						}
						if (heading == 3) {
							playerCustom.animLeft = true;
						}
						if (heading == 1) {
							playerCustom.animRight = true;
						}
					}
				} else {
					playerCustom.isOnWall = false;
					playerCustom.animLeft = false;
					playerCustom.animRight = false;
				}
				if (playerCustom.isOnWall && player.moveForward > 0) {
					player.motionZ *= 0.95D;
					player.motionX *= 0.95D;
					player.motionY *= 0.75D;
				}
			} else {
				playerCustom.isOnWall = false;
			}
		}
		if (player.onGround) {
			if (playerCustom.rotationYaw != 0) {
				playerCustom.rotationYaw = 0;
				if	(!playerCustom.isRolling)	{
					playerCustom.rotationPitch = 0;
				}
				playerCustom.prevRotationPitch = 0;
				playerCustom.prevRotationYaw = 0;
			}
			playerCustom.wallJump = false;
			playerCustom.animRight = false;
			playerCustom.animLeft = false;
			playerCustom.isJumpingOnWall = false;
		}
		if (player.capabilities.isFlying) {
			playerCustom.isOnWall = false;
			playerCustom.animRight = false;
			playerCustom.animLeft = false;
		}
		if (playerCustom.isGrabbing) {
			playerCustom.animLeft = false;
			playerCustom.animRight = false;
		}
	}

	@SubscribeEvent
	public void onClick(MouseEvent event) {
		if (((EntityPlayerCustom) Minecraft.getMinecraft().thePlayer
				.getExtendedProperties("Cube's Edge Player")).animLeft
				|| ((EntityPlayerCustom) Minecraft.getMinecraft().thePlayer
						.getExtendedProperties("Cube's Edge Player")).animRight) {
			event.setCanceled(true);
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Wall Jump";
	}
}
