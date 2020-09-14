package dev.ethp.pal.math.asserts

import dev.ethp.pal.math.ColorSpace.RGB
import org.assertj.core.api.AbstractAssert
import kotlin.math.abs

/**
 * Assertions for [RGB].
 */
class RgbAssert(actual: RGB?) : AbstractAssert<RgbAssert?, RGB?>(actual, RgbAssert::class.java) {
	
	/**
	 * Assert that the color space values are roughly equal to provided values.
	 * Precision: e-3
	 *
	 * @param r The R value.
	 * @param g The G value.
	 * @param b The B value.
	 * @return Self, for chaining.
	 */
	fun hasRGB(r: Double, g: Double, b: Double): RgbAssert {
		isNotNull()
		if (abs(actual!!.R - r) > 0.001 || abs(actual.G - g) > 0.001 || abs(actual.B - b) > 0.001) {
			failWithMessage("""
				Expecting:  RGB($r,$g,$b)
				Actual:     RGB(${actual.R},${actual.G},${actual.B})
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the color space values are roughly equal to another RGB color space object.
	 * Precision: e-3
	 *
	 * @param expected The expected RGB color space values.
	 * @return Self, for chaining.
	 */
	fun isEqualTo(expected: RGB): RgbAssert {
		return hasRGB(expected.R, expected.G, expected.B)
	}

	companion object {
		@JvmStatic
		fun assertThat(actual: RGB?): RgbAssert {
			return RgbAssert(actual)
		}
	}
}
