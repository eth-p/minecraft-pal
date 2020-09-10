package dev.ethp.pal.chat.asserts;

import dev.ethp.pal.chat.Color;
import org.assertj.core.api.AbstractAssert;

/**
 * Assertions for {@link Color}.
 */
public class ColorAssert extends AbstractAssert<ColorAssert, Color> {

	public ColorAssert(Color actual) {
		super(actual, ColorAssert.class);
	}

	public static ColorAssert assertThat(Color actual) {
		return new ColorAssert(actual);
	}

	/**
	 * Assert that the color must be quantized for legacy clients.
	 *
	 * @return Self, for chaining.
	 */
	public ColorAssert isQuantized() {
		isNotNull();
		if (actual.isLegacy()) {
			failWithMessage("Color " + actual + " does not require quantization.");
		}
		return this;
	}

	/**
	 * Assert that the color is a valid legacy color.
	 *
	 * @return Self, for chaining.
	 */
	public ColorAssert isLegacy() {
		isNotNull();
		if (!actual.isLegacy()) {
			failWithMessage("Color " + actual + " is not a legacy color.");
		}
		return this;
	}

	/**
	 * Assert that the color has a specific code corresponding to it.
	 *
	 * @param expected The expected code.
	 * @return Self, for chaining.
	 */
	public ColorAssert hasCode(char expected) {
		isNotNull();
		if (actual.getCode() != expected) {
			failWithMessage("Color: " + actual + "\nExpecting Code:\n '" + expected + "'\nActual Code:\n '" + actual.getCode() + "'");
		}
		return this;
	}
	
	/**
	 * Assert that the color has a specific RGB corresponding to it.
	 *
	 * @param expected The expected RGB.
	 * @return Self, for chaining.
	 */
	public ColorAssert hasRgb(int expected) {
		isNotNull();
		if (actual.getRgb() != expected) {
			failWithMessage("Expecting:\n " + Color.rgb(expected) + "\nActual Code:\n " + actual);
		}
		return this;
	}

	/**
	 * Assert that the color is equal to another color.
	 *
	 * @param expected The expected color.
	 * @return Self, for chaining.
	 */
	public ColorAssert isEqualTo(Color expected) {
		if (!actual.equals(expected)) {
			failWithMessage("Expecting:\n " + expected + "\nActual:\n " + actual);
		}
		return this;
	}

}
