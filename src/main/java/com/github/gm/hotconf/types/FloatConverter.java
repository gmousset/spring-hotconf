/**
 * 
 */
package com.github.gm.hotconf.types;

/**
 * @author Gwendal Mousset
 *
 */
public class FloatConverter implements TypeConverter<Float> {

	/*
	 * (non-Javadoc)
	 * @see com.github.gm.hotconf.types.TypeConverter#convertFrom(java.lang.String)
	 */
	@Override
	public Float convertFrom(String pStr) {
		return Float.valueOf(pStr);
	}
}
