package dev.ethp.pal.player

import dev.ethp.apistub.Export
import dev.ethp.pal.util.Version

/**
 * A Minecraft client version.
 *
 * @property major The major version number.
 * @property minor The minor version number.
 * @property patch The patch version number.
 * 
 * @since 1.0
 */
@Export
class ClientVersion(major: Int, minor: Int, patch: Int) : Version(major, minor, patch) {

	/**
	 * A list of supported client features.
	 * 
	 * @since 1.0
	 */
	@Export
	val features: List<ClientFeature> = ClientFeature.values().filter { f -> f supports this }
	
	companion object {

		// ----------------------------------------
		// region: Parsing
		// ----------------------------------------

		private val VERSION_CACHE: HashMap<String, ClientVersion> = HashMap()

		/**
		 * Parses a Minecraft version string into a [ClientVersion].
		 *
		 * @param version The version string to parse.
		 * @return The client version.
		 *
		 * @throws IllegalArgumentException If an invalid version string is provided.
		 *
		 * @since 1.0
		 */
		@Export
		fun parse(version: String): ClientVersion {
			val cached = VERSION_CACHE[version]
			if (cached != null) return cached

			// Create a new version.
			val versionParts = Version.parse(version)
			val versionObject = ClientVersion(versionParts.major, versionParts.minor, versionParts.patch)
			VERSION_CACHE[version] = versionObject
			return versionObject
		}

		// ----------------------------------------
		// endregion
		// ----------------------------------------


	}

}
