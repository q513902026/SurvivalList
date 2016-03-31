package io.bilicraft.r6.test.api;

import net.minecraft.item.EnumAction;
import net.minecraftforge.common.util.EnumHelper;

public enum FactorType {
    NONE(EnumAction.NONE, 0, IFactorItem.class);
    private EnumAction action;
    private int num;
    private Class<IFactorItem> actionClass;

    private FactorType(EnumAction action, int num, Class<IFactorItem> actionClass) {
	this.action = action;
	this.num = num;
	this.actionClass = actionClass;
    }
    public static String typeListToString()
    {
	StringBuilder sBuilder = new StringBuilder();
	for(FactorType factorType:values())
	{
	    sBuilder.append(" ,{"+factorType.name()+","+factorType.action.name()+","+factorType.num+","+factorType.actionClass.getSimpleName()+"}");
	}
	return sBuilder.toString();
    }
    @SuppressWarnings("rawtypes")
    private static Class[][] commonTypes = { { FactorType.class, EnumAction.class, int.class, Class.class } };
    
    public static void addEnum(String name, EnumAction action, int num, Class<?> actionClass) {
	EnumHelper.addEnum(commonTypes, FactorType.class, name, action, num, actionClass);
    }
    
    public static void main(String args[])
    {
	FactorType.addEnum("TEST", EnumAction.DRINK, 5, IFactorItem.class);
	System.out.println(FactorType.typeListToString());
    }
}
