package fr.zak.cubesedge.ticks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import fr.zak.cubesedge.Movement;
import fr.zak.cubesedge.MovementClient;
import fr.zak.cubesedge.Util;
import fr.zak.cubesedge.entity.EntityPlayerCustom;

public class PlayerTickHandler {

	@SubscribeEvent
	public void playerUpdate(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			EntityPlayerCustom player = ((EntityPlayerCustom)event.player.getExtendedProperties("Cube's Edge Player"));
			for (int i = 0; i < Util.getMovements().length; i++) {
				Object o = Util.getMovements()[i];
				if (!((Movement) o).isMovementDisabled()) {
					((Movement) o).control(player, event.player, event.side);
//					Util.channel.sendToServer(new PacketPlayer.CPacketPlayerAction(i));
				}
			}
			for (Object o : Util.getClientsMovements()) {
//				if (!((IMovementClient) o).isMovementDisabled()) {
				if(event.side == Side.CLIENT){
					((MovementClient) o).controlClient(player, event.player);
				}
//				}
			}
		}
	}
}
