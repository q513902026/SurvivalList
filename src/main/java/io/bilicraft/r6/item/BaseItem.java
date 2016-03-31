package io.bilicraft.r6.item;

import io.bilicraft.r6.IReg;
import io.bilicraft.r6.ModItems;
import io.bilicraft.r6.ModMetaValue;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseItem extends Item implements IReg<BaseItem>{
    protected String name = "";
    public BaseItem(String name) {
	super();
	this.name = name;
    }
    /**
     * 初始化食物 设置创造栏的位置 
     * 内部名称采用{@linkplain ModMetaValue#MOD_ID MODID}+"_"+未本地化名称 
     * @return
     */
    public BaseItem initItem() {
	this.setUnlocalizedName(name);
	this.setCreativeTab(CreativeTabs.tabMisc);
	GameRegistry.registerItem(this, ModMetaValue.MOD_ID+"_"+name);
	ModItems.items.put(name, this);
	return this;
    }
    @Override
    public String getName() {
	
	return name;
    }
    @Override
    public String getRenderName() {
	return name;
    }
    @Override
    public BaseItem getRenderItem() {
	return this;
    }
}
