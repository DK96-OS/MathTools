package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [ShortPair] class.
 * @author DK96-OS : 2023
 */
public final class ShortPairTest {

	private ShortPair mPair0;
	private final short value0 = 0;

	@BeforeEach
	void testSetup() {
		mPair0 = new ShortPair(value0, value0);
	}

	@Test
	void testSetFirst() {
		final short newValue = 8;
		mPair0.setFirst(newValue);
		assertEquals(newValue, mPair0.getFirst());
		assertEquals(value0, mPair0.getSecond());
	}

	@Test
	void testSetSecond() {
		final short newValue = 12;
		mPair0.setSecond(newValue);
		assertEquals(value0, mPair0.getFirst());
		assertEquals(newValue, mPair0.getSecond());
	}

	@Test
	void testToFixed() {
		ShortPairFixed result = mPair0.toFixed();
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
		ShortPair pair0 = new ShortPair(value0, value0);
		assertEquals(mPair0, pair0);
	}

	@Test
	void testEquals_DifferentValues_ReturnsFalse() {
		ShortPair pair = new ShortPair(value0, (byte) 2);
		assertNotEquals(mPair0, pair);
		pair = new ShortPair((byte) 2, value0);
		assertNotEquals(mPair0, pair);
	}

	@Test
	void testEquals_WithFixedShortPair_ReturnsTrue() {
		ShortPairFixed pair0 = mPair0.toFixed();
		assertEquals(mPair0, pair0);
	}

	@Test
	void testEquals_WithFixedShortPairDifferentValues_ReturnsFalse() {
		ShortPairFixed pair = new ShortPairFixed(value0, (byte) 1);
		assertNotEquals(mPair0, pair);
		pair = new ShortPairFixed((byte) 1, value0);
		assertNotEquals(mPair0, pair);
	}

	@Test
	void testEquals_UnsupportedType_ReturnsFalse() {
		String str = "";
		assertNotEquals(mPair0, str);
	}

}