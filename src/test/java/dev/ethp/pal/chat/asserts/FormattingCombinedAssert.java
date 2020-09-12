package dev.ethp.pal.chat.asserts;

import java.util.Arrays;
import java.util.HashSet;

import dev.ethp.pal.chat.Formatting;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

/**
 * Assertions for {@link Formatting.Combined}.
 */
public class FormattingCombinedAssert extends AbstractAssert<FormattingCombinedAssert, Formatting.Combined> {

	public FormattingCombinedAssert(Formatting.Combined actual) {
		super(actual, FormattingCombinedAssert.class);
	}

	public static FormattingCombinedAssert assertThat(Formatting.Combined actual) {
		return new FormattingCombinedAssert(actual);
	}

	/**
	 * Assert that the the combined style has a specific style in it.
	 *
	 * @param expected The expected style.
	 * @return Self, for chaining.
	 */
	public FormattingCombinedAssert hasStyle(Formatting expected) {
		isNotNull();
		if (!this.actual.has(expected)) {
			failWithMessage("Formatting: " + actual + "\nExpecting Style:\n '" + expected + "'\nDoes not have style.");
		}
		return this;
	}

	/**
	 * Assert that the the combined style does not have a specific style in it.
	 *
	 * @param expected The style that should not be there.
	 * @return Self, for chaining.
	 */
	public FormattingCombinedAssert withoutStyle(Formatting expected) {
		isNotNull();
		if (this.actual.has(expected)) {
			failWithMessage("Formatting: " + actual + "\nNot Expecting Style:\n '" + expected + "'\nHas style.");
		}
		return this;
	}

	/**
	 * Assert that the combined formatting is equal to another combined formatting.
	 *
	 * @param expected The expected combined formatting.
	 * @return Self, for chaining.
	 */
	public FormattingCombinedAssert isEqualTo(Formatting.Combined expected) {
		if (!actual.equals(expected)) {
			failWithMessage("Expecting:\n " + expected + "\nActual:\n " + actual);
		}
		return this;
	}

	/**
	 * Assert that the combined formatting only contains the specified styles.
	 *
	 * @param expected The expected combined formatting.
	 * @return Self, for chaining.
	 */
	public FormattingCombinedAssert isEqualTo(Formatting... expected) {
		Assertions.assertThat(new HashSet<>(actual.styles()))
				.isEqualTo(new HashSet<>(Arrays.asList(expected)));
		
		return this;
	}

}
