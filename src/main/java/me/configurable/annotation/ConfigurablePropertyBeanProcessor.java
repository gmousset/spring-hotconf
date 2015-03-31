/**
 * 
 */
package me.configurable.annotation;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.StringUtils;

/**
 * @author gwendalmousset
 *
 */
public class ConfigurablePropertyBeanProcessor implements BeanPostProcessor {

	private static Logger LOGGER = LoggerFactory.getLogger(ConfigurablePropertyBeanProcessor.class);
	
	@Autowired
	private ConfigurableProperties confProps;
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				if (field.getAnnotation(ConfigurableProperty.class) != null && field.getAnnotation(Value.class) != null) {
					Value value = field.getAnnotation(Value.class);
					String rawPropertyName = value.value();
					String propertyName = StringUtils.deleteAny(rawPropertyName, "${}");
					LOGGER.info("Add " + propertyName);
					confProps.addProperty(bean, field, propertyName);
				}
			}
		});
		
		return bean;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		/*
		Collection<Object> allBeans = this.confProps.getProperties().values();
		
		Class beanClass = bean.getClass();

        if (Proxy.isProxyClass(beanClass)) {
            return bean;
        }
		
		final Object ret;
		if (allBeans.contains(bean)) {
			List<Class<?>> interfaceList = getAllInterfaces(beanClass);
	        Class[] interfaces = (interfaceList.toArray(new Class[interfaceList.size()]));
	        ret = Proxy.newProxyInstance(beanClass.getClassLoader(), interfaces, new InvocationHandler() {
	            @Override
	            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
	            	LOGGER.info(">>>>>>> invoke: " + method.getName());
	            	
	                return method.invoke(bean, objects);
	            }
	        });
		} else {
			ret = bean;
		}
		
		return ret;
		*/
		return bean;
	}
	
	/*
	private List<Class<?>> getAllInterfaces(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        LinkedHashSet<Class<?>> interfacesFound = new LinkedHashSet<Class<?>>();
        getAllInterfaces(cls, interfacesFound);
        return new ArrayList<Class<?>>(interfacesFound);
    }

    private void getAllInterfaces(Class<?> cls, HashSet<Class<?>> interfacesFound) {
        while (cls != null) {
            Class<?>[] interfaces = cls.getInterfaces();
            for (Class<?> i : interfaces) {
                if (interfacesFound.add(i)) {
                    getAllInterfaces(i, interfacesFound);
                }
            }
            cls = cls.getSuperclass();
        }
    }
    */
}
