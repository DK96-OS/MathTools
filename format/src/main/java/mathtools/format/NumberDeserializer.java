package mathtools.format;

import com.google.common.primitives.UnsignedInteger;

import javax.annotation.Nonnull;

import mathtools.pairs.BytePair;

/** Encode Numbers from Strings and characters
 * @author DK96-OS : 2021 - 2022 */
public final class NumberDeserializer {

	/** Unpack 1st of 2 bytes from a character
	 * @param character The character to extract from
	 * @return The first Byte value that was stored in character */
	public static byte getByte1(
		final char character
	) {
		return (byte) (character >>> 8);
	}

	/** Unpack 2nd of 2 bytes from a character
	 * @param character The character to extract from
	 * @return The second Byte value that was stored in character */
	public static byte getByte2(
		final char character
	) {
		return (byte) character;
	}

	/** Extract 2 Byte from a Character
	 * @param character The character to extract from
	 * @return A BytePair containing the two bytes */
	@Nonnull
	public static BytePair getBytes(
		final char character
	) {
		return new BytePair(
			(byte) (character >>> 8),
			(byte) character
		);
	}

	/** Unpack a short integer from a character
	 * @param character The character to extract from */
	public static short getShort(
		final char character
	) {
		return (short) character;
	}

	/** Unpack a 32-bit float from two 16-bit characters
	 * @param char1 The first character containing half information
	 * @param char2 The second character containing half information
	 * @return The float that was serialized */
	public static float getFloat(
		final char char1,
		final char char2
	) {
		return Float.intBitsToFloat(
			UnsignedInteger.fromIntBits(char1)
				.plus(UnsignedInteger.fromIntBits(char2 << 16))
				.intValue()
		);
	}

	private NumberDeserializer() {}

}