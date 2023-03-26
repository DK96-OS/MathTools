package mathtools.numbers.format;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static mathtools.numbers.format.NumberDeserializer.getByte1;
import static mathtools.numbers.format.NumberDeserializer.getByte2;
import static mathtools.numbers.format.NumberDeserializer.getBytes;
import static mathtools.numbers.format.NumberSerializer.putBytes;

import org.junit.jupiter.api.Test;

import mathtools.numbers.structs.BytePair;

/** Testing [NumberSerializer] and [NumberDeserializer] Byte methods
 * @author DK96-OS : 2022 */
public final class NumberSerializerByteTest {

	@Test
	public void testPositiveBytes() {
		byte b1 = 0;
		byte b2;
		//
		for (; 0 <= b1; b1 += 4) {
			//
			for (b2 = 0; 0 <= b2; b2 += 3) {
				//
				final char serialized = putBytes(b1, b2);
				// Check individual byte unpacking
				assertEquals(
					b1, getByte1(serialized));
				assertEquals(
					b2, getByte2(serialized));
				// Check pair of bytes unpacked
				final BytePair deserializedPair = getBytes(serialized);
				assertPairEquals(
					b1, b2, deserializedPair
				);
			}
		}
	}

	@Test
	public void testNegativeBytes() {
		byte b1 = Byte.MIN_VALUE;
		byte b2;
		//
		for (; 0 > b1; b1 += 4) {
			//
			for (b2 = Byte.MIN_VALUE; 0 > b2; b2 += 4) {
				//
				final char serialized = putBytes(b1, b2);
				// Check individual byte unpacking
				assertEquals(
					b1, getByte1(serialized));
				assertEquals(
					b2, getByte2(serialized));
				// Check pair function
				final BytePair deserializedPair = getBytes(serialized);
				assertPairEquals(
					b1, b2, deserializedPair
				);
			}
		}
	}

	/** Assert that actual is not null, and it's values match expectations.
	 */
	static void assertPairEquals(
		final byte expectedFirst,
		final byte expectedSecond,
		final BytePair actual
	) {
		assertNotNull(actual);
		assertEquals(expectedFirst, actual.getFirst());
		assertEquals(expectedSecond, actual.getSecond());
	}

}