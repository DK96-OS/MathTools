package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [LongPairFixed] class.
 * @author DK96-OS : 2023
 */
public final class LongPairFixedTest {

	private final long number1 = Integer.MAX_VALUE + 1;
	private final long number2 = Integer.MIN_VALUE - 1;

	private LongPairFixed mPair;

	@BeforeEach
	void testSetup() {
		mPair = new LongPairFixed(number1, number2);
	}

	@Test
	void testInitialCondition() {
		assertEquals(number1, mPair.first);
		assertEquals(number2, mPair.second);
	}

	@Test
	void testToMutable() {
		LongPair pair = mPair.toMutable();
		assertEquals(mPair.first, pair.getFirst());
		assertEquals(mPair.second, pair.getSecond());
	}

	@Test
	void testEquals_SameValues_ReturnsTrue() {
		LongPairFixed pair = new LongPairFixed(number1, number2);
		assertEquals(mPair, pair);
	}

	@Test
	void testEquals_DifferentValues_ReturnsFalse() {
		LongPairFixed pair = new LongPairFixed(
			number1, (byte) (1 + number2)
		);
		assertNotEquals(mPair, pair);
		pair = new LongPairFixed(
			(byte) (1 + number1), number2
		);
		assertNotEquals(mPair, pair);
	}

	@Test
	void testEquals_WithMutableLongPair_ReturnsTrue() {
		LongPair pair0 = mPair.toMutable();
		assertEquals(mPair, pair0);
	}

	@Test
	void testEquals_WithMutableLongPairDifferentValues_ReturnsFalse() {
		LongPair pair = new LongPair(number1, (byte) 10);
		assertNotEquals(mPair, pair);
		pair = new LongPair((byte) 10, number2);
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
			"(" + number1 + ", " + number2 +")",
			mPair.toString()
		);
	}

}