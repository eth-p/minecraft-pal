package dev.ethp.pal.chat;

import org.junit.jupiter.api.Test;
import static dev.ethp.pal.chat.asserts.ColorAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class ColorTests {

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
	}


}

