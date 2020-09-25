package dev.ethp.pal.text.component

import dev.ethp.pal.text.component.TextStringNode

/**
 * Assertions for [TextStringNode].
 */
class TextStringNodeAssert(actual: TextStringNode?) : TextNodeAssert<TextStringNodeAssert, TextStringNode>(actual, TextStringNodeAssert::class.java) {
	
	/**
	 * Assert that the text is equal to another text.
	 */
	fun isEqualTo(expected: TextStringNode): TextStringNodeAssert {
		if (actually != expected) {
			failWithMessage("""
				Expecting:  ${expected.toLegacyString()}
				Actual:     ${actually.toLegacyString()}
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the text has a specific text string.
	 */
	fun isText(expected: String): TextStringNodeAssert {
		if (actually.text != expected) {
			failWithMessage("""
				Expecting:  "$expected"
				Actual:     "${actually.color}"
			""".trimIndent().trim())
		}
		return this
	}

	companion object {
		@JvmStatic
		fun assertThat(actual: TextStringNode?): TextStringNodeAssert {
			return TextStringNodeAssert(actual)
		}
	}
}
