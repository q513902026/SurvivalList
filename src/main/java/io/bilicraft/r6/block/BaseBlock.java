package io.bilicraft.r6.block;

import java.util.TreeMap;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseBlock extends Block {
    public static TreeMap<String, BaseBlock> blocks = Maps.newTreeMap();
    private String name = "";

    public BaseBlock(Material materialIn) {
	super(materialIn);
	this.name = this.getClass().getSimpleName().replace("Block", "");
    }

    public BaseBlock(Material material, String name) {
	super(material);
	this.name = name;
    }

    public void initBlock() {
	this.setUnlocalizedName(name);
	this.setHardness(2.0F);
	this.setResistance(10.0F);
	this.setLightLevel(1.0F);
	this.setStepSound(soundTypeStone);
	GameRegistry.registerBlock(this, name);
	blocks.put(name, this);
    }

    public static void initBlocks() {
	for (BaseBlock baseBlock : blocks.values())
	    baseBlock.initBlock();
    }
}
