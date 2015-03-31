/**
 * 
 */
package me.configuration;

import java.util.Properties;

import org.crsh.spring.SpringWebBootstrap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * @author gwendalmousset
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"me.controller", "me.controlleradvice", "me.initialisation"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
	
	@Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
	}
	
	@Bean
	public SpringWebBootstrap getCrashShell() {
		SpringWebBootstrap swb = new SpringWebBootstrap();
		Properties props = new Properties();
		props.setProperty("prompt", "LOOOOOOOOOOOOOOOOOOOOOL");
		props.setProperty("crash.vfs.refresh_period", "1");
		props.setProperty("crash.ssh.port", "2000");
		props.setProperty("crash.ssh.auth_timeout", "300000");
		props.setProperty("crash.ssh.idle_timeout", "300000");
		props.setProperty("crash.telnet.port", "5000");
		props.setProperty("crash.auth", "simple");
		props.setProperty("crash.auth.simple.username", "admin");
		props.setProperty("crash.auth.simple.password", "");
		props.setProperty("crash.mail.smtp.host", "smtp.gmail.com");
		props.setProperty("crash.mail.smtp.port", "587");
		props.setProperty("crash.mail.smtp.secure", "tls");
		props.setProperty("crash.mail.smtp.username", "gwendal.mousset");
		props.setProperty("crash.mail.smtp.password", "Bachelorette1%");
		props.setProperty("crash.mail.smtp.from", "gwendal.mousset@gmail.com");
		props.setProperty("crash.mail.debug", "false");
		swb.setConfig(props);
		return swb;
	}	
	
//	@Override
//	public void addFormatters(FormatterRegistry registry) {
//		registry.addConverter(new StringToPersonIdBean());
//	}
	
//	@Bean
//    public LocalValidatorFactoryBean validator() {
//        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
//        validatorFactoryBean.setValidationMessageSource(messageSource);
//        return validatorFactoryBean;
//    }
//	
//	@Override
//	public Validator getValidator() {
//		return validator();
//	}
}
