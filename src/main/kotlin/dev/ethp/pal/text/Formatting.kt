package dev.ethp.pal.text

import dev.ethp.apistub.Export
import java.util.*
import kotlin.experimental.and
import kotlin.experimental.or
import kotlin.experimental.inv

/**
 * A Minecraft text formatting style.
 * @since 1.0
 */
@Export
final class Formatting {

	// -------------------------------------------------------------------------------------------------------------
	// Class:
	// -------------------------------------------------------------------------------------------------------------

	private constructor(code: Char, property: String, mask: Short) {
		this.code = code
		this.name = property
		this.mask = mask
	}

	/**
	 * The legacy character code for this formatting code.
	 * @since 1.0
	 */
	@Export
	val code: Char

	/**
	 * The name of the formatting style.
	 * @since 1.0
	 */
	@Export
	val name: String

	/**
	 * A bitmask used for compressing formatting codes together.
	 */
	private val mask: Short

	/**
	 * Combines the formatting style with another formatting style.
	 * @param other The other formatting style.
	 * @return The [Combined] style.
	 * @since 1.0
	 */
	infix fun with(other: Formatting): Combined {
		return Combined(this, other)
	}

	/**
	 * Combines the formatting style with other formatting styles.
	 * @param other The other formatting styles.
	 * @return The [Combined] style.
	 * @since 1.0
	 */
	fun with(vararg other: Formatting): Combined {
		return Combined(this, *other)
	}

	/**
	 * Combines the formatting style with another formatting style.
	 * @param other The other formatting style.
	 * @return The [Combined] style.
	 * @since 1.0
	 */
	infix fun with(other: Combined): Combined {
		return other with this
	}

	/**
	 * Gets the formatting style as a legacy Minecraft text string.
	 *
	 * @return The formatting code specifier (\xA7) followed by the formatting code.
	 * @since 1.0
	 */
	@Export
	fun toLegacyString(): String {
		return "\u00A7${this.code}"
	}

	@Export
	override operator fun equals(other: Any?): Boolean {
		if (other !is Formatting) return false
		return this.code == other.code
	}

	@Export
	override fun toString(): String {
		return this.name
	}

	@Export
	override fun hashCode(): Int {
		return this.code.hashCode()
	}

	/**
	 * A combined formatting style consisting of zero or more styles.
	 * @since 1.0
	 */
	@Export
	final class Combined {

		internal var bitfield: Short;

		private constructor(stylesMask: Short) {
			this.bitfield = stylesMask
		}

		/**
		 * Creates a new combined formatting style.
		 *
		 * @param styles The formatting style.
		 * @since 1.0
		 */
		@Export
		constructor(style: Formatting) {
			this.bitfield = style.mask
		}

		/**
		 * Creates a new combined formatting style.
		 *
		 * @param styles The formatting styles.
		 * @since 1.0
		 */
		@Export
		constructor(vararg styles: Formatting) {
			this.bitfield = styles.fold(0.toShort(), { acc, format ->
				(acc or format.mask)
			})
		}

		/**
		 * Creates a formatting style with another style in it.
		 *
		 * @param style The style to add.
		 * @return The new combined formatting style.
		 * @since 1.0
		 */
		@Export
		infix fun with(style: Formatting): Combined {
			return Combined(this.bitfield or style.mask)
		}

		/**
		 * Creates a formatting style with other formatting styles in it.
		 *
		 * @param style The styles to add.
		 * @return The new combined formatting style.
		 * @since 1.0
		 */
		@Export
		fun with(vararg style: Formatting): Combined {
			return Combined(style.fold(this.bitfield, { acc, format ->
				acc or format.mask
			}))
		}

		/**
		 * Creates a formatting style with another style in it.
		 *
		 * @param style The style to add.
		 * @return The new combined formatting style.
		 * @since 1.0
		 */
		@Export
		infix fun with(style: Combined): Combined {
			return Combined(this.bitfield or style.bitfield)
		}

		/**
		 * Creates a formatting style without another style in it.
		 *
		 * @param style The style to remove.
		 * @return The new combined formatting style.
		 * @since 1.0
		 */
		@Export
		infix fun without(style: Formatting): Combined {
			return Combined(this.bitfield and style.mask.inv())
		}

		/**
		 * Creates a formatting style without other styles in it.
		 *
		 * @param style The styles to remove.
		 * @return The new combined formatting style.
		 * @since 1.0
		 */
		@Export
		infix fun without(style: Combined): Combined {
			return Combined(this.bitfield and style.bitfield.inv())
		}

		/**
		 * Creates a formatting style without other styles in it.
		 *
		 * @param style The styles to remove.
		 * @return The new combined formatting style.
		 * @since 1.0
		 */
		@Export
		fun without(vararg style: Formatting): Combined {
			return Combined(style.fold(this.bitfield, { acc, format ->
				acc and format.mask.inv()
			}))
		}

		/**
		 * Checks if this combined formatting contains a formatting style.
		 *
		 * @param style The style to check.
		 * @return True if this combined formatting contains the specified style.
		 * @since 1.0
		 */
		@Export
		infix fun has(style: Formatting): Boolean {
			return (this.bitfield and style.mask) > 0
		}

		/**
		 * Gets the formatting styles that this combined style is made from.
		 *
		 * @return The applicable styles.
		 * @since 1.0
		 */
		@Export
		fun styles(): List<Formatting> {
			return values().filter { style ->
				this has style
			}
		}

		/**
		 * Gets the formatting styles as a legacy Minecraft text string.
		 *
		 * @return For each style, the formatting code specifier (\xA7) followed by the formatting code.
		 * @since 1.0
		 */
		fun toLegacyString(): String {
			val builder = StringBuilder()
			if (this has RESET) builder.append("\u00A7k")
			if (this has OBFUSCATED) builder.append("\u00A7k")
			if (this has BOLD) builder.append("\u00A7l")
			if (this has STRIKETHROUGH) builder.append("\u00A7m")
			if (this has UNDERLINE) builder.append("\u00A7n")
			if (this has ITALIC) builder.append("\u00A7o")
			return builder.toString()
		}

		@Export
		override fun equals(other: Any?): Boolean {
			return when (other) {
				is Combined -> this.bitfield == other.bitfield
				is Formatting -> this.bitfield == other.mask
				else -> false
			}
		}

		@Export
		override fun hashCode(): Int {
			return this.bitfield.toInt()
		}

		@Export
		override fun toString(): String {
			return styles().toString()
		}
	}

