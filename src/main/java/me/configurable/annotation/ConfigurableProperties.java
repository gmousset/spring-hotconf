/**
 * 
 */
package me.configurable.annotation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gwendalmousset
 *
 */
public class ConfigurableProperties {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurableProperties.class);
	
	private Map<String, PropertyInfo> properties;
	
	public ConfigurableProperties() {
		super();
		this.properties = new HashMap<>();
	}
	
	public Object getPropertyValue(String propertyName) {
		PropertyInfo pi = this.properties.get(propertyName);
		
		Object ret;
		try {
			pi.field.setAccessible(true);
			ret = pi.field.get(pi.bean);
			pi.field.setAccessible(false);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			LOGGER.error(e.getMessage());
			ret = "error";
		}
		return ret;
	}
	
	public void setPropertyValue(String propertyName, Object value) {
		PropertyInfo pi = this.properties.get(propertyName);
		try {
			pi.field.setAccessible(true);
			pi.field.set(pi.bean, value);
			pi.field.setAccessible(false);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	public void addProperty(Object bean, Field field, String propertyName) {
		PropertyInfo pi = new PropertyInfo(bean, field);
		this.properties.put(propertyName, pi);
	}
	
	public Set<String> getAllProperties() {
		return this.properties.keySet(); 
	}
	
	private class PropertyInfo {
		public Object bean;
		public Field field;
		
		public PropertyInfo(Object bean, Field field) {
			this.bean = bean;
			this.field = field;
		}
	}
}

