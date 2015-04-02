/**
 * 
 */
package com.github.gm.hotconf.types;

/**
 * @author Gwendal Mousset
 *
 */
public final class LongConverter implements TypeConverter<Long> {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.github.gm.hotconf.types.TypeConverter#convertFrom(java.lang.String)
     */
    @Override
    public Long convertFrom(final String pStr) {
        return Long.valueOf(pStr);
    }
}
