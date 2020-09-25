package dev.ethp.pal.text

import Assert
import dev.ethp.pal.text.Color.Companion.rgb

/**
 * Assertions for [Color].
 */
class ColorAssert(actual: Color?) : Assert<ColorAssert, Color>(actual, ColorAssert::class.java) {

	/**
	 * Assert that the color must be quantized for legacy clients.
	 *
	 * @return Self, for chaining.
	 */
	fun isQuantized(): ColorAssert {
		if (actually.isLegacy()) {
			failWithMessage("Color $actually does not require quantization.")
		}
		return this
	}

	/**
	 * Assert that the color is a valid legacy color.
	 *
	 * @return Self, for chaining.
	 */
	fun isLegacy(): ColorAssert {
		if (!actually.isLegacy()) {
			failWithMessage("Color $actually is not a legacy color.")
		}
		return this
	}

	/**
	 * Assert that the color is equal to another color.
	 *
	 * @param expected The expected color.
	 * @return Self, for chaining.
	 */
	fun isVariantOf(expected: Color): ColorAssert {
		return isEqualTo(expected)
				.isEqualToIdentity(expected)
				.hasName(expected.name)
	}

	/**
	 * Assert that the color has a specific code corresponding to it.
	 *
	 * @param expected The expected code.
	 * @return Self, for chaining.
	 */
	fun hasCode(expected: Char): ColorAssert {
		if (actually.code != expected) {
			failWithMessage("""
				Expecting:  '$expected'
				Actual:     '${actually.code}'
 			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the color has a specific RGB corresponding to it.
	 *
	 * @param expected The expected RGB.
	 * @return Self, for chaining.
	 */
	fun hasRgb(expected: Int): ColorAssert {
		if (actually.rgb != expected) {
			failWithMessage("""
				Expecting:  ${rgb(expected)}
				Actual:     $actually
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the color has a specific name.
	 *
	 * @param expected The expected name.
	 * @return Self, for chaining.
	 */
	fun hasName(expected: String): ColorAssert {
		if (actually.name != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     ${actually.name}
			""".trimIndent().trim())
		}
		return this
	}
	
	/**
	 * Assert that the color has a specific legacy name.
	 *
	 * @param expected The expected legacy name.
	 * @return Self, for chaining.
	 */
	fun hasLegacyName(expected: String): ColorAssert {
		if (actually.legacyName != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     ${actually.legacyName}
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the color is equal to another color.
	 *
	 * @param expected The expected color.
	 * @return Self, for chaining.
	 */
	fun isEqualTo(expected: Color): ColorAssert {
		if (actually != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     $actually
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the object identity is equal to another color.
	 *
	 * @param expected The expected object.
	 * @return Self, for chaining.
	 */
	fun isEqualToIdentity(expected: Color): ColorAssert {
		if (actually !== expected) {
			failWithMessage("Expecting equal identity, but objects were not equal.")
		}
		return this
	}

	companion object {
		@JvmStatic
		fun assertThat(actual: Color?): ColorAssert {
			return ColorAssert(actual)
		}
	}
}
