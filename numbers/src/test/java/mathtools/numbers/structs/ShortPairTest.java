package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Testing [LongPair] class.
 * @author DK96-OS : 2023
 */
public final class ShortPairTest {

	@Test
	public void testSetters() {
		final ShortPair pair = new ShortPair((short) 0, Short.MAX_VALUE);
		short newValue = 8;
		pair.setFirst(newValue);
		assertEquals(newValue, pair.getFirst());
		newValue = 12;
		pair.setSecond(newValue);
		assertEquals(newValue, pair.getSecond());
	}

}