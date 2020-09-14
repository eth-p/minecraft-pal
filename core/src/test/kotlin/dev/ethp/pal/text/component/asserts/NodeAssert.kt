package dev.ethp.pal.text.component.asserts

import com.google.gson.JsonElement
import dev.ethp.pal.client.Client
import dev.ethp.pal.text.Color
import dev.ethp.pal.text.Formatting
import dev.ethp.pal.text.component.Node
import org.assertj.core.api.AbstractAssert
import java.util.function.Supplier

/**
 * Assertions for [Node].
 */
@Suppress("UNCHECKED_CAST")
open class NodeAssert<Self: NodeAssert<Self, in Actual>, Actual : Node>(actual: Actual?, type: Class<*>)
	: AbstractAssert<NodeAssert<Self, Actual>, Actual?>(actual, type) {
	
	/**
	 * Assert that the node is equal to another node.
	 */
	fun isEqualTo(expected: Node): Self {
		if (actual!! != expected) {
			failWithMessage("""
				Expecting:  ${expected.toLegacyString()}
				Actual:     ${actual.toLegacyString()}
			""".trimIndent().trim())
		}
		return this as Self
	}
	
	/**
	 * Assert that the node's generated JSON is equal to some other JSON.
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
	 * Assert that the node has no color.
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
	 * Assert that the node has a specific color.
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
	 * Assert that the node is exactly a specific formatting style.
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
		fun <Self: NodeAssert<Self, Node>> assertThat(actual: Node?): NodeAssert<Self, Node> {
			return NodeAssert(actual, NodeAssert::class.java)
		}
	}
}
