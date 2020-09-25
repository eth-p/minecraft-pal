package dev.ethp.pal.text.component

import com.google.gson.JsonElement
import dev.ethp.apistub.Export
import dev.ethp.pal._internal.JsonUtil.gson
import dev.ethp.pal.client.Client
import dev.ethp.pal.text.placeholder.PlaceholderResolver

/**
 * An interface for generating Minecraft text nodes.
 *
 * @since 1.0
 */
@Export
interface Node {

	// -------------------------------------------------------------------------------------------------------------
	// Methods:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * Gets the node as a text string.
	 * 
	 * @return The the text string.
	 * 
	 * @since 1.0
	 */
	@Export
	override fun toString(): String

	/**
	 * Gets the node as a text string with all placeholders resolved.
	 *
	 * @param resolver The placeholder resolver.
	 * @return The the text string.
	 *
	 * @since 1.0
	 */
	@Export
	@JvmDefault
	fun toString(resolver: PlaceholderResolver?): String {
		return this.toString()
	}

	/**
	 * Gets the node as a legacy Minecraft text string.
	 * 
	 * @return The legacy text string with Minecraft formatting codes.
	 * 
	 * @since 1.0
	 */
	@Export
	fun toLegacyString(): String
	
	/**
	 * Gets the node as a legacy Minecraft text string with all placeholders resolved.
	 *
	 * @param resolver The placeholder resolver.
	 * @return The legacy text string with Minecraft formatting codes.
	 *
	 * @since 1.0
	 */
	@Export
	@JvmDefault
	fun toLegacyString(resolver: PlaceholderResolver?): String {
		return this.toLegacyString()
	}
	
	/**
	 * Gets the node as a JSON text object.
	 *
	 * @param client The client information.
	 * @param resolver The placeholder resolver.
	 * @return The JSON object.
	 *
	 * @since 1.0
	 */
	@Export
	fun toJson(client: Client?, resolver: PlaceholderResolver?): JsonElement

	/**
	 * Gets the node as a JSON text object.
	 * Placeholders will not be resolved.
	 *
	 * @param client The client information.
	 * @return The JSON object.
	 *
	 * @since 1.0
	 */
	@Export
	@JvmDefault
	fun toJson(client: Client?): JsonElement {
		return this.toJson(client, null)
	}

	/**
	 * Gets the node as a JSON text object.
	 *
	 * @param resolver The placeholder resolver.
	 * @return The JSON object.
	 *
	 * @since 1.0
	 */
	@Export
	@JvmDefault
	fun toJson(resolver: PlaceholderResolver?): JsonElement {
		return this.toJson(null, resolver)
	}

	/**
	 * Gets the node as a JSON text object.
	 * This assumes a legacy client, and placeholders will not be resolved.
	 *
	 * @return The JSON object.
	 *
	 * @since 1.0
	 */
	@Export
	@JvmDefault
	fun toJson(): JsonElement {
		return this.toJson(null, null)
	}

	/**
	 * Gets the node as serialized text JSON.
	 *
	 * @param client The client information.
	 * @param resolver The placeholder resolver.
	 * @return The serialized JSON.
	 *
	 * @since 1.0
	 */
	@Export
	@JvmDefault
	fun toJsonString(client: Client?, resolver: PlaceholderResolver?): String {
		return gson.toJson(this.toJson(client, resolver))
	}

	/**
	 * Gets the text as serialized Minecraft text JSON.
	 * This assumes a legacy client.
	 *
	 * @return The serialized JSON.
	 *
	 * @since 1.0
	 */
	@Export
	@JvmDefault
	fun toJsonString(): String {
		return gson.toJson(this.toJson())
	}

}
