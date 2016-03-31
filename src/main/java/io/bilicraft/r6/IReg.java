package io.bilicraft.r6;

public interface IReg<T> {
    public String getName();

    public String getRenderName();

    public T initItem();

    public T getRenderItem();
}
