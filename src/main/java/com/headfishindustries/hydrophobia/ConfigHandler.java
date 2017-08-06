package com.headfishindustries.hydrophobia;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
@Config(modid=Hydrophobia.MODID, name=Hydrophobia.MODID + "/" + Hydrophobia.MODID)
public class ConfigHandler {
	@Config.Comment({"~SERVER ONLY~","The percentage chance rain will be held back. Set to 100 to prevent all rain."})
	@Config.RangeDouble(min = 0, max=100)
	@Config.Name("RainCancellationChance")
	public static double cancelChance = 10;
}
