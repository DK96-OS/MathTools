package mathtools.numbers.format;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static mathtools.numbers.format.StringPackager.INSTANCE;

import org.junit.jupiter.api.Test;

import kotlin.Pair;

/** Testing [StringPackager] methods
 * @author DK96-OS : 2022 */
public final class StringPackagerByteTest {

	@Test
	public void testPositiveBytes() {
		byte b0 = 0;
		byte b1;
		//
		for (; 0 <= b0; b0 += 4) {
			//
			for (b1 = 0; 0 <= b1; b1 += 4) {
				//
				final char packed = INSTANCE.packBytes(b0, b1);
				// Check individual byte unpacking
				assertEquals(
					b0, INSTANCE.unpackByte0(packed));
				assertEquals(
					b1, INSTANCE.unpackByte1(packed));
				// Check pair of bytes unpacked
				final Pair<Byte, Byte> unpackedPair =
					INSTANCE.unpackBytes(packed);
				assertEquals(
					b0, unpackedPair.getFirst());
				assertEquals(
					b1, unpackedPair.getSecond());
			}
		}
	}

	@Test
	public void testNegativeBytes() {
		byte b0 = Byte.MIN_VALUE;
		byte b1;
		//
		for (; 0 > b0; b0 += 4) {
			//
			for (b1 = Byte.MIN_VALUE; 0 > b1; b1 += 4) {
				//
				final char packed = INSTANCE.packBytes(b0, b1);
				// Check individual byte unpacking
				assertEquals(
					b0, INSTANCE.unpackByte0(packed));
				assertEquals(
					b1, INSTANCE.unpackByte1(packed));
				// Check pair function
				final Pair<Byte, Byte> unpackedPair =
					INSTANCE.unpackBytes(packed);
				assertEquals(
					b0, unpackedPair.getFirst());
				assertEquals(
					b1, unpackedPair.getSecond());
			}
		}
	}

}