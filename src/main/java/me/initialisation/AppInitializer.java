/**
 * 
 */
package me.initialisation;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author gwendalmousset
 *
 */
public class AppInitializer implements WebApplicationInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppInitializer.class);
	
	public void onStartup(ServletContext servletContext) throws ServletException {
		LOGGER.info("Start web application");
		WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        
        servletContext.addFilter("etag", ShallowEtagHeaderFilter.class).addMappingForUrlPatterns(null, false, "/*");;
        
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        LOGGER.info("Done");
	}
	
	private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("me.configuration");
        return context;
    }
}
