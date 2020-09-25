package dev.ethp.pal.text.component

import dev.ethp.pal.text.Color
import dev.ethp.pal.text.Formatting
import dev.ethp.pal.text.component.TextNode

/**
 * Assertions for [TextNode].
 */
open class TextNodeAssert<Self, Actual>(actual: Actual?, type: Class<in Self>) : NodeAssert<Self, Actual>(actual, type)
		where Self : TextNodeAssert<Self, Actual>, Actual : TextNode {
	
	/**
	 * Assert that the node is equal to another node.
	 */
	fun isEqualTo(expected: TextNode): Self {
		if (actually != expected) {
			failWithMessage("""
				Expecting:  ${expected.toLegacyString()}
				Actual:     ${actually.toLegacyString()}
			""".trimIndent().trim())
		}
		return self()
	}

	/**
	 * Assert that the node has no color.
	 */
	fun isNoColor(): Self {
		if (actually.color != null) {
			failWithMessage("""
				Expecting:  Color()
				Actual:     Color(${actually.color})
			""".trimIndent().trim())
		}
		return self()
	}

	/**
	 * Assert that the node has a specific color.
	 */
	fun isColor(expected: Color): Self {
		if (actually.color != expected) {
			failWithMessage("""
				Expecting:  Color(${expected})
				Actual:     Color(${actually.color})
			""".trimIndent().trim())
		}
		return self()
	}

	/**
	 * Assert that the text has no formatting style.
	 */
	fun isNoStyle(): Self {
		if (actually.style != null) {
			failWithMessage("""
				Expecting:  Formatting()
				Actual:     Formatting(${actually.style})
			""".trimIndent().trim())
		}
		return self()
	}
	
	/**
	 * Assert that the node is exactly a specific formatting style.
	 */
	fun isStyle(vararg expected: Formatting): Self {
		val expectedCombined = Formatting.Combined(*expected)
		if (actually.style != expectedCombined) {
			failWithMessage("""
				Expecting:  Formatting(${expectedCombined})
				Actual:     Formatting(${actually.style})
			""".trimIndent().trim())
		}
		return self()
	}

	companion object {
		@JvmStatic
		fun <Self: TextNodeAssert<Self, TextNode>> assertThat(actual: TextNode?): TextNodeAssert<Self, TextNode> {
			return TextNodeAssert(actual, TextNodeAssert::class.java)
		}
	}
}
