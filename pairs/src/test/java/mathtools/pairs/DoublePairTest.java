package mathtools.pairs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [DoublePair] class.
 * @author DK96-OS : 2023
 */
public final class DoublePairTest {
	
	private DoublePair mPair0;
	private final double value0 = 0;

	@BeforeEach
	void testSetup() {
		mPair0 = new DoublePair(value0, value0);
	}
	
	@Test
	void testSetters() {
		mPair0.setFirst(8.0);
		assertEquals(8.0, mPair0.getFirst(), 0f);
		assertEquals(value0, mPair0.getSecond(), 0f);
	}

	@Test
	void testSetSecond() {
		mPair0.setSecond(12.0);
		assertEquals(value0, mPair0.getFirst(), 0f);
		assertEquals(12.0, mPair0.getSecond(), 0f);
	}
	
	@Test
	void testToFixed() {
		DoublePairFixed result = mPair0.toFixed();
		//Modify Pair0
		mPair0.setFirst(20);
		mPair0.setSecond(-10);
		// Fixed Pair is unchanged
		assertEquals(value0, result.first);
		assertEquals(value0, result.second);
	}

	@Test
	void testEquals_SameValues_ReturnsTrue() {
		DoublePair pair0 = new DoublePair(value0, value0);
		assertEquals(mPair0, pair0);
	}

	@Test
	void testEquals_DifferentValues_ReturnsFalse() {
		DoublePair pair = new DoublePair(value0, (byte) 2);
		assertNotEquals(mPair0, pair);
		pair = new DoublePair((byte) 2, value0);
		assertNotEquals(mPair0, pair);
	}

	@Test
	void testEquals_WithFixedDoublePair_ReturnsTrue() {
		DoublePairFixed pair0 = mPair0.toFixed();
		assertEquals(mPair0, pair0);
	}

	@Test
	void testEquals_WithFixedDoublePairDifferentValues_ReturnsFalse() {
		DoublePairFixed pair = new DoublePairFixed(value0, (byte) 1);
		assertNotEquals(mPair0, pair);
		pair = new DoublePairFixed((byte) 1, value0);
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
			"(0.0, 0.0)",
			mPair0.toString()
		);
	}

	@Test
	void testToString_AfterSetValues() {
		mPair0.setFirst(301.5f);
		mPair0.setSecond(-450f);
		assertEquals(
			"(301.5, -450.0)",
			mPair0.toString()
		);
	}

}