package dev.ethp.pal.text.component;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import dev.ethp.pal.text.Formatting;
import org.junit.jupiter.api.Test;
import static dev.ethp.pal.text.Color.GREEN;
import static dev.ethp.pal.text.Color.RED;
import static dev.ethp.pal.text.Formatting.*;
import static dev.ethp.pal.text.component.asserts.TextNodeAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class TextNodeTests {

	/**
	 * Test that the constructors works.
	 */
	@Test
	void testConstructors() {
		// Text(String)
		assertThat(new TextNode("Test"))
				.isText("Test")
				.isNoColor()
				.isNoStyle();

		// Text(String, Color)
		assertThat(new TextNode("Test 2", RED))
				.isText("Test 2")
				.isColor(RED)
				.isNoStyle();

		// Text(String, Formatting)
		assertThat(new TextNode("Test 3", BOLD))
				.isText("Test 3")
				.isNoColor()
				.isStyle(BOLD);

		// Text(String, Color, Formatting)
		assertThat(new TextNode("Test 4", RED, BOLD))
				.isText("Test 4")
				.isColor(RED)
				.isStyle(BOLD);

		// Text(String, Color, Formatting...)
		assertThat(new TextNode("Test 4", RED, BOLD, OBFUSCATED))
				.isText("Test 4")
				.isColor(RED)
				.isStyle(BOLD, OBFUSCATED);

		// Text(String, Formatting, Color)
		assertThat(new TextNode("Test 5", BOLD, RED))
				.isText("Test 5")
				.isColor(RED)
				.isStyle(BOLD);

		// Text(String, Formatting.Combined, Color)
		assertThat(new TextNode("Test 6", new Formatting.Combined(BOLD, ITALIC), RED))
				.isText("Test 6")
				.isColor(RED)
				.isStyle(BOLD, ITALIC);


		// Text(String, Color, Formatting.Combined)
		assertThat(new TextNode("Test 7", GREEN, new Formatting.Combined(BOLD, UNDERLINED)))
				.isText("Test 7")
				.isColor(GREEN)
				.isStyle(BOLD, UNDERLINED);
	}

	/**
	 * Test that the {@link TextNode#toLegacyString()} function works.
	 */
	@Test
	void testLegacyString() {
		assertThat(new TextNode("Test", RED, BOLD, ITALIC).toLegacyString())
				.isEqualTo("\u00A7c\u00A7l\u00A7oTest");
	}

	/**
	 * Test that the {@link TextNode#toJson()} function works.
	 */
	@Test
	void testJson() {
		assertThat(new TextNode("Test", RED, BOLD))
				.isJsonEqualTo(null, () -> {
					JsonObject obj = new JsonObject();
					obj.add("color", new JsonPrimitive("red"));
					obj.add("bold", new JsonPrimitive(true));
					obj.add("text", new JsonPrimitive("Test"));
					return obj;
				});

		assertThat(new TextNode("Test"))
				.isJsonEqualTo(null, () -> new JsonPrimitive("Test"));
	}


}

