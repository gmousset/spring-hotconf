/**
 * 
 */
package com.github.gm.hotconf.springconf;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

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
		GenericBeanDefinition def1 = new GenericBeanDefinition();
		def1.setBeanClass(HotConfigurableProperties.class);
		registry.registerBeanDefinition("configurableProperties", def1);
		
		GenericBeanDefinition def2 = new GenericBeanDefinition();
		def2.setBeanClass(HotConfigurablePropertyBeanProcessor.class);
		registry.registerBeanDefinition("configurablePropertyBeanProcessor", def2);
	}
}
