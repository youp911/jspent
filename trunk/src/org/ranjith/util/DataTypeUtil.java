package org.ranjith.util;

import java.util.HashMap;
import java.util.Map;

public class DataTypeUtil {
	static final Map PrimitiveToClassMap = new HashMap();
	static {
		PrimitiveToClassMap.put(int.class, Integer.class);
		PrimitiveToClassMap.put(long.class, Long.class);
		PrimitiveToClassMap.put(float.class, Float.class);
		PrimitiveToClassMap.put(double.class, Double.class);
		PrimitiveToClassMap.put(boolean.class, Boolean.class);
		PrimitiveToClassMap.put(char.class, Character.class);
		PrimitiveToClassMap.put(short.class, Short.class);
		PrimitiveToClassMap.put(byte.class, Byte.class);
	}
	private DataTypeUtil() {
		
	}
	public static Class getWrapperType(Class type) {
		if(!type.isPrimitive()){
			return type;
		}else {
			return (Class) PrimitiveToClassMap.get(type);
		}
	}
}
