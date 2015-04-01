package com.github.gm.hotconf.types;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Accepted property field types.
 * @author Gwendal Mousset
 */
public enum AcceptedFieldTypes {
	
	/** Accepted types. */
	STRING(String.class, new StringConverter()),
	INTEGER(Integer.class, new IntegerConverter()),
	INT_PRIMITIVE(int.class, new IntegerConverter()),
	LONG(Long.class, new LongConverter()),
	LONG_PRIMITIVE(long.class, new LongConverter()),
	DOUBLE(Double.class, new DoubleConverter()),
	DOUBLE_PRIMITIVE(double.class, new DoubleConverter()),
	FLOAT(Double.class, new FloatConverter()),
	FLOAT_PRIMITIVE(float.class, new FloatConverter())
	;
	
	private Class<?> clazz;
	private TypeConverter<?> converter;
	private static Set<AcceptedFieldTypes> set;
	private static Set<Class<?>> acceptedClasses;
	private static Map<Class<?>, AcceptedFieldTypes> map;
	
	static {
		set = new HashSet<>();
		set.add(STRING);
		set.add(INTEGER);
		set.add(INT_PRIMITIVE);
		set.add(LONG);
		set.add(LONG_PRIMITIVE);
		set.add(DOUBLE);
		set.add(DOUBLE_PRIMITIVE);
		set.add(FLOAT);
		set.add(FLOAT_PRIMITIVE);
		
		acceptedClasses = new HashSet<Class<?>>();
		map = new HashMap<>();
		for (AcceptedFieldTypes aft : set) {
			acceptedClasses.add(aft.clazz);
			map.put(aft.clazz, aft);
		}
	}
	
	/**
	 * Enum constructor.
	 * @param pClazz The accepted class.
	 * @param pConverter The converter for this type.
	 */
	private AcceptedFieldTypes(Class<?> pClazz, TypeConverter<?> pConverter) {
		this.clazz = pClazz;
		this.converter = pConverter;
	}
	
	/**
	 * @return A set of all accepted classes.
	 */
	public static Set<Class<?>> classes() {
		return acceptedClasses;
	}
	
	/**
	 * @return A map to retrieve field type info by classes.
	 */
	public static Map<Class<?>, AcceptedFieldTypes> map() {
		return map;
	}
	
	/**
	 * @param pClazz A class.
	 * @return The appropriate converter.
	 */
	public static TypeConverter<?> converterForClass(Class<?> pClazz) {
		return map.get(pClazz).converter;
	}
	
	/**
	 * @return A converter for current type.
	 */
	public TypeConverter<?> getConverter() {
		return converter;
	}
}