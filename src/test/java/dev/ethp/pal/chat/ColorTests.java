package dev.ethp.pal.chat;

import org.junit.jupiter.api.Test;
import static dev.ethp.pal.chat.asserts.ColorAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class ColorTests {

	/**
	 * Test that color variants are the same.
	 */
	@Test
	void variants() {
		assertThat(Color.LIGHT_PURPLE).isEqualTo(Color.PINK);
		assertThat(Color.LIGHT_PURPLE).isEqualToIdentity(Color.PINK);
		assertThat(Color.nameUnsafe("PINK")).isEqualTo(Color.PINK);
	}

	/**
	 * Test that color identities are the same.
	 * This only checks the color constants.
	 */
	@Test
	void identity() {
		assertThat(Color.BLACK).isEqualToIdentity(Color.BLACK);
		assertThat(Color.DARK_BLUE).isEqualToIdentity(Color.DARK_BLUE);
		assertThat(Color.DARK_GREEN).isEqualToIdentity(Color.DARK_GREEN);
		assertThat(Color.DARK_AQUA).isEqualToIdentity(Color.DARK_AQUA);
		assertThat(Color.DARK_RED).isEqualToIdentity(Color.DARK_RED);
		assertThat(Color.DARK_PURPLE).isEqualToIdentity(Color.DARK_PURPLE);
		assertThat(Color.GOLD).isEqualToIdentity(Color.GOLD);
		assertThat(Color.GRAY).isEqualToIdentity(Color.GRAY);
		assertThat(Color.DARK_GRAY).isEqualToIdentity(Color.DARK_GRAY);
		assertThat(Color.BLUE).isEqualToIdentity(Color.BLUE);
		assertThat(Color.GREEN).isEqualToIdentity(Color.GREEN);
		assertThat(Color.AQUA).isEqualToIdentity(Color.AQUA);
		assertThat(Color.RED).isEqualToIdentity(Color.RED);
		assertThat(Color.LIGHT_PURPLE).isEqualToIdentity(Color.LIGHT_PURPLE);
		assertThat(Color.YELLOW).isEqualToIdentity(Color.YELLOW);
		assertThat(Color.WHITE).isEqualToIdentity(Color.WHITE);
	}

	/**
	 * Test that {@link Color#rgb(int)} generates the correct RGB value.
	 */
	@Test
	void rgb() {
		assertThat(Color.rgb(0xF3E4D5)).hasRgb(0xF3E4D5);
		assertThat(Color.rgb(0xBBF3E4D5)).hasRgb(0xF3E4D5);
	}

	/**
	 * Test that {@link Color#rgb(String)} parses the correct RGB value.
	 */
	@Test
	void rgbString() {
		assertThat(Color.rgbUnsafe("#f3e4d5")).hasRgb(0xF3E4D5);
		assertThat(Color.rgbUnsafe("#fed")).hasRgb(0xFFEEDD);

		assertThat(Color.rgb("#f3e4d")).isEmpty();
		assertThat(Color.rgb("#f")).isEmpty();
		assertThat(Color.rgb("fff")).isEmpty();
		assertThat(Color.rgb("ffffff")).isEmpty();
	}

	/**
	 * Test that Color objects have the correct corresponding code.
	 */
	@Test
	void codes() {
		assertThat(Color.BLACK).isLegacy().hasCode('0');
		assertThat(Color.DARK_BLUE).isLegacy().hasCode('1');
		assertThat(Color.DARK_GREEN).isLegacy().hasCode('2');
		assertThat(Color.DARK_AQUA).isLegacy().hasCode('3');
		assertThat(Color.DARK_RED).isLegacy().hasCode('4');
		assertThat(Color.DARK_PURPLE).isLegacy().hasCode('5');
		assertThat(Color.GOLD).isLegacy().hasCode('6');
		assertThat(Color.GRAY).isLegacy().hasCode('7');
		assertThat(Color.DARK_GRAY).isLegacy().hasCode('8');
		assertThat(Color.BLUE).isLegacy().hasCode('9');
		assertThat(Color.GREEN).isLegacy().hasCode('a');
		assertThat(Color.AQUA).isLegacy().hasCode('b');
		assertThat(Color.RED).isLegacy().hasCode('c');
		assertThat(Color.LIGHT_PURPLE).isLegacy().hasCode('d');
		assertThat(Color.YELLOW).isLegacy().hasCode('e');
		assertThat(Color.WHITE).isLegacy().hasCode('f');
	}

	/**
	 * Test that color code lookup is working as expected.
	 */
	@Test
	void codesLookup() {
		assertThat(Color.codeUnsafe('0')).isEqualTo(Color.BLACK);
		assertThat(Color.codeUnsafe('1')).isEqualTo(Color.DARK_BLUE);
		assertThat(Color.codeUnsafe('2')).isEqualTo(Color.DARK_GREEN);
		assertThat(Color.codeUnsafe('3')).isEqualTo(Color.DARK_AQUA);
		assertThat(Color.codeUnsafe('4')).isEqualTo(Color.DARK_RED);
		assertThat(Color.codeUnsafe('5')).isEqualTo(Color.DARK_PURPLE);
		assertThat(Color.codeUnsafe('6')).isEqualTo(Color.GOLD);
		assertThat(Color.codeUnsafe('7')).isEqualTo(Color.GRAY);
		assertThat(Color.codeUnsafe('8')).isEqualTo(Color.DARK_GRAY);
		assertThat(Color.codeUnsafe('9')).isEqualTo(Color.BLUE);
		assertThat(Color.codeUnsafe('a')).isEqualTo(Color.GREEN);
		assertThat(Color.codeUnsafe('b')).isEqualTo(Color.AQUA);
		assertThat(Color.codeUnsafe('c')).isEqualTo(Color.RED);
		assertThat(Color.codeUnsafe('d')).isEqualTo(Color.LIGHT_PURPLE);
		assertThat(Color.codeUnsafe('e')).isEqualTo(Color.YELLOW);
		assertThat(Color.codeUnsafe('f')).isEqualTo(Color.WHITE);
		assertThat(Color.code('k')).isEmpty();
		assertThat(Color.code('l')).isEmpty();
		assertThat(Color.code('m')).isEmpty();
		assertThat(Color.code('n')).isEmpty();
		assertThat(Color.code('o')).isEmpty();
		assertThat(Color.code(' ')).isEmpty();

		// Indices outside the lookup table.
		assertThat(Color.code('/')).isEmpty(); // '0' - 1
		assertThat(Color.code(':')).isEmpty(); // '9' + 1
		assertThat(Color.code('`')).isEmpty(); // 'a' - 1
		assertThat(Color.code('g')).isEmpty(); // 'f' + 1
	}

	/**
	 * Test that color name lookup is working as expected.
	 */
	@Test
	void nameLookup() {
		assertThat(Color.nameUnsafe("BLACK")).isEqualTo(Color.BLACK);
		assertThat(Color.nameUnsafe("DARK_BLUE")).isEqualTo(Color.DARK_BLUE);
		assertThat(Color.nameUnsafe("DARK_GREEN")).isEqualTo(Color.DARK_GREEN);
		assertThat(Color.nameUnsafe("DARK_AQUA")).isEqualTo(Color.DARK_AQUA);
		assertThat(Color.nameUnsafe("DARK_RED")).isEqualTo(Color.DARK_RED);
		assertThat(Color.nameUnsafe("DARK_PURPLE")).isEqualTo(Color.DARK_PURPLE);
		assertThat(Color.nameUnsafe("GOLD")).isEqualTo(Color.GOLD);
		assertThat(Color.nameUnsafe("GRAY")).isEqualTo(Color.GRAY);
		assertThat(Color.nameUnsafe("DARK_GRAY")).isEqualTo(Color.DARK_GRAY);
		assertThat(Color.nameUnsafe("BLUE")).isEqualTo(Color.BLUE);
		assertThat(Color.nameUnsafe("GREEN")).isEqualTo(Color.GREEN);
		assertThat(Color.nameUnsafe("AQUA")).isEqualTo(Color.AQUA);
		assertThat(Color.nameUnsafe("RED")).isEqualTo(Color.RED);
		assertThat(Color.nameUnsafe("LIGHT_PURPLE")).isEqualTo(Color.LIGHT_PURPLE);
		assertThat(Color.nameUnsafe("YELLOW")).isEqualTo(Color.YELLOW);
		assertThat(Color.nameUnsafe("WHITE")).isEqualTo(Color.WHITE);
		
		// Name variants.
		assertThat(Color.nameUnsafe("PINK")).isEqualTo(Color.PINK);
		
		// Case variants.
		assertThat(Color.nameUnsafe("black")).isEqualTo(Color.BLACK);
		assertThat(Color.nameUnsafe("bLaCk")).isEqualTo(Color.BLACK);
		assertThat(Color.nameUnsafe("LIGHT PURPLE")).isEqualTo(Color.LIGHT_PURPLE);

		// Values outside the lookup table.
		assertThat(Color.name("dark_black")).isEmpty();
		assertThat(Color.name("")).isEmpty();
		assertThat(Color.name("\0")).isEmpty();
	}

	/**
	 * Test that the values list contains all values.
	 */
	@Test
	void valuesList() {
		assertThat(Color.values())
				.contains(Color.BLACK)
				.contains(Color.DARK_BLUE)
				.contains(Color.DARK_GREEN)
				.contains(Color.DARK_AQUA)
				.contains(Color.DARK_RED)
				.contains(Color.DARK_PURPLE)
				.contains(Color.GOLD)
				.contains(Color.GRAY)
				.contains(Color.DARK_GRAY)
				.contains(Color.BLUE)
				.contains(Color.GREEN)
				.contains(Color.AQUA)
				.contains(Color.RED)
				.contains(Color.LIGHT_PURPLE)
				.contains(Color.YELLOW)
				.contains(Color.WHITE)
				.hasSize(16);
	}

	/**
	 * Test that the {@link Color#toLegacyString()} function works.
	 */
	void legacyString() {
		assertThat(Color.BLACK.toLegacyString())
				.isEqualTo("\u00A70");
	}

	/**
	 * Test that RGB to legacy code color quantization is working as expected.
	 */
	@Test
	void codesQuantized() {
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

