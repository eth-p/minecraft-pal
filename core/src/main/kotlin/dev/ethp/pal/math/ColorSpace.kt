package dev.ethp.pal.math

import java.lang.Math.pow
import kotlin.math.*


/**
 * A class for values in various color spaces and color space conversions.
 */
class ColorSpace {

	/**
	 * A RGB color value represented as a double-precision floating point triplet.
	 */
	data class RGB(val R: Double, val G: Double, val B: Double) {

		/**
		 * A RGB color value from a 00RRGGBB integer.
		 */
		constructor(rgb: Int) : this(
				((rgb shr 16) and 0xFF).toDouble() / 0xFF,
				((rgb shr 8) and 0xFF).toDouble() / 0xFF,
				((rgb shr 0) and 0xFF).toDouble() / 0xFF
		)

		/**
		 * Converts the RGB triplet to an integer.
		 * @return The corresponding integer in 00RRGGBB format.
		 */
		fun toIntRGB(): Int {
			return ((R * 0xFF).toInt() shl 16) or
					((G * 0xFF).toInt() shl 8) or
					((B * 0xFF).toInt())
		}

		/**
		 * Converts the RGB triplet to a XYZ triplet.
		 * XYZ is D65/2° standard illuminant.
		 */
		fun toXYZ(): XYZ {
			// http://www.easyrgb.com/en/math.php
			// https://en.wikipedia.org/wiki/SRGB#The_reverse_transformation
			var R = 100 * if (this.R > 0.04045) ((this.R + 0.055) / 1.055).pow(2.4) else (this.R / 12.92)
			var G = 100 * if (this.G > 0.04045) ((this.G + 0.055) / 1.055).pow(2.4) else (this.G / 12.92)
			var B = 100 * if (this.B > 0.04045) ((this.B + 0.055) / 1.055).pow(2.4) else (this.B / 12.92)

			return XYZ(
					(R * 0.41239080) + (G * 0.35758434) + (B * 0.18048079),
					(R * 0.21263901) + (G * 0.71516868) + (B * 0.07219232),
					(R * 0.01933082) + (G * 0.11919478) + (B * 0.95053215),
			)
		}

		/**
		 * Converts the RGB triplet to a LAB triplet.
		 */
		fun toLAB(): LAB {
			return this.toXYZ().toLAB(XYZ.REFERENCE_D65)
		}
	}

	data class XYZ(val X: Double, val Y: Double, val Z: Double) {

		/**
		 * Converts XYZ triplet to a LAB triplet.
		 * Assumes D65 reference (Daylight, sRGB, Adobe-RGB).
		 *
		 * @param reference The reference XYZ triplet.
		 */
		fun toLAB(reference: XYZ): LAB {
			// http://www.easyrgb.com/en/math.php
			// Divide by the reference XYZ.
			var X = this.X / reference.X
			var Y = this.Y / reference.Y
			var Z = this.Z / reference.Z

			// Convert to LAB.
			X = if (X > 0.008856) X.pow(1.0 / 3.0) else (7.787 * X) + (16.0 / 116.0)
			Y = if (Y > 0.008856) Y.pow(1.0 / 3.0) else (7.787 * Y) + (16.0 / 116.0)
			Z = if (Z > 0.008856) Z.pow(1.0 / 3.0) else (7.787 * Z) + (16.0 / 116.0)

			return LAB(
					(116.0 * Y) - 16.0,
					500.0 * (X - Y),
					200.0 * (Y - Z)
			)
		}

		// TODO: toRGB

		companion object {

			@JvmField
			val REFERENCE_D65 = XYZ(95.047, 100.000, 108.883)

		}
	}

	data class LAB(val L: Double, val a: Double, val b: Double) {

		fun delta(other: LAB): Double {
			// http://www.easyrgb.com/en/math.php
			// https://github.com/wuchubuzai/OpenIMAJ/blob/master/image/image-processing/src/main/java/org/openimaj/image/analysis/colour/CIEDE2000.java
			val KL = 1.0
			val KC = 1.0
			val KH = 1.0

			val Lmean = (this.L + other.L) / 2.0
			val C1 = sqrt((this.a * this.a) + (this.b * this.b))
			val C2 = sqrt((other.a * other.a) + (other.b * other.b))

			val Cmean = (C1 + C2) / 2.0
			
			val G = (1 - sqrt(Cmean.pow(7.0) / (Cmean.pow(7.0) + 25.0.pow(7.0)))) / 2.0

			val a1prime = this.a * (1.0 + G)
			val a2prime = other.a * (1.0 + G)

			val C1prime = sqrt((a1prime * a1prime) + (this.b * this.b))
			val C2prime = sqrt((a2prime * a2prime) + (other.b * other.b))

			val Cmeanprime = (C1prime + C2prime) / 2.0 
			
			val h1prime = atan2(this.b, a1prime) + 2 * Math.PI * if (atan2(this.b, a1prime) < 0) 1.0 else 0.0
			val h2prime = atan2(other.b, a2prime) + 2 * Math.PI * if (atan2(other.b, a2prime) < 0) 1.0 else 0.0
			val Hmeanprime = if (abs(h1prime - h2prime) > Math.PI) (h1prime + h2prime + 2 * Math.PI) / 2.0 else (h1prime + h2prime) / 2.0

			val T = 1.0 - 0.17 * cos(Hmeanprime - Math.PI / 6.0) + 0.24 * cos(2.0 * Hmeanprime) + 0.32 * cos(3.0 * Hmeanprime + Math.PI / 30.0) - 0.2 * cos(4.0 * Hmeanprime - 21.0 * Math.PI / 60.0)
			val deltahprime = if (abs(h1prime - h2prime) <= Math.PI) h2prime - h1prime else if (h2prime <= h1prime) h2prime - h1prime + 2.0 * Math.PI else h2prime - h1prime - 2.0 * Math.PI
			
			val deltaLprime: Double = other.L - this.L
			val deltaCprime = C2prime - C1prime
			val deltaHprime = 2.0 * sqrt(C1prime * C2prime) * sin(deltahprime / 2.0)

			val SL = 1.0 + 0.015 * (Lmean - 50) * (Lmean - 50) / sqrt(20 + (Lmean - 50) * (Lmean - 50))
			val SC = 1.0 + 0.045 * Cmeanprime
			val SH = 1.0 + 0.015 * Cmeanprime * T


			val deltaTheta = 30 * Math.PI / 180 * Math.exp(-((180 / Math.PI * Hmeanprime - 275) / 25) * ((180 / Math.PI * Hmeanprime - 275) / 25))
			val RC = 2 * sqrt(pow(Cmeanprime, 7.0) / (pow(Cmeanprime, 7.0) + pow(25.0, 7.0)))
			val RT = -RC * sin(2 * deltaTheta)

			return Math.sqrt(
					deltaLprime / (KL * SL) * (deltaLprime / (KL * SL)) +
							deltaCprime / (KC * SC) * (deltaCprime / (KC * SC)) +
							deltaHprime / (KH * SH) * (deltaHprime / (KH * SH)) +
							RT * (deltaCprime / (KC * SC)) * (deltaHprime / (KH * SH))
			)
		}


	}


}
