package io.bilicraft.r6.proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.bilicraft.r6.ModMetaValue;
import io.bilicraft.r6.SurvivalList;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModProxy {
    private static Logger logging = LogManager.getLogger(ModMetaValue.MOD_ID + "|" + ModProxy.class.getSimpleName());

    public void onPreInit(FMLPreInitializationEvent event) {
    }

    public void onInit(FMLInitializationEvent event) {
    }

    public void onPostInit(FMLPostInitializationEvent event) {
    }

    @SuppressWarnings("unused")
    private static void info(String init, String commit, String state) {
	SurvivalList.info(init, commit, state, logging);
    }
}
