package fr.zak.cubesedge.event;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.zak.cubesedge.ticks.ClientTickHandler;

public class RenderPlayerEventCustom {

	@SubscribeEvent
	public void onRenderFirstPerson(RenderPlayerEvent.Post event){
		if(ClientTickHandler.animLeft){
			((ModelBiped)ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, event.renderer, 1)).heldItemLeft = 50;
		}
		else{
			((ModelBiped)ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, event.renderer, 1)).heldItemLeft = 0;
		}
//		((ModelBiped)ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, event.renderer, 1)).bipedHead.offsetY = 1.4F;
//		((ModelBiped)ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, event.renderer, 1)).bipedLeftLeg.postRender(0.0625F);
	}
	
}