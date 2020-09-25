package dev.ethp.pal.text

import Assert

/**
 * Assertions for [Formatting].
 */
class FormattingAssert(actual: Formatting?) : Assert<FormattingAssert, Formatting>(actual, FormattingAssert::class.java) {
	
	/**
	 * Assert that the formatting has a specific code corresponding to it.
	 *
	 * @param expected The expected code.
	 * @return Self, for chaining.
	 */
	fun hasCode(expected: Char): FormattingAssert {
		if (actually.code != expected) {
			failWithMessage("""
				Expecting:  '$expected'
				Actual:     '${actually.code}'
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the color has a specific name.
	 *
	 * @param expected The expected RGB.
	 * @return Self, for chaining.
	 */
	fun hasName(expected: String): FormattingAssert {
		if (actually.name != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     ${actually.name}
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
		if (actually != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     $actually
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
		if (actually !== expected) {
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
