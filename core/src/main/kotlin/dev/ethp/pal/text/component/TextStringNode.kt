package dev.ethp.pal.text.component

import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import dev.ethp.apistub.Export
import dev.ethp.pal._internal.JsonUtil.asJson
import dev.ethp.pal._internal.JsonUtil.set
import dev.ethp.pal.client.Client
import dev.ethp.pal.text.Color
import dev.ethp.pal.text.Formatting
import dev.ethp.pal.text.placeholder.PlaceholderResolver

/**
 * A text node created from arbitrary text.
 * This can contain exactly one styled string.
 *
 * @since 1.0
 */
@Export
class TextStringNode : TextNode {

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

	@Export
	override fun toLegacyString(): String {
		return "${super.toLegacyString()}$text"
	}

	@Export
	override fun toJson(client: Client?, resolver: PlaceholderResolver?): JsonElement {
		return when (val json = super.toJson(client, resolver)) {
			is JsonNull -> this.text.asJson()
			is JsonObject -> {
				json["text"] = this.text
				json
			}
			else -> throw UnsupportedOperationException("Unsupported JSON: ${json.javaClass}")
		}
	}

}
