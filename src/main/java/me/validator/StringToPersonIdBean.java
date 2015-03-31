/**
 * 
 */
package me.validator;

import me.bean.PersonIdBean;

import org.springframework.core.convert.converter.Converter;

/**
 * @author gwendalmousset
 *
 */
public class StringToPersonIdBean implements Converter<String, PersonIdBean> {

	public PersonIdBean convert(String arg0) {
		PersonIdBean pid = new PersonIdBean();
		pid.setId(arg0);
		return pid;
	};
}
