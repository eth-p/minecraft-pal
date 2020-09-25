package dev.ethp.pal.text.component

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import dev.ethp.apistub.Export
import dev.ethp.pal._internal.JsonUtil.jsonOf
import dev.ethp.pal._internal.JsonUtil.plusAssign
import dev.ethp.pal.client.Client
import dev.ethp.pal.text.action.ClickAction
import dev.ethp.pal.text.action.HoverAction
import dev.ethp.pal.text.placeholder.PlaceholderResolver
import java.lang.UnsupportedOperationException

/**
 * An action node.
 * This wraps a [TextNode] and provides hover actions or click actions.
 *
 * @since 1.0
 */
@Export
class ActionNode : Node {

	// -------------------------------------------------------------------------------------------------------------
	// Constructors:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * Creates a new action node.
	 *
	 * @param text The text wrapped by this action.
	 * @param click The click action.
	 * @param hover The hover action.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(text: TextNode, click: ClickAction, hover: HoverAction) {
		this.text = text
		this.onClick = click
		this.onHover = hover
	}

	/**
	 * Creates a new action node.
	 *
	 * @param text The text wrapped by this action.
	 * @param click The click action.
	 * @param hover The hover action.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(text: TextNode, hover: HoverAction, click: ClickAction) : this(text, click, hover)

	/**
	 * Creates a new action node.
	 *
	 * @param text The text wrapped by this action.
	 * @param click The click action.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(text: TextNode, click: ClickAction) {
		this.text = text
		this.onClick = click
		this.onHover = null
	}

	/**
	 * Creates a new action node.
	 *
	 * @param text The text wrapped by this action.
	 * @param hover The hover action.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(text: TextNode, hover: HoverAction) {
		this.text = text
		this.onClick = null
		this.onHover = hover
	}


	// -------------------------------------------------------------------------------------------------------------
	// Fields:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * The wrapped node.
	 *
	 * @since 1.0
	 */
	@Export
	val text: TextNode

	/**
	 * The click action.
	 *
	 * @since 1.0
	 * @see [ClickAction]
	 */
	@Export
	val onClick: ClickAction?

	/**
	 * The hover action.
	 *
	 * @since 1.0
	 * @see [HoverAction]
	 */
	@Export
	val onHover: HoverAction?


	// -------------------------------------------------------------------------------------------------------------
	// Methods:
	// -------------------------------------------------------------------------------------------------------------

	@Export
	override fun toString(): String {
		return this.text.toString()
	}

	@Export
	override fun toLegacyString(): String {
		return this.text.toLegacyString()
	}

	@Export
	override fun toJson(client: Client?, resolver: PlaceholderResolver?): JsonElement {
		// Get the wrapped node JSON.
		val json: JsonObject = when (val json: JsonElement = this.text.toJson(client, resolver)) {
			is JsonObject -> json
			is JsonPrimitive -> jsonOf("text" to json)
			else -> throw UnsupportedOperationException("Unsupported JSON: ${json.javaClass}")
		}

		// Add the actions.
		this.onHover?.toJson(client, resolver)?.let { json += it }
		this.onClick?.toJson(client, resolver)?.let { json += it }

		// Return.
		return json
	}

}
