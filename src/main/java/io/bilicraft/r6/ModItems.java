package io.bilicraft.r6;

import java.util.TreeMap;

import com.google.common.collect.Maps;

import io.bilicraft.r6.item.BaseItem;
import io.bilicraft.r6.item.BaseItemDrink;
import io.bilicraft.r6.item.BaseItemFood;
import net.minecraft.item.Item;

public class ModItems {
    public static TreeMap<String, IReg<? extends Item>> items = Maps.newTreeMap();
    // drink
    public static BaseItemDrink huangjiu;
    public static BaseItemDrink maotai;
    public static BaseItemDrink sake;
    public static BaseItemDrink coke;
    public static BaseItemDrink cider;
    public static BaseItemDrink champagne;
    public static BaseItemDrink brandy;
    public static BaseItemDrink cupOfCoffee; // drinkBackItem is coffeeCup
    public static BaseItemDrink energyDrink;
    public static BaseItemDrink greenTea; // drinkBackItem is teaCup
    public static BaseItemDrink redTea; // drinkBackItem is redTeaCup
    public static BaseItemDrink vodka;
    public static BaseItemDrink whiskey;
    public static BaseItemDrink wine;
    public static BaseItemDrink beer; // drinkBackItem is mug
    // eat
    public static BaseItemFood lays;
    public static BaseItemFood baozi;
    public static BaseItemFood chocolate;
    public static BaseItemFood swiss_roll;
    
    //misc
    public static BaseItem coffeePot;
    public static BaseItem teaPot;
    public static BaseItem coffeeCup;
    public static BaseItem teaCup;
    public static BaseItem redTeaCup;
    public static BaseItem mug;
}
