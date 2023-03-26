package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [IntPair] class.
 * @author DK96-OS : 2022
 */
public final class IntPairTest {

	private IntPair mPair0;
	private final int value0 = 0;

	@BeforeEach
	void testSetup() {
		mPair0 = new IntPair(value0, value0);
	}

	@Test
	void testSetFirst() {
		final int newValue = 8;
		mPair0.setFirst(newValue);
		assertEquals(newValue, mPair0.getFirst());
		assertEquals(value0, mPair0.getSecond());
	}

	@Test
	void testSetSecond() {
		final int newValue = 12;
		mPair0.setSecond(newValue);
		assertEquals(value0, mPair0.getFirst());
		assertEquals(newValue, mPair0.getSecond());
	}

	@Test
	void testToFixed() {
		IntPairFixed result = mPair0.toFixed();
		//Modify Pair0
		final short newValue = 20;
		mPair0.setFirst(newValue);
		mPair0.setSecond(newValue);
		// Fixed Pair is unchanged
		assertEquals(value0, result.first);
		assertEquals(value0, result.second);
	}

	@Test
	void testEquals_SameValues_ReturnsTrue() {
		IntPair pair0 = new IntPair(value0, value0);
		assertTrue(mPair0.equals(pair0));
	}

	@Test
	void testEquals_DifferentValues_ReturnsFalse() {
		IntPair pair = new IntPair(value0, (byte) 2);
		assertFalse(mPair0.equals(pair));
		pair = new IntPair((byte) 2, value0);
		assertFalse(mPair0.equals(pair));
	}

	@Test
	void testEquals_WithFixedIntPair_ReturnsTrue() {
		IntPairFixed pair0 = mPair0.toFixed();
		assertTrue(mPair0.equals(pair0));
	}

	@Test
	void testEquals_WithFixedIntPairDifferentValues_ReturnsFalse() {
		IntPairFixed pair = new IntPairFixed(value0, (byte) 1);
		assertFalse(mPair0.equals(pair));
		pair = new IntPairFixed((byte) 1, value0);
		assertFalse(mPair0.equals(pair));
	}

	@Test
	void testEquals_UnsupportedType_ReturnsFalse() {
		String str = "";
		assertFalse(mPair0.equals(str));
	}
}