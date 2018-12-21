package com.headfishindustries.hydrophobia;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.RangeInt;

@Config(modid=Hydrophobia.MODID, name=Hydrophobia.MODID + "/" + Hydrophobia.MODID)
public class ConfigHandler {
	@Config.Comment({"The percentage chance rain will be held back. Set to 100 to prevent all rain."})
	@Config.RangeDouble(min = 0, max=100)
	public static double rainCancelChance = 10;

	@Config.Name("RainCancellationChance")
	public static double cancelChance = 10;
	@Config.Comment({"The mode of thunder changes.", "Set to 0 for no change, 1 to change thunder to rain and 2 to change it to clear weather."})
	@RangeInt(min = 0, max=2)
	@Config.Name("ThunderMode")
	public static int thunderMode=0;
	@Config.Comment({"The percentage chance rain will continue after sleeping. Set to 100 to prevent rain cancellation on sleep."})
	@Config.RangeDouble(min = 0, max=100)
	@Config.Name("RainContinuationChance")
	public static double continueChance = 0;

}
