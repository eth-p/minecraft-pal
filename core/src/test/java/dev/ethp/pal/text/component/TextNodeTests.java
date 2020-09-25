package dev.ethp.pal.text.component;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import dev.ethp.pal.client.Client;
import dev.ethp.pal.text.Color;
import org.junit.jupiter.api.Test;
import static dev.ethp.pal.text.Color.*;
import static dev.ethp.pal.text.Formatting.*;
import static dev.ethp.pal.text.component.TextNodeAssert.assertThat;

public class TextNodeTests {

	static private class ImplNode extends TextNode {
		public ImplNode(Color color, Combined style) {
			super(color, style);
		}
	}

	/**
	 * Test that the constructors works.
	 */
	@Test
	void testConstructors() {
		assertThat(new ImplNode(null, null))
				.isNoColor()
				.isNoStyle();

		assertThat(new ImplNode(RED, null))
				.isColor(RED)
				.isNoStyle();

		assertThat(new ImplNode(GREEN, new Combined(BOLD)))
				.isColor(GREEN)
				.isStyle(BOLD);

		assertThat(new ImplNode(RED, new Combined(BOLD, ITALIC)))
				.isColor(RED)
				.isStyle(BOLD, ITALIC);

		assertThat(new ImplNode(null, new Combined(BOLD, ITALIC)))
				.isNoColor()
				.isStyle(BOLD, ITALIC);
	}

	/**
	 * Test that the {@link TextNode#toLegacyString()} function works.
	 */
	@Test
	void testLegacyString() {
		assertThat(new ImplNode(RED, new Combined(BOLD, ITALIC)))
				.isLegacyStringEqualTo("\u00A7c\u00A7l\u00A7o")
				.isLegacyStringWithResolverEqualTo(null, "\u00A7c\u00A7l\u00A7o");

		assertThat(new ImplNode(rgb(0x102110), null))
				.isLegacyStringEqualTo("\u00A70")
				.isLegacyStringWithResolverEqualTo(null, "\u00A70");
	}
	
	/**
	 * Test that the {@link TextNode#toString()} function works.
	 */
	@Test
	void testString() {
		assertThat(new ImplNode(RED, new Combined(BOLD, ITALIC)))
				.isStringEqualTo("")
				.isStringWithResolverEqualTo(null, "");
	}

	/**
	 * Test that the {@link TextNode#toJson()} function works.
	 */
	@Test
	void testJson() {
		assertThat(new ImplNode(null, null))
				.isJsonEqualTo(() -> JsonNull.INSTANCE);

		// Color: Legacy
		assertThat(new ImplNode(RED, null))
				.isJsonEqualTo(() -> {
					JsonObject obj = new JsonObject();
					obj.add("color", new JsonPrimitive("red"));
					return obj;
				});

		// Color: RGB
		assertThat(new ImplNode(rgb(0xFF0000), null))
				.isJsonWithClientEqualTo(Client.DEFAULT_1_16_0, () -> {
					JsonObject obj = new JsonObject();
					obj.add("color", new JsonPrimitive("#ff0000"));
					return obj;
				});

		// Color: RGB -> Legacy
		assertThat(new ImplNode(rgb(0xFF0000), null))
				.isJsonWithClientEqualTo(Client.DEFAULT_1_15_0, () -> {
					JsonObject obj = new JsonObject();
					obj.add("color", new JsonPrimitive("red"));
					return obj;
				});

		// Styles: None
		assertThat(new ImplNode(null, new Combined()))
				.isJsonEqualTo(JsonObject::new);

		// Styles: Some
		assertThat(new ImplNode(null, new Combined(BOLD, RESET)))
				.isJsonEqualTo(() -> {
					JsonObject obj = new JsonObject();
					obj.add("bold", new JsonPrimitive(true));
					obj.add("reset", new JsonPrimitive(true));
					return obj;
				});

		// Styles: All
		assertThat(new ImplNode(null, new Combined(BOLD, RESET, UNDERLINED, STRIKETHROUGH, OBFUSCATED, ITALIC)))
				.isJsonEqualTo(() -> {
					JsonObject obj = new JsonObject();
					obj.add("bold", new JsonPrimitive(true));
					obj.add("reset", new JsonPrimitive(true));
					obj.add("underlined", new JsonPrimitive(true));
					obj.add("strikethrough", new JsonPrimitive(true));
					obj.add("obfuscated", new JsonPrimitive(true));
					obj.add("italic", new JsonPrimitive(true));
					return obj;
				});
	}


}

