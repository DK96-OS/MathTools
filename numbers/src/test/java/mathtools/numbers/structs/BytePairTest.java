package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/** Testing [BytePair] class
 * @author DK96-OS : 2022 */
public final class BytePairTest {

	/** Assert that actual is not null, and it's values match expectations */
	public static void assertPairEquals(
		final byte expectedFirst,
		final byte expectedSecond,
		final BytePair actual
	) {
		assertNotNull(actual);
		assertEquals(expectedFirst, actual.getFirst());
		assertEquals(expectedSecond, actual.getSecond());
	}

	@Test
	void testSetters() {
		final BytePair pair = new BytePair((byte) 0, (byte) 1);
		pair.setFirst((byte) 8);
		assertEquals(
			8, pair.getFirst());
		pair.setSecond((byte) 12);
		assertEquals(
			12, pair.getSecond());
	}

}