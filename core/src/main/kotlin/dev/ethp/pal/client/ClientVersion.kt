package dev.ethp.pal.client

import dev.ethp.apistub.Export
import dev.ethp.pal.util.Version
import java.util.*
import kotlin.collections.HashMap

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
	val features: List<ClientFeature> = ClientFeature.values().filter { f -> f appliesTo this }
	
	companion object {

		// ----------------------------------------
		// region: Parsing
		// ----------------------------------------

		private val VERSION_CACHE: HashMap<String, ClientVersion> = HashMap()

		/**
		 * Parses a Minecraft version string into a [ClientVersion].
		 *
		 * @param version The version string to parse.
		 * @return An optional of the client version.
		 *         This will be empty if an invalid version string is provided.
		 *
		 * @since 1.0
		 */
		@Export
		@JvmStatic
		fun parse(version: String): Optional<ClientVersion> {
			val cached = VERSION_CACHE[version]
			if (cached != null) return Optional.of(cached)

			// Create a new version.
			val versionObject = Version.parse(version).map {
				ClientVersion(it.major, it.minor, it.patch)
			}
			
			if (!versionObject.isPresent) {
				return versionObject
			}
			
			VERSION_CACHE[version] = versionObject.get()
			return versionObject
		}

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
		@JvmStatic
		fun parseUnsafe(version: String): ClientVersion {
			return parse(version).orElseThrow {
				throw IllegalArgumentException("Invalid version string: $version")
			}
		}

		// ----------------------------------------
		// endregion
		// ----------------------------------------


	}

}
