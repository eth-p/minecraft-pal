package dev.ethp.pal.math

import Assert
import dev.ethp.pal.math.ColorSpace.XYZ
import org.assertj.core.api.AbstractAssert
import kotlin.math.abs

/**
 * Assertions for [dev.ethp.pal.math.ColorSpace.XYZ].
 */
class XyzAssert(actual: XYZ?) : Assert<XyzAssert, XYZ>(actual, XyzAssert::class.java) {
	
	/**
	 * Assert that the color space values are roughly equal to provided values.
	 * Precision: e-1
	 *
	 * @param x The X value.
	 * @param y The Y value.
	 * @param z The Z value.
	 * @return Self, for chaining.
	 */
	fun hasXYZ(x: Double, y: Double, z: Double): XyzAssert {
		if (abs(actually.X - x) > 0.1 || abs(actually.Y - y) > 0.1 || abs(actually.Z - z) > 0.1) {
			failWithMessage("""
				Expecting:  XYZ($x,$y,$z)
				Actual:     XYZ(${actually.X},${actually.Y},${actually.Z})
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the color space values are roughly equal to another XYZ color space object.
	 * Precision: e-1
	 *
	 * @param expected The expected XYZ color space values.
	 * @return Self, for chaining.
	 */
	fun isEqualTo(expected: XYZ): XyzAssert {
		return hasXYZ(expected.X, expected.Y, expected.Z)
	}

	companion object {
		@JvmStatic
		fun assertThat(actual: XYZ?): XyzAssert {
			return XyzAssert(actual)
		}
	}
}
