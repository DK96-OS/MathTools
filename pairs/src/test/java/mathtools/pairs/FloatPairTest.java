package mathtools.pairs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [FloatPair] class.
 * @author DK96-OS : 2023
 */
public final class FloatPairTest {

	private FloatPair mPair0;
	private final float value0 = 0;

	@BeforeEach
	void testSetup() {
		mPair0 = new FloatPair(value0, value0);
	}

	@Test
	void testSetFirst() {
		mPair0.setFirst(8f);
		assertEquals(8f, mPair0.getFirst(), 0f);
		assertEquals(value0, mPair0.getSecond(), 0f);
	}

	@Test
	void testSetSecond() {
		mPair0.setSecond(12f);
		assertEquals(value0, mPair0.getFirst(), 0f);
		assertEquals(12f, mPair0.getSecond(), 0f);
	}

	@Test
	void testToFixed() {
		FloatPairFixed result = mPair0.toFixed();
		//Modify Pair0
		mPair0.setFirst(20f);
		mPair0.setSecond(-10f);
		// Fixed Pair is unchanged
		assertEquals(value0, result.first);
		assertEquals(value0, result.second);
	}

	@Test
	void testEquals_SameValues_ReturnsTrue() {
		FloatPair pair0 = new FloatPair(value0, value0);
		assertEquals(mPair0, pair0);
	}

	@Test
	void testEquals_DifferentValues_ReturnsFalse() {
		FloatPair pair = new FloatPair(value0, (byte) 2);
		assertNotEquals(mPair0, pair);
		pair = new FloatPair((byte) 2, value0);
		assertNotEquals(mPair0, pair);
	}

	@Test
	void testEquals_WithFixedFloatPair_ReturnsTrue() {
		FloatPairFixed pair0 = mPair0.toFixed();
		assertEquals(mPair0, pair0);
	}

	@Test
	void testEquals_WithFixedFloatPairDifferentValues_ReturnsFalse() {
		FloatPairFixed pair = new FloatPairFixed(value0, (byte) 1);
		assertNotEquals(mPair0, pair);
		pair = new FloatPairFixed((byte) 1, value0);
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