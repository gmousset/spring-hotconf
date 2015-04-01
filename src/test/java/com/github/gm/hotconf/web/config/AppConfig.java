/**
 * 
 */
package com.github.gm.hotconf.web.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.github.gm.hotconf.HotConfBootstrap;
import com.github.gm.hotconf.annotations.EnableHotConfiguration;

/**
 * @author Gwendal Mousset
 *
 */
@Configuration
@ComponentScan(basePackages = "com.github.gm.hotconf.web.service")
@PropertySource({"classpath:config.properties"})
@EnableHotConfiguration
public class AppConfig {

	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }
	
	@Bean
	public HotConfBootstrap getCrashShell() {
		HotConfBootstrap swb = new HotConfBootstrap();
		Properties props = new Properties();
		props.setProperty("crash.vfs.refresh_period", "1");
		props.setProperty("crash.ssh.port", "2001");
		props.setProperty("crash.ssh.auth_timeout", "300000");
		props.setProperty("crash.ssh.idle_timeout", "300000");
		props.setProperty("crash.auth", "simple");
		props.setProperty("crash.auth.simple.username", "admin");
		props.setProperty("crash.auth.simple.password", "admin");
		swb.setConfig(props);
		return swb;
	}	
}
