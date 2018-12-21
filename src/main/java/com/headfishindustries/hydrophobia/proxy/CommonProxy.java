package com.headfishindustries.hydrophobia.proxy;

import com.headfishindustries.hydrophobia.EventHandler;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
		public void preInit(FMLPreInitializationEvent e){
			EventHandler.registerEvents();
		}
}
