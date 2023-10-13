package mathtools.format;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static mathtools.format.NumberDeserializer.getFloat;
import static mathtools.format.NumberSerializer.putFloat;
import static mathtools.format.NumberSerializer.putFloats;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/** Testing [NumberSerializer] and [NumberDeserializer] float methods
 * @author DK96-OS : 2022 */
public final class NumberSerializerFloatTest {

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
		float[] ARRAY = new float[0];
		assertNull(
			putFloats(ARRAY));
	}

	@Test
	public void testPutFloatsSingleValue() {
		float value = 34.567f;
		char[] result = putFloats(new float[]{value});
		assertEquals(
			2, result.length
		);
		final float deserialized = getFloat(
			result[0], result[1]
		);
		assertEquals(
			value, deserialized
		);
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

	@ParameterizedTest
	@ValueSource(floats = {
			Float.POSITIVE_INFINITY,
			Float.NEGATIVE_INFINITY,
			Float.NaN
	})
	public void testNAN(
		final float testValue
	) {
		var serialized = putFloat(testValue);
		float deserialized = getFloat(serialized[0], serialized[1]);
		assertEquals(
			testValue, deserialized, 0f
		);
	}

}