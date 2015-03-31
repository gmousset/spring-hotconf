/**
 * 
 */
package me.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
//import javax.validation.constraints.Digits;
//import javax.validation.constraints.Pattern;

/**
 * @author gwendalmousset
 *
 */
@Target({ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PersonIdValidator.class})
@ReportAsSingleViolation
//@Digits(integer = 5, fraction = 0)
//@Pattern(regexp = "^[0-9]{3}$", message = "bad id format")
@SuppressWarnings("rawtypes")
public @interface PersonId {
	String message() default "{invalid.person.id}";
	Class[] groups() default {};
    Class[] payload() default {};
}
