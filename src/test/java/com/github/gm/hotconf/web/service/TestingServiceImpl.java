/**
 * 
 */
package com.github.gm.hotconf.web.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.gm.hotconf.annotations.HotConfigurableProperty;

/**
 * @author Gwendal Mousset
 *
 */
@Service
public class TestingServiceImpl implements TestingService {

	@HotConfigurableProperty
	@Value("${int.min}")
	private int intPrimProp;
	
	@HotConfigurableProperty
	@Value("${int.max}")
	private Integer integerProp;
	
	@HotConfigurableProperty("long.primitive")
	private long longPrimProp;
	
	@HotConfigurableProperty("long.obj")
	private Long longProp;
	
	@HotConfigurableProperty("double.primitive")
	private double doublePrimProp;
	
	@HotConfigurableProperty("double.obj")
	private Double doublePrim;
	
	@HotConfigurableProperty("float.primitive")
	private float floatPrimProp;
	
	@HotConfigurableProperty("float.obj")
	private Float floatProp;
	
	@HotConfigurableProperty
	private String stringProp;

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#getIntPrimProp()
	 */
	@Override
	public int getIntPrimProp() {
		return intPrimProp;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#setIntPrimProp(int)
	 */
	@Override
	public void setIntPrimProp(int intPrimProp) {
		this.intPrimProp = intPrimProp;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#getIntegerProp()
	 */
	@Override
	public Integer getIntegerProp() {
		return integerProp;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#setIntegerProp(java.lang.Integer)
	 */
	@Override
	public void setIntegerProp(Integer integerProp) {
		this.integerProp = integerProp;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#getLongPrimProp()
	 */
	@Override
	public long getLongPrimProp() {
		return longPrimProp;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#setLongPrimProp(long)
	 */
	@Override
	public void setLongPrimProp(long longPrimProp) {
		this.longPrimProp = longPrimProp;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#getLongProp()
	 */
	@Override
	public Long getLongProp() {
		return longProp;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#setLongProp(java.lang.Long)
	 */
	@Override
	public void setLongProp(Long longProp) {
		this.longProp = longProp;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#getDoublePrimProp()
	 */
	@Override
	public double getDoublePrimProp() {
		return doublePrimProp;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#setDoublePrimProp(double)
	 */
	@Override
	public void setDoublePrimProp(double doublePrimProp) {
		this.doublePrimProp = doublePrimProp;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#getDoublePrim()
	 */
	@Override
	public Double getDoublePrim() {
		return doublePrim;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#setDoublePrim(java.lang.Double)
	 */
	@Override
	public void setDoublePrim(Double doublePrim) {
		this.doublePrim = doublePrim;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#getFloatPrimProp()
	 */
	@Override
	public float getFloatPrimProp() {
		return floatPrimProp;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#setFloatPrimProp(float)
	 */
	@Override
	public void setFloatPrimProp(float floatPrimProp) {
		this.floatPrimProp = floatPrimProp;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#getFloatProp()
	 */
	@Override
	public Float getFloatProp() {
		return floatProp;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#setFloatProp(java.lang.Float)
	 */
	@Override
	public void setFloatProp(Float floatProp) {
		this.floatProp = floatProp;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#getStringProp()
	 */
	@Override
	public String getStringProp() {
		return stringProp;
	}

	/* (non-Javadoc)
	 * @see com.github.gm.hotconf.web.service.ITestingService#setStringProp(java.lang.String)
	 */
	@Override
	public void setStringProp(String stringProp) {
		this.stringProp = stringProp;
	}
}
