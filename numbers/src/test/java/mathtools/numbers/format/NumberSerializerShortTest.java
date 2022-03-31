package mathtools.numbers.format;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Testing [NumberSerializer] and [NumberDeserializer] Short methods
 * @author DK96-OS : 2022 */
public final class NumberSerializerShortTest {

	@Test
	public void testSmallShortConversion() {
		short input = -5;
		//
		for (; 5 >= input; ++input) {
			//
			final char c0 = NumberSerializer.putShort(input);
			assertEquals(
				input,
				NumberDeserializer.getShort(c0)
			);
		}
	}

	@Test
	public void testPositiveShortConversion() {
		short input = Short.MAX_VALUE;
		//
		for (; 5000 < input; input -= 50) {
			//
			final char c0 = NumberSerializer.putShort(input);
			assertEquals(
				input,
				NumberDeserializer.getShort(c0)
			);
		}
	}

	@Test
	public void testNegativeShortConversion() {
		short input = Short.MIN_VALUE;
		//
		for (; -5000 > input; input += 50) {
			//
			final char c0 = NumberSerializer.putShort(input);
			assertEquals(
				input,
				NumberDeserializer.getShort(c0)
			);
		}
	}

}