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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public final class EventHandler {
	
	double rainChance = ConfigHandler.cancelChance;
	
	private Map<World, Boolean> rainingMap = new HashMap<World, Boolean>();
	
	public static void registerEvents(){
		EventHandler events = new EventHandler();
		MinecraftForge.EVENT_BUS.register(events);
	}
	
	@SubscribeEvent
	public void onTick(WorldTickEvent e){
		WorldInfo wi = e.world.getWorldInfo();
		Hydrophobia.LOGGER.info(wi.getRainTime());
		if (wi.getRainTime() == 1000){
			if (e.world.rand.nextDouble() <= rainChance/100){
				wi.setRainTime(0);
				wi.setRaining(false);
				Hydrophobia.LOGGER.info("The rain is staved off.");
			}
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
			rainingMap.put(e.world, wi.isRaining());
		}
	}
	
	@SubscribeEvent
	public void onWake(PlayerWakeUpEvent e) {
		World w = e.getEntityPlayer().world;
		if (w.getWorldTime() % 24000L <= 10 && rainingMap.get(w) && w.rand.nextDouble() >= ConfigHandler.continueChance/100) {
			Timer t = new Timer();
			t.schedule(new TimerTask() {

				@Override
				public void run() {
					w.getWorldInfo().setRaining(true);
					w.getWorldInfo().setRainTime((300 + (new Random()).nextInt(600)) * 20);
				}
				
			}, 10);
			
		}
	}
}
