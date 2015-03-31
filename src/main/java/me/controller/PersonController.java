/**
 * 
 */
package me.controller;

import me.bean.Person;
import me.validator.PersonId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author gwendalmousset
 *
 */
@Controller
@Validated
public class PersonController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
	
//	@RequestMapping("/person/{id:^[0-9]{3}$}")
	@RequestMapping("/person/{id}")
	public ModelAndView getPerson(@PersonId @PathVariable("id") String personId, ModelAndView mav/*, BindingResult br*/) {
		LOGGER.info("hop");
		Person p = new Person("John", "Doe", 35);
		mav.addObject("person", p);
		mav.setViewName("person");
		return mav;
	}
	
}
