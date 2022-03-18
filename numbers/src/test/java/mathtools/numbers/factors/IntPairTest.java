package mathtools.numbers.factors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public final class IntPairTest {

	/** Assert that actual is not null, and it's values match expectations */
	public static void assertPairEquals(
		final int expectedFirst,
		final int expectedSecond,
		final IntPair actual
	) {
		assertNotNull(actual);
		assertEquals(expectedFirst, actual.getFirst());
		assertEquals(expectedSecond, actual.getSecond());
	}

	@Test
	void testSetters() {
		final IntPair pair = new IntPair(0, 1);
		pair.setFirst(8);
		assertEquals(8, pair.getFirst());
		pair.setSecond(12);
		assertEquals(12, pair.getSecond());
	}

}