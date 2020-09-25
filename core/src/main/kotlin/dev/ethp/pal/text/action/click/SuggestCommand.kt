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
 * A [ClickAction] that fills the user's chat box with a command suggestion.
 *
 * @since 1.0
 */
@Export
class SuggestCommand : ClickAction {

	// -------------------------------------------------------------------------------------------------------------
	// Constructors:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * Creates a new click action that suggests a command to the user.
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
	 * Creates a new click action that suggests a command to the user.
	 *
	 * @param text The command string.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(text: String) {
		this.text = TextStringNode(text)
	}

	/**
	 * Creates a new click action that suggests a command to the user.
	 *
	 * @param placeholder The placeholder for the command string.
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
	 * Gets the command that will be executed.
	 * Placeholders will not be resolved.
	 *
	 * @return The command that will be executed.
	 *
	 * @since 1.0
	 */
	fun getCommand(): String {
		return this.text.toString()
	}

	/**
	 * Gets the command that will be executed.
	 *
	 * @param resolver The placeholder resolver.
	 * @return The command that will be executed.
	 *
	 * @since 1.0
	 */
	fun getCommand(resolver: PlaceholderResolver?): String {
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
						"action" to "suggest_command".asJson(),
						"value" to this.getCommand(resolver).asJson()
				)
		)
	}


}
