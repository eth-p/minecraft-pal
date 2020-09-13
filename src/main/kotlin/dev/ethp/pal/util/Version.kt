package dev.ethp.pal.util

import dev.ethp.apistub.Export

/**
 * A version object.
 * 
 * @property major The major version number.
 * @property minor The minor version number.
 * @property patch The patch version number.
 *
 * @since 1.0
 */
@Export
open class Version(@Export val major: Int, @Export val minor: Int, @Export val patch: Int) {
	
	/**
	 * Creates a new version with major and minor numbers.
	 * The patch number is assumed to be zero.
	 *
	 * @since 1.0
	 */
	constructor(major: Int, minor: Int) : this(major, minor, 0)
	
	/**
	 * Compares this version with another version.
	 * 
	 * @param other The other version to compare to.
	 * @return 0 -> Equal, 1 -> Higher, -1 -> Lower
	 * 
	 * @since 1.0
	 */
	@Export
	operator fun compareTo(other: Version): Int {
		return when {
			major > other.major -> 1
			major < other.major -> -1
			else -> when {
				minor > other.minor -> 1
				minor < other.minor -> -1
				else -> when {
					patch > other.patch -> 1
					patch < other.patch -> -1
					else -> 0
				}
			}
		}
	}

	companion object {

		// ----------------------------------------
		// region: Parsing
		// ----------------------------------------

		/**
		 * Parses a version string into a [Version].
		 *
		 * @param version The version string to parse.
		 * @return The corresponding version object.
		 *
		 * @throws IllegalArgumentException If an invalid version string is provided.
		 *
		 * @since 1.0
		 */
		@Export
		@JvmStatic
		fun parse(version: String): Version {
			var split = version.split('.')
			when (split.size) {
				2 -> split = split + "0"
				3 -> {
				}
				else -> throw IllegalArgumentException("Invalid version string: $version")
			}

			// Create a new version.
			try {
				val splitInt = split.map { n -> n.toInt(10) }
				return Version(splitInt[0], splitInt[1], splitInt[2])
			} catch (ex: NumberFormatException) {
				throw IllegalArgumentException("Invalid version string: $version")
			}
		}

		// ----------------------------------------
		// endregion
		// ----------------------------------------


	}

}
