package org.ranjith.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
	/**
	 * Returns wrapper class for a primitive passed
	 * @param type primitive type.
	 * @return corresponding Class object if primitive.
	 */
	public static Class getWrapperType(Class type) {
		if(!type.isPrimitive()){
			return type;
		}else {
			return (Class) PrimitiveToClassMap.get(type);
		}
	}
	
	/**
	 * Returns only year/month/date part of 
	 * given date object. The purpose is to get
	 * rid of Time part. Any reference to time will
	 * be set to midnight.
	 * @param date
	 * @return date without time on it.
	 */
	public static Date getDateYYYYMMDD(Date date){
		 Calendar actualCalendar = Calendar.getInstance();
		 actualCalendar.setTime(date);
		 Calendar modifiedCalendar = new GregorianCalendar(actualCalendar.get(Calendar.YEAR),actualCalendar.get(Calendar.MONTH),actualCalendar.get(Calendar.DAY_OF_MONTH));
	     return new Date(modifiedCalendar.getTimeInMillis());	
	}
	
	public static boolean isEmptyOrNullString(String string) {
		return (string == null || string.trim().length() == 0);
	}
}
