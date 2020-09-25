package dev.ethp.pal.text.placeholder

import dev.ethp.apistub.Export
import java.util.*

/**
 * An interface for resolving [Placeholder] items.
 *
 * @since 1.0
 */
@Export
interface PlaceholderResolver {

	// -------------------------------------------------------------------------------------------------------------
	// Methods:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * Resolves a placeholder.
	 *
	 * @param placeholder The placeholder to resolve.
	 *
	 * @return The resolved text.
	 *
	 * @since 1.0
	 */
	fun resolve(placeholder: Placeholder): Optional<String>

}
