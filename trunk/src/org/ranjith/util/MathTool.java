/**
 * 
 */
package org.ranjith.util;

/**
 * A util class to perform Math operations on random objects. Heavily copied
 * from apache velocity code.
 * 
 * @author ranjith.
 * 
 */
public class MathTool {

	public static Number toNumber(Object o) {
		if (o == null) {
			return null;
		}
		if (o instanceof Number) {
			return (Number) o;
		}
		try {
			return parseNumber(String.valueOf(o));
		} catch (NumberFormatException e) {
			return null;
		}
	}

	protected static Number parseNumber(String value)
			throws NumberFormatException {
		// check for the floating point
		if (value.indexOf('.') < 0) {
			// check for large numbers
			long i = new Long(value).longValue();
			if (i > Integer.MAX_VALUE || i < Integer.MIN_VALUE) {
				return new Long(i);
			} else {
				return new Integer((int) i);
			}
		} else {
			return new Double(value);
		}
	}

}
