package dev.ethp.pal.math.asserts;

import dev.ethp.pal.math.ColorSpace.XYZ;
import org.assertj.core.api.AbstractAssert;

/**
 * Assertions for {@link dev.ethp.pal.math.ColorSpace.XYZ}.
 */
public class XyzAssert extends AbstractAssert<XyzAssert, XYZ> {

	public XyzAssert(XYZ actual) {
		super(actual, XyzAssert.class);
	}

	public static XyzAssert assertThat(XYZ actual) {
		return new XyzAssert(actual);
	}

	/**
	 * Assert that the color space values are roughly equal to provided values.
	 * Precision: e-1
	 *
	 * @param x The X value.
	 * @param y The Y value.
	 * @param z The Z value.
	 * @return Self, for chaining.
	 */
	public XyzAssert hasXYZ(double x, double y, double z) {
		isNotNull();
		
		if (Math.abs(actual.getX() - x) > 0.1 ||
				Math.abs(actual.getY() - y) > 0.1 ||
				Math.abs(actual.getZ() - z) > 0.1) {
			failWithMessage("Expecting:\n (%s,%s,%s).\nto have XYZ:\n (%s,%s,%s)",
					x, y, z,
					actual.getX(), actual.getY(), actual.getZ()
			);
		}

		return this;
	}

	/**
	 * Assert that the color space values are roughly equal to another XYZ color space object.
	 * Precision: e-1
	 *
	 * @param expected The expected XYZ color space values.
	 * @return Self, for chaining.
	 */
	public XyzAssert isEqualTo(XYZ expected) {
		isNotNull();
		hasXYZ(expected.getX(), expected.getY(), expected.getZ());
		return this;
	}

}
