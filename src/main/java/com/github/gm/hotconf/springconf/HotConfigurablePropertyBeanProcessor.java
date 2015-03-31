/**
 * 
 */
package com.github.gm.hotconf.springconf;

import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.StringUtils;

import com.github.gm.hotconf.HotConfigurableProperties;
import com.github.gm.hotconf.annotations.HotConfigurableProperty;

/**
 * @author Gwendal Mousset
 *
 */
public final class HotConfigurablePropertyBeanProcessor implements BeanPostProcessor {

	/** Configurable properties manager. */
	@Autowired
	private HotConfigurableProperties confProperties;
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {
			@Override
			public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
				if (field.getAnnotation(HotConfigurableProperty.class) != null && field.getAnnotation(Value.class) != null) {
					Value value = field.getAnnotation(Value.class);
					String rawPropertyName = value.value();
					String propertyName = StringUtils.deleteAny(rawPropertyName, "${}");
					confProperties.addProperty(bean, field, propertyName);
				}
			}
		});
		
		return bean;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
		return bean;
	}
}
