package dev.ethp.pal.chat

import dev.ethp.apistub.Export
import dev.ethp.pal.math.ColorSpace
import java.util.*

/**
 * A Minecraft text color.
 *
 * This supports both legacy color codes (1.0-1.15.2) and RGB color codes (1.16+).
 *
 * @since 1.0
 */
@Export
final class Color {

	// -------------------------------------------------------------------------------------------------------------
	// Class:
	// -------------------------------------------------------------------------------------------------------------

	private constructor(rgb: Int) : this('\u0000', rgb, null)
	private constructor(code: Char, rgb: Int, property: String?) {
		this.rgb = rgb
		this._char = code
		this._property = property
	}

	/**
	 * The color in RRGGBB format.
	 * @since 1.0
	 */
	@Export
	val rgb: Int

	/**
	 * The legacy character code that this color is most similar to.
	 * @since 1.0
	 */
	val code: Char
		@Export
		get() {
			if (this._char == '\u0000') {
				this._char = rgbToCode(rgb);
			}

			return this._char
		}

	private var _char: Char

	/**
	 * The JSON property name for this color code.
	 * @since 1.0
	 */
	private val _property: String?

	/**
	 * The name of the color.
	 * @since 1.0
	 */
	val name: String
		@Export
		get() {
			if (this._property != null) return this._property
			return String.format("#%06x", this.rgb)
		}

	/**
	 * The legacy name of the color, performing color quantization if necessary.
	 * @since 1.0
	 */
	val legacyName: String
		@Export
		get() {
			if (this._property != null) return this._property
			return codeUnsafe(this.code)._property!!
		}

	/**
	 * Checks if the color is a legacy color.
	 * @since 1.0
	 */
	@Export
	fun isLegacy(): Boolean {
		return rgbIsLegacy(this.rgb)
	}

	/**
	 * Gets the color as a legacy Minecraft text string.
	 *
	 * @return The color code specifier (\xA7) followed by the color code.
	 * @since 1.0
	 */
	@Export
	fun toLegacyString(): String {
		return "\u00A7${this.code}"
	}

	@Export
	override operator fun equals(other: Any?): Boolean {
		if (other !is Color) return false
		return this.rgb == other.rgb
	}

	@Export
	override fun toString(): String {
		return String.format("#%06X", 0xFFFFFF and this.rgb)
	}

	@Export
	override fun hashCode(): Int {
		return this.rgb;
	}


