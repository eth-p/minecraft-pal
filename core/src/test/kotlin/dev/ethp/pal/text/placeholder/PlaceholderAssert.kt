package dev.ethp.pal.text.placeholder

import Assert
import dev.ethp.pal.text.placeholder.Placeholder

/**
 * Assertions for [Placeholder].
 */
class PlaceholderAssert(actual: Placeholder?) : Assert<PlaceholderAssert, Placeholder>(actual, PlaceholderAssert::class.java) {
	
	/**
	 * Assert that the text is equal to another text.
	 */
	fun isEqualTo(expected: Placeholder?): PlaceholderAssert {
		if (actually != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     $actually
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the Placeholder has a specific name.
	 */
	fun hasName(expected: String): PlaceholderAssert {
		if (actually.name != expected) {
			failWithMessage("""
				Expecting name:  "$expected"
				Actual:          "${actually.name}"
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the Placeholder has a specific item.
	 */
	fun hasItem(expected: String?): PlaceholderAssert {
		if (actually.item != expected) {
			failWithMessage("""
				Expecting item:  "$expected"
				Actual:          "${actually.item}"
			""".trimIndent().trim())
		}
		return this
	}

	companion object {
		@JvmStatic
		fun assertThat(actual: Placeholder?): PlaceholderAssert {
			return PlaceholderAssert(actual)
		}
	}
}
