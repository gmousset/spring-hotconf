/**
 * 
 */
package me.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.github.gm.hotconf.annotations.EnableHotConfiguration;
import com.github.gm.hotconf.springconf.HotConfigurablePropertyBeanProcessor;

/**
 * @author gwendalmousset
 *
 */
@Configuration
@ComponentScan(basePackages = "me.service")
@PropertySource(name = "props", value = {"classpath:config.properties"})
@EnableCaching
@EnableHotConfiguration
public class AppConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);
	
	@Bean
	public CacheManager getCacheManager() {
		LOGGER.info("Config: CacheManager");
		net.sf.ehcache.CacheManager cm = net.sf.ehcache.CacheManager.create();
		cm.addCache("books");
		EhCacheCacheManager ecm = new EhCacheCacheManager(cm);
		return ecm;
	}
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }
}
