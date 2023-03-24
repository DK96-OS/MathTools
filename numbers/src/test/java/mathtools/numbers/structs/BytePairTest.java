package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [BytePair] class
 * @author DK96-OS : 2022
 */
public final class BytePairTest {

	private BytePair mPair0;
	private final byte value0 = 0;

	@BeforeEach
	void testSetup() {
		mPair0 = new BytePair(value0, value0);
	}

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
	void testSetFirst() {
		mPair0.setFirst((byte) 8);
		assertEquals(8, mPair0.getFirst());
		assertEquals(value0, mPair0.getSecond());
	}

	@Test
	void testSetSecond() {
		mPair0.setSecond((byte) 12);
		assertEquals(value0, mPair0.getFirst());
		assertEquals(12, mPair0.getSecond());
	}

	@Test
	void testToFixed() {
		BytePairFixed result = mPair0.toFixed();
		//Modify Pair0
		mPair0.setFirst((byte) 20);
		mPair0.setSecond((byte) -10);
		// Fixed Pair is unchanged
		assertEquals(value0, result.first);
		assertEquals(value0, result.second);
	}

	@Test
	void testEquals_SameValues_ReturnsTrue() {
		BytePair pair0 = new BytePair(value0, value0);
		assertTrue(mPair0.equals(pair0));
	}

	@Test
	void testEquals_DifferentValues_ReturnsFalse() {
		BytePair pair = new BytePair(value0, (byte) 2);
		assertFalse(mPair0.equals(pair));
		pair = new BytePair((byte) 2, value0);
		assertFalse(mPair0.equals(pair));
	}

	@Test
	void testEquals_WithFixedBytePair_ReturnsTrue() {
		BytePairFixed pair0 = mPair0.toFixed();
		assertTrue(mPair0.equals(pair0));
	}

	@Test
	void testEquals_WithFixedBytePairDifferentValues_ReturnsFalse() {
		BytePairFixed pair = new BytePairFixed(value0, (byte) 1);
		assertFalse(mPair0.equals(pair));
		pair = new BytePairFixed((byte) 1, value0);
		assertFalse(mPair0.equals(pair));
	}

	@Test
	void testEquals_UnsupportedType_ReturnsFalse() {
		String str = "";
		assertFalse(mPair0.equals(str));
	}

}