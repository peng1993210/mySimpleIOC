package com.IOC.Utils;

public class ReflectUtil {

	public static Object newInstance(Class<?> clazz) {
		if(clazz!=null){
			try {
				return clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
