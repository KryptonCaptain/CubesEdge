package fr.zak.cubesedge;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Config {

	public static boolean renderLeftHand;
	public static boolean showSpeedometer;
	public static boolean allowSlow;
	
	public static boolean loaded_Battlegear;
	
	public static Configuration config;
	
	public static void loadConfig(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		renderLeftHand = config.get("Modules", "Render left hand", true, 
				"Renders the left hand you are not holding anything. Will be automatically disabled if Battlegear is installed due to weirdness.")
				.getBoolean(true);
		
		showSpeedometer = config.get("Modules", "Show speed HUD", true, 
				"Controls whether speed HUD is rendered in the bottom right corner. Disable if you are using another mod like Speedometer")
				.getBoolean(true);
		
		allowSlow = config.get("Modules", "Enable time-slow effect", true, 
				"Allows use of the time-slow effect. May best be disabled on servers")
				.getBoolean(true);
		
		config.save();
	}
	
	public static void checkMods() {
		
		loaded_Battlegear = Loader.isModLoaded("battlegear2");
		
	}
	
	
}
