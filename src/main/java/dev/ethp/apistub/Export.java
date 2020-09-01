package dev.ethp.apistub;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO: Use this to annotate fields and methods that will be added to API stub JARs.
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface Export {

	/**
	 * Only export when a condition has been met.
	 */
	String when = null;
	
}