	companion object {

		// ----------------------------------------
		// region: Colors
		// ----------------------------------------

		/**
		 * Creates a RGB color.
		 * This is only supported on 1.16, and will be quantized for versions lower than 1.16.
		 *
		 * @param rgb The RGB color in 00RRGGBB format.
		 * @return The corresponding [Color] object.
		 * @since 1.0
		 */
		@JvmStatic
		@Export
		fun rgb(rgb: Int): Color {
			return Color(rgb and 0xFFFFFF)
		}

		/**
		 * Creates a RGB color from a CSS color string.
		 * This is only supported on 1.16, and will be quantized for versions lower than 1.16.
		 *
		 * @param string The RGB string in CSS hex format.
		 * @return The corresponding [Color] object.
		 * @since 1.0
		 */
		@JvmStatic
		@Export
		fun rgb(string: String): Optional<Color> {
			if (string[0] != '#') return Optional.empty()
			try {
				return when (string.length) {
					4 -> {
						val n = string.substring(1).toInt(16)
						val w = n and 0xF00 shl 8 or (n and 0x0F0 shl 4) or (n and 0x00F)
						return Optional.of(rgb(w or (w shl 4)))
					}
					7 -> Optional.of(rgb(string.substring(1).toInt(16)))
					else -> Optional.empty()
				}
			} catch (ex: NumberFormatException) {
				return Optional.empty()
			}
		}

		/**
		 * Creates a RGB color from a CSS color string.
		 * This is only supported on 1.16, and will be quantized for versions lower than 1.16.
		 * This will throw on invalid color strings.
		 *
		 * @param string The RGB string in CSS hex format.
		 * @return The corresponding [Color] object.
		 * @throws IllegalArgumentException When provided an invalid color string.
		 * @since 1.0
		 */
		@JvmStatic
		@Export
		fun rgbUnsafe(string: String): Color {
			return rgb(string).orElseThrow {
				IllegalArgumentException("'${string}' is not a valid color string.")
			}
		}

		/**
		 * Finds a Color from the legacy color code.
		 *
		 * This does *not* support formatting codes.
		 * See [Formatting] for formatting codes.
		 *
		 * @param code The code character.
		 * @return The corresponding [Color] object.
		 * @since 1.0
		 */
		@JvmStatic
		@Export
		fun code(code: Char): Optional<Color> {
			return codeToColor(code)
		}

		/**
		 * Finds a Color from the legacy color code.
		 * This will throw on invalid colors.
		 *
		 * This does *not* support formatting codes.
		 * See [Formatting] for formatting codes.
		 *
		 * @param code The code character.
		 * @return The corresponding [Color] object.
		 * @throws IllegalArgumentException When provided an invalid color code.
		 * @since 1.0
		 */
		@JvmStatic
		@Export
		fun codeUnsafe(code: Char): Color {
			return codeToColor(code).orElseThrow {
				IllegalArgumentException("'${code}' is not a valid color code.")
			}
		}

		/**
		 * Finds a Color from the legacy color name.
		 *
		 * @param name The legacy color name (case insensitive).
		 * @return The corresponding [Color] object.
		 * @since 1.0
		 */
		@JvmStatic
		@Export
		fun name(name: String): Optional<Color> {
			return Optional.ofNullable(LEGACY_NAMETABLE[name.toUpperCase().replace(' ', '_')])
		}

		/**
		 * Finds a Color from the legacy color name.
		 * This will throw on invalid colors.
		 *
		 * @param name The legacy color name (case insensitive).
		 * @return The corresponding [Color] object.
		 * @throws IllegalArgumentException When provided an invalid color name.
		 * @since 1.0
		 */
		@JvmStatic
		@Export
		fun nameUnsafe(name: String): Color {
			return name(name).orElseThrow {
				IllegalArgumentException("'${name}' is not a valid color name.")
			}
		}

		// ----------------------------------------
		// endregion
		// region: Legacy Color Fields
		// ----------------------------------------

		/**
		 * Black chat color.
		 * Code: `&0`
		 * RGB:  0x000000 (0, 0, 170)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val BLACK: Color = Color('0', 0x000000, "black")

		/**
		 * Dark blue chat color.
		 * Code: `&1`
		 * RGB: 0x0000AA (0, 0, 170)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val DARK_BLUE: Color = Color('1', 0x0000AA, "dark_blue")

		/**
		 * Dark green chat color.
		 * Code: `&2`
		 * RGB: 0x00AA00 (0, 170, 0)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val DARK_GREEN: Color = Color('2', 0x00AA00, "dark_green")

		/**
		 * Dark aqua chat color.
		 * Code: `&3`
		 * RGB: 0x00AAAA (0, 170, 170)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val DARK_AQUA: Color = Color('3', 0x00AAAA, "dark_aqua")

		/**
		 * Dark red chat color.
		 * Code: `&4`
		 * RGB: 0x00AAAA (170, 0, 0)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val DARK_RED: Color = Color('4', 0xAA0000, "dark_red")

		/**
		 * Dark purple chat color.
		 * Code: `&5`
		 * RGB: 0xAA00AA (170, 0, 170)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val DARK_PURPLE: Color = Color('5', 0xAA00AA, "dark_purple")

		/**
		 * Dark purple chat color.
		 * Code: `&6`
		 * RGB: 0xFFAA00 (255, 170, 0)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val GOLD: Color = Color('6', 0xFFAA00, "gold")

		/**
		 * Gray chat color.
		 * Code: `&7`
		 * RGB: 0xAAAAAA (170, 170, 170)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val GRAY: Color = Color('7', 0xAAAAAA, "gray")

		/**
		 * Dark gray chat color.
		 * Code: `&8`
		 * RGB: 0x555555 (85, 85, 85)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val DARK_GRAY: Color = Color('8', 0x555555, "dark_gray")

		/**
		 * Blue chat color.
		 * Code: `&9`
		 * RGB: 0x5555FF (85, 85, 255)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val BLUE: Color = Color('9', 0x5555FF, "blue")

		/**
		 * Blue chat color.
		 * Code: `&a`
		 * RGB: 0x55FF55 (85, 255, 85)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val GREEN: Color = Color('a', 0x55FF55, "green")

		/**
		 * Aqua chat color.
		 * Code: `&b`
		 * RGB: 0x55FFFF (85, 255, 255)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val AQUA: Color = Color('b', 0x55FFFF, "aqua")

		/**
		 * Red chat color.
		 * Code: `&c`
		 * RGB: 0xFF5555 (255, 85, 85)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val RED: Color = Color('c', 0xFF5555, "red")

		/**
		 * Light purple chat color.
		 * Code: `&d`
		 * RGB: 0xFF5555 (255, 85, 255)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val LIGHT_PURPLE: Color = Color('d', 0xFF55FF, "light_purple")

		/**
		 * Pink purple chat color.
		 * Code: `&d`
		 * RGB: 0xFF5555 (255, 85, 255)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val PINK: Color = LIGHT_PURPLE

		/**
		 * Yellow chat color.
		 * Code: `&e`
		 * RGB: 0xFFFF55 (255, 255, 85)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val YELLOW: Color = Color('e', 0xFFFF55, "yellow")

		/**
		 * White chat color.
		 * Code: `&f`
		 * RGB: 0xFFFFFF (255, 255, 255)
		 * @since 1.0
		 */
		@JvmField
		@Export
		val WHITE: Color = Color('f', 0xFFFFFF, "white")

		// ----------------------------------------
		// endregion
		// region: Legacy Color Table
		// ----------------------------------------

		private data class LegacyColor(val color: Color) {
			val lab: ColorSpace.LAB = ColorSpace.RGB(color.rgb).toLAB()
			val rgb: Int
				inline get() = color.rgb
		}

		private val LEGACY_TABLE: Array<LegacyColor> = arrayOf(
				LegacyColor(BLACK), LegacyColor(DARK_BLUE), LegacyColor(DARK_GREEN), LegacyColor(DARK_AQUA),
				LegacyColor(DARK_RED), LegacyColor(DARK_PURPLE), LegacyColor(GOLD), LegacyColor(GRAY),
				LegacyColor(DARK_GRAY), LegacyColor(BLUE), LegacyColor(GREEN), LegacyColor(AQUA),
				LegacyColor(RED), LegacyColor(LIGHT_PURPLE), LegacyColor(YELLOW), LegacyColor(WHITE)
		)

		private val LEGACY_NAMETABLE: Map<String, Color> = mapOf(
				"BLACK" to BLACK, "DARK_BLUE" to DARK_BLUE, "DARK_GREEN" to DARK_GREEN, "DARK_AQUA" to DARK_AQUA,
				"DARK_RED" to DARK_RED, "DARK_PURPLE" to DARK_PURPLE, "GOLD" to GOLD, "GRAY" to GRAY,
				"DARK_GRAY" to DARK_GRAY, "BLUE" to BLUE, "GREEN" to GREEN, "AQUA" to AQUA,
				"RED" to RED, "LIGHT_PURPLE" to LIGHT_PURPLE, "YELLOW" to YELLOW, "WHITE" to WHITE,
				"PINK" to PINK
		)

		/**
		 * A list of all legacy colors.
		 * @since 1.0
		 */
		@JvmStatic
		fun values(): List<Color> {
			return LEGACY_TABLE.asList().map { legacyColor ->
				legacyColor.color
			}
		}

		// ----------------------------------------
		// endregion
		// region: Legacy Color Conversion
		// ----------------------------------------

		@JvmStatic
		private fun rgbToCode(rgb: Int): Char {
			// Fast path: Exact color.
			// Slow path: Approximation using Delta E 2000.
			return LEGACY_TABLE.find { color -> color.rgb == rgb }?.color?._char ?: run {
				var bestDelta = Double.MAX_VALUE
				var bestColor = LEGACY_TABLE[0]
				val lab = ColorSpace.RGB(rgb).toLAB()
				for (color in LEGACY_TABLE) {
					val delta = color.lab.delta(lab)
					if (delta < bestDelta) {
						bestDelta = delta
						bestColor = color
					}
				}
				bestColor.color._char
			}
		}

		@JvmStatic
		private fun rgbIsLegacy(rgb: Int): Boolean {
			return LEGACY_TABLE.find { color -> color.rgb == rgb } != null
		}

		@JvmStatic
		private fun codeToColor(code: Char): Optional<Color> {
			return when (code) {
				in '0'..'9' -> Optional.of(LEGACY_TABLE[(code - '0')].color)
				in 'a'..'f' -> Optional.of(LEGACY_TABLE[(code - 'a') + 10].color)
				else -> Optional.empty()
			}
		}

		@JvmStatic
		private fun codeToRgb(code: Char): Optional<Int> {
			return codeToColor(code).map { color -> color.rgb }
		}

		//endregion
		// ----------------------------------------

	}


}
