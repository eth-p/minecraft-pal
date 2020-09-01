package dev.ethp.pal.math;

import dev.ethp.pal.math.ColorSpace.RGB;
import dev.ethp.pal.math.ColorSpace.XYZ;
import dev.ethp.pal.math.ColorSpace.LAB;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static dev.ethp.pal.math.asserts.XyzAssert.assertThat;
import static dev.ethp.pal.math.asserts.RgbAssert.assertThat;
import static dev.ethp.pal.math.asserts.LabAssert.assertThat;

public class ColorSpaceTests {

	@Test
	void rgb() {
		assertThat(new RGB(0.51, 0.22, 0.33)).hasRGB(0.51, 0.22, 0.33);
	}

	@Test
	void rgbIntToFloat() {
		assertThat(new RGB(0xFF3344)).hasRGB(1.0, 0.2, 0.2666);
	}

	@Test
	void rgbFloatToInt() {
		assertThat(new RGB(1.0, 0.2, 0.27).toIntRGB()).isEqualTo(0xFF3344);
	}

	@Test
	void rgbToXyz() {
		assertThat(new RGB(1.0, 0.0, 0.0).toXYZ()).hasXYZ(41.246, 21.267, 1.933);
		assertThat(new RGB(0.50, 0.78, 0.93).toXYZ()).hasXYZ(44.530, 51.471, 87.808);
	}

	@Test
	void xyzToLAB() {
		assertThat(new XYZ(44.530, 51.471, 87.808).toLAB(ColorSpace.XYZ.REFERENCE_D65))
				.hasLAB(76.964, -12.369, -25.878);
	}

	@Test
	void deltaE() {
		final double PRECISION = 5;

		assertThat(new LAB(20, 40, 60).delta(new LAB(20, 40, 60)))
				.describedAs("Same colors should be zero.")
				.isCloseTo(0, Offset.offset(0.0001));

		assertThat(new LAB(20, 40, 60).delta(new LAB(60, 40, 20)))
				.describedAs("Order shouldn't matter.")
				.isCloseTo(new LAB(60, 40, 20).delta(new LAB(20, 40, 60)), Offset.offset(0.0001));
		
		assertThat(new LAB(76.964, -12.369, -25.878).delta(new LAB(65.065, -21.571, -26.261)))
				.isCloseTo(12.7567, Offset.offset(PRECISION));

		assertThat(new LAB(12, 24, 36).delta(new LAB(80, 24, 12)))
				.isCloseTo( 64.1836, Offset.offset(PRECISION));
		
		assertThat(new LAB(0, 30, 52).delta(new LAB(100, -60, 29)))
				.isCloseTo( 111.2875, Offset.offset(PRECISION));
	}

}

