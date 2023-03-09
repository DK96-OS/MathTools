package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Testing [LongPair] class.
 * @author DK96-OS : 2023
 */
public final class LongPairTest {

	@Test
	public void testSetters() {
		final LongPair pair = new LongPair(0L, Long.MAX_VALUE);
		pair.setFirst(8);
		assertEquals(8, pair.getFirst());
		pair.setSecond(12);
		assertEquals(12, pair.getSecond());
	}

}