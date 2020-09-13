package dev.ethp.pal.util;

import org.junit.jupiter.api.Test;
import static dev.ethp.pal.util.Version.parseUnsafe;
import static dev.ethp.pal.util.asserts.VersionAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class VersionTests {

	/**
	 * Test that {@link Version#parse(String)} works correctly.
	 */
	@Test
	void testParse() {
		assertThat(parseUnsafe("01.02.03")).isEqualTo(new Version(1, 2, 3));
		assertThat(parseUnsafe("1.2.3")).isEqualTo(new Version(1, 2, 3));
		assertThat(parseUnsafe("1.2")).isEqualTo(new Version(1, 2, 0));

		assertThatThrownBy(() -> parseUnsafe("1")).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> parseUnsafe("1.2.3.4")).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> parseUnsafe("a.b.c")).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> parseUnsafe("1a.1b")).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> parseUnsafe("0xf.0xf.0xf")).isInstanceOf(IllegalArgumentException.class);
	}

	/**
	 * Test that version comparison works correctly.
	 */
	@Test
	void testComparison() {
		assertThat(parseUnsafe("1.2.3")).isEqualTo(parseUnsafe("1.2.3"));
		assertThat(parseUnsafe("1.2.3")).isNotEqualTo(parseUnsafe("1.2.2"));
		
		assertThat(parseUnsafe("1.2.3")).isGreaterThan(parseUnsafe("1.2.2"));
		assertThat(parseUnsafe("1.2.3")).isGreaterThan(parseUnsafe("1.1.3"));
		assertThat(parseUnsafe("1.2.3")).isGreaterThan(parseUnsafe("1.1.4"));
		assertThat(parseUnsafe("1.2.3")).isGreaterThan(parseUnsafe("0.2.3"));
		assertThat(parseUnsafe("1.2.3")).isGreaterThan(parseUnsafe("0.2.4"));
		assertThat(parseUnsafe("1.2.3")).isGreaterThan(parseUnsafe("0.3.3"));
		
		assertThat(parseUnsafe("1.2.3")).isLessThan(parseUnsafe("1.2.4"));
		assertThat(parseUnsafe("1.2.3")).isLessThan(parseUnsafe("1.3.3"));
		assertThat(parseUnsafe("1.2.3")).isLessThan(parseUnsafe("1.3.2"));
		assertThat(parseUnsafe("1.2.3")).isLessThan(parseUnsafe("2.2.3"));
		assertThat(parseUnsafe("1.2.3")).isLessThan(parseUnsafe("2.1.1"));
		assertThat(parseUnsafe("1.2.3")).isLessThan(parseUnsafe("3.3.3"));
	}

}

