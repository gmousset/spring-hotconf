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
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gwendal Mousset
 *
 */
public final class HotConfigurableHooks {

    /** Class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(HotConfigurableHooks.class);

    /** Hooks After by property. */
    private Map<String, List<HookInfo>> hooksBefore;

    /** Hooks After by property. */
    private Map<String, List<HookInfo>> hooksAfter;

    /**
     * Constructor.
     */
    public HotConfigurableHooks() {
        super();
        this.hooksBefore = new HashMap<>();
        this.hooksAfter = new HashMap<>();
    }

    /**
     * Add a hook for property change.
     * 
     * @param pBean
     *            The method owner.
     * @param pMethod
     *            The called method.
     * @param pPriority
     *            The hook invocation priority.
     * @param pPropertyName
     *            The property name.
     */
    public void addHookBefore(final Object pBean, final Method pMethod, final int pPriority, final String pPropertyName) {
        this.addHook(pBean, pMethod, pPriority, pPropertyName, hooksBefore);
    }

    /**
     * Add a hook for property change.
     * 
     * @param pBean
     *            The method owner.
     * @param pMethod
     *            The called method.
     * @param pPriority
     *            The hook invocation priority.
     * @param pPropertyName
     *            The property name.
     */
    public void addHookAfter(final Object pBean, final Method pMethod, final int pPriority, final String pPropertyName) {
        this.addHook(pBean, pMethod, pPriority, pPropertyName, hooksAfter);
    }

    /**
     * Add a hook for property change.
     * 
     * @param pBean
     *            The method owner.
     * @param pMethod
     *            The called method.
     * @param pPriority
     *            The hook invocation priority.
     * @param pPropertyName
     *            The property name.
     * @param pMap
     *            Before or after hooks map.
     */
    private void addHook(final Object pBean, final Method pMethod, final int pPriority, final String pPropertyName, final Map<String, List<HookInfo>> pMap) {
        // create list of it's the first hook for the current property
        if (pMap.get(pPropertyName) == null) {
            pMap.put(pPropertyName, new ArrayList<>());
        }
        // add hookinfo
        final List<HookInfo> hookList = pMap.get(pPropertyName);
        hookList.add(new HookInfo(pBean, pMethod, pPriority));
        LOGGER.info("Add hook " + pBean.getClass().getName() + "." + pMethod.getName());
    }

    /**
     * Call hooks for property before change.
     * 
     * @param pPropertyName
     *            The property name.
     */
    public void callHooksBeforePropertyChange(final String pPropertyName) {
        final List<HookInfo> hookList = this.hooksBefore.get(pPropertyName);
        this.callHooksForPropertyChange(hookList);
    }

    /**
     * Call hooks for property after change.
     * 
     * @param pPropertyName
     *            The property name.
     */
    public void callHooksAfterPropertyChange(final String pPropertyName) {
        final List<HookInfo> hookList = this.hooksAfter.get(pPropertyName);
        this.callHooksForPropertyChange(hookList);
    }

    /**
     * Call hooks for property.
     * 
     * @param pHookList
     *            The hooks called.
     */
    private void callHooksForPropertyChange(final List<HookInfo> pHookList) {
        if (pHookList != null && pHookList.size() > 0) {
            // sort hooks by priority
            final List<HookInfo> sortedHooks = pHookList.stream().sorted((h1, h2) -> Integer.compare(h2.priority, h1.priority)).collect(Collectors.toList());

            for (HookInfo hook : sortedHooks) {
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
    }

    /**
     * Representation of a hook method.
     */
    private final class HookInfo {
        /** Owner bean. */
        private Object bean;

        /** Called method. */
        private Method method;

        /** Hook priority. */
        private int priority;

        /**
         * Constructor.
         * 
         * @param pBean
         *            The bean owner.
         * @param pMethod
         *            The method.
         * @param pPriority
         *            The hook invocation priority.
         */
        public HookInfo(final Object pBean, final Method pMethod, final int pPriority) {
            super();
            this.bean = pBean;
            this.method = pMethod;
            this.priority = pPriority;
        }
    }
}
