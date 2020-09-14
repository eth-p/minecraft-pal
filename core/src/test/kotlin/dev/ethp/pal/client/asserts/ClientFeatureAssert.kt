package dev.ethp.pal.client.asserts

import dev.ethp.pal.client.Client
import dev.ethp.pal.client.ClientFeature
import dev.ethp.pal.client.ClientVersion
import org.assertj.core.api.AbstractAssert

/**
 * Assertions for [ClientFeature].
 */
class ClientFeatureAssert(actual: ClientFeature?) : AbstractAssert<ClientFeatureAssert?, ClientFeature?>(actual, ClientFeatureAssert::class.java) {

	/**
	 * Assert that the feature is not supported by a specific client version.
	 *
	 * @param version The client version.
	 * @return Self, for chaining.
	 */
	fun notAppliesTo(version: ClientVersion): ClientFeatureAssert {
		if (actual!! appliesTo version) {
			failWithMessage("""
				Expecting:  $version does not support $actual
				Actual:     $version supports $actual
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the feature is supported by a specific client version.
	 *
	 * @param version The client version.
	 * @return Self, for chaining.
	 */
	fun appliesTo(version: ClientVersion): ClientFeatureAssert {
		if (!(actual!! appliesTo version)) {
			failWithMessage("""
				Expecting:  $version supports $actual
				Actual:     $version does not support $actual
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the feature is supported by a specific client.
	 *
	 * @param client The client.
	 * @return Self, for chaining.
	 */
	fun appliesTo(client: Client): ClientFeatureAssert {
		return this.appliesTo(client.version)
	}

	/**
	 * Assert that the feature is not supported by a specific client.
	 *
	 * @param client The client.
	 * @return Self, for chaining.
	 */
	fun notAppliesTo(client: Client): ClientFeatureAssert {
		return this.notAppliesTo(client.version)
	}

	companion object {
		@JvmStatic
		fun assertThat(actual: ClientFeature?): ClientFeatureAssert {
			return ClientFeatureAssert(actual)
		}
	}
}
