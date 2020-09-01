package dev.ethp.pal.math.asserts;

import dev.ethp.pal.math.ColorSpace.RGB;
import org.assertj.core.api.AbstractAssert;

/**
 * Assertions for {@link RGB}.
 */
public class RgbAssert extends AbstractAssert<RgbAssert, RGB> {

	public RgbAssert(RGB actual) {
		super(actual, RgbAssert.class);
	}

	public static RgbAssert assertThat(RGB actual) {
		return new RgbAssert(actual);
	}

	/**
	 * Assert that the color space values are roughly equal to provided values.
	 * Precision: e-3
	 *
	 * @param r The R value.
	 * @param g The G value.
	 * @param b The B value.
	 * @return Self, for chaining.
	 */
	public RgbAssert hasRGB(double r, double g, double b) {
		isNotNull();
		
		if (Math.abs(actual.getR() - r) > 0.001 ||
				Math.abs(actual.getG() - g) > 0.001 ||
				Math.abs(actual.getB() - b) > 0.001) {
			failWithMessage("Expecting:\n (%s,%s,%s).\nto have RGB:\n (%s,%s,%s)",
					r, g, b,
					actual.getR(), actual.getG(), actual.getB()
			);
		}

		return this;
	}

	/**
	 * Assert that the color space values are roughly equal to another RGB color space object.
	 * Precision: e-3
	 *
	 * @param expected The expected RGB color space values.
	 * @return Self, for chaining.
	 */
	public RgbAssert isEqualTo(RGB expected) {
		isNotNull();
		hasRGB(expected.getR(), expected.getG(), expected.getB());
		return this;
	}

}
