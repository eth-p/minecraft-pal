package dev.ethp.pal.text.component;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import dev.ethp.pal.text.Formatting;
import dev.ethp.pal.text.placeholder.Placeholder;
import org.junit.jupiter.api.Test;
import static dev.ethp.pal.text.Color.GREEN;
import static dev.ethp.pal.text.Color.RED;
import static dev.ethp.pal.text.Formatting.*;
import static dev.ethp.pal.text.component.TextPlaceholderNodeAssert.assertThat;
import static dev.ethp.pal.text.placeholder.PlaceholderResolverAssert.mockFailingResolver;
import static dev.ethp.pal.text.placeholder.PlaceholderResolverAssert.mockResolver;

public class TextPlaceholderNodeTests {

	/**
	 * Test that the constructors works.
	 */
	@Test
	void testConstructors() {
		final Placeholder placeholder = new Placeholder("Test", "123");
		final Placeholder placeholder2 = new Placeholder("Testing", "ABC");

		// TextPlaceholderNode(String)
		assertThat(new TextPlaceholderNode(placeholder))
				.isPlaceholder(placeholder)
				.isNoColor()
				.isNoStyle();

		// TextPlaceholderNode(String, Color)
		assertThat(new TextPlaceholderNode(placeholder2, RED))
				.isPlaceholder(placeholder2)
				.isColor(RED)
				.isNoStyle();

		// TextPlaceholderNode(String, Formatting)
		assertThat(new TextPlaceholderNode(placeholder, BOLD))
				.isPlaceholder(placeholder)
				.isNoColor()
				.isStyle(BOLD);

		// TextPlaceholderNode(String, Color, Formatting)
		assertThat(new TextPlaceholderNode(placeholder, RED, BOLD))
				.isPlaceholder(placeholder)
				.isColor(RED)
				.isStyle(BOLD);

		// TextPlaceholderNode(String, Color, Formatting...)
		assertThat(new TextPlaceholderNode(placeholder, RED, BOLD, OBFUSCATED))
				.isPlaceholder(placeholder)
				.isColor(RED)
				.isStyle(BOLD, OBFUSCATED);

		// TextPlaceholderNode(String, Formatting...)
		assertThat(new TextPlaceholderNode(placeholder, BOLD, OBFUSCATED))
				.isPlaceholder(placeholder)
				.isNoColor()
				.isStyle(BOLD, OBFUSCATED);

		// TextPlaceholderNode(String, Formatting, Color)
		assertThat(new TextPlaceholderNode(placeholder, BOLD, RED))
				.isPlaceholder(placeholder)
				.isColor(RED)
				.isStyle(BOLD);

		// TextPlaceholderNode(String, Formatting.Combined, Color)
		assertThat(new TextPlaceholderNode(placeholder, new Formatting.Combined(BOLD, ITALIC), RED))
				.isPlaceholder(placeholder)
				.isColor(RED)
				.isStyle(BOLD, ITALIC);

		// TextPlaceholderNode(String, Color, Formatting.Combined)
		assertThat(new TextPlaceholderNode(placeholder, GREEN, new Formatting.Combined(BOLD, UNDERLINED)))
				.isPlaceholder(placeholder)
				.isColor(GREEN)
				.isStyle(BOLD, UNDERLINED);
	}

	/**
	 * Test that the {@link TextPlaceholderNode#toString()} function works.
	 */
	@Test
	void testString() {
		assertThat(new TextPlaceholderNode(new Placeholder("Test", "ABC"), RED, BOLD, ITALIC))
				.isStringEqualTo("{Test ABC}")
				.isStringWithResolverEqualTo(null, "{Test ABC}")
				.isStringWithResolverEqualTo(mockFailingResolver(), "{Test ABC}")
				.isStringWithResolverEqualTo(mockResolver(), "RESOLVED({Test ABC})");
	}

	/**
	 * Test that the {@link TextPlaceholderNode#toLegacyString()} function works.
	 */
	@Test
	void testLegacyString() {
		assertThat(new TextPlaceholderNode(new Placeholder("Test", "ABC"), RED, BOLD, ITALIC))
				.isLegacyStringEqualTo("\u00A7c\u00A7l\u00A7o{Test ABC}")
				.isLegacyStringWithResolverEqualTo(null, "\u00A7c\u00A7l\u00A7o{Test ABC}")
				.isLegacyStringWithResolverEqualTo(mockFailingResolver(), "\u00A7c\u00A7l\u00A7o{Test ABC}")
				.isLegacyStringWithResolverEqualTo(mockResolver(), "\u00A7c\u00A7l\u00A7oRESOLVED({Test ABC})");
	}

	/**
	 * Test that the {@link TextPlaceholderNode#toJson()} function works.
	 */
	@Test
	void testJson() {
		assertThat(new TextPlaceholderNode(new Placeholder("Test", "ABC"), RED, BOLD))

				.isJsonWithResolverEqualTo(mockResolver(), () -> {
					JsonObject obj = new JsonObject();
					obj.add("color", new JsonPrimitive("red"));
					obj.add("bold", new JsonPrimitive(true));
					obj.add("text", new JsonPrimitive("RESOLVED({Test ABC})"));
					return obj;
				})

				.isJsonWithResolverEqualTo(mockFailingResolver(), () -> {
					JsonObject obj = new JsonObject();
					obj.add("color", new JsonPrimitive("red"));
					obj.add("bold", new JsonPrimitive(true));
					obj.add("text", new JsonPrimitive("{Test ABC}"));
					return obj;
				});


		assertThat(new TextPlaceholderNode(new Placeholder("Test", "123")))
				.isJsonWithResolverEqualTo(mockResolver(), () -> new JsonPrimitive("RESOLVED({Test 123})"))
				.isJsonWithResolverEqualTo(mockFailingResolver(), () -> new JsonPrimitive("{Test 123}"));
	}


}

