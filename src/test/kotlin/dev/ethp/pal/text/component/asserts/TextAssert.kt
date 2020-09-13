package dev.ethp.pal.text.component.asserts

import com.google.gson.JsonElement
import dev.ethp.pal.player.Client
import dev.ethp.pal.text.Color
import dev.ethp.pal.text.Formatting
import dev.ethp.pal.text.component.Text
import org.assertj.core.api.AbstractAssert
import java.util.function.Supplier

/**
 * Assertions for [Text].
 */
@Suppress("UNCHECKED_CAST")
open class TextAssert<Self: TextAssert<Self, in Actual>, Actual : Text>(actual: Actual?, type: Class<*>)
	: AbstractAssert<TextAssert<Self, Actual>, Actual?>(actual, type) {
	
	/**
	 * Assert that the text is equal to another text.
	 */
	fun isEqualTo(expected: Text): Self {
		if (actual!! != expected) {
			failWithMessage("""
				Expecting:  ${expected.toLegacyString()}
				Actual:     ${actual.toLegacyString()}
			""".trimIndent().trim())
		}
		return this as Self
	}
	
	/**
	 * Assert that the text's generated JSON is equal to some other JSON.
	 */
	fun isJsonEqualTo(client: Client?, generator: Supplier<JsonElement>): Self {
		isNotNull()
		val generated = generator.get()
		val actual = this.actual!!.toJson(client)
		if (actual != generated) {
			failWithMessage("""
				Expecting:  $generated
				Actual:     $actual
			""".trimIndent().trim())
		}
		return this as Self
	}

	/**
	 * Assert that the text has no color.
	 */
	fun isNoColor(): Self {
		isNotNull()
		if (actual!!.color != null) {
			failWithMessage("""
				Expecting:  Color()
				Actual:     Color(${actual.color})
			""".trimIndent().trim())
		}
		return this as Self
	}

	/**
	 * Assert that the text has a specific color.
	 */
	fun isColor(expected: Color): Self {
		isNotNull()
		if (actual!!.color != expected) {
			failWithMessage("""
				Expecting:  Color(${expected})
				Actual:     Color(${actual.color})
			""".trimIndent().trim())
		}
		return this as Self
	}

	/**
	 * Assert that the text has no formatting style.
	 */
	fun isNoStyle(): Self {
		if (actual!!.style != null) {
			failWithMessage("""
				Expecting:  Formatting()
				Actual:     Formatting(${actual.style})
			""".trimIndent().trim())
		}
		return this as Self
	}
	
	/**
	 * Assert that the text is exactly a specific formatting style.
	 */
	fun isStyle(vararg expected: Formatting): Self {
		isNotNull()
		val expectedCombined = Formatting.Combined(*expected)
		if (actual!!.style != expectedCombined) {
			failWithMessage("""
				Expecting:  Formatting(${expectedCombined})
				Actual:     Formatting(${actual.style})
			""".trimIndent().trim())
		}
		return this as Self
	}

	companion object {
		@JvmStatic
		fun <Self: TextAssert<Self, Text>> assertThat(actual: Text?): TextAssert<Self, Text> {
			return TextAssert(actual, TextAssert::class.java)
		}
	}
}
