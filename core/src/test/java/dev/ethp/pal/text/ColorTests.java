package dev.ethp.pal.text;

import org.junit.jupiter.api.Test;
import static dev.ethp.pal.text.asserts.ColorAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static dev.ethp.pal.text.Color.*;

public class ColorTests {

	/**
	 * Test that {@link Color} variants are the same.
	 * Variants are alternate names for certain formatting types.
	 */
	@Test
	void testVariants() {
		// LIGHT_PURPLE == PINK
		assertThat(LIGHT_PURPLE).isVariantOf(PINK);
		assertThat(Color.nameUnsafe("PINK")).isEqualTo(PINK);
	}

	/**
	 * Test that the identities of color objects are the same.
	 * This only checks the color constants.
	 */
	@Test
	void testEqualityByIdentity() {
		assertThat(BLACK).isEqualToIdentity(BLACK);
		assertThat(DARK_BLUE).isEqualToIdentity(DARK_BLUE);
		assertThat(DARK_GREEN).isEqualToIdentity(DARK_GREEN);
		assertThat(DARK_AQUA).isEqualToIdentity(DARK_AQUA);
		assertThat(DARK_RED).isEqualToIdentity(DARK_RED);
		assertThat(DARK_PURPLE).isEqualToIdentity(DARK_PURPLE);
		assertThat(GOLD).isEqualToIdentity(GOLD);
		assertThat(GRAY).isEqualToIdentity(GRAY);
		assertThat(DARK_GRAY).isEqualToIdentity(DARK_GRAY);
		assertThat(BLUE).isEqualToIdentity(BLUE);
		assertThat(GREEN).isEqualToIdentity(GREEN);
		assertThat(AQUA).isEqualToIdentity(AQUA);
		assertThat(RED).isEqualToIdentity(RED);
		assertThat(LIGHT_PURPLE).isEqualToIdentity(LIGHT_PURPLE);
		assertThat(YELLOW).isEqualToIdentity(YELLOW);
		assertThat(WHITE).isEqualToIdentity(WHITE);
	}

	/**
	 * Test that the values of color objects are the same.
	 * This only checks the color constants.
	 */
	@Test
	void testEqualityByValue() {
		assertThat(BLACK).isEqualTo(BLACK);
		assertThat(DARK_BLUE).isEqualTo(DARK_BLUE);
		assertThat(DARK_GREEN).isEqualTo(DARK_GREEN);
		assertThat(DARK_AQUA).isEqualTo(DARK_AQUA);
		assertThat(DARK_RED).isEqualTo(DARK_RED);
		assertThat(DARK_PURPLE).isEqualTo(DARK_PURPLE);
		assertThat(GOLD).isEqualTo(GOLD);
		assertThat(GRAY).isEqualTo(GRAY);
		assertThat(DARK_GRAY).isEqualTo(DARK_GRAY);
		assertThat(BLUE).isEqualTo(BLUE);
		assertThat(GREEN).isEqualTo(GREEN);
		assertThat(AQUA).isEqualTo(AQUA);
		assertThat(RED).isEqualTo(RED);
		assertThat(LIGHT_PURPLE).isEqualTo(LIGHT_PURPLE);
		assertThat(YELLOW).isEqualTo(YELLOW);
		assertThat(WHITE).isEqualTo(WHITE);
	}

	/**
	 * Test that {@link Color#rgb(int)} generates the correct RGB value.
	 */
	@Test
	void testRgb() {
		assertThat(Color.rgb(0xF3E4D5)).hasRgb(0xF3E4D5);
		assertThat(Color.rgb(0xBBF3E4D5)).hasRgb(0xF3E4D5);
	}

	/**
	 * Test that {@link Color#rgb(String)} parses the correct RGB value.
	 */
	@Test
	void testRgBString() {
		assertThat(Color.rgbUnsafe("#f3e4d5")).hasRgb(0xF3E4D5);
		assertThat(Color.rgbUnsafe("#fed")).hasRgb(0xFFEEDD);

		assertThat(Color.rgb("#f3e4d")).isEmpty();
		assertThat(Color.rgb("#f")).isEmpty();
		assertThat(Color.rgb("fff")).isEmpty();
		assertThat(Color.rgb("ffffff")).isEmpty();
	}

