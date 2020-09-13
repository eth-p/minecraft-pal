package dev.ethp.pal.client

import dev.ethp.apistub.Export

/**
 * A Minecraft client.
 * 
 * This holds information about settings and client capabilities for a particular user.
 *
 * @property language The client language being used.
 * @property version The client version.
 * 
 * @since 1.0
 */
@Export
open class Client(@Export open val language: String, @Export open val version: ClientVersion) {

	/**
	 * The supported client features. 
	 */
	val features: List<ClientFeature>
		@Export
		get() = version.features

	/**
	 * Checks if the client supports a feature.
	 *
	 * @param feature The feature to check.
	 * @return True if the client supports the feature.
	 *
	 * @since 1.0
	 */
	@Export
	infix fun supports(feature: ClientFeature): Boolean {
		return feature appliesTo this.version
	}

	companion object {


		// ----------------------------------------
		// region: Versions
		// ----------------------------------------

		/**
		 * The minecraft client for `1.16.0`.
		 *
		 * @since 1.0
		 */
		@Export
		@JvmField
		val DEFAULT_1_16_0 = Client("en_US", ClientVersion.parseUnsafe("1.16.0"))

		/**
		 * The minecraft client for `1.15.0`.
		 *
		 * @since 1.0
		 */
		@Export
		@JvmField
		val DEFAULT_1_15_0 = Client("en_US", ClientVersion.parseUnsafe("1.15.0"))

		/**
		 * The minecraft client for `1.14.0`.
		 *
		 * @since 1.0
		 */
		@Export
		@JvmField
		val DEFAULT_1_14_0 = Client("en_US", ClientVersion.parseUnsafe("1.14.0"))

		/**
		 * The minecraft client for `1.13.0`.
		 *
		 * @since 1.0
		 */
		@Export
		@JvmField
		val DEFAULT_1_13_0 = Client("en_US", ClientVersion.parseUnsafe("1.13.0"))
		
		// ----------------------------------------
		// endregion
		// ----------------------------------------
		
		/**
		 * The latest Minecraft client.
		 * Do not use this if you're aiming for compatibility.
		 *
		 * @since 1.0
		 */
		@Export
		@JvmField
		val LATEST = DEFAULT_1_16_0

		/**
		 * The oldest Minecraft client supported by this library.
		 *
		 * @since 1.0
		 */
		@Export
		@JvmField
		val COMPATIBLE = Client("en_US", ClientVersion.parseUnsafe("1.14.0"))

	}

}
