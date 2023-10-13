package mathtools.pairs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [ShortPairFixed] class.
 * @author DK96-OS : 2023
 */
public final class ShortPairFixedTest {

	private final short number1 = Byte.MAX_VALUE + 1;
	private final short number2 = Byte.MIN_VALUE - 1;

	private ShortPairFixed mPair;

	@BeforeEach
	void testSetup() {
		mPair = new ShortPairFixed(number1, number2);
	}

	@Test
	void testInitialCondition() {
		assertEquals(number1, mPair.first);
		assertEquals(number2, mPair.second);
	}

	@Test
	void testToMutable() {
		ShortPair pair = mPair.toMutable();
		assertEquals(mPair.first, pair.getFirst());
		assertEquals(mPair.second, pair.getSecond());
	}

	@Test
	void testEquals_SameValues_ReturnsTrue() {
		ShortPairFixed pair = new ShortPairFixed(number1, number2);
		assertEquals(mPair, pair);
	}

	@Test
	void testEquals_DifferentValues_ReturnsFalse() {
		ShortPairFixed pair = new ShortPairFixed(
			number1, (byte) (1 + number2)
		);
		assertNotEquals(mPair, pair);
		pair = new ShortPairFixed(
			(byte) (1 + number1), number2
		);
		assertNotEquals(mPair, pair);
	}

	@Test
	void testEquals_WithMutableShortPair_ReturnsTrue() {
		ShortPair pair0 = mPair.toMutable();
		assertEquals(mPair, pair0);
	}

	@Test
	void testEquals_WithMutableShortPairDifferentValues_ReturnsFalse() {
		ShortPair pair = new ShortPair(number1, (byte) 10);
		assertNotEquals(mPair, pair);
		pair = new ShortPair((byte) 10, number2);
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
			"(128, -129)",
			mPair.toString()
		);
	}

}