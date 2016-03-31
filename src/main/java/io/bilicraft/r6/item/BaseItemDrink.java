package io.bilicraft.r6.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class BaseItemDrink extends BaseItemFood {
    protected int times = 4;
    private int lastStackSize = 0;

    public BaseItemDrink(String name, int times, int healamount, float dura, boolean wolfFood) {
	super(name, healamount, dura, wolfFood);
	this.times = times;
	setMaxDamage(times - 1);
    }
    public BaseItemDrink(String name, int healamount, float dura, boolean wolfFood) {
	super(name, healamount, dura, wolfFood);
	setMaxDamage(times - 1);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
	if (lastStackSize == 0) {
	    lastStackSize = stack.stackSize;
	}
	stack.damageItem(1, playerIn);
	playerIn.getFoodStats().addStats(this, stack);

	worldIn.playSoundAtEntity(playerIn, "random.burp", 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
	this.onFoodEaten(stack, worldIn, playerIn);
	// base add backItem
	if (lastStackSize > stack.stackSize) {
	    playerIn.inventory.addItemStackToInventory(new ItemStack(drinkBackItem, 1));
	    lastStackSize = stack.stackSize;
	}
	// base end
	playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
	return stack;
    }
    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }
    @Override
    public BaseItemDrink setDrinkBackItem(Item item) {
        return (BaseItemDrink) super.setDrinkBackItem(item);
    }

}
