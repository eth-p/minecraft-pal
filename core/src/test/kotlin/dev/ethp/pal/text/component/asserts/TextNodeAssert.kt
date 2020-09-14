package dev.ethp.pal.text.component.asserts

import dev.ethp.pal.text.component.TextNode

/**
 * Assertions for [TextNode].
 */
class TextNodeAssert(actual: TextNode?) : NodeAssert<TextNodeAssert, TextNode>(actual, TextNodeAssert::class.java) {
	
	/**
	 * Assert that the text is equal to another text.
	 */
	fun isEqualTo(expected: TextNode): TextNodeAssert {
		if (actual!! != expected) {
			failWithMessage("""
				Expecting:  ${expected.toLegacyString()}
				Actual:     ${actual.toLegacyString()}
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the text has a specific text string.
	 */
	fun isText(expected: String): TextNodeAssert {
		isNotNull()
		if (actual!!.text != expected) {
			failWithMessage("""
				Expecting:  "$expected"
				Actual:     "${actual.color}"
			""".trimIndent().trim())
		}
		return this
	}

	companion object {
		@JvmStatic
		fun assertThat(actual: TextNode?): TextNodeAssert {
			return TextNodeAssert(actual)
		}
	}
}
