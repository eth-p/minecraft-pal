package dev.ethp.pal.chat.asserts

import dev.ethp.pal.chat.Formatting
import org.assertj.core.api.AbstractAssert

/**
 * Assertions for [Formatting].
 */
class FormattingAssert(actual: Formatting?) : AbstractAssert<FormattingAssert?, Formatting?>(actual, FormattingAssert::class.java) {
	
	/**
	 * Assert that the formatting has a specific code corresponding to it.
	 *
	 * @param expected The expected code.
	 * @return Self, for chaining.
	 */
	fun hasCode(expected: Char): FormattingAssert {
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
	 * Assert that the formatting is equal to another color.
	 *
	 * @param expected The expected formatting.
	 * @return Self, for chaining.
	 */
	fun isVariantOf(expected: Formatting): FormattingAssert {
		return this.isEqualTo(expected)
				.isEqualToIdentity(expected)
	}

	/**
	 * Assert that the formatting is equal to another color.
	 *
	 * @param expected The expected formatting.
	 * @return Self, for chaining.
	 */
	fun isEqualTo(expected: Formatting): FormattingAssert {
		if (actual!! != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     $actual
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the object identity is equal to another formatting style.
	 *
	 * @param expected The expected object.
	 * @return Self, for chaining.
	 */
	fun isEqualToIdentity(expected: Formatting): FormattingAssert {
		if (actual !== expected) {
			failWithMessage("Expecting equal identity, but objects were not equal.")
		}
		return this
	}

	companion object {
		@JvmStatic
		fun assertThat(actual: Formatting?): FormattingAssert {
			return FormattingAssert(actual)
		}
	}
}
