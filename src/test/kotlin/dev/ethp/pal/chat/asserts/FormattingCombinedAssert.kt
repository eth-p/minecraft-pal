package dev.ethp.pal.chat.asserts

import dev.ethp.pal.chat.Formatting
import dev.ethp.pal.chat.Formatting.Combined
import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.Assertions

/**
 * Assertions for [Formatting.Combined].
 */
class FormattingCombinedAssert(actual: Combined?) : AbstractAssert<FormattingCombinedAssert?, Combined?>(actual, FormattingCombinedAssert::class.java) {
	
	/**
	 * Assert that the the combined style has a specific style in it.
	 *
	 * @param expected The expected style.
	 * @return Self, for chaining.
	 */
	fun hasStyle(expected: Formatting): FormattingCombinedAssert {
		isNotNull()
		if (!(actual!! has expected)) {
			failWithMessage("""
				Expecting:  HAS $expected
				Actual:     $actual
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
		isNotNull()
		if (actual!! has unexpected) {
			failWithMessage("""
				Expecting:  NOT HAS $unexpected
				Actual:     $actual
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
		if (actual!! != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     $actual
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
		Assertions.assertThat(java.util.HashSet(actual!!.styles()))
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
