package dev.ethp.pal.chat.asserts

import dev.ethp.pal.chat.Color
import dev.ethp.pal.chat.Color.Companion.rgb
import org.assertj.core.api.Assertions.*
import org.assertj.core.api.AbstractAssert

/**
 * Assertions for [Color].
 */
class ColorAssert(actual: Color?) : AbstractAssert<ColorAssert?, Color?>(actual, ColorAssert::class.java) {

	/**
	 * Assert that the color must be quantized for legacy clients.
	 *
	 * @return Self, for chaining.
	 */
	fun isQuantized(): ColorAssert {
		isNotNull()
		if (actual!!.isLegacy()) {
			failWithMessage("Color $actual does not require quantization.")
		}
		return this
	}

	/**
	 * Assert that the color is a valid legacy color.
	 *
	 * @return Self, for chaining.
	 */
	fun isLegacy(): ColorAssert {
		isNotNull()
		if (!actual!!.isLegacy()) {
			failWithMessage("Color $actual is not a legacy color.")
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
		isNotNull()
		if (actual!!.code != expected) {
			failWithMessage("""
				Expecting:  '$expected'
				Actual:     '${actual.code}'
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
		isNotNull()
		if (actual!!.rgb != expected) {
			failWithMessage("""
				Expecting:  ${rgb(expected)}
				Actual:     $actual
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
		isNotNull()
		if (actual!!.name != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     ${actual.name}
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
		isNotNull()
		if (actual!!.legacyName != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     ${actual.legacyName}
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
		if (actual!! != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     $actual
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
		if (actual !== expected) {
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
