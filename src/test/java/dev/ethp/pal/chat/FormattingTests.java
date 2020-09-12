package dev.ethp.pal.chat;

import org.junit.jupiter.api.Test;
import static dev.ethp.pal.chat.asserts.FormattingAssert.assertThat;
import static dev.ethp.pal.chat.asserts.FormattingCombinedAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static dev.ethp.pal.chat.Formatting.*;

public class FormattingTests {

	/**
	 * Test that {@link Formatting} variants are the same.
	 * Variants are alternate names for certain formatting types.
	 */
	@Test
	void testVariants() {
		// MAGIC == OBFUSCATED
		assertThat(MAGIC).isVariantOf(OBFUSCATED);
		assertThat(Formatting.nameUnsafe("MAGIC")).isEqualTo(OBFUSCATED);

		// MAGIC == UNDERLINE
		assertThat(UNDERLINE).isVariantOf(UNDERLINED);
		assertThat(Formatting.nameUnsafe("UNDERLINE")).isEqualTo(UNDERLINED);
	}

	/**
	 * Test that the identities of formatting objects are the same.
	 * This only checks the formatting constants.
	 */
	@Test
	void testEqualityByIdentity() {
		assertThat(RESET).isEqualToIdentity(RESET);
		assertThat(BOLD).isEqualToIdentity(BOLD);
		assertThat(STRIKETHROUGH).isEqualToIdentity(STRIKETHROUGH);
		assertThat(UNDERLINED).isEqualToIdentity(UNDERLINED);
		assertThat(ITALIC).isEqualToIdentity(ITALIC);
		assertThat(OBFUSCATED).isEqualToIdentity(OBFUSCATED);
	}

	/**
	 * Test that the values of formatting objects are the same.
	 */
	@Test
	void testEqualityByValue() {
		assertThat(RESET).isEqualTo(RESET);
		assertThat(BOLD).isEqualTo(BOLD);
		assertThat(STRIKETHROUGH).isEqualTo(STRIKETHROUGH);
		assertThat(UNDERLINED).isEqualTo(UNDERLINED);
		assertThat(ITALIC).isEqualTo(ITALIC);
		assertThat(OBFUSCATED).isEqualTo(OBFUSCATED);
	}

	/**
	 * Test that Formatting objects have the correct legacy formatting code.
	 * <p>
	 * RESET         == 'r'
	 * OBFUSCATED    == 'k'
	 * BOLD          == 'l'
	 * STRIKETHROUGH == 'm'
	 * UNDERLINED    == 'n'
	 * ITALIC        == 'o'
	 */
	@Test
	void testLegacyCode() {
		assertThat(RESET).hasCode('r');
		assertThat(OBFUSCATED).hasCode('k');
		assertThat(BOLD).hasCode('l');
		assertThat(STRIKETHROUGH).hasCode('m');
		assertThat(UNDERLINED).hasCode('n');
		assertThat(ITALIC).hasCode('o');
	}

	/**
	 * Test that formatting code lookup is working as expected.
	 */
	@Test
	void testLegacyLookup() {
		assertThat(Formatting.codeUnsafe('r')).isEqualTo(RESET);
		assertThat(Formatting.codeUnsafe('k')).isEqualTo(OBFUSCATED);
		assertThat(Formatting.codeUnsafe('l')).isEqualTo(BOLD);
		assertThat(Formatting.codeUnsafe('m')).isEqualTo(STRIKETHROUGH);
		assertThat(Formatting.codeUnsafe('n')).isEqualTo(UNDERLINED);
		assertThat(Formatting.codeUnsafe('o')).isEqualTo(ITALIC);

		// Indices outside the lookup table. (Color codes)
		assertThat(Formatting.code('1')).isEmpty();
		assertThat(Formatting.code('2')).isEmpty();
		assertThat(Formatting.code('a')).isEmpty();
		assertThat(Formatting.code('b')).isEmpty();
		assertThat(Formatting.code('c')).isEmpty();
		assertThat(Formatting.code('d')).isEmpty();

		// Indices outside the lookup table. (Range)
		assertThat(Formatting.code('q')).isEmpty(); // 'r' - 1
		assertThat(Formatting.code('s')).isEmpty(); // 'r' + 1
		assertThat(Formatting.code('j')).isEmpty(); // 'k' - 1
		assertThat(Formatting.code('p')).isEmpty(); // 'o' + 1
	}

	/**
	 * Test that formatting name lookup is working as expected.
	 */
	@Test
	void testNameLookup() {
		assertThat(Formatting.nameUnsafe("RESET")).isEqualTo(RESET);
		assertThat(Formatting.nameUnsafe("BOLD")).isEqualTo(BOLD);
		assertThat(Formatting.nameUnsafe("STRIKETHROUGH")).isEqualTo(STRIKETHROUGH);
		assertThat(Formatting.nameUnsafe("UNDERLINED")).isEqualTo(UNDERLINED);
		assertThat(Formatting.nameUnsafe("ITALIC")).isEqualTo(ITALIC);
		assertThat(Formatting.nameUnsafe("OBFUSCATED")).isEqualTo(OBFUSCATED);

		// Case insensitivity.
		assertThat(Formatting.nameUnsafe("reset")).isEqualTo(Formatting.RESET);
		assertThat(Formatting.nameUnsafe("reSet")).isEqualTo(Formatting.RESET);

		// Values outside the lookup table.
		assertThat(Formatting.name("BLACK")).isEmpty();
		assertThat(Formatting.name("")).isEmpty();
		assertThat(Formatting.name("\0")).isEmpty();
	}

