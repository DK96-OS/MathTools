package mathtools.numbers.format;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static mathtools.numbers.format.NumberDeserializer.getFloat;
import static mathtools.numbers.format.NumberSerializer.putFloat;
import static mathtools.numbers.format.NumberSerializer.putFloats;

import org.junit.jupiter.api.Test;

/** Testing [NumberSerializer] and [NumberDeserializer] float methods
 * @author DK96-OS : 2022 */
public final class NumberSerializerFloatTest {

	static final float[] ARRAY = new float[0];

	@Test
	void testFloatConversions() {
		for (
			int e = 0; 128 > e; ++e
		) {
			final int exp = e << 23;
			for (int m = 0; 23 > m; ++m) {
				final int mBit = 1 << m;
				final float f = Float.intBitsToFloat(
					exp + mBit
				);
				final char[] serial = putFloat(f);
				assertEquals(
					2, serial.length);
				// Inverse Operation
				assertEquals(
					f, getFloat(
						serial[0], serial[1]
					));
			}
		}
	}

	@Test
	public void testPutFloatsEmpty() {
		assertNull(
			putFloats(ARRAY));
	}

	@Test
	public void testFloatList1() {
		final float[] inputArray;
		final char[] serialized;
		//
		inputArray = new float[]{
			1f, 4f, Float.MAX_VALUE, Float.MIN_VALUE
		};
		serialized = putFloats(inputArray);
		assert null != serialized;
		for (
			int index = 0;
			index < inputArray.length; ++index
		) {
			final float deserialized = getFloat(
				serialized[index * 2],
				serialized[index * 2 + 1]
			);
			assertEquals(
				inputArray[index], deserialized);
		}
	}

}