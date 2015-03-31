/**
 * 
 */
package me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.gm.hotconf.HotConfigurableProperties;
import com.github.gm.hotconf.annotations.HotConfigurableProperty;

import me.bean.Conf;

/**
 * @author gwendalmousset
 *
 */
@Service
public class ConfServiceImpl implements ConfService {

	@Autowired
	private HotConfigurableProperties confProp;
	
	@HotConfigurableProperty
	@Value("${conf.min}")
	private int min;
	
	@HotConfigurableProperty
	@Value("${conf.max}")
	private int max;
	
	@HotConfigurableProperty
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
