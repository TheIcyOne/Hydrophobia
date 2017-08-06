package com.headfishindustries.hydrophobia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Hydrophobia.MODID, name=Hydrophobia.NAME, version=Hydrophobia.VERSION, serverSideOnly=true, acceptableRemoteVersions="*")
public class Hydrophobia {
	public static final String MODID = "hydrophobia";
	public static final String NAME = "Hydrophobia";
	public static final String VERSION = "%gradle.version%";
	
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e){
		EventHandler.registerEvents();
	}
}
