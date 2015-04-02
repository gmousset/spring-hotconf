/**
 * 
 */
package com.github.gm.hotconf.types;

/**
 * @author Gwendal Mousset
 *
 */
public final class IntegerConverter implements TypeConverter<Integer> {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.github.gm.hotconf.types.TypeConverter#convertFrom(java.lang.String)
     */
    @Override
    public Integer convertFrom(final String pStr) {
        return Integer.valueOf(pStr);
    }
}
