/**
 * 
 */
package me.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author gwendalmousset
 *
 */
public class PersonIdValidator implements ConstraintValidator<PersonId, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonIdValidator.class);
	
	public void initialize(PersonId constraintAnnotation) {
		LOGGER.info("init");
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		return false;
	}
	
}