	/**
	 * Test that {@link Formatting#values()} contains all values.
	 */
	@Test
	void testList() {
		assertThat(Formatting.values())
				.contains(RESET)
				.contains(OBFUSCATED)
				.contains(BOLD)
				.contains(STRIKETHROUGH)
				.contains(UNDERLINED)
				.contains(ITALIC)
				.hasSize(6);
	}

	/**
	 * Test combined formatting styles using the `with` methods.
	 */
	@Test
	void testCombinedWith() {
		// Formatting.Combined(Formatting.Combined(Formatting))
		assertThat(new Formatting.Combined(BOLD))
				.isEqualTo(BOLD);

		// Formatting.Combined(Formatting.Combined(Formatting...))
		assertThat(new Formatting.Combined(BOLD, ITALIC))
				.isEqualTo(BOLD, ITALIC);

		// Formatting.with(Formatting)
		assertThat(BOLD.with(ITALIC))
				.isEqualTo(BOLD, ITALIC);

		// Formatting.with(Formatting...)
		assertThat(BOLD.with(ITALIC, RESET))
				.isEqualTo(BOLD, ITALIC, RESET);

		// Formatting.with(Formatting.Combined)
		assertThat(BOLD.with(new Formatting.Combined(ITALIC)))
				.isEqualTo(BOLD, ITALIC);

		// Formatting.Combined(Formatting)
		assertThat(new Formatting.Combined(BOLD))
				.isEqualTo(BOLD);

		// Formatting.Combined.with(Formatting)
		assertThat(new Formatting.Combined(BOLD)
				.with(ITALIC))
				.isEqualTo(BOLD, ITALIC);

		// Formatting.Combined.with(Formatting...)
		assertThat(new Formatting.Combined()
				.with(ITALIC)
				.with(BOLD))
				.isEqualTo(BOLD, ITALIC);

		// Formatting.Combined.with(Formatting.Combined)
		assertThat(new Formatting.Combined()
				.with(new Formatting.Combined(BOLD, ITALIC)))
				.isEqualTo(BOLD, ITALIC);

		// All formatting.
		assertThat(new Formatting.Combined()
				.with(BOLD, ITALIC, OBFUSCATED, RESET, STRIKETHROUGH, UNDERLINED))
				.isEqualTo(BOLD, ITALIC, OBFUSCATED, RESET, STRIKETHROUGH, UNDERLINED);
	}


	/**
	 * Test combined formatting styles using the `without` methods.
	 */
	@Test
	void testCombinedWithout() {
		Formatting.Combined ALL = new Formatting.Combined(Formatting.values().toArray(new Formatting[0]));
		
		// Formatting.Combined.without(Formatting)
		assertThat(ALL.without(RESET))
				.isEqualTo(BOLD, ITALIC, OBFUSCATED, STRIKETHROUGH, UNDERLINED);

		// Formatting.Combined.without(Formatting...)
		assertThat(ALL.without(RESET, UNDERLINED))
				.isEqualTo(BOLD, ITALIC, OBFUSCATED, STRIKETHROUGH);

		// Formatting.Combined.without(Formatting.Combined)
		assertThat(ALL.without(new Formatting.Combined(RESET, UNDERLINED)))
				.isEqualTo(BOLD, ITALIC, OBFUSCATED, STRIKETHROUGH);

		// Remove all.
		assertThat(ALL.without(ALL)).isEqualTo();
	}

	/**
	 * Test combined formatting styles using the `has` methods.
	 */
	@Test
	void testCombinedHas() {
		assertThat(new Formatting.Combined(Formatting.values().toArray(new Formatting[0])))
				.hasStyle(RESET)
				.hasStyle(BOLD)
				.hasStyle(UNDERLINED)
				.hasStyle(ITALIC)
				.hasStyle(OBFUSCATED)
				.hasStyle(STRIKETHROUGH);			

		assertThat(new Formatting.Combined())
				.withoutStyle(RESET)
				.withoutStyle(BOLD)
				.withoutStyle(UNDERLINED)
				.withoutStyle(ITALIC)
				.withoutStyle(OBFUSCATED)
				.withoutStyle(STRIKETHROUGH);
	}

	/**
	 * Test that the {@link Formatting#toLegacyString()} function works.
	 */
	@Test
	void testLegacyString() {
		assertThat(OBFUSCATED.toLegacyString())
				.isEqualTo("\u00A7k");
	}

	/**
	 * Test that the {@link Formatting.Combined#toLegacyString()} function works.
	 */
	@Test
	void testCombinedLegacyString() {
		assertThat(new Formatting.Combined(RESET, OBFUSCATED, STRIKETHROUGH, UNDERLINED, ITALIC, BOLD).toLegacyString())
				.isEqualTo("\u00A7k\u00A7k\u00A7l\u00A7m\u00A7n\u00A7o");

		assertThat(new Formatting.Combined(BOLD, ITALIC).toLegacyString())
				.isEqualTo("\u00A7l\u00A7o");
	}

}

