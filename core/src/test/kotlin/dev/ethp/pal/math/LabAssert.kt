package dev.ethp.pal.math

import Assert
import dev.ethp.pal.math.ColorSpace.LAB
import org.assertj.core.api.AbstractAssert
import kotlin.math.abs

/**
 * Assertions for [LAB].
 */
class LabAssert(actual: LAB?) : Assert<LabAssert, LAB>(actual, LabAssert::class.java) {
	
	/**
	 * Assert that the color space values are roughly equal to provided values.
	 * Precision: e-1
	 *
	 * @param l The L value.
	 * @param a The A value.
	 * @param b The B value.
	 * @return Self, for chaining.
	 */
	fun hasLAB(l: Double, a: Double, b: Double): LabAssert {
		if (abs(actually.L - l) > 0.1 || abs(actually.a - a) > 0.1 || abs(actually.b - b) > 0.1) {
			failWithMessage("""
				Expecting:  LAB(${actually.L},${actually.a},${actually.b}).
				Actual:     LAB($l,$a,$b)
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the color space values are roughly equal to another LAB color space object.
	 * Precision: e-1
	 *
	 * @param expected The expected LAB color space values.
	 * @return Self, for chaining.
	 */
	fun isEqualTo(expected: LAB): LabAssert {
		return hasLAB(expected.L, expected.a, expected.b)
	}

	companion object {
		@JvmStatic
		fun assertThat(actual: LAB?): LabAssert {
			return LabAssert(actual)
		}
	}
}
