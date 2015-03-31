/**
 * 
 */
package me.controlleradvice;

import me.bean.WsResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author gwendalmousset
 *
 */
//@ControllerAdvice
public class Advice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Advice.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public WsResponse catchException(Exception e) {
		e.printStackTrace();
		LOGGER.error(e.getMessage());
		WsResponse error = new WsResponse("oups");
		return error;
	}
}
