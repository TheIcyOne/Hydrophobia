package com.headfishindustries.hydrophobia;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class EventHandler {
	
	double rainChance = ConfigHandler.cancelChance;
	
	public static void registerEvents(){
		EventHandler events = new EventHandler();
		MinecraftForge.EVENT_BUS.register(events);
	}
	
	@SubscribeEvent
	@SideOnly(Side.SERVER)
	public void onTick(WorldTickEvent e){
		if (e.world.getWorldInfo().getRainTime() == 1000){
			if (e.world.rand.nextDouble() <= rainChance/100){
				e.world.getWorldInfo().setRainTime(0);
				e.world.getWorldInfo().setRaining(false);
				Hydrophobia.LOGGER.info("The rain is staved off.");
			}
		}
	}
}