	/**
	 * Test that Color objects have the correct legacy formatting code.
	 * <p>
	 * BLACK         == '0'
	 * DARK_BLUE     == '1'
	 * DARK_GREEN    == '2'
	 * DARK_AQUA     == '3'
	 * DARK_RED      == '4'
	 * DARK_PURPLE   == '5'
	 * GOLD          == '6'
	 * GRAY          == '7'
	 * DARK_GRAY     == '8'
	 * BLUE          == '9'
	 * GREEN         == 'a'
	 * AQUA          == 'b'
	 * RED           == 'c'
	 * LIGHT_PURPLE  == 'd'
	 * YELLOW        == 'e'
	 * WHITE         == 'f'
	 */
	@Test
	void testLegacyCode() {
		assertThat(BLACK).isLegacy().hasCode('0');
		assertThat(DARK_BLUE).isLegacy().hasCode('1');
		assertThat(DARK_GREEN).isLegacy().hasCode('2');
		assertThat(DARK_AQUA).isLegacy().hasCode('3');
		assertThat(DARK_RED).isLegacy().hasCode('4');
		assertThat(DARK_PURPLE).isLegacy().hasCode('5');
		assertThat(GOLD).isLegacy().hasCode('6');
		assertThat(GRAY).isLegacy().hasCode('7');
		assertThat(DARK_GRAY).isLegacy().hasCode('8');
		assertThat(BLUE).isLegacy().hasCode('9');
		assertThat(GREEN).isLegacy().hasCode('a');
		assertThat(AQUA).isLegacy().hasCode('b');
		assertThat(RED).isLegacy().hasCode('c');
		assertThat(LIGHT_PURPLE).isLegacy().hasCode('d');
		assertThat(YELLOW).isLegacy().hasCode('e');
		assertThat(WHITE).isLegacy().hasCode('f');
	}
	
	/**
	 * Test that color code lookup is working as expected.
	 */
	@Test
	void testLegacyLookup() {
		assertThat(Color.codeUnsafe('0')).isEqualTo(BLACK);
		assertThat(Color.codeUnsafe('1')).isEqualTo(DARK_BLUE);
		assertThat(Color.codeUnsafe('2')).isEqualTo(DARK_GREEN);
		assertThat(Color.codeUnsafe('3')).isEqualTo(DARK_AQUA);
		assertThat(Color.codeUnsafe('4')).isEqualTo(DARK_RED);
		assertThat(Color.codeUnsafe('5')).isEqualTo(DARK_PURPLE);
		assertThat(Color.codeUnsafe('6')).isEqualTo(GOLD);
		assertThat(Color.codeUnsafe('7')).isEqualTo(GRAY);
		assertThat(Color.codeUnsafe('8')).isEqualTo(DARK_GRAY);
		assertThat(Color.codeUnsafe('9')).isEqualTo(BLUE);
		assertThat(Color.codeUnsafe('a')).isEqualTo(GREEN);
		assertThat(Color.codeUnsafe('b')).isEqualTo(AQUA);
		assertThat(Color.codeUnsafe('c')).isEqualTo(RED);
		assertThat(Color.codeUnsafe('d')).isEqualTo(LIGHT_PURPLE);
		assertThat(Color.codeUnsafe('e')).isEqualTo(YELLOW);
		assertThat(Color.codeUnsafe('f')).isEqualTo(WHITE);

		// Indices outside the lookup table. (Formatting codes)
		assertThat(Color.code('k')).isEmpty();
		assertThat(Color.code('l')).isEmpty();
		assertThat(Color.code('m')).isEmpty();
		assertThat(Color.code('n')).isEmpty();
		assertThat(Color.code('o')).isEmpty();
		assertThat(Color.code(' ')).isEmpty();

		// Indices outside the lookup table. (Range)
		assertThat(Color.code('/')).isEmpty(); // '0' - 1
		assertThat(Color.code(':')).isEmpty(); // '9' + 1
		assertThat(Color.code('`')).isEmpty(); // 'a' - 1
		assertThat(Color.code('g')).isEmpty(); // 'f' + 1
	}

	/**
	 * Test that formatting name lookup is working as expected.
	 */
	@Test
	void testNameLookup() {
		assertThat(Color.nameUnsafe("BLACK")).isEqualTo(BLACK);
		assertThat(Color.nameUnsafe("DARK_BLUE")).isEqualTo(DARK_BLUE);
		assertThat(Color.nameUnsafe("DARK_GREEN")).isEqualTo(DARK_GREEN);
		assertThat(Color.nameUnsafe("DARK_AQUA")).isEqualTo(DARK_AQUA);
		assertThat(Color.nameUnsafe("DARK_RED")).isEqualTo(DARK_RED);
		assertThat(Color.nameUnsafe("DARK_PURPLE")).isEqualTo(DARK_PURPLE);
		assertThat(Color.nameUnsafe("GOLD")).isEqualTo(GOLD);
		assertThat(Color.nameUnsafe("GRAY")).isEqualTo(GRAY);
		assertThat(Color.nameUnsafe("DARK_GRAY")).isEqualTo(DARK_GRAY);
		assertThat(Color.nameUnsafe("BLUE")).isEqualTo(BLUE);
		assertThat(Color.nameUnsafe("GREEN")).isEqualTo(GREEN);
		assertThat(Color.nameUnsafe("AQUA")).isEqualTo(AQUA);
		assertThat(Color.nameUnsafe("RED")).isEqualTo(RED);
		assertThat(Color.nameUnsafe("LIGHT_PURPLE")).isEqualTo(LIGHT_PURPLE);
		assertThat(Color.nameUnsafe("YELLOW")).isEqualTo(YELLOW);
		assertThat(Color.nameUnsafe("WHITE")).isEqualTo(WHITE);

		// Case/space insensitivity.
		assertThat(Color.nameUnsafe("black")).isEqualTo(BLACK);
		assertThat(Color.nameUnsafe("bLaCk")).isEqualTo(BLACK);
		assertThat(Color.nameUnsafe("LIGHT PURPLE")).isEqualTo(LIGHT_PURPLE);

		// Values outside the lookup table.
		assertThat(Color.name("dark_black")).isEmpty();
		assertThat(Color.name("")).isEmpty();
		assertThat(Color.name("\0")).isEmpty();
	}

