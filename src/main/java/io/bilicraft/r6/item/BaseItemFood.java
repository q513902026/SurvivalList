package io.bilicraft.r6.item;

import java.util.HashMap;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import io.bilicraft.r6.IReg;
import io.bilicraft.r6.ModItems;
import io.bilicraft.r6.ModMetaValue;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseItemFood extends ItemFood implements IReg<BaseItemFood> {
    protected HashMap<PotionEffect, Float> potionEffect = Maps.newHashMap();
    protected String name = "";
    protected Item drinkBackItem = Item.getItemFromBlock(Blocks.air);
    protected boolean isDrink = false;

    public BaseItemFood(String name, int healamount) {
	super(healamount, false);
	this.name = name;
    }

    public BaseItemFood(String name, int healamount, float dura, boolean wolfFood) {
	super(healamount, dura, wolfFood);
	this.name = name;
    }

    public BaseItemFood setDrink() {
	isDrink = true;
	return this;
    }

    /**
     * 初始化食物 设置创造栏的位置 内部名称采用{@linkplain ModMetaValue#MOD_ID MODID}+"_"+未本地化名称
     * 
     * @return
     */
    public BaseItemFood initItem() {
	this.setUnlocalizedName(getName());
	this.setCreativeTab(CreativeTabs.tabFood);
	GameRegistry.registerItem(this, ModMetaValue.MOD_ID + "_" + getName());
	//System.out.println(GameRegistry.findUniqueIdentifierFor(this).toString());
	ModItems.items.put(getName(), this);
	return this;
    }

    public String getName() {
	return name;
    }

    public BaseItemFood setDrinkBackItem(Item item) {
	this.drinkBackItem = item;
	return this;
    }

    @Override
    public ItemFood setPotionEffect(int id, int duration, int amplifier, float probability) {
	potionEffect.put(new PotionEffect(id, duration * 20, amplifier), probability);
	return this;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
	if (isDrink) {
	    return EnumAction.DRINK;
	}
	return super.getItemUseAction(stack);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
	--stack.stackSize;
	playerIn.getFoodStats().addStats(this, stack);

	worldIn.playSoundAtEntity(playerIn, "random.burp", 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
	this.onFoodEaten(stack, worldIn, playerIn);
	// base add backItem
	playerIn.inventory.addItemStackToInventory(new ItemStack(drinkBackItem, 1));
	// base end
	playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
	return stack;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
	if (!worldIn.isRemote) {
	    // 使其支持多药水效果添加
	    for (Entry<PotionEffect, Float> entry : potionEffect.entrySet()) {
		if (worldIn.rand.nextFloat() < entry.getValue()) {
		    player.addPotionEffect(entry.getKey());
		}
	    }
	}
    }

    @SideOnly(Side.CLIENT)
    /**
     * 用于注册MOD中的物品
     * 
     * @param item
     * @param meta
     */
    public static void registerModNormalItemFoodRender(IReg<? extends Item> item, int meta) {
	ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(
		ModMetaValue.MOD_ID.toLowerCase() + ":" + item.getRenderName(), "inventory");
	ModelLoader.setCustomModelResourceLocation(item.getRenderItem(), meta, itemModelResourceLocation);

    }

    @Override
    public String getRenderName() {
	return name;
    }

    @Override
    public BaseItemFood getRenderItem() {
	return this;
    }
}
