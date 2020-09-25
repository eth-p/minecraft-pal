package dev.ethp.pal.text.component

import Assert
import com.google.gson.JsonElement
import dev.ethp.pal.client.Client
import dev.ethp.pal.text.placeholder.PlaceholderResolver
import java.util.function.Supplier

/**
 * Assertions for [Node].
 */
open class NodeAssert<Self, Actual>(actual: Actual?, type: Class<in Self>) : Assert<Self, Actual>(actual, type)
		where Self : NodeAssert<Self, Actual>, Actual : Node {

	/**
	 * Assert that the node is equal to another node.
	 */
	fun isEqualTo(expected: Node): Self {
		if (actually != expected) {
			failWithMessage("""
				Expecting:  ${expected.toLegacyString()}
				Actual:     ${actually.toLegacyString()}
			""".trimIndent().trim())
		}
		return self()
	}

	/**
	 * Assert that the node's generated string is equal to some string.
	 *
	 * @param expected The expected string.
	 */
	fun isStringEqualTo(expected: String): Self {
		val actual = actually.toString()
		if (actual != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     $actual
			""".trimIndent().trim())
		}
		return self()
	}

	/**
	 * Assert that the node's generated string is equal to some string.
	 *
	 * @param resolver The resolver to use.
	 * @param expected The expected string.
	 */
	fun isStringWithResolverEqualTo(resolver: PlaceholderResolver?, expected: String): Self {
		val actual = actually.toString(resolver)
		if (actual != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     $actual
			""".trimIndent().trim())
		}
		return self()
	}

	/**
	 * Assert that the node's generated legacy string is equal to some string.
	 *
	 * @param expected The expected string.
	 */
	fun isLegacyStringEqualTo(expected: String): Self {
		val actual = actually.toLegacyString()
		if (actual != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     $actual
			""".trimIndent().trim())
		}
		return self()
	}

	/**
	 * Assert that the node's generated legacy string is equal to some string.
	 *
	 * @param resolver The resolver to use.
	 * @param expected The expected string.
	 */
	fun isLegacyStringWithResolverEqualTo(resolver: PlaceholderResolver?, expected: String): Self {
		val actual = actually.toLegacyString(resolver)
		if (actual != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     $actual
			""".trimIndent().trim())
		}
		return self()
	}

	/**
	 * Assert that the node's generated JSON is equal to some other JSON.
	 *
	 * @param resolver The placeholder resolver.
	 * @param generator A function that generates a JSON object to compare with.
	 */
	fun isJsonWithResolverEqualTo(resolver: PlaceholderResolver?, generator: Supplier<JsonElement>): Self {
		assertJsonEquality(this.actually.toJson(resolver), generator.get())
		return self()
	}

	/**
	 * Assert that the node's generated JSON is equal to some other JSON.
	 *
	 * @param client The player's client.
	 * @param generator A function that generates a JSON object to compare with.
	 */
	fun isJsonWithClientEqualTo(client: Client?, generator: Supplier<JsonElement>): Self {
		assertJsonEquality(this.actually.toJson(client), generator.get())
		return self()
	}

	/**
	 * Assert that the node's generated JSON is equal to some other JSON.
	 *
	 * @param client The player's client.
	 * @param resolver The placeholder resolver.
	 * @param generator A function that generates a JSON object to compare with.
	 */
	fun isJsonWithAllEqualTo(client: Client?, resolver: PlaceholderResolver?, generator: Supplier<JsonElement>): Self {
		assertJsonEquality(this.actually.toJson(client, resolver), generator.get())
		return self()
	}

	/**
	 * Assert that the node's generated JSON is equal to some other JSON.
	 * 
	 * @param generator A function that generates a JSON object to compare with.
	 */
	fun isJsonEqualTo(generator: Supplier<JsonElement>): Self {
		assertJsonEquality(this.actually.toJson(), generator.get())
		return self()
	}

	/**
	 * Assert that the node's generated JSON is equal to some other JSON.
	 * @param json The json to compare.
	 */
	fun isJsonEqualTo(json: JsonElement): Self {
		return this.isJsonEqualTo {
			json
		}
	}

	private fun assertJsonEquality(actual: JsonElement, expected: JsonElement) {
		if (actual != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     $actual
			""".trimIndent().trim())
		}
	}
	
	companion object {
		@JvmStatic
		fun <Self : NodeAssert<Self, Node>, Actual : Node> assertThat(actual: Node?): NodeAssert<Self, Node> {
			return NodeAssert(actual, NodeAssert::class.java)
		}
	}
}
