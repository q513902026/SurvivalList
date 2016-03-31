package io.bilicraft.r6.test.api;

import net.minecraft.item.EnumAction;

public interface IFactorItem {
    /**
     * 获取该物品所依赖的要素
     * @return 依赖的要素列表 通过FactorType.valueof(name)获取实例
     */
    public String[] getFactorList();
    /**
     * 在物品被使用前调用
     */
    public abstract boolean doActionStart();
    /**
     * 在物品使用中被调用
     */
    public abstract boolean doActioning();
    /**
     * 在物品结束使用后调用
     */
    public abstract boolean doActionEnd();
    /**
     * 判断当前进行了什么样的动作
     * @return 
     */
    public EnumAction getActionType();
}
