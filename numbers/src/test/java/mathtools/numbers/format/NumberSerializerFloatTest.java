package mathtools.numbers.format;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static mathtools.numbers.format.NumberDeserializer.getFloat;
import static mathtools.numbers.format.NumberSerializer.putFloat;

import org.junit.jupiter.api.Test;

/** Testing [NumberSerializer] and [NumberDeserializer]
 * @author DK96-OS : 2022 */
public final class NumberSerializerFloatTest {

	@Test
	void testFloat() {
		for (int e = 0; e < 128; ++e) {
			final int exp = e << 23;
			for (int m = 0; m < 23; ++m) {
				final int mBit = 1 << m;
				final float f = Float.intBitsToFloat(exp + mBit);
				final String serial = putFloat(f);
				assertEquals(
					2, serial.length());
				//
				assertEquals(
					f, getFloat(serial.charAt(0), serial.charAt(1)));
			}
		}
	}

}