package dev.ethp.pal.chat

import dev.ethp.apistub.Export
import java.util.*

/**
 * A Minecraft chat formatting style.
 * @since 1.0
 */
@Export
final class Formatting {

	// -------------------------------------------------------------------------------------------------------------
	// Class:
	// -------------------------------------------------------------------------------------------------------------

	private constructor(code: Char, property: String, mask: Short) {
		this.code = code
		this.property = property
		this.mask = mask
	}

	/**
	 * The legacy character code for this formatting code.
	 * @since 1.0
	 */
	@Export
	val code: Char

	/**
	 * The JSON property name for this formatting code.
	 * @since 1.0
	 */
	private val property: String

	/**
	 * A bitmask used for compressing formatting codes together.
	 */
	private val mask: Short

	@Export
	override operator fun equals(other: Any?): Boolean {
		if (other !is Formatting) return false
		return this.code == other.code
	}

	@Export
	override fun toString(): String {
		return this.property
	}

	@Export
	override fun hashCode(): Int {
		return this.code.hashCode()
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
				OBFUSCATED, BOLD, STRIKETHROUGH, UNDERLINED, ITALIC
		)

		private val LEGACY_NAMETABLE: Map<String, Formatting> = mapOf(
				"RESET" to RESET, "BOLD" to BOLD, "ITALIC" to ITALIC, "STRIKETHROUGH" to STRIKETHROUGH,
				"UNDERLINED" to UNDERLINED, "UNDERLINE" to UNDERLINE, "OBFUSCATED" to OBFUSCATED, "MAGIC" to MAGIC,
		)

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
