package dev.ethp.pal.text.component;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import dev.ethp.pal.text.Formatting;
import org.junit.jupiter.api.Test;
import static dev.ethp.pal.text.Color.GREEN;
import static dev.ethp.pal.text.Color.RED;
import static dev.ethp.pal.text.Formatting.*;
import static dev.ethp.pal.text.component.TextStringNodeAssert.assertThat;
import static dev.ethp.pal.text.placeholder.PlaceholderResolverAssert.mockThrowingResolver;

public class TextStringNodeTests {

	/**
	 * Test that the constructors works.
	 */
	@Test
	void testConstructors() {
		// TextStringNode(String)
		assertThat(new TextStringNode("Test"))
				.isText("Test")
				.isNoColor()
				.isNoStyle();

		// TextStringNode(String, Color)
		assertThat(new TextStringNode("Test 2", RED))
				.isText("Test 2")
				.isColor(RED)
				.isNoStyle();

		// TextStringNode(String, Formatting)
		assertThat(new TextStringNode("Test 3", BOLD))
				.isText("Test 3")
				.isNoColor()
				.isStyle(BOLD);

		// TextStringNode(String, Color, Formatting)
		assertThat(new TextStringNode("Test 4", RED, BOLD))
				.isText("Test 4")
				.isColor(RED)
				.isStyle(BOLD);

		// TextStringNode(String, Color, Formatting...)
		assertThat(new TextStringNode("Test 4", RED, BOLD, OBFUSCATED))
				.isText("Test 4")
				.isColor(RED)
				.isStyle(BOLD, OBFUSCATED);

		// TextStringNode(String, Formatting...)
		assertThat(new TextStringNode("Test 4", BOLD, OBFUSCATED))
				.isText("Test 4")
				.isNoColor()
				.isStyle(BOLD, OBFUSCATED);

		// TextStringNode(String, Formatting, Color)
		assertThat(new TextStringNode("Test 5", BOLD, RED))
				.isText("Test 5")
				.isColor(RED)
				.isStyle(BOLD);

		// TextStringNode(String, Formatting.Combined, Color)
		assertThat(new TextStringNode("Test 6", new Formatting.Combined(BOLD, ITALIC), RED))
				.isText("Test 6")
				.isColor(RED)
				.isStyle(BOLD, ITALIC);


		// TextStringNode(String, Color, Formatting.Combined)
		assertThat(new TextStringNode("Test 7", GREEN, new Formatting.Combined(BOLD, UNDERLINED)))
				.isText("Test 7")
				.isColor(GREEN)
				.isStyle(BOLD, UNDERLINED);
	}

	/**
	 * Test that the {@link TextStringNode#toLegacyString()} function works.
	 */
	@Test
	void testLegacyString() {
		assertThat(new TextStringNode("{Test}", RED, BOLD, ITALIC))
				.isLegacyStringEqualTo("\u00A7c\u00A7l\u00A7o{Test}")
				.isLegacyStringWithResolverEqualTo(null, "\u00A7c\u00A7l\u00A7o{Test}")
				.isLegacyStringWithResolverEqualTo(mockThrowingResolver(), "\u00A7c\u00A7l\u00A7o{Test}");
	}
	
	/**
	 * Test that the {@link TextStringNode#toString()} function works.
	 */
	@Test
	void testString() {
		assertThat(new TextStringNode("{Test}", RED, BOLD, ITALIC))
				.isStringEqualTo("{Test}")
				.isStringWithResolverEqualTo(null, "{Test}")
				.isStringWithResolverEqualTo(mockThrowingResolver(), "{Test}");
	}

	/**
	 * Test that the {@link TextStringNode#toJson()} function works.
	 */
	@Test
	void testJson() {
		assertThat(new TextStringNode("Test", RED, BOLD))
				.isJsonEqualTo(() -> {
					JsonObject obj = new JsonObject();
					obj.add("color", new JsonPrimitive("red"));
					obj.add("bold", new JsonPrimitive(true));
					obj.add("text", new JsonPrimitive("Test"));
					return obj;
				});

		assertThat(new TextStringNode("Test"))
				.isJsonEqualTo(() -> new JsonPrimitive("Test"));

		// Ensure resolving doesn't get added to TextStringNode.
		assertThat(new TextStringNode("{test}"))
				.isJsonWithResolverEqualTo(mockThrowingResolver(), () -> new JsonPrimitive("{test}"));
	}


}

