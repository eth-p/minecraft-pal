package dev.ethp.pal.chat;

import org.junit.jupiter.api.Test;
import static dev.ethp.pal.chat.asserts.FormattingAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class FormattingTests {

	/**
	 * Test that formatting variants are the same.
	 */
	@Test
	void variants() {
		assertThat(Formatting.MAGIC).isEqualTo(Formatting.OBFUSCATED);
		assertThat(Formatting.MAGIC).isEqualToIdentity(Formatting.OBFUSCATED);
		assertThat(Formatting.nameUnsafe("MAGIC")).isEqualTo(Formatting.OBFUSCATED);
		assertThat(Formatting.UNDERLINE).isEqualTo(Formatting.UNDERLINED);
		assertThat(Formatting.UNDERLINE).isEqualToIdentity(Formatting.UNDERLINED);
		assertThat(Formatting.nameUnsafe("UNDERLINE")).isEqualTo(Formatting.UNDERLINED);
	}

	/**
	 * Test that formatting identities are the same.
	 * This only checks the formatting constants.
	 */
	@Test
	void identity() {
		assertThat(Formatting.RESET).isEqualToIdentity(Formatting.RESET);
		assertThat(Formatting.BOLD).isEqualToIdentity(Formatting.BOLD);
		assertThat(Formatting.STRIKETHROUGH).isEqualToIdentity(Formatting.STRIKETHROUGH);
		assertThat(Formatting.UNDERLINED).isEqualToIdentity(Formatting.UNDERLINED);
		assertThat(Formatting.ITALIC).isEqualToIdentity(Formatting.ITALIC);
		assertThat(Formatting.OBFUSCATED).isEqualToIdentity(Formatting.OBFUSCATED);
	}

	/**
	 * Test that Formatting objects have the correct corresponding code.
	 */
	@Test
	void codes() {
		assertThat(Formatting.RESET).hasCode('r');
		assertThat(Formatting.BOLD).hasCode('l');
		assertThat(Formatting.STRIKETHROUGH).hasCode('m');
		assertThat(Formatting.UNDERLINED).hasCode('n');
		assertThat(Formatting.ITALIC).hasCode('o');
		assertThat(Formatting.OBFUSCATED).hasCode('k');
	}

	/**
	 * Test that formatting code lookup is working as expected.
	 */
	@Test
	void codesLookup() {
		assertThat(Formatting.codeUnsafe('r')).isEqualTo(Formatting.RESET);
		assertThat(Formatting.codeUnsafe('l')).isEqualTo(Formatting.BOLD);
		assertThat(Formatting.codeUnsafe('m')).isEqualTo(Formatting.STRIKETHROUGH);
		assertThat(Formatting.codeUnsafe('n')).isEqualTo(Formatting.UNDERLINED);
		assertThat(Formatting.codeUnsafe('o')).isEqualTo(Formatting.ITALIC);
		assertThat(Formatting.codeUnsafe('k')).isEqualTo(Formatting.OBFUSCATED);
		assertThat(Formatting.code('1')).isEmpty();
		assertThat(Formatting.code('2')).isEmpty();
		assertThat(Formatting.code('a')).isEmpty();
		assertThat(Formatting.code('b')).isEmpty();
		assertThat(Formatting.code('c')).isEmpty();
		assertThat(Formatting.code('d')).isEmpty();

		// Indices outside the lookup table.
		assertThat(Formatting.code('q')).isEmpty(); // 'r' - 1
		assertThat(Formatting.code('s')).isEmpty(); // 'r' + 1
		assertThat(Formatting.code('j')).isEmpty(); // 'k' - 1
		assertThat(Formatting.code('p')).isEmpty(); // 'o' + 1
	}

	/**
	 * Test that formatting name lookup is working as expected.
	 */
	@Test
	void nameLookup() {
		assertThat(Formatting.nameUnsafe("RESET")).isEqualTo(Formatting.RESET);
		assertThat(Formatting.nameUnsafe("BOLD")).isEqualTo(Formatting.BOLD);
		assertThat(Formatting.nameUnsafe("STRIKETHROUGH")).isEqualTo(Formatting.STRIKETHROUGH);
		assertThat(Formatting.nameUnsafe("UNDERLINED")).isEqualTo(Formatting.UNDERLINED);
		assertThat(Formatting.nameUnsafe("ITALIC")).isEqualTo(Formatting.ITALIC);
		assertThat(Formatting.nameUnsafe("OBFUSCATED")).isEqualTo(Formatting.OBFUSCATED);
		
		
		// Case variants.
		assertThat(Formatting.nameUnsafe("reset")).isEqualTo(Formatting.RESET);
		assertThat(Formatting.nameUnsafe("reSet")).isEqualTo(Formatting.RESET);

		// Values outside the lookup table.
		assertThat(Formatting.name("BLACK")).isEmpty();
		assertThat(Formatting.name("")).isEmpty();
		assertThat(Formatting.name("\0")).isEmpty();
	}


}

