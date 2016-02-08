package fr.zak.cubesedge;

import java.io.File;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import fr.zak.cubesedge.event.ConstructEvent;
import fr.zak.cubesedge.movement.MovementGrab;
import fr.zak.cubesedge.movement.MovementJump;
import fr.zak.cubesedge.movement.MovementRoll;
import fr.zak.cubesedge.movement.MovementSlide;
import fr.zak.cubesedge.movement.MovementSprint;
import fr.zak.cubesedge.movement.MovementTurn;
import fr.zak.cubesedge.movement.MovementWallJump;
import fr.zak.cubesedge.packet.PacketPlayer;
import fr.zak.cubesedge.packet.PacketPlayer.CPacketPlayerAction;
import fr.zak.cubesedge.proxys.CommonProxy;
import fr.zak.cubesedge.ticks.PlayerTickHandler;

/**
 * 
 * @author Zak (alex.ulysse@gmail.com)
 * 
 */
@Mod(modid = "cubesedge", name = "Cube's Edge", version = Util.VERSION, guiFactory = "fr.zak.cubesedge.GuiFactory")
public class CubesEdge {

	@SidedProxy(clientSide = "fr.zak.cubesedge.proxys.ClientProxy", serverSide = "fr.zak.cubesedge.proxys.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Util.cfg = new Configuration(new File(Loader.instance().getConfigDir(),
				"cube's edge.cfg"));
		Util.cfg.load();
		Util.detectObfuscation();
		MinecraftForge.EVENT_BUS.register(new ConstructEvent());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(new PlayerTickHandler());
		Util.registerMovement(new MovementTurn());
		Util.registerMovement(new MovementRoll());
		Util.registerMovement(new MovementGrab());
		Util.registerMovement(new MovementWallJump());
		Util.registerMovement(new MovementJump());
		Util.registerMovement(new MovementSlide());
		Util.registerMovement(new MovementSprint());
		proxy.registerRenderThings();
//		Util.channel = NetworkRegistry.INSTANCE.newSimpleChannel("cubesedge");
//		Util.channel.registerMessage(
//				CPacketPlayerAction.Handler.class,
//				PacketPlayer.CPacketPlayerAction.class, 0, Side.SERVER);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		Iterator<Block> i = Block.blockRegistry.iterator();
		while (i.hasNext()) {
			Block block = i.next();
			if (block.getRenderType() == 0) {
				Util.cubes.add(block);
			}
		}
		Util.cubes.remove(Blocks.stone_pressure_plate);
		Util.cubes.remove(Blocks.wooden_pressure_plate);
		Util.cubes.remove(Blocks.stone_button);
		Util.cubes.remove(Blocks.snow_layer);
		Util.cubes.remove(Blocks.portal);
		Util.cubes.remove(Blocks.cake);
		Util.cubes.remove(Blocks.stained_glass_pane);
		Util.cubes.remove(Blocks.trapdoor);
		Util.cubes.remove(Blocks.wooden_slab);
		Util.cubes.remove(Blocks.wooden_button);
		Util.cubes.remove(Blocks.light_weighted_pressure_plate);
		Util.cubes.remove(Blocks.heavy_weighted_pressure_plate);
		Util.cubes.remove(Blocks.daylight_detector);
		Util.cubes.remove(Blocks.carpet);
		Util.cubes.remove(Blocks.stone_slab);
		Util.cubes.add(Blocks.log);
		Util.cubes.add(Blocks.log2);
		if(Loader.isModLoaded("chisel")){
			//TODO : Compability with chisel's blocks
		}
	}
}
