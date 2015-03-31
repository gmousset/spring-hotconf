/**
 * 
 */
package me.controller;

import me.bean.Conf;
import me.service.ConfService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gwendalmousset
 *
 */
@RestController
public class ConfController {

	@Autowired
	private ConfService confService; 
	
	@RequestMapping(value = "/conf", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE, MimeTypeUtils.APPLICATION_XML_VALUE})
	public Conf getConfView() {
		return this.confService.getConfView();
	}
}
