package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [LongPair] class.
 * @author DK96-OS : 2023
 */
public final class LongPairTest {

	private LongPair mPair0;
	private final long value0 = 0L;

	@BeforeEach
	void testSetup() {
		mPair0 = new LongPair(value0, value0);
	}

	@Test
	void testSetFirst() {
		mPair0.setFirst(8);
		assertEquals(8, mPair0.getFirst());
		assertEquals(value0, mPair0.getSecond());
	}

	@Test
	void testSetSecond() {
		mPair0.setSecond(12);
		assertEquals(value0, mPair0.getFirst());
		assertEquals(12, mPair0.getSecond());
	}

	@Test
	void testToFixed() {
		LongPairFixed result = mPair0.toFixed();
		//Modify Pair0
		mPair0.setFirst(20);
		mPair0.setSecond(-10);
		// Fixed Pair is unchanged
		assertEquals(value0, result.first);
		assertEquals(value0, result.second);
	}

	@Test
	void testEquals_SameValues_ReturnsTrue() {
		LongPair pair0 = new LongPair(value0, value0);
		assertTrue(mPair0.equals(pair0));
	}

	@Test
	void testEquals_DifferentValues_ReturnsFalse() {
		LongPair pair = new LongPair(value0, (byte) 2);
		assertFalse(mPair0.equals(pair));
		pair = new LongPair((byte) 2, value0);
		assertFalse(mPair0.equals(pair));
	}

	@Test
	void testEquals_WithFixedLongPair_ReturnsTrue() {
		LongPairFixed pair0 = mPair0.toFixed();
		assertTrue(mPair0.equals(pair0));
	}

	@Test
	void testEquals_WithFixedLongPairDifferentValues_ReturnsFalse() {
		LongPairFixed pair = new LongPairFixed(value0, (byte) 1);
		assertFalse(mPair0.equals(pair));
		pair = new LongPairFixed((byte) 1, value0);
		assertFalse(mPair0.equals(pair));
	}

	@Test
	void testEquals_UnsupportedType_ReturnsFalse() {
		String str = "";
		assertFalse(mPair0.equals(str));
	}

	@Test
	void testToString() {
		assertEquals(
			"(0, 0)",
			mPair0.toString()
		);
	}

	@Test
	void testToString_AfterSetValues() {
		mPair0.setFirst((short) 301);
		mPair0.setSecond((short) -450);
		assertEquals(
			"(301, -450)",
			mPair0.toString()
		);
	}

}