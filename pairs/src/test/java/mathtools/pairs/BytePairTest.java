package mathtools.pairs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
		assertEquals(mPair0, pair0);
	}

	@Test
	void testEquals_DifferentValues_ReturnsFalse() {
		BytePair pair = new BytePair(value0, (byte) 2);
		assertNotEquals(mPair0, pair);
		pair = new BytePair((byte) 2, value0);
		assertNotEquals(mPair0, pair);
	}

	@Test
	void testEquals_WithFixedBytePair_ReturnsTrue() {
		BytePairFixed pair0 = mPair0.toFixed();
		assertEquals(mPair0, pair0);
	}

	@Test
	void testEquals_WithFixedBytePairDifferentValues_ReturnsFalse() {
		BytePairFixed pair = new BytePairFixed(value0, (byte) 1);
		assertNotEquals(mPair0, pair);
		pair = new BytePairFixed((byte) 1, value0);
		assertNotEquals(mPair0, pair);
	}

	@Test
	void testEquals_UnsupportedType_ReturnsFalse() {
		String str = "";
		assertNotEquals(mPair0, str);
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
		mPair0.setFirst((byte) 30);
		mPair0.setSecond((byte) -45);
		assertEquals(
			"(30, -45)",
			mPair0.toString()
		);
	}

}