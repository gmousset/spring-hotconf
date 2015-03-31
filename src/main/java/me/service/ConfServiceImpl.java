/**
 * 
 */
package me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import me.bean.Conf;
import me.configurable.annotation.ConfigurableProperties;
import me.configurable.annotation.ConfigurableProperty;

/**
 * @author gwendalmousset
 *
 */
@Service
public class ConfServiceImpl implements ConfService {

	@Autowired
	private ConfigurableProperties confProp;
	
	@ConfigurableProperty
	@Value("${conf.min}")
	private int min;
	
	@ConfigurableProperty
	@Value("${conf.max}")
	private int max;
	
	@ConfigurableProperty
	@Value("${conf.text}")
	private String text;
	
	/* (non-Javadoc)
	 * @see me.service.ConfService#getConfView()
	 */
	@Override
	public Conf getConfView() {
		
		return new Conf(this.min, this.max);
	}
}
