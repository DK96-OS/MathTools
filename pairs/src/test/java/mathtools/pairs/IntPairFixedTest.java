package mathtools.pairs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [IntPairFixed] class.
 * @author DK96-OS : 2023
 */
public final class IntPairFixedTest {

	private final int number1 = 1;
	private final int number2 = 2;

	private IntPairFixed mPair;

	@BeforeEach
	void testSetup() {
		mPair = new IntPairFixed(number1, number2);
	}

	@Test
	void testInitialCondition() {
		assertEquals(number1, mPair.first);
		assertEquals(number2, mPair.second);
	}

	@Test
	void testToMutable() {
		IntPair pair = mPair.toMutable();
		assertEquals(mPair.first, pair.getFirst());
		assertEquals(mPair.second, pair.getSecond());
	}

	@Test
	void testEquals_SameValues_ReturnsTrue() {
		IntPairFixed pair = new IntPairFixed(number1, number2);
		assertEquals(mPair, pair);
	}

	@Test
	void testEquals_DifferentValues_ReturnsFalse() {
		IntPairFixed pair = new IntPairFixed(
			number1, (byte) (1 + number2)
		);
		assertNotEquals(mPair, pair);
		pair = new IntPairFixed(
			(byte) (1 + number1), number2
		);
		assertNotEquals(mPair, pair);
	}

	@Test
	void testEquals_WithMutableIntPair_ReturnsTrue() {
		IntPair pair0 = mPair.toMutable();
		assertEquals(mPair, pair0);
	}

	@Test
	void testEquals_WithMutableIntPairDifferentValues_ReturnsFalse() {
		IntPair pair = new IntPair(number1, (byte) 10);
		assertNotEquals(mPair, pair);
		pair = new IntPair((byte) 10, number2);
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
			"(1, 2)",
			mPair.toString()
		);
	}

}