/**
 * 
 */
package me.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author gwendalmousset
 *
 */
@XmlRootElement(name = "response")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WsResponse {
	
	private WsUser user;
	private String message;

	public WsResponse() {
		super();
	}
	
	public WsResponse(String message) {
		super();
		this.message = message;
	}

	@XmlAttribute
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public WsUser getUser() {
		return user;
	}

	public void setUser(WsUser user) {
		this.user = user;
	}
}