	companion object {

		// ----------------------------------------
		// region: Codes
		// ----------------------------------------

		/**
		 * Finds a Formatting from the legacy formatting code.
		 *
		 * This does *not* support color codes.
		 * See [Color] for formatting codes.
		 *
		 * @param code The code character.
		 * @return The corresponding [Formatting] object.
		 * @since 1.0
		 */
		@JvmStatic
		@Export
		fun code(code: Char): Optional<Formatting> {
			return codeToFormatting(code)
		}

		/**
		 * Finds a Formatting from the legacy formatting code.
		 * This will throw on invalid styles.
		 *
		 * This does *not* support color codes.
		 * See [Color] for formatting codes.
		 *
		 * @param code The code character.
		 * @return The corresponding [Formatting] object.
		 * @throws IllegalArgumentException When provided an invalid formatting code.
		 * @since 1.0
		 */
		@JvmStatic
		@Export
		fun codeUnsafe(code: Char): Formatting {
			return codeToFormatting(code).orElseThrow {
				IllegalArgumentException("'${code}' is not a valid formatting code.")
			}
		}

		/**
		 * Finds a Formatting from the legacy formatting name.
		 *
		 * @param name The legacy formatting name (case insensitive).
		 * @return The corresponding [Formatting] object.
		 * @since 1.0
		 */
		@JvmStatic
		@Export
		fun name(name: String): Optional<Formatting> {
			return Optional.ofNullable(LEGACY_NAMETABLE[name.toUpperCase().replace(' ', '_')])
		}

		/**
		 * Finds a Formatting from the legacy formatting name.
		 * This will throw on invalid formatting types.
		 *
		 * @param name The legacy formatting name (case insensitive).
		 * @return The corresponding [Formatting] object.
		 * @throws IllegalArgumentException When provided an invalid formatting name.
		 * @since 1.0
		 */
		@JvmStatic
		@Export
		fun nameUnsafe(name: String): Formatting {
			return name(name).orElseThrow {
				IllegalArgumentException("'${name}' is not a valid formatting name.")
			}
		}

		// ----------------------------------------
		// endregion
		// region: Legacy Formatting Fields
		// ----------------------------------------

		/**
		 * Reset color and formatting.
		 * Code: `&r`
		 * @since 1.0
		 */
		@JvmField
		@Export
		val RESET: Formatting = Formatting('r', "reset", 0b000001)

		/**
		 * Bold formatting.
		 * Code: `&l`
		 * @since 1.0
		 */
		@JvmField
		@Export
		val BOLD: Formatting = Formatting('l', "bold", 0b000010)

		/**
		 * Italic formatting.
		 * Code: `&o`
		 * @since 1.0
		 */
		@JvmField
		@Export
		val ITALIC: Formatting = Formatting('o', "italic", 0b000100)

		/**
		 * Strikethrough formatting.
		 * Code: `&m`
		 * @since 1.0
		 */
		@JvmField
		@Export
		val STRIKETHROUGH: Formatting = Formatting('m', "strikethrough", 0b001000)

		/**
		 * Underlined formatting.
		 * Code: `&n`
		 * @since 1.0
		 */
		@JvmField
		@Export
		val UNDERLINED: Formatting = Formatting('n', "underlined", 0b010000)

		/**
		 * Underlined formatting.
		 * Code: `&n`
		 * @since 1.0
		 */
		@JvmField
		@Export
		val UNDERLINE: Formatting = UNDERLINED

		/**
		 * Obfuscated formatting.
		 * Code: `&k`
		 * @since 1.0
		 */
		@JvmField
		@Export
		val OBFUSCATED: Formatting = Formatting('k', "obfuscated", 0b100000)

		/**
		 * Obfuscated formatting.
		 * Code: `&k`
		 * @since 1.0
		 */
		@JvmField
		@Export
		val MAGIC: Formatting = OBFUSCATED


		// ----------------------------------------
		// endregion
		// region: Legacy Formatting Table
		// ----------------------------------------

		private val LEGACY_TABLE: Array<Formatting> = arrayOf(
				OBFUSCATED, BOLD, STRIKETHROUGH, UNDERLINED, ITALIC, RESET
		)

		private val LEGACY_NAMETABLE: Map<String, Formatting> = mapOf(
				"RESET" to RESET, "BOLD" to BOLD, "ITALIC" to ITALIC, "STRIKETHROUGH" to STRIKETHROUGH,
				"UNDERLINED" to UNDERLINED, "UNDERLINE" to UNDERLINE, "OBFUSCATED" to OBFUSCATED, "MAGIC" to MAGIC,
		)

		/**
		 * A list of all formatting styles.
		 * @since 1.0
		 */
		@JvmStatic
		fun values(): List<Formatting> {
			return LEGACY_TABLE.asList()
		}

		// ----------------------------------------
		// endregion
		// region: Legacy Formatting Conversion
		// ----------------------------------------

		@JvmStatic
		private fun codeToFormatting(code: Char): Optional<Formatting> {
			return when (code) {
				'r' -> Optional.of(RESET)
				in 'k'..'o' -> Optional.of(LEGACY_TABLE[(code - 'k')])
				else -> Optional.empty()
			}
		}

		//endregion
		// ----------------------------------------

	}


}
