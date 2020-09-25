package dev.ethp.pal.text.placeholder;

import dev.ethp.pal.text.Color;
import org.junit.jupiter.api.Test;
import static dev.ethp.pal.text.placeholder.PlaceholderAssert.assertThat;

public class PlaceholderTests {

	/**
	 * Test the constructors for the Placeholder objects.
	 */
	@Test
	void testConstructors() {
		assertThat(new Placeholder("test"))
				.hasName("test")
				.hasItem(null);

		assertThat(new Placeholder("test", "ing"))
				.hasName("test")
				.hasItem("ing");
	}
	
	/**
	 * Test the equality of the Placeholder objects.
	 */
	@Test
	void testEquality() {
		assertThat(new Placeholder("test")).isEqualTo(new Placeholder("test"));
		assertThat(new Placeholder("test", "ing")).isEqualTo(new Placeholder("test", "ing"));
	}

}

