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
import dev.ethp.pal.text.placeholder.Placeholder
import dev.ethp.pal.text.placeholder.PlaceholderResolver
import java.util.*

/**
 * A text node that displays a placeholder.
 *
 * @since 1.0
 */
@Export
class TextPlaceholderNode : TextNode {

	// -------------------------------------------------------------------------------------------------------------
	// Constructors:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * Creates a new placeholder node with a color and a formatting style.
	 *
	 * @param placeholder The placeholder.
	 * @param color The color.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(placeholder: Placeholder, color: Color?, style: Formatting.Combined?) : super(color, style) {
		this.placeholder = placeholder
	}

	/**
	 * Creates a new placeholder node.
	 *
	 * @param placeholder The placeholder.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(placeholder: Placeholder) : this(placeholder, null, null)

	/**
	 * Creates a new placeholder node with a color.
	 *
	 * @param placeholder The placeholder.
	 * @param color The color.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(placeholder: Placeholder, color: Color) : this(placeholder, color, null)

	/**
	 * Creates a new placeholder node with a formatting style.
	 *
	 * @param placeholder The placeholder.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(placeholder: Placeholder, style: Formatting.Combined) : this(placeholder, null, style)

	/**
	 * Creates a new placeholder node with a formatting style.
	 *
	 * @param placeholder The placeholder.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(placeholder: Placeholder, style: Formatting) : this(placeholder, Formatting.Combined(style))

	/**
	 * Creates a new placeholder node with a formatting style.
	 *
	 * @param placeholder The placeholder.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(placeholder: Placeholder, vararg style: Formatting) : this(placeholder, Formatting.Combined(*style))

	/**
	 * Creates a new placeholder node with a color and a formatting style.
	 *
	 * @param placeholder The placeholder.
	 * @param color The color.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(placeholder: Placeholder, color: Color, style: Formatting) : this(placeholder, color, Formatting.Combined(style))

	/**
	 * Creates a new placeholder node with a color and a formatting style.
	 *
	 * @param placeholder The placeholder.
	 * @param color The color.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(placeholder: Placeholder, color: Color, vararg style: Formatting) : this(placeholder, color, Formatting.Combined(*style))

	/**
	 * Creates a new placeholder node with a color and a formatting style.
	 *
	 * @param placeholder The placeholder.
	 * @param color The color.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(placeholder: Placeholder, style: Formatting.Combined, color: Color) : this(placeholder, color, style)

	/**
	 * Creates a new placeholder node with a color and a formatting style.
	 *
	 * @param placeholder The placeholder.
	 * @param color The color.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(placeholder: Placeholder, style: Formatting, color: Color) : this(placeholder, color, style)


	// -------------------------------------------------------------------------------------------------------------
	// Fields:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * The placeholder.
	 * @since 1.0
	 */
	@Export
	val placeholder: Placeholder


	// -------------------------------------------------------------------------------------------------------------
	// Methods:
	// -------------------------------------------------------------------------------------------------------------

	@Export
	override fun toString(): String {
		return this.placeholder.toString()
	}

	@Export
	override fun toString(resolver: PlaceholderResolver?): String {
		return if (resolver == null) {
			this.placeholder.toString()
		} else {
			resolver.resolve(this.placeholder).orElseGet {
				this.placeholder.toString()
			}
		}
	}

	@Export
	override fun toLegacyString(): String {
		return super.toLegacyString() + this.toString()
	}

	@Export
	override fun toLegacyString(resolver: PlaceholderResolver?): String {
		return super.toLegacyString() + this.toString(resolver)
	}

	@Export
	override fun toJson(client: Client?, resolver: PlaceholderResolver?): JsonElement {
		val string = (resolver?.resolve(this.placeholder) ?: Optional.empty())
				.orElseGet { this.placeholder.toString() }
				.asJson()

		return when (val json = super.toJson(client, resolver)) {
			is JsonNull -> string
			is JsonObject -> {
				json["text"] = string
				json
			}
			else -> throw UnsupportedOperationException("Unsupported JSON: ${json.javaClass}")
		}
	}

}
