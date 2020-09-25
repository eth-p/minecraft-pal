package dev.ethp.pal.text.placeholder

import Assert
import dev.ethp.pal.text.placeholder.Placeholder
import java.lang.AssertionError
import java.util.*

/**
 * Assertions for [PlaceholderResolver].
 */
class PlaceholderResolverAssert(actual: PlaceholderResolver?) : Assert<PlaceholderResolverAssert, PlaceholderResolver>(actual, PlaceholderResolverAssert::class.java) {
	
	/**
	 * Assert that the text is equal to another text.
	 */
	fun resolvesTo(placeholder: Placeholder, expected: String?): PlaceholderResolverAssert {
		val resolved = actually.resolve(placeholder)
		if (!resolved.isPresent || resolved.get() != expected) {
			failWithMessage("""
				Expecting resolves to:  $expected
				Actual:                 $resolved
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the text is equal to another text.
	 */
	fun resolvesToNothing(placeholder: Placeholder): PlaceholderResolverAssert {
		val resolved = actually.resolve(placeholder)
		if (resolved.isPresent) {
			failWithMessage("""
				Expecting resolves to:  Empty
				Actual:                 $resolved
			""".trimIndent().trim())
		}
		return this
	}


	// -------------------------------------------------------------------------------------------------------------
	// Mocks:
	// -------------------------------------------------------------------------------------------------------------

	private class MockPlaceholderResolver : PlaceholderResolver {

		override fun resolve(placeholder: Placeholder): Optional<String> {
			return Optional.of("RESOLVED($placeholder)")
		}

	}

	private class MockFailingPlaceholderResolver : PlaceholderResolver {

		override fun resolve(placeholder: Placeholder): Optional<String> {
			return Optional.empty()
		}

	}

	private class MockThrowingPlaceholderResolver : PlaceholderResolver {

		override fun resolve(placeholder: Placeholder): Optional<String> {
			throw AssertionError("A placeholder was resolved when it should not have been.")
		}

	}


	companion object {
		@JvmStatic
		fun assertThat(actual: PlaceholderResolver?): PlaceholderResolverAssert {
			return PlaceholderResolverAssert(actual)
		}
		
		/**
		 * Creates a resolver that always returns "RESOLVED($placeholder)" for placeholder resolution.
		 */
		@JvmStatic
		fun mockResolver(): PlaceholderResolver {
			return MockPlaceholderResolver()
		}

		/**
		 * Creates a resolver that always returns empty for placeholder resolution.
		 */
		@JvmStatic
		fun mockFailingResolver(): PlaceholderResolver {
			return MockFailingPlaceholderResolver()
		}
		/**
		 * 
		 * Creates a resolver that throws.
		 */
		@JvmStatic
		fun mockThrowingResolver(): PlaceholderResolver {
			return MockThrowingPlaceholderResolver()
		}

	}
}
