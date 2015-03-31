/**
 * 
 */
package me.configurable.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author gwendalmousset
 *
 */
public class ConfigurablePropertiesBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	/* (non-Javadoc)
	 * @see org.springframework.context.annotation.ImportBeanDefinitionRegistrar#registerBeanDefinitions(org.springframework.core.type.AnnotationMetadata, org.springframework.beans.factory.support.BeanDefinitionRegistry)
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		GenericBeanDefinition def1 = new GenericBeanDefinition();
		def1.setBeanClass(ConfigurableProperties.class);
		registry.registerBeanDefinition("configurableProperties", def1);
		
		GenericBeanDefinition def2 = new GenericBeanDefinition();
		def2.setBeanClass(ConfigurablePropertyBeanProcessor.class);
		registry.registerBeanDefinition("configurablePropertyBeanProcessor", def2);
	}
}
