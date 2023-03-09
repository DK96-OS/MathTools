package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Testing [FloatPair] class.
 * @author DK96-OS : 2023
 */
public final class FloatPairTest {

	@Test
	void testSetters() {
		final FloatPair pair = new FloatPair(0f, 1f);
		pair.setFirst(8f);
		assertEquals(
			8f, pair.getFirst(), 0f);
		pair.setSecond(12f);
		assertEquals(
			12f, pair.getSecond(), 0f);
	}

}