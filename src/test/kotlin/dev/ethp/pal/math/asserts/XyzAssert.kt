package dev.ethp.pal.math.asserts

import dev.ethp.pal.math.ColorSpace.XYZ
import org.assertj.core.api.AbstractAssert
import kotlin.math.abs

/**
 * Assertions for [dev.ethp.pal.math.ColorSpace.XYZ].
 */
class XyzAssert(actual: XYZ?) : AbstractAssert<XyzAssert?, XYZ?>(actual, XyzAssert::class.java) {
	
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
		isNotNull()
		if (abs(actual!!.X - x) > 0.1 || abs(actual.Y - y) > 0.1 || abs(actual.Z - z) > 0.1) {
			failWithMessage("""
				Expecting:  XYZ($x,$y,$z)
				Actual:     XYZ(${actual.X},${actual.Y},${actual.Z})
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
