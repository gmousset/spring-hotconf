/**
 * 
 */
package com.github.gm.hotconf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gwendal Mousset
 *
 */
public class HotConfigurableHooks {

	/** Class logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(HotConfigurableHooks.class);
	
	/**
	 * Hooks by property.
	 */
	private Map<String, List<HookInfo>> hooks;
	
	/**
	 * Constructor.
	 */
	public HotConfigurableHooks() {
		super();
		this.hooks = new HashMap<>();
	}
	
	/**
	 * Add a hook for property change.
	 * @param pBean The method owner.
	 * @param pMethod The called method.
	 * @param pPropertyName The property name.
	 */
	public void addHook(final Object pBean, final Method pMethod, final String pPropertyName) {
		// create list of it's the first hook for the current property
		if (this.hooks.get(pPropertyName) == null) {
			this.hooks.put(pPropertyName, new ArrayList<>());
		}
		// add hookinfo
		final List<HookInfo> hookList = this.hooks.get(pPropertyName);
		hookList.add(new HookInfo(pBean, pMethod));
		LOGGER.info("Add hook " + pBean.getClass().getName() + "." + pMethod.getName());
	}
	
	/**
	 * Call hooks for property.
	 * @param pPropertyName The property name.
	 */
	public void callHooksForPropertyChange(final String pPropertyName) {
		final List<HookInfo> hookList = this.hooks.get(pPropertyName);
		for (HookInfo hook : hookList) {
			try {
				final boolean accessible = hook.method.isAccessible();
				if (!accessible) {
					hook.method.setAccessible(true);
				}
				hook.method.invoke(hook.bean, (Object[]) null);
				if (!accessible) {
					hook.method.setAccessible(false);
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				LOGGER.error("Invokation of hook method " + hook.bean.getClass() + "." + hook.method.getName() + " failed: " + e.getMessage());
			}
		}
	}
	
	/**
	 * Representation of a hook method.
	 */
	private final class HookInfo {
		/** Owner bean. */
		private Object bean;
		
		/** Called method */
		private Method method;

		/**
		 * Constructor.
		 * @param bean The bean owner.
		 * @param method The method.
		 */
		public HookInfo(Object bean, Method method) {
			super();
			this.bean = bean;
			this.method = method;
		}
	}
}
