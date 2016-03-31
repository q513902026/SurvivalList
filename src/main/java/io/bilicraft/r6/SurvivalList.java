package io.bilicraft.r6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.bilicraft.r6.item.BaseItemFood;
import io.bilicraft.r6.item.BaseItem;
import io.bilicraft.r6.item.BaseItemDrink;
import io.bilicraft.r6.proxy.ModProxy;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModMetaValue.MOD_ID, version = ModMetaValue.MOD_VERSION, name = ModMetaValue.MOD_NAME)
public class SurvivalList {
    private static Logger logging = LogManager.getLogger(ModMetaValue.MOD_NAME);
    @Instance(ModMetaValue.MOD_ID)
    public static SurvivalList instance;
    @SidedProxy(clientSide = ModMetaValue.MOD_CLIENTPROXY, serverSide = ModMetaValue.MOD_SERVERPROXY)
    public static ModProxy proxy;
    private static ModConfiguration configuration;

    @EventHandler
    public void onModPreInit(FMLPreInitializationEvent event) {
	// loadConfiguration
	info("PreInit", "loadModConfiguration", "Start");
	configuration = new ModConfiguration(event.getSuggestedConfigurationFile(), ModConfig.class.getName());
	configuration.loadConfig();
	info("PreInit", "loadModConfiguration", "End");

	info("PreInit", "registerItems", "Start");
	regItems();
	info("PreInit", "registerItems", "End");
	info("PreInit", "add Item PotionEffect on eat", "Start");
	addItemPotionEffects();
	info("PreInit", "add Item PotionEffect on eat", "End");
	info("PreInit", "add BaseItemDrink drinkBackItem", "Start");
	addDrinkBackItems();
	info("PreInit", "add BaseItemDrink drinkBackItem", "End");

	proxy.onPreInit(event);
    }

    private void regItems() {
	// drink
	ModItems.huangjiu = (BaseItemDrink) new BaseItemDrink("huangjiu", 4, 0.9f, false).initItem().setAlwaysEdible()
		.setMaxStackSize(1);
	ModItems.maotai = (BaseItemDrink) new BaseItemDrink("maotai", 6, 0.95f, false).initItem().setAlwaysEdible()
		.setMaxStackSize(1);
	ModItems.sake = (BaseItemDrink) new BaseItemDrink("sake", 4, 0.9f, false).initItem().setAlwaysEdible()
		.setMaxStackSize(1);
	ModItems.coke = (BaseItemDrink) new BaseItemDrink("coke", 2, 0.5F, false).initItem().setAlwaysEdible()
		.setMaxStackSize(1);
	ModItems.cider = (BaseItemDrink) new BaseItemDrink("cider", 4, 0.8f, false).initItem().setAlwaysEdible()
		.setMaxStackSize(1);
	ModItems.champagne = (BaseItemDrink) new BaseItemDrink("champagne", 4, 0.8f, false).initItem().setAlwaysEdible()
		.setMaxStackSize(1);
	ModItems.brandy = (BaseItemDrink) new BaseItemDrink("brandy", 4, 0.8f, false).initItem().setAlwaysEdible()
		.setMaxStackSize(1);
	ModItems.cupOfCoffee = (BaseItemDrink) new BaseItemDrink("cup_of_coffee", 4, 0.8f, false).initItem()
		.setAlwaysEdible().setMaxStackSize(1);
	ModItems.energyDrink = (BaseItemDrink) new BaseItemDrink("energy_drink", 4, 0.8f, false).initItem()
		.setAlwaysEdible().setMaxStackSize(1);
	ModItems.greenTea = (BaseItemDrink) new BaseItemDrink("green_tea", 3, 0.7f, false).initItem().setAlwaysEdible()
		.setMaxStackSize(1);
	ModItems.redTea = (BaseItemDrink) new BaseItemDrink("red_tea", 3, 0.7f, false).initItem().setAlwaysEdible()
		.setMaxStackSize(1).setFull3D();
	ModItems.vodka = (BaseItemDrink) new BaseItemDrink("vodka", 4, 0.8f, false).initItem().setAlwaysEdible()
		.setMaxStackSize(1);
	ModItems.whiskey = (BaseItemDrink) new BaseItemDrink("whiskey", 4, 0.8f, false).initItem().setAlwaysEdible()
		.setMaxStackSize(1);
	ModItems.wine = (BaseItemDrink) new BaseItemDrink("wine", 4, 0.8f, false).initItem().setAlwaysEdible()
		.setMaxStackSize(1);
	ModItems.beer = (BaseItemDrink) new BaseItemDrink("beer", 4, 0.8f, false).initItem().setAlwaysEdible()
		.setMaxStackSize(1);
	// eatFood
	ModItems.lays = (BaseItemFood) new BaseItemFood("lays", 4, 0.7F, true).initItem().setAlwaysEdible()
		.setMaxStackSize(16);
	ModItems.baozi = (BaseItemFood) new BaseItemFood("baozi", 3, 0.4f, true).initItem().setAlwaysEdible()
		.setMaxStackSize(16);
	ModItems.chocolate = (BaseItemFood) new BaseItemFood("chocolate", 5, 0.4f, false).initItem().setAlwaysEdible()
		.setMaxStackSize(16);
	ModItems.swiss_roll = (BaseItemFood) new BaseItemFood("swiss_roll", 3, 0.7f, false).initItem().setAlwaysEdible()
		.setMaxStackSize(16);
	// misc food ingredients
	ModItems.coffeePot = (BaseItem) new BaseItem("coffee_pot").initItem().setMaxStackSize(1);
	ModItems.teaPot = (BaseItem) new BaseItem("tea_pot").initItem().setMaxStackSize(1);
	ModItems.coffeeCup = (BaseItem) new BaseItem("coffee_cup").initItem().setMaxStackSize(4);
	ModItems.teaCup = (BaseItem) new BaseItem("tea_cup").initItem().setMaxStackSize(4);
	ModItems.redTeaCup = (BaseItem) new BaseItem("red_tea_cup").initItem().setMaxStackSize(4);
	ModItems.mug = (BaseItem) new BaseItem("mug").initItem().setMaxStackSize(4);
    }

    private void addItemPotionEffects() {
	ModItems.lays.setPotionEffect(Potion.hunger.getId(), 10, 1, 0.7f).setPotionEffect(Potion.digSpeed.getId(), 15,
		1, 0.2f);
    }

    private void addDrinkBackItems() {
	ModItems.cupOfCoffee.setDrinkBackItem(ModItems.coffeeCup);
	ModItems.greenTea.setDrinkBackItem(ModItems.teaCup);
	ModItems.redTea.setDrinkBackItem(ModItems.redTeaCup);
	ModItems.beer.setDrinkBackItem(ModItems.mug);
    }

    @EventHandler
    public void onModInit(FMLInitializationEvent event) {
	proxy.onInit(event);

    }

    @EventHandler
    public void onModPostInit(FMLPostInitializationEvent event) {
	proxy.onPostInit(event);
    }

    public static Configuration getConfig() {
	return configuration;
    }

    public static void info(String init, String commit, String state, Logger logging) {
	logging.info(init + " --> " + commit + "--> [" + state + "]");
    }

    private void info(String init, String commit, String state) {
	info(init, commit, state, logging);
    }
}
