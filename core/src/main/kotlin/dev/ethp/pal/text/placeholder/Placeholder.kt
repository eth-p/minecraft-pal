package dev.ethp.pal.text.placeholder

import dev.ethp.apistub.Export

/**
 * A placeholder for a to-be-determined value that can be included in text.
 *
 * @param name The placeholder name.
 * @param item The item to retrieve from the placeholder.
 *
 * @since 1.0
 */
@Export
data class Placeholder(val name: String, val item: String?) {

	// -------------------------------------------------------------------------------------------------------------
	// Constructors:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * Creates a new placeholder.
	 *
	 * @param name The placeholder name.
	 */
	@Export
	constructor(name: String) : this(name, null)


	// -------------------------------------------------------------------------------------------------------------
	// Methods:
	// -------------------------------------------------------------------------------------------------------------
	
	/**
	 * Converts the placeholder to its unparsed placeholder string.
	 * This is used as a fallback for when placeholders cannot be resolved.
	 */
	@Export
	override fun toString(): String {
		return if (item == null) {
			"{${name}}"
		} else {
			"{${name} ${item}}"
		}
	}

}
