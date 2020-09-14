package dev.ethp.pal.util.asserts

import dev.ethp.pal.util.Version
import org.assertj.core.api.AbstractAssert

/**
 * Assertions for [Version].
 */
class VersionAssert(actual: Version?) : AbstractAssert<VersionAssert?, Version?>(actual, VersionAssert::class.java) {
	
	/**
	 * Assert that the version is equal to another version.
	 *
	 * @param expected The expected version.
	 * @return Self, for chaining.
	 */
	fun isEqualTo(expected: Version): VersionAssert {
		isNotNull()
		if (actual!! != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     $actual
			""".trimIndent().trim())
		}
		return this
	}

	/**
	 * Assert that the version is greater than another version.
	 *
	 * @param expected The other version.
	 * @return Self, for chaining.
	 */
	fun isGreaterThan(other: Version): VersionAssert {
		isNotNull()
		if (actual!! <= other) {
			val op = if (actual.compareTo(other) == 0) "=" else "<" 
			failWithMessage("""
				Expecting:  $actual > $other
				Actual:     $actual $op $other
			""".trimIndent().trim())
		}
		return this
	}


	/**
	 * Assert that the version is greater than another version.
	 *
	 * @param expected The other version.
	 * @return Self, for chaining.
	 */
	fun isLessThan(other: Version): VersionAssert {
		isNotNull()
		if (actual!! >= other) {
			val op = if (actual.compareTo(other) == 0) "=" else ">"
			failWithMessage("""
				Expecting:  $actual < $other
				Actual:     $actual $op $other
			""".trimIndent().trim())
		}
		return this
	}

	companion object {
		@JvmStatic
		fun assertThat(actual: Version?): VersionAssert {
			return VersionAssert(actual)
		}
	}
}
