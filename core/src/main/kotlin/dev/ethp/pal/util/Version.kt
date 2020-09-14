package dev.ethp.pal.util

import dev.ethp.apistub.Export
import java.util.*

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
	@Export
	constructor(major: Int, minor: Int) : this(major, minor, 0)

	/**
	 * Compares the version with another version.
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

	/**
	 * Checks if the version is equal to another version.
	 *
	 * @param other The other version to compare to.
	 *
	 * @since 1.0
	 */
	@Export
	override operator fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Version

		if (major != other.major) return false
		if (minor != other.minor) return false
		if (patch != other.patch) return false

		return true
	}

	/**
	 * @since 1.0
	 */
	@Export
	override fun hashCode(): Int {
		var result = major
		result = 31 * result + minor
		result = 31 * result + patch
		return result
	}

	/**
	 * @since 1.0
	 */
	@Export
	override fun toString(): String {
		return "${major}.${minor}.${patch}"
	}

	companion object {

		// ----------------------------------------
		// region: Parsing
		// ----------------------------------------

		/**
		 * Parses a version string into a [Version].
		 *
		 * @param version The version string to parse.
		 * @return An optional of the corresponding version object.
		 *         This will be empty if an invalid version string is provided.
		 *
		 * @since 1.0
		 */
		@Export
		@JvmStatic
		fun parse(version: String): Optional<Version> {
			var split = version.split('.')
			when (split.size) {
				2 -> split = split + "0"
				3 -> {
				}
				else -> return Optional.empty()
			}

			// Create a new version.
			try {
				val splitInt = split.map { n -> n.toInt(10) }
				return Optional.of(Version(splitInt[0], splitInt[1], splitInt[2]))
			} catch (ex: NumberFormatException) {
				return Optional.empty()
			}
		}

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
		fun parseUnsafe(version: String): Version {
			return parse(version).orElseThrow {
				throw IllegalArgumentException("Invalid version string: $version")
			}
		}

		// ----------------------------------------
		// endregion
		// ----------------------------------------


	}

}
