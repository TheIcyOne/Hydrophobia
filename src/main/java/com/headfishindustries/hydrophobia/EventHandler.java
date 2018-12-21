package com.headfishindustries.hydrophobia;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public final class EventHandler {
	
	static double rainChance = ConfigHandler.rainCancelChance;
	static double thunderChance = ConfigHandler.thunderCancelChance;
	static int thunderMode = ConfigHandler.thunderMode;
	
	public static void registerEvents(){
		EventHandler events = new EventHandler();
		MinecraftForge.EVENT_BUS.register(events);
	}
	
	@SubscribeEvent
	public static void onTick(WorldTickEvent e){
		if (e.world.getWorldInfo().getRainTime() == 1000){
			if (e.world.rand.nextDouble() <= rainChance/100){
				e.world.getWorldInfo().setRainTime(0);
				e.world.getWorldInfo().setRaining(false);
				Hydrophobia.LOGGER.info("The rain is staved off.");
			}
		}
		
		if (e.world.getWorldInfo().getThunderTime() == 1000) {
			if (e.world.rand.nextDouble() <= thunderChance/100){
				e.world.getWorldInfo().setThunderTime(0);
				e.world.getWorldInfo().setThundering(false);
				Hydrophobia.LOGGER.info("The storm is staved off.");
			}
		}
		if (e.world.isThundering() && thunderMode > 0) {
			if (thunderMode == 1) {
                e.world.getWorldInfo().setThunderTime(0);
                e.world.getWorldInfo().setThundering(false);
                e.world.getWorldInfo().setRaining(true);
			}else {
				e.world.getWorldInfo().setRainTime(0);
				e.world.getWorldInfo().setThunderTime(0);
				e.world.getWorldInfo().setRaining(false);
                e.world.getWorldInfo().setThundering(false);
			}
		}
	}

}
