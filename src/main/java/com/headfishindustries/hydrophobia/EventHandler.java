package com.headfishindustries.hydrophobia;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public final class EventHandler {
	
	static double rainChance = ConfigHandler.rainCancelChance;
	static int thunderMode = ConfigHandler.thunderMode;
	
	private static Map<World, Boolean> rainingMap = new HashMap<World, Boolean>();
	
	public static void registerEvents(){
		MinecraftForge.EVENT_BUS.register(EventHandler.class);
	}
	
	@SubscribeEvent
	public static void onTick(WorldTickEvent e){
		if (e.world.isRemote) return;
		WorldInfo wi = e.world.getWorldInfo();
		rainingMap.put(e.world, wi.isRaining());
		if (wi.getRainTime() == 1000){
			if (e.world.rand.nextDouble() <= rainChance/100){
				wi.setRainTime(0);
				wi.setRaining(false);
				Hydrophobia.LOGGER.info("The rain is staved off.");
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

		if (wi.getWorldTime() % 200 == 0) {
			if (wi.isThundering()){
				switch(ConfigHandler.thunderMode) {
					case 0:
						break;
					case 1:
						wi.setThundering(false);
						wi.setRaining(true);
						break;
					case 2:
						wi.setThundering(false);
						wi.setRaining(false);
						wi.setRainTime(0);
						break;
					}
				}
			
				
			}
		}
	}
	
	@SubscribeEvent
	public static void onWake(PlayerWakeUpEvent e) {
		World w = e.getEntityPlayer().world;
		if (w.isRemote) return;
		if (w.getWorldTime() % 24000L <= 10 && rainingMap.get(w) && w.rand.nextDouble() <= ConfigHandler.continueChance/100) {
			Timer t = new Timer();
			t.schedule(new TimerTask() {

				@Override
				public void run() {
					w.getWorldInfo().setRaining(true);
					w.getWorldInfo().setRainTime((300 + (new Random()).nextInt(600)) * 20);
					Hydrophobia.LOGGER.info("The rain continues into the new day.");
				}
				
			}, 10);
			

		}
	}

}
