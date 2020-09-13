package dev.ethp.pal.text.component

import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import dev.ethp.apistub.Export
import dev.ethp.pal.text.Color
import dev.ethp.pal.text.Formatting
import dev.ethp.pal.player.Client

/**
 * A basic text node.
 * This can contain one styled string.
 * 
 * @since 1.0
 */
@Export
class TextNode : Text {

	// -------------------------------------------------------------------------------------------------------------
	// Constructors:
	// -------------------------------------------------------------------------------------------------------------
	
	/**
	 * Creates a new text node with a color and a formatting style.
	 *
	 * @param text The message.
	 * @param color The color.
	 * @param style The formatting style.
	 * 
	 * @since 1.0
	 */
	@Export
	constructor(text: String, color: Color?, style: Formatting.Combined?) : super(color, style) {
		this.text = text
	}
	
	/**
	 * Creates a new text node.
	 *
	 * @param text The message.
	 * 
	 * @since 1.0
	 */
	@Export
	constructor(text: String) : this(text, null, null)

	/**
	 * Creates a new text node with a color.
	 *
	 * @param text The message.
	 * @param color The color.
	 * 
	 * @since 1.0
	 */
	@Export
	constructor(text: String, color: Color) : this(text, color, null)

	/**
	 * Creates a new text node with a formatting style.
	 *
	 * @param text The message.
	 * @param style The formatting style.
	 * 
	 * @since 1.0
	 */
	@Export
	constructor(text: String, style: Formatting.Combined) : this(text, null, style)

	/**
	 * Creates a new text node with a formatting style.
	 *
	 * @param text The message.
	 * @param style The formatting style.
	 * 
	 * @since 1.0
	 */
	@Export
	constructor(text: String, style: Formatting) : this(text, Formatting.Combined(style))

	/**
	 * Creates a new text node with a formatting style.
	 *
	 * @param text The message.
	 * @param style The formatting style.
	 * 
	 * @since 1.0
	 */
	@Export
	constructor(text: String, vararg style: Formatting) : this(text, Formatting.Combined(*style))

	/**
	 * Creates a new text node with a color and a formatting style.
	 *
	 * @param text The message.
	 * @param color The color.
	 * @param style The formatting style.
	 * 
	 * @since 1.0
	 */
	@Export
	constructor(text: String, color: Color, style: Formatting) : this(text, color, Formatting.Combined(style))

	/**
	 * Creates a new text node with a color and a formatting style.
	 *
	 * @param text The message.
	 * @param color The color.
	 * @param style The formatting style.
	 * 
	 * @since 1.0
	 */
	@Export
	constructor(text: String, color: Color, vararg style: Formatting) : this(text, color, Formatting.Combined(*style))

	/**
	 * Creates a new text node with a color and a formatting style.
	 *
	 * @param text The message.
	 * @param color The color.
	 * @param style The formatting style.
	 * 
	 * @since 1.0
	 */
	@Export
	constructor(text: String, style: Formatting.Combined, color: Color) : this(text, color, style)

	/**
	 * Creates a new text node with a color and a formatting style.
	 *
	 * @param text The message.
	 * @param color The color.
	 * @param style The formatting style.
	 * 
	 * @since 1.0
	 */
	@Export
	constructor(text: String, style: Formatting, color: Color) : this(text, color, style)


	// -------------------------------------------------------------------------------------------------------------
	// Fields:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * The text string.
	 * @since 1.0
	 */
	@Export
	val text: String


	// -------------------------------------------------------------------------------------------------------------
	// Methods:
	// -------------------------------------------------------------------------------------------------------------

	@Export
	override fun toString(): String {
		return this.text
	}

	/**
	 * Gets the text as a legacy Minecraft text string.
	 * @return The legacy text string with Minecraft formatting codes.
	 * 
	 * @since 1.0
	 */
	@Export
	override fun toLegacyString(): String {
		return "${super.toLegacyString()}$text"
	}

	@Export
	override fun toJson(client: Client?): JsonElement {
		val json: JsonElement = super.toJson(client)
		if (json == JsonNull.INSTANCE) return JsonPrimitive(this.text)
		
		val obj: JsonObject = json as JsonObject
		obj.add("text", JsonPrimitive(this.text))
		return obj
	}
	
}
