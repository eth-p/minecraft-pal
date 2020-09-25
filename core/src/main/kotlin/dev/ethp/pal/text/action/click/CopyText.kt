package dev.ethp.pal.text.action.click

import com.google.gson.JsonObject
import dev.ethp.apistub.Export
import dev.ethp.pal._internal.JsonUtil.asJson
import dev.ethp.pal._internal.JsonUtil.jsonOf
import dev.ethp.pal.client.Client
import dev.ethp.pal.text.action.ClickAction
import dev.ethp.pal.text.component.TextNode
import dev.ethp.pal.text.component.TextPlaceholderNode
import dev.ethp.pal.text.component.TextStringNode
import dev.ethp.pal.text.placeholder.Placeholder
import dev.ethp.pal.text.placeholder.PlaceholderResolver

/**
 * A [ClickAction] that copies a string to the user's clipboard.
 *
 * @since 1.0
 */
@Export
class CopyText : ClickAction {

	// -------------------------------------------------------------------------------------------------------------
	// Constructors:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * Creates a new action that copies a text node to the user's clipboard.
	 * All formatting and actions contained within the node will be stripped.
	 *
	 * @param text The text node that will be copied.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(text: TextNode) {
		this.text = text
	}

	/**
	 * Creates a new action that copies a string to the user's clipboard.
	 *
	 * @param text The string that will be copied.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(text: String) {
		this.text = TextStringNode(text)
	}

	/**
	 * Creates a new action that copies the value of a placeholder to the user's clipboard.
	 *
	 * @param placeholder The placeholder that will be copied.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(placeholder: Placeholder) {
		this.text = TextPlaceholderNode(placeholder)
	}


	// -------------------------------------------------------------------------------------------------------------
	// Fields:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * The text node that will be copied.
	 *
	 * @since 1.0
	 */
	@Export
	val text: TextNode


	// -------------------------------------------------------------------------------------------------------------
	// Methods:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * Gets the string that will be copied.
	 * Placeholders will not be resolved.
	 *
	 * @return The string that will be copied.
	 *
	 * @since 1.0
	 */
	@Export
	fun getString(): String {
		return this.text.toString()
	}

	/**
	 * Gets the string that will be copied.
	 *
	 * @param resolver The placeholder resolver.
	 * @return The string that will be copied.
	 *
	 * @since 1.0
	 */
	@Export
	fun getString(resolver: PlaceholderResolver?): String {
		return if (resolver == null) {
			this.text.toString()
		} else {
			this.text.toString(resolver)
		}
	}


	// -------------------------------------------------------------------------------------------------------------
	// Implementation:
	// -------------------------------------------------------------------------------------------------------------

	@Export
	override fun toJson(client: Client?, resolver: PlaceholderResolver?): JsonObject {
		return jsonOf(
				"clickEvent" to jsonOf(
						"action" to "copy_to_clipboard".asJson(),
						"value" to this.getString(resolver).asJson()
				)
		)
	}


}
