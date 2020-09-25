package dev.ethp.pal.text.component

import dev.ethp.pal.text.placeholder.Placeholder
import dev.ethp.pal.text.placeholder.PlaceholderResolver


/**
 * Assertions for [TextPlaceholderNode].
 */
class TextPlaceholderNodeAssert(actual: TextPlaceholderNode?) : TextNodeAssert<TextPlaceholderNodeAssert, TextPlaceholderNode>(actual, TextPlaceholderNodeAssert::class.java) {
	
	/**
	 * Assert that the node is equal to another node.
	 */
	fun isEqualTo(expected: TextPlaceholderNode): TextPlaceholderNodeAssert {
		if (actually != expected) {
			failWithMessage("""
				Expecting:  ${expected.toLegacyString()}
				Actual:     ${actually.toLegacyString()}
			""".trimIndent().trim())
		}
		return this
	}
	
	/**
	 * Assert that the node has a specific placeholder.
	 */
	fun isPlaceholder(expected: Placeholder): TextPlaceholderNodeAssert {
		if (actually.placeholder != expected) {
			failWithMessage("""
				Expecting placeholder:  "$expected"
				Actual:                 "${actually.placeholder}"
			""".trimIndent().trim())
		}
		return this
	}

	companion object {
		@JvmStatic
		fun assertThat(actual: TextPlaceholderNode?): TextPlaceholderNodeAssert {
			return TextPlaceholderNodeAssert(actual)
		}
	}
}
