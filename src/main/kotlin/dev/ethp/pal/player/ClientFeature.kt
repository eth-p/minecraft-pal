package dev.ethp.pal.player

import dev.ethp.apistub.Export
import dev.ethp.pal.util.Version
import dev.ethp.pal.util.Version.Companion.parse

/**
 * An enum of supported client features.
 *
 * @since 1.0
 */
@Export
enum class ClientFeature(private val min: Version) {

	/**
	 * The client supports using RGB color codes in JSON text objects.
	 * Requires: 1.16
	 *
	 * @since 1.0
	 */
	@Export
	TEXT_RGB(parse("1.16")),

	/**
	 * The client supports namespaced identifiers.
	 * Requires: 1.13
	 *
	 * @since 1.0
	 */
	@Export
	NAMESPACED_IDS(parse("1.13"));

	/**
	 * Checks if a Minecraft version supports this feature.
	 *
	 * @param version The version to check.
	 * @return True if the version supports this feature.
	 *
	 * @since 1.0
	 */
	@Export
	infix fun supports(version: Version): Boolean {
		return version >= this.min
	}

}
