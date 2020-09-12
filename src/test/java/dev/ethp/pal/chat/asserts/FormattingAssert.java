package dev.ethp.pal.chat.asserts;

import dev.ethp.pal.chat.Color;
import dev.ethp.pal.chat.Formatting;
import org.assertj.core.api.AbstractAssert;

/**
 * Assertions for {@link Formatting}.
 */
public class FormattingAssert extends AbstractAssert<FormattingAssert, Formatting> {

	public FormattingAssert(Formatting actual) {
		super(actual, FormattingAssert.class);
	}

	public static FormattingAssert assertThat(Formatting actual) {
		return new FormattingAssert(actual);
	}

	/**
	 * Assert that the formatting has a specific code corresponding to it.
	 *
	 * @param expected The expected code.
	 * @return Self, for chaining.
	 */
	public FormattingAssert hasCode(char expected) {
		isNotNull();
		if (actual.getCode() != expected) {
			failWithMessage("Formatting: " + actual + "\nExpecting Code:\n '" + expected + "'\nActual Code:\n '" + actual.getCode() + "'");
		}
		return this;
	}

	/**
	 * Assert that the formatting is equal to another color.
	 *
	 * @param expected The expected formatting.
	 * @return Self, for chaining.
	 */
	public FormattingAssert isVariantOf(Formatting expected) {
		return this.isEqualTo(expected)
				.isEqualToIdentity(expected);
	}
	
	/**
	 * Assert that the formatting is equal to another color.
	 *
	 * @param expected The expected formatting.
	 * @return Self, for chaining.
	 */
	public FormattingAssert isEqualTo(Formatting expected) {
		if (!actual.equals(expected)) {
			failWithMessage("Expecting:\n " + expected + "\nActual:\n " + actual);
		}
		return this;
	}

	/**
	 * Assert that the object identity is equal to another formatting style.
	 *
	 * @param expected The expected object.
	 * @return Self, for chaining.
	 */
	public FormattingAssert isEqualToIdentity(Formatting expected) {
		if (actual != expected) {
			failWithMessage("Expecting equal identity, but objects were not equal.");
		}
		return this;
	}

}
