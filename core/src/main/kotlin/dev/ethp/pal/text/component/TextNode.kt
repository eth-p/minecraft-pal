package dev.ethp.pal.text.component

import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import dev.ethp.apistub.Export
import dev.ethp.pal.client.Client
import dev.ethp.pal.client.ClientFeature.TEXT_RGB
import dev.ethp.pal.text.Color
import dev.ethp.pal._internal.JsonUtil.set;
import dev.ethp.pal.text.Formatting
import dev.ethp.pal.text.Formatting.Companion.BOLD
import dev.ethp.pal.text.Formatting.Companion.ITALIC
import dev.ethp.pal.text.Formatting.Companion.OBFUSCATED
import dev.ethp.pal.text.Formatting.Companion.RESET
import dev.ethp.pal.text.Formatting.Companion.STRIKETHROUGH
import dev.ethp.pal.text.Formatting.Companion.UNDERLINED
import dev.ethp.pal.text.placeholder.Placeholder
import dev.ethp.pal.text.placeholder.PlaceholderResolver

/**
 * Abstract base class for all stylable Minecraft text nodes.
 * This contains all the basic properties that any text can have.
 *
 * @since 1.0
 */
@Export
abstract class TextNode : Node {

	// -------------------------------------------------------------------------------------------------------------
	// Constructors:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * Creates a new abstract text object with a color and a formatting style.
	 *
	 * @param color The color.
	 * @param style The formatting style.
	 * @since 1.0
	 */
	@Export
	constructor(color: Color?, style: Formatting.Combined?) {
		this.color = color
		this.style = style
	}


	// -------------------------------------------------------------------------------------------------------------
	// Fields:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * The text color.
	 * @since 1.0
	 */
	@Export
	val color: Color?

	/**
	 * The text style.
	 * @since 1.0
	 */
	@Export
	val style: Formatting.Combined?

	
	// -------------------------------------------------------------------------------------------------------------
	// Implementation:
	// -------------------------------------------------------------------------------------------------------------
	
	@Export
	override fun toString(): String {
		return ""
	}
	
	@Export
	open override fun toLegacyString(): String {
		val builder = StringBuilder()
		val color = this.color
		val styles = this.style

		// Append color code.
		if (color != null) {
			builder.append(color.toLegacyString())
		}

		// Append style codes.
		if (styles != null) {
			builder.append(styles.toLegacyString())
		}

		return builder.toString()
	}

	@Export
	open override fun toJson(client: Client?, resolver: PlaceholderResolver?): JsonElement {
		val color = this.color
		val styles = this.style

		if (color == null && styles == null) {
			return JsonNull.INSTANCE
		}

		// Set color property.
		val obj = JsonObject()
		if (color != null) {
			obj["color"] = if (client != null && client supports TEXT_RGB) {
				color.name
			} else {
				color.legacyName
			}
		}

		// Set formatting properties.
		if (styles != null) {
			if (styles has RESET) obj["reset"] = true
			if (styles has OBFUSCATED) obj["obfuscated"] = true
			if (styles has BOLD) obj["bold"] = true
			if (styles has STRIKETHROUGH) obj["strikethrough"] = true
			if (styles has UNDERLINED) obj["underlined"] = true
			if (styles has ITALIC) obj["italic"] = true
		}

		// Return.
		return obj
	}

}
