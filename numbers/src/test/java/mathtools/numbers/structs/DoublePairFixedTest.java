package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [DoublePairFixed] class.
 * @author DK96-OS : 2023
 */
public final class DoublePairFixedTest {

	private final double number1 = 1.0;
	private final double number2 = Double.MAX_VALUE;

	private DoublePairFixed mPair;
	
	@BeforeEach
	void testSetup() {
		mPair = new DoublePairFixed(number1, number2);
	}

	@Test
	void testInitialCondition() {
		assertEquals(number1, mPair.first);
		assertEquals(number2, mPair.second);
	}

	@Test
	void testToMutable() {
		DoublePair pair = mPair.toMutable();
		assertEquals(mPair.first, pair.getFirst());
		assertEquals(mPair.second, pair.getSecond());
	}

	@Test
	void testEquals_SameValues_ReturnsTrue() {
		DoublePairFixed pair = new DoublePairFixed(number1, number2);
		assertEquals(mPair, pair);
	}

	@Test
	void testEquals_DifferentValues_ReturnsFalse() {
		DoublePairFixed pair = new DoublePairFixed(
			number1, (byte) (1 + number2)
		);
		assertNotEquals(mPair, pair);
		pair = new DoublePairFixed(
			(byte) (1 + number1), number2
		);
		assertNotEquals(mPair, pair);
	}

	@Test
	void testEquals_WithMutableDoublePair_ReturnsTrue() {
		DoublePair pair0 = mPair.toMutable();
		assertEquals(mPair, pair0);
	}

	@Test
	void testEquals_WithMutableDoublePairDifferentValues_ReturnsFalse() {
		DoublePair pair = new DoublePair(number1, (byte) 10);
		assertNotEquals(mPair, pair);
		pair = new DoublePair((byte) 10, number2);
		assertNotEquals(mPair, pair);
	}

	@Test
	void testEquals_UnsupportedType_ReturnsFalse() {
		String str = "";
		assertNotEquals(mPair, str);
	}

	@Test
	void testToString() {
		assertEquals(
			"(1.0, " + Double.MAX_VALUE + ")",
			mPair.toString()
		);
	}

}