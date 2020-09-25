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
 * A [ClickAction] that causes a user to run a command.
 *
 * @since 1.0
 */
@Export
class RunCommand : ClickAction {

	// -------------------------------------------------------------------------------------------------------------
	// Constructors:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * Creates a new click action that causes the user to run a command.
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
	 * Creates a new click action that causes the user to run a command.
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
	 * Creates a new click action that causes the user to run a command.
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
						"action" to "run_command".asJson(),
						"value" to this.getCommand(resolver).asJson()
				)
		)
	}


}
