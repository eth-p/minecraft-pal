package dev.ethp.pal.math.asserts;

import dev.ethp.pal.math.ColorSpace.LAB;
import org.assertj.core.api.AbstractAssert;

/**
 * Assertions for {@link LAB}.
 */
public class LabAssert extends AbstractAssert<LabAssert, LAB> {

	public LabAssert(LAB actual) {
		super(actual, LabAssert.class);
	}

	public static LabAssert assertThat(LAB actual) {
		return new LabAssert(actual);
	}

	/**
	 * Assert that the color space values are roughly equal to provided values.
	 * Precision: e-1
	 *
	 * @param l The L value.
	 * @param a The A value.
	 * @param b The B value.
	 * @return Self, for chaining.
	 */
	public LabAssert hasLAB(double l, double a, double b) {
		isNotNull();
		
		if (Math.abs(actual.getL() - l) > 0.1 ||
				Math.abs(actual.getA() - a) > 0.1 ||
				Math.abs(actual.getB() - b) > 0.1) {
			failWithMessage("Expecting:\n (%s,%s,%s).\nto have LAB:\n (%s,%s,%s)",
					l, a, b,
					actual.getL(), actual.getA(), actual.getB()
			);
		}

		return this;
	}

	/**
	 * Assert that the color space values are roughly equal to another LAB color space object.
	 * Precision: e-1
	 *
	 * @param expected The expected LAB color space values.
	 * @return Self, for chaining.
	 */
	public LabAssert isEqualTo(LAB expected) {
		isNotNull();
		hasLAB(expected.getL(), expected.getA(), expected.getB());
		return this;
	}

}
