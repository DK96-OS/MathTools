package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [FloatPairFixed] class.
 * @author DK96-OS : 2023
 */
public final class FloatPairFixedTest {

	private final float number1 = 1f;
	private final float number2 = Float.MAX_VALUE;

	@Test
	void testConstructor() {
		final DoublePairFixed pair = new DoublePairFixed(number1, number2);
		assertEquals(number1, pair.first);
		assertEquals(number2, pair.second);
	}

	private FloatPairFixed mPair;

	@BeforeEach
	void testSetup() {
		mPair = new FloatPairFixed(number1, number2);
	}

	@Test
	void testInitialCondition() {
		assertEquals(number1, mPair.first);
		assertEquals(number2, mPair.second);
	}

	@Test
	void testToMutable() {
		FloatPair pair = mPair.toMutable();
		assertEquals(mPair.first, pair.getFirst());
		assertEquals(mPair.second, pair.getSecond());
	}

	@Test
	void testEquals_SameValues_ReturnsTrue() {
		FloatPairFixed pair = new FloatPairFixed(number1, number2);
		assertEquals(mPair, pair);
	}

	@Test
	void testEquals_DifferentValues_ReturnsFalse() {
		FloatPairFixed pair = new FloatPairFixed(
			number1, (byte) (1 + number2)
		);
		assertNotEquals(mPair, pair);
		pair = new FloatPairFixed(
			(byte) (1 + number1), number2
		);
		assertNotEquals(mPair, pair);
	}

	@Test
	void testEquals_WithMutableFloatPair_ReturnsTrue() {
		FloatPair pair0 = mPair.toMutable();
		assertEquals(mPair, pair0);
	}

	@Test
	void testEquals_WithMutableFloatPairDifferentValues_ReturnsFalse() {
		FloatPair pair = new FloatPair(number1, (byte) 10);
		assertNotEquals(mPair, pair);
		pair = new FloatPair((byte) 10, number2);
		assertNotEquals(mPair, pair);
	}

	@Test
	void testEquals_UnsupportedType_ReturnsFalse() {
		String str = "";
		assertNotEquals(mPair, str);
	}

}