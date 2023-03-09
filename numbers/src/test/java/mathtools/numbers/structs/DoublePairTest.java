package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Testing [DoublePair] class.
 * @author DK96-OS : 2023
 */
public final class DoublePairTest {

	@Test
	void testSetters() {
		final DoublePair pair = new DoublePair(0.0, Double.MAX_VALUE);
		pair.setFirst(8.0);
		assertEquals(
			8.0, pair.getFirst(), 0f);
		pair.setSecond(12.0);
		assertEquals(
			12.0, pair.getSecond(), 0f);
	}

}