package dev.ethp.pal.text.action.hover

import com.google.gson.JsonObject
import dev.ethp.apistub.Export
import dev.ethp.pal._internal.JsonUtil.asJson
import dev.ethp.pal._internal.JsonUtil.jsonOf
import dev.ethp.pal.client.Client
import dev.ethp.pal.text.action.HoverAction
import dev.ethp.pal.text.component.TextNode
import dev.ethp.pal.text.component.TextPlaceholderNode
import dev.ethp.pal.text.component.TextStringNode
import dev.ethp.pal.text.placeholder.Placeholder
import dev.ethp.pal.text.placeholder.PlaceholderResolver

/**
 * A [HoverAction] that shows text to the player.
 *
 * @since 1.0
 */
@Export
class ShowText : HoverAction {

	// -------------------------------------------------------------------------------------------------------------
	// Constructors:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * Creates a new hover action that shows text to the player.
	 * All actions contained within the node will be stripped.
	 *
	 * @param text The text node that will be displayed.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(text: TextNode) {
		this.text = text
	}

	/**
	 * Creates a new hover action that shows text to the player.
	 *
	 * @param text The string that will be displayed.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(text: String) {
		this.text = TextStringNode(text)
	}

	/**
	 * Creates a new hover action that shows text to the player.
	 *
	 * @param placeholder The placeholder that will be displayed.
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
	 * The text node that will be displayed.
	 *
	 * @since 1.0
	 */
	@Export
	val text: TextNode


	// -------------------------------------------------------------------------------------------------------------
	// Implementation:
	// -------------------------------------------------------------------------------------------------------------

	@Export
	override fun toJson(client: Client?, resolver: PlaceholderResolver?): JsonObject {
		return jsonOf(
				"hoverEvent" to jsonOf(
						"action" to "show_text".asJson(),
						"value" to this.text.toJson(client, resolver)
				)
		)
	}


}
