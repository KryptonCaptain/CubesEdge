package fr.zak.cubesedge;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Config {

	public static boolean renderLeftHand;
	public static boolean renderSpeedometer;
	
	public static boolean movementSlow;
	public static boolean movementTurn;
	public static boolean movementRoll;
	public static boolean movementSlide;
	
	public static boolean loaded_Battlegear;
	
	public static Configuration config;
	
	public static void loadConfig(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		renderLeftHand = config.get("Render", "Render left hand", true, 
				"Renders the left hand you are not holding anything. Will be automatically disabled if Battlegear is installed due to weirdness.")
				.getBoolean(true);
		
		renderSpeedometer = config.get("Render", "Show speed HUD", true, 
				"Controls whether speed HUD is rendered in the bottom right corner. Disable if you are using another mod like Speedometer")
				.getBoolean(true);
		
		movementSlow = config.get("Movement", "Enable time-slow effect", true, 
				"Allows use of the time-slow effect. May best be disabled on servers")
				.getBoolean(true);
		/*
		movementTurn = config.get("Movement", "Enable quick-turn", true, 
				"Allows use of the time-slow effect. May best be disabled on servers")
				.getBoolean(true);
		
		movementRoll = config.get("Movement", "Enable rolling", true, 
				"Allows use of the time-slow effect. May best be disabled on servers")
				.getBoolean(true);
		
		movementSlide = config.get("Movement", "Enable sliding", true, 
				"Allows use of the time-slow effect. May best be disabled on servers")
				.getBoolean(true);
		*/
		
		config.save();
	}
	
	public static void checkMods() {
		
		loaded_Battlegear = Loader.isModLoaded("battlegear2");
		
	}
	
	
}