	/**
	 * Test that {@link Color#values()} contains all values.
	 */
	@Test
	void testList() {
		assertThat(Color.values())
				.contains(BLACK)
				.contains(DARK_BLUE)
				.contains(DARK_GREEN)
				.contains(DARK_AQUA)
				.contains(DARK_RED)
				.contains(DARK_PURPLE)
				.contains(GOLD)
				.contains(GRAY)
				.contains(DARK_GRAY)
				.contains(BLUE)
				.contains(GREEN)
				.contains(AQUA)
				.contains(RED)
				.contains(LIGHT_PURPLE)
				.contains(YELLOW)
				.contains(WHITE)
				.hasSize(16);
	}

	/**
	 * Test that the {@link Color#toLegacyString()} function works.
	 */
	@Test
	void testLegacyString() {
		assertThat(BLACK.toLegacyString())
				.isEqualTo("\u00A70");
	}

	/**
	 * Test that the {@link Color#getName()} function works.
	 */
	@Test
	void testName() {
		assertThat(BLACK).hasName("black");
		assertThat(DARK_BLUE).hasName("dark_blue");
		assertThat(DARK_GREEN).hasName("dark_green");
		assertThat(DARK_AQUA).hasName("dark_aqua");
		assertThat(DARK_RED).hasName("dark_red");
		assertThat(DARK_PURPLE).hasName("dark_purple");
		assertThat(GOLD).hasName("gold");
		assertThat(GRAY).hasName("gray");
		assertThat(DARK_GRAY).hasName("dark_gray");
		assertThat(BLUE).hasName("blue");
		assertThat(GREEN).hasName("green");
		assertThat(AQUA).hasName("aqua");
		assertThat(RED).hasName("red");
		assertThat(LIGHT_PURPLE).hasName("light_purple");
		assertThat(YELLOW).hasName("yellow");
		assertThat(WHITE).hasName("white");

		assertThat(rgb(0xF3AB10)).hasName("#f3ab10");
	}
	
	/**
	 * Test that the {@link Color#getLegacyName()} function works.
	 */
	@Test
	void testLegacyName() {
		assertThat(BLACK).hasLegacyName("black");
		assertThat(DARK_BLUE).hasLegacyName("dark_blue");
		assertThat(DARK_GREEN).hasLegacyName("dark_green");
		assertThat(DARK_AQUA).hasLegacyName("dark_aqua");
		assertThat(DARK_RED).hasLegacyName("dark_red");
		assertThat(DARK_PURPLE).hasLegacyName("dark_purple");
		assertThat(GOLD).hasLegacyName("gold");
		assertThat(GRAY).hasLegacyName("gray");
		assertThat(DARK_GRAY).hasLegacyName("dark_gray");
		assertThat(BLUE).hasLegacyName("blue");
		assertThat(GREEN).hasLegacyName("green");
		assertThat(AQUA).hasLegacyName("aqua");
		assertThat(RED).hasLegacyName("red");
		assertThat(LIGHT_PURPLE).hasLegacyName("light_purple");
		assertThat(YELLOW).hasLegacyName("yellow");
		assertThat(WHITE).hasLegacyName("white");

		assertThat(rgb(0x131313)).hasLegacyName("black");
		assertThat(rgb(0xEAEAEA)).hasLegacyName("white");
	}
	
	/**
	 * Test that RGB to legacy code color quantization is working as expected.
	 */
	@Test
	void testQuantization() {
		assertThat(Color.rgb(0x131313)).isQuantized().hasCode('0');
		assertThat(Color.rgb(0x444444)).isQuantized().hasCode('8');
		assertThat(Color.rgb(0xEAEAEA)).isQuantized().hasCode('f');
		assertThat(Color.rgb(0xFF0000)).isQuantized().hasCode('c');
		assertThat(Color.rgb(0xFF00AA)).isQuantized().hasCode('d');
		assertThat(Color.rgb(0x123499)).isQuantized().hasCode('1');
		assertThat(Color.rgb(0x1234FF)).isQuantized().hasCode('9');
		assertThat(Color.rgb(0xCF9000)).isQuantized().hasCode('6');
	}


}

