package io.bilicraft.r6;

import io.bilicraft.r6.ModConfiguration.Config;

public class ModConfig {

    @Config(value = "test1")
    public static String teString1 = "test1";
    @Config(value = "test2")
    public static int teInteger2 = 666;
    @Config(value = "test3")
    public static double teDouble3 = 66.66D;
    @Config(value = "test4")
    public static float teFloat4 = 55.52F;
    @Config(value = "test5")
    public static String[] teStrings5 = new String[] { "t", "e", "S", "t", "r", "i", "n", "g", "s", "5" };
    @Config(value = "test6")
    public static int[] teIntergers6 = new int[] { 6, 6, 6, 6, 66, 6, 6, 6, 6, 6, 6, 6 };

}
