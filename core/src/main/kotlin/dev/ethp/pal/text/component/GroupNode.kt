package dev.ethp.pal.text.component

import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import dev.ethp.apistub.Export
import dev.ethp.pal._internal.JsonUtil.set
import dev.ethp.pal.text.Color
import dev.ethp.pal.text.Formatting
import dev.ethp.pal.client.Client
import dev.ethp.pal.text.placeholder.PlaceholderResolver
import java.lang.UnsupportedOperationException

/**
 * A group node.
 * This can contain multiple different nodes, and will cause them to inherit the group style.
 *
 * @since 1.0
 */
@Export
class GroupNode : TextNode {

	// -------------------------------------------------------------------------------------------------------------
	// Constructors:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * Creates a new group node with a color and a formatting style.
	 *
	 * @param children The children nodes.
	 * @param color The color.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(children: Collection<Node>, color: Color?, style: Formatting.Combined?) : super(color, style) {
		this.children = children.toList()
	}

	/**
	 * Creates a new group node.
	 *
	 * @param children The children nodes.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(children: Collection<Node>) : this(children, null, null)

	/**
	 * Creates a new group node with a color.
	 *
	 * @param children The children nodes.
	 * @param color The color.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(children: Collection<Node>, color: Color) : this(children, color, null)

	/**
	 * Creates a new group node with a formatting style.
	 *
	 * @param children The children nodes.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(children: Collection<Node>, style: Formatting.Combined) : this(children, null, style)

	/**
	 * Creates a new group node with a formatting style.
	 *
	 * @param children The children nodes.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(children: Collection<Node>, style: Formatting) : this(children, Formatting.Combined(style))

	/**
	 * Creates a new group node with a formatting style.
	 *
	 * @param children The children nodes.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(children: Collection<Node>, vararg style: Formatting) : this(children, Formatting.Combined(*style))

	/**
	 * Creates a new group node with a color and a formatting style.
	 *
	 * @param children The children nodes.
	 * @param color The color.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(children: Collection<Node>, color: Color, style: Formatting) : this(children, color, Formatting.Combined(style))

	/**
	 * Creates a new group node with a color and a formatting style.
	 *
	 * @param children The children nodes.
	 * @param color The color.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(children: Collection<Node>, color: Color, vararg style: Formatting) : this(children, color, Formatting.Combined(*style))

	/**
	 * Creates a new group node with a color and a formatting style.
	 *
	 * @param text The message.
	 * @param color The color.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(children: Collection<Node>, style: Formatting.Combined, color: Color) : this(children, color, style)

	/**
	 * Creates a new group node with a color and a formatting style.
	 *
	 * @param children The children nodes.
	 * @param color The color.
	 * @param style The formatting style.
	 *
	 * @since 1.0
	 */
	@Export
	constructor(children: Collection<Node>, style: Formatting, color: Color) : this(children, color, style)


	// -------------------------------------------------------------------------------------------------------------
	// Fields:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * The child nodes.
	 * @since 1.0
	 */
	@Export
	val children: List<Node>


	// -------------------------------------------------------------------------------------------------------------
	// Methods:
	// -------------------------------------------------------------------------------------------------------------

	@Export
	override fun toString(): String {
		return this.children.joinToString("") {
			it.toString()
		}
	}

	@Export
	override fun toString(resolver: PlaceholderResolver?): String {
		return this.children.joinToString("") {
			it.toString(resolver)
		}
	}

	@Export
	override fun toLegacyString(): String {
		val legacy = super.toLegacyString()
		return legacy + this.children.joinToString("") {
			it.toLegacyString()
		}.replace(Formatting.RESET.toLegacyString(), legacy)
	}

	@Export
	override fun toLegacyString(resolver: PlaceholderResolver?): String {
		val legacy = super.toLegacyString(resolver)
		return legacy + this.children.joinToString("") {
			it.toLegacyString(resolver)
		}.replace(Formatting.RESET.toLegacyString(), legacy)
	}

	@Export
	override fun toJson(client: Client?, resolver: PlaceholderResolver?): JsonElement {
		val json: JsonObject = when (val style: JsonElement = super.toJson(client, resolver)) {
			is JsonObject -> style
			is JsonNull -> JsonObject()
			else -> throw UnsupportedOperationException("Unsupported JSON: ${style.javaClass}")
		}

		json["extra"] = this.children.map { it.toJson(client, resolver) }
		return json
	}

}
