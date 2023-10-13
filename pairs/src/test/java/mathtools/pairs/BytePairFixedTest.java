package mathtools.pairs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [BytePairFixed] class.
 * @author DK96-OS : 2023
 */
public final class BytePairFixedTest {

	private final byte number1 = 1;
	private final byte number2 = 2;

	private BytePairFixed mPair;

	@BeforeEach
	void testSetup() {
		mPair = new BytePairFixed(number1, number2);
	}

	@Test
	void testInitialCondition() {
		assertEquals(
			number1, mPair.first);
		assertEquals(
			number2, mPair.second);
	}

	@Test
	void testToMutable() {
		BytePair pair = mPair.toMutable();
		assertEquals(
			mPair.first, pair.getFirst());
		assertEquals(
			mPair.second, pair.getSecond());
	}

	@Test
	void testEquals_SameValues_ReturnsTrue() {
		BytePairFixed pair = new BytePairFixed(number1, number2);
		assertEquals(mPair, pair);
	}

	@Test
	void testEquals_DifferentValues_ReturnsFalse() {
		BytePairFixed pair = new BytePairFixed(
			number1, (byte) (1 + number2)
		);
		assertNotEquals(mPair, pair);
		pair = new BytePairFixed(
			(byte) (1 + number1), number2
		);
		assertNotEquals(mPair, pair);
	}

	@Test
	void testEquals_WithMutableBytePair_ReturnsTrue() {
		BytePair pair0 = mPair.toMutable();
		assertEquals(mPair, pair0);
	}

	@Test
	void testEquals_WithMutableBytePairDifferentValues_ReturnsFalse() {
		BytePair pair = new BytePair(number1, (byte) 10);
		assertNotEquals(mPair, pair);
		pair = new BytePair((byte) 10, number2);
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