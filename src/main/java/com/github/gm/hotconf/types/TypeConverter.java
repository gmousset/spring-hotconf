/**
 * 
 */
package com.github.gm.hotconf.types;

/**
 * @author Gwendal Mousset
 * @param <T> The type to convert.
 */
public interface TypeConverter<T> {
    /**
     * Convert String to parameterized type.
     * 
     * @param str
     *            The string value.
     * @return The T value.
     */
    T convertFrom(String str);
}
