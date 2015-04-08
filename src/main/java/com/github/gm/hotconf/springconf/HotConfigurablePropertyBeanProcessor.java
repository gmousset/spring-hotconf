/**
 * 
 */
package com.github.gm.hotconf.springconf;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.MethodCallback;
import org.springframework.util.StringUtils;

import com.github.gm.hotconf.HotConfigurableHooks;
import com.github.gm.hotconf.HotConfigurableProperties;
import com.github.gm.hotconf.annotations.HotConfigurableProperty;
import com.github.gm.hotconf.annotations.HotConfigurationHookAfter;
import com.github.gm.hotconf.annotations.HotConfigurationHookBefore;

/**
 * @author Gwendal Mousset
 *
 */
public final class HotConfigurablePropertyBeanProcessor implements BeanPostProcessor {

    /** Class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(HotConfigurablePropertyBeanProcessor.class);

    /** Configurable properties manager. */
    @Autowired
    private HotConfigurableProperties confProperties;

    /** Configurable hooks manager. */
    @Autowired
    private HotConfigurableHooks confHooks;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.beans.factory.config.BeanPostProcessor#
     * postProcessBeforeInitialization(java.lang.Object, java.lang.String)
     */
    @Override
    public Object postProcessBeforeInitialization(final Object pBean, final String pBeanName) throws BeansException {
        // looking for @HotConfigurableProperty
        this.processFields(pBean, pBeanName);
        // looking for @HotConfigurationHook
        this.processMethods(pBean);
        return pBean;
    }

    /**
     * Process annotations on methods.
     * 
     * @param pBean
     *            The bean field owner.
     *            
     * @throws BeansException
     *             If an error occurs.
     */
    private void processMethods(final Object pBean) throws BeansException {
        ReflectionUtils.doWithMethods(pBean.getClass(), new MethodCallback() {
            @Override
            public void doWith(final Method method) throws IllegalArgumentException, IllegalAccessException {
                // look for @HotConfigurationHookXXX annotation
                final HotConfigurationHookBefore annotationBefore = method.getAnnotation(HotConfigurationHookBefore.class);
                if (annotationBefore != null) {
                    confHooks.addHookBefore(pBean, method, annotationBefore.priority(), annotationBefore.value());
                }
                final HotConfigurationHookAfter annotationAfter = method.getAnnotation(HotConfigurationHookAfter.class);
                if (annotationAfter != null) {
                    confHooks.addHookAfter(pBean, method, annotationAfter.priority(), annotationAfter.value());
                }
            }
        });
    }

    /**
     * Process annotations on fields.
     * 
     * @param pBean
     *            The bean field owner.
     * @param pBeanName
     *            The bean name in Spring context.
     * @throws BeansException
     *             If an error occurs.
     */
    private void processFields(final Object pBean, final String pBeanName) throws BeansException {
        ReflectionUtils.doWithFields(pBean.getClass(), new FieldCallback() {
            @Override
            public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
                // look for @HotConfigurableProperty annotation
                final HotConfigurableProperty annotation = field.getAnnotation(HotConfigurableProperty.class);
                if (annotation != null) {
                    if (StringUtils.hasText(annotation.value())) {
                        // take name in value attribute
                        confProperties.addProperty(pBean, field, annotation.value());
                    } else {
                        // look if there's @Value associated
                        if (field.getAnnotation(Value.class) != null) {
                            Value value = field.getAnnotation(Value.class);
                            String rawPropertyName = value.value();
                            // remove EL characters
                            String propertyName = StringUtils.deleteAny(rawPropertyName, "${}");
                            confProperties.addProperty(pBean, field, propertyName);
                        } else {
                            // generate the property name =
                            // beanClassName.fieldName
                            final String propertyName = pBeanName + "." + field.getName();
                            confProperties.addProperty(pBean, field, propertyName);
                            LOGGER.debug("No explicit property name for " + propertyName);
                        }
                    }
                } // not a configurable property
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.beans.factory.config.BeanPostProcessor#
     * postProcessAfterInitialization(java.lang.Object, java.lang.String)
     */
    @Override
    public Object postProcessAfterInitialization(final Object pBean, final String pBeanName) throws BeansException {
        return pBean;
    }
}
