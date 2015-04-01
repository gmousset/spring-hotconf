/**
 * 
 */
package com.github.gm.hotconf.types;

/**
 * @author Gwendal Mousset
 */
public class DoubleConverter implements TypeConverter<Double> {

	/*
	 * (non-Javadoc)
	 * @see com.github.gm.hotconf.types.TypeConverter#convertFrom(java.lang.String)
	 */
	@Override
	public Double convertFrom(final String pStr) {
		return Double.valueOf(pStr);
	}
}
