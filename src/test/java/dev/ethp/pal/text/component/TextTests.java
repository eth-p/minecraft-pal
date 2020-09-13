package dev.ethp.pal.text.component;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import dev.ethp.pal.client.Client;
import dev.ethp.pal.text.Color;
import org.junit.jupiter.api.Test;
import static dev.ethp.pal.text.Color.*;
import static dev.ethp.pal.text.Formatting.*;
import static dev.ethp.pal.text.component.asserts.TextAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class TextTests {
	
	static private class ImplText extends Text {
		public ImplText(Color color, Combined style) {
			super(color, style);
		}
	}

	/**
	 * Test that the constructors works.
	 */
	@Test
	void testConstructors() {
		assertThat(new ImplText(null, null))
				.isNoColor()
				.isNoStyle();

		assertThat(new ImplText(RED, null))
				.isColor(RED)
				.isNoStyle();
		
		assertThat(new ImplText(GREEN, new Combined(BOLD)))
				.isColor(GREEN)
				.isStyle(BOLD);
		
		assertThat(new ImplText(RED, new Combined(BOLD, ITALIC)))
				.isColor(RED)
				.isStyle(BOLD, ITALIC);

		assertThat(new ImplText(null, new Combined(BOLD, ITALIC)))
				.isNoColor()
				.isStyle(BOLD, ITALIC);
	}

	/**
	 * Test that the {@link Text#toLegacyString()} function works.
	 */
	@Test
	void testLegacyString() {
		assertThat(new ImplText(RED, new Combined(BOLD, ITALIC)).toLegacyString())
				.isEqualTo("\u00A7c\u00A7l\u00A7o");

		assertThat(new ImplText(rgb(0x102110), null).toLegacyString())
				.isEqualTo("\u00A70");
	}

	/**
	 * Test that the {@link Text#toJson()} function works.
	 */
	@Test
	void testJson() {
		assertThat(new ImplText(null, null))
				.isJsonEqualTo(null, () -> JsonNull.INSTANCE);

		// Color: Legacy
		assertThat(new ImplText(RED, null))
				.isJsonEqualTo(null, () -> {
					JsonObject obj = new JsonObject();
					obj.add("color", new JsonPrimitive("red"));
					return obj;
				});

		// Color: RGB
		assertThat(new ImplText(rgb(0xFF0000), null))
				.isJsonEqualTo(Client.DEFAULT_1_16_0, () -> {
					JsonObject obj = new JsonObject();
					obj.add("color", new JsonPrimitive("#ff0000"));
					return obj;
				});

		// Color: RGB -> Legacy
		assertThat(new ImplText(rgb(0xFF0000), null))
				.isJsonEqualTo(Client.DEFAULT_1_15_0, () -> {
					JsonObject obj = new JsonObject();
					obj.add("color", new JsonPrimitive("red"));
					return obj;
				});

		// Styles: None
		assertThat(new ImplText(null, new Combined()))
				.isJsonEqualTo(null, JsonObject::new);

		// Styles: Some
		assertThat(new ImplText(null, new Combined(BOLD, RESET)))
				.isJsonEqualTo(null, () -> {
					JsonObject obj = new JsonObject();
					obj.add("bold", new JsonPrimitive(true));
					obj.add("reset", new JsonPrimitive(true));
					return obj;
				});

		// Styles: All
		assertThat(new ImplText(null, new Combined(BOLD, RESET, UNDERLINED, STRIKETHROUGH, OBFUSCATED, ITALIC)))
				.isJsonEqualTo(null, () -> {
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

