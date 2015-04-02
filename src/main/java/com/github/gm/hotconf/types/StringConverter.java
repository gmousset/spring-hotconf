/**
 * 
 */
package com.github.gm.hotconf.types;

import org.springframework.util.StringUtils;

/**
 * @author Gwendal Mousset
 *
 */
public final class StringConverter implements TypeConverter<String> {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.github.gm.hotconf.types.TypeConverter#convertFrom(java.lang.String)
     */
    @Override
    public String convertFrom(final String pStr) {
        return StringUtils.trimWhitespace(pStr);
    }
}
