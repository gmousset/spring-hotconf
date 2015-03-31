/**
 * 
 */
package me.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author gwendalmousset
 *
 */
@XmlRootElement(name = "conf")
public class Conf {

	private int min;
	private int max;
	
	public Conf() {
		// TODO Auto-generated constructor stub
	}
	
	public Conf(int min, int max) {
		super();
		this.min = min;
		this.max = max;
	}


	public int getMin() {
		return min;
	}
	
	public int getMax() {
		return max;
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public void setMax(int max) {
		this.max = max;
	}
}
