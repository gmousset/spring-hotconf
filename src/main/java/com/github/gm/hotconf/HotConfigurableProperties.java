/**
 * 
 */
package com.github.gm.hotconf;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Central class:
 * <ul>
 * <li>This object contains all configurable properties information.</li>
 * <li>This object allows property value modification.</li>
 * </ul>
 * 
 * @author Gwendal Mousset
 */
public final class HotConfigurableProperties {

	/** Class logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(HotConfigurableProperties.class);
	
	/** 
	 * Property map. 
	 * The key is the declared property name.
	 */
	private Map<String, PropertyInfo> properties;
	
	/**
	 * Default constructor.
	 */
	public HotConfigurableProperties() {
		super();
		this.properties = new HashMap<>();
	}
	
	/**
	 * Get property value.
	 * @param pPropertyName The property name.
	 * @return The property value.
	 */
	public Object getPropertyValue(final String pPropertyName) {
		PropertyInfo pi = this.properties.get(pPropertyName);
		
		Object ret;
		try {
			final boolean accessible = pi.field.isAccessible();
			if (!accessible) {
				pi.field.setAccessible(true);
			}
			
			ret = pi.field.get(pi.bean);
			
			if (!accessible) {
				pi.field.setAccessible(false);
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			LOGGER.error(e.getMessage());
			ret = "error";
		}
		return ret;
	}
	
	/**
	 * Set property value.
	 * @param pPropertyName The property name.
	 * @param pNewValue The new property value.
	 */
	public void setPropertyValue(final String pPropertyName, final Object pNewValue) {
		final PropertyInfo pi = this.properties.get(pPropertyName);
		try {
			pi.field.setAccessible(true);
			pi.field.set(pi.bean, pNewValue);
			pi.field.setAccessible(false);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	/**
	 * Add a new configurable property.
	 * @param pBean The property owner.
	 * @param pField The field.
	 * @param pPropertyName The property name.
	 */ 
	public void addProperty(final Object pBean, final Field pField, final String pPropertyName) {
		final PropertyInfo pi = new PropertyInfo(pBean, pField);
		this.properties.put(pPropertyName, pi);
	}
	
	/**
	 * Get all configurable property list.
	 * @return A set of property names.
	 */
	public Set<String> getAllProperties() {
		return this.properties.keySet(); 
	}
	
	/**
	 * Property representation. 
	 * Info :
	 * - Owner
	 * - Field
	 */
	private final class PropertyInfo {
		/** Owner bean. */
		private Object bean;
		
		/** Field. */
		private Field field;
		
		/**
		 * Constructor.
		 * @param pBean The owner bean.
		 * @param pField The field.
		 */
		public PropertyInfo(final Object pBean, final Field pField) {
			this.bean = pBean;
			this.field = pField;
		}
	}
}

