package dev.ethp.pal.util

import Assert
import dev.ethp.pal.util.Version
import org.assertj.core.api.AbstractAssert

/**
 * Assertions for [Version].
 */
class VersionAssert(actual: Version?) : Assert<VersionAssert, Version>(actual, VersionAssert::class.java) {
	
	/**
	 * Assert that the version is equal to another version.
	 *
	 * @param expected The expected version.
	 * @return Self, for chaining.
	 */
	fun isEqualTo(expected: Version): VersionAssert {
		if (actually != expected) {
			failWithMessage("""
				Expecting:  $expected
				Actual:     $actually
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
		if (actually <= other) {
			val op = if (actually.compareTo(other) == 0) "=" else "<" 
			failWithMessage("""
				Expecting:  $actually > $other
				Actual:     $actually $op $other
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
		if (actually >= other) {
			val op = if (actually.compareTo(other) == 0) "=" else ">"
			failWithMessage("""
				Expecting:  $actually < $other
				Actual:     $actually $op $other
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
