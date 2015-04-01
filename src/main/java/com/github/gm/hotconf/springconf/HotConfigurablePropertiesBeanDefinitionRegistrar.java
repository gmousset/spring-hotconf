/**
 * 
 */
package com.github.gm.hotconf.springconf;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.github.gm.hotconf.HotConfigurableHooks;
import com.github.gm.hotconf.HotConfigurableProperties;

/**
 * @author Gwendal Mousset
 *
 */
public class HotConfigurablePropertiesBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	/* (non-Javadoc)
	 * @see org.springframework.context.annotation.ImportBeanDefinitionRegistrar#registerBeanDefinitions(org.springframework.core.type.AnnotationMetadata, org.springframework.beans.factory.support.BeanDefinitionRegistry)
	 */
	@Override
	public final void registerBeanDefinitions(final AnnotationMetadata importingClassMetadata, final BeanDefinitionRegistry registry) {
		GenericBeanDefinition beanDefConfProps = new GenericBeanDefinition();
		beanDefConfProps.setBeanClass(HotConfigurableProperties.class);
		registry.registerBeanDefinition("configurableProperties", beanDefConfProps);
		
		GenericBeanDefinition beanDefConfHooks = new GenericBeanDefinition();
		beanDefConfHooks.setBeanClass(HotConfigurableHooks.class);
		registry.registerBeanDefinition("configurableHooks", beanDefConfHooks);
		
		GenericBeanDefinition beanDefPropBeanProcessor = new GenericBeanDefinition();
		beanDefPropBeanProcessor.setBeanClass(HotConfigurablePropertyBeanProcessor.class);
		registry.registerBeanDefinition("configurablePropertyBeanProcessor", beanDefPropBeanProcessor);
	}
}
