package dev.ethp.pal.text

import Assert
import dev.ethp.pal.text.Formatting.Combined
import org.assertj.core.api.Assertions

/**
 * Assertions for [Formatting.Combined].
 */
class FormattingCombinedAssert(actual: Combined?) : Assert<FormattingCombinedAssert, Combined>(actual, FormattingCombinedAssert::class.java) {
	
	/**
	 * Assert that the the combined style has a specific style in it.
	 *
	 * @param expected The expected style.
	 * @return Self, for chaining.
	 */
	fun hasStyle(expected: Formatting): FormattingCombinedAssert {
		if (!(actually has expected)) {
			failWithMessage("""
				Expecting:  HAS $expected
				Actual:     $actually
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the the combined style does not have a specific style in it.
	 *
	 * @param unexpected The style that should not be there.
	 * @return Self, for chaining.
	 */
	fun withoutStyle(unexpected: Formatting): FormattingCombinedAssert {
		if (actually has unexpected) {
			failWithMessage("""
				Expecting:  NOT HAS $unexpected
				Actual:     $actually
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the combined formatting is equal to another combined formatting.
	 *
	 * @param expected The expected combined formatting.
	 * @return Self, for chaining.
	 */
	fun isEqualTo(expected: Combined): FormattingCombinedAssert {
		if (actually != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     $actually
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the combined formatting only contains the specified styles.
	 *
	 * @param expected The expected combined formatting.
	 * @return Self, for chaining.
	 */
	fun isEqualTo(vararg expected: Formatting?): FormattingCombinedAssert {
		Assertions.assertThat(java.util.HashSet(actually.styles()))
				.isEqualTo(java.util.HashSet<Formatting>(listOf(*expected)))
		return this
	}

	companion object {
		@JvmStatic
		fun assertThat(actual: Combined?): FormattingCombinedAssert {
			return FormattingCombinedAssert(actual)
		}
	}
}
