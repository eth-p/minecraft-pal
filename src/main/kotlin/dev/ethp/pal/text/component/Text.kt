package dev.ethp.pal.text.component

import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import dev.ethp.apistub.Export
import dev.ethp.pal._internal.gson
import dev.ethp.pal.client.Client
import dev.ethp.pal.client.ClientFeature.TEXT_RGB
import dev.ethp.pal.text.Color
import dev.ethp.pal.text.Formatting
import dev.ethp.pal.text.Formatting.Companion.BOLD
import dev.ethp.pal.text.Formatting.Companion.ITALIC
import dev.ethp.pal.text.Formatting.Companion.OBFUSCATED
import dev.ethp.pal.text.Formatting.Companion.RESET
import dev.ethp.pal.text.Formatting.Companion.STRIKETHROUGH
import dev.ethp.pal.text.Formatting.Companion.UNDERLINED

/**
 * Abstract base class for all Minecraft text.
 * This contains all the basic properties that any text can have.
 *
 * @since 1.0
 */
@Export
abstract class Text {

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
	// Methods:
	// -------------------------------------------------------------------------------------------------------------

	@Export
	open override fun toString(): String {
		return "[[AbstractText]]"
	}

	/**
	 * Gets the text as a legacy Minecraft text string.
	 * @return The legacy text string with Minecraft formatting codes.
	 * @since 1.0
	 */
	@Export
	open fun toLegacyString(): String {
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

	/**
	 * Gets the text as Minecraft text JSON.
	 *
	 * @param client The client information.
	 * @return The JSON object.
	 *
	 * @since 1.0
	 */
	@Export
	open fun toJson(client: Client?): JsonElement {
		val color = this.color
		val styles = this.style

		if (color == null && styles == null) {
			return JsonNull.INSTANCE
		}

		// Set color property.
		val obj = JsonObject()
		if (color != null) {
			val colorName = if (client != null && client supports TEXT_RGB) color.name else color.legacyName
			obj.add("color", JsonPrimitive(colorName))
		}

		// Set formatting properties.
		if (styles != null) {
			if (styles has RESET) obj.add("reset", JsonPrimitive(true))
			if (styles has OBFUSCATED) obj.add("obfuscated", JsonPrimitive(true))
			if (styles has BOLD) obj.add("bold", JsonPrimitive(true))
			if (styles has STRIKETHROUGH) obj.add("strikethrough", JsonPrimitive(true))
			if (styles has UNDERLINED) obj.add("underlined", JsonPrimitive(true))
			if (styles has ITALIC) obj.add("italic", JsonPrimitive(true))
		}

		// Return.
		return obj
	}

	/**
	 * Gets the text as Minecraft text JSON.
	 * This assumes a legacy client.
	 *
	 * @return The JSON object.
	 *
	 * @since 1.0
	 */
	@Export
	fun toJson(): Any? {
		return this.toJson(null)
	}

	/**
	 * Gets the text as serialized Minecraft text JSON.
	 *
	 * @param client The client information.
	 * @return The serialized JSON.
	 *
	 * @since 1.0
	 */
	@Export
	fun toJsonString(client: Client?): String {
		return gson.toJson(toJson(client))
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
	fun toJsonString(): String {
		return gson.toJson(toJson())
	}


	// -------------------------------------------------------------------------------------------------------------
	// Helpers:
	// -------------------------------------------------------------------------------------------------------------

	companion object {

//		fun parse

	}

}
