package io.bilicraft.r6.proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.bilicraft.r6.IReg;
import io.bilicraft.r6.ModItems;
import io.bilicraft.r6.ModMetaValue;
import io.bilicraft.r6.SurvivalList;
import io.bilicraft.r6.item.BaseItemFood;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModClientProxy extends ModProxy {
    private static Logger logging = LogManager
	    .getLogger(ModMetaValue.MOD_NAME + "|" + ModClientProxy.class.getSimpleName());

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {
	super.onPreInit(event);
	info("Init", "loadItemRenderModel-ClientOnly", "Start");
	try {
	    for (IReg<? extends Item> item : ModItems.items.values()) {
		logging.info("Register ItemRender [" + item.getName() + "] Start!");
		BaseItemFood.registerModNormalItemFoodRender(item, 0);
		logging.info("Register ItemRender [" + item.getName() + "] End!");
	    }
	} catch (Exception e) {
	    logging.error("Register Error![" + e.getClass().getName() + "]");
	    logging.error(getStackTrack(e));
	} finally {
	    info("Init", "loadItemRenderModel-ClientOnly", "End");
	}
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event) {
	super.onPostInit(event);
    }

    @Override
    public void onInit(FMLInitializationEvent event) {
	super.onInit(event);

    }

    private static StringBuilder getStackTrack(Exception e) {
	StringBuilder result = new StringBuilder("\n{");
	for (StackTraceElement ste : e.getStackTrace()) {
	    result.append("[" + ste.getClassName() + "," + ste.getFileName() + "," + ste.getMethodName() + ","
		    + ste.getLineNumber() + "]\n,");
	}
	result.append("}");
	return result;
    }

    private static void info(String init, String commit, String state) {
	SurvivalList.info(init, commit, state, logging);
    }
}
