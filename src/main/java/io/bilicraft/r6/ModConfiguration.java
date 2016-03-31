package io.bilicraft.r6;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.config.Configuration;

public class ModConfiguration extends Configuration {
    public static Logger logging = LogManager.getLogger(ModConfiguration.class.getSimpleName());
    private String clazz;

    public ModConfiguration(File configFile, String clasz) {
	super(configFile);
	this.clazz = clasz;

    }

    public void loadConfig() {
	try {
	    load();
	    Class<?> clazs = findClass(clazz);
	    for (Field f : clazs.getFields()) {
		if (f.isAnnotationPresent(Config.class)) {
		    Config anno = f.getAnnotation(Config.class);
		    Class<?> type = f.getType();
		    setConfigPara(clazs, f, anno, type);
		}
	    }
	} catch (Exception e) {

	} finally {
	    save();
	}
    }

    private void setConfigPara(Class<?> superClasz, Field configField, Config configAnno, Class<?> configType)
	    throws IllegalAccessException {
	if (configType.equals(int.class)) {
	    configField.set(superClasz, get(configAnno.value(), configField.getName(), configField.getInt(superClasz))
		    .getInt(configField.getInt(superClasz)));
	} else if (configType.equals(double.class)) {
	    configField.set(superClasz,
		    get(configAnno.value(), configField.getName(), configField.getDouble(superClasz))
			    .getDouble(configField.getDouble(superClasz)));
	} else if (configType.equals(boolean.class)) {
	    configField.set(superClasz,
		    get(configAnno.value(), configField.getName(), configField.getBoolean(superClasz))
			    .getBoolean(configField.getBoolean(superClasz)));
	} else if (configType.equals(int[].class)) {
	    configField.set(superClasz,
		    get(configAnno.value(), configField.getName(), (int[]) (configField.get(superClasz))).getIntList());
	
	} else if (configType.equals(String[].class)) {
	    configField.set(superClasz,
		    get(configAnno.value(), configField.getName(), (String[]) (configField.get(superClasz)))
			    .getStringList());
	}
    }

    @SuppressWarnings("rawtypes")
    public static Class findClass(String clasz) throws ClassNotFoundException {
	logging.debug("LoadClass :" + clasz);
	return Thread.currentThread().getContextClassLoader().loadClass(clasz);
    }

    /**
     * 
     * @author HopeAsd
     *
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public static @interface Config {
	/**
	 * 获取配置项的值
	 * 
	 * @return {@link ModConfiguration#init()}
	 */
	String value() default CATEGORY_GENERAL;

	// TODO 对配置项的初始化加载中未完成对commit的加载
	// String commit() default "";
    }

}
