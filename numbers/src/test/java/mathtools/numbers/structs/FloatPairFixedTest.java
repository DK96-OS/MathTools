package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Testing [FloatPairFixed] class.
 * @author DK96-OS : 2023
 */
public final class FloatPairFixedTest {

	private final float number1 = 1f;
	private final float number2 = Float.MAX_VALUE;

	@Test
	public void testConstructor() {
		final DoublePairFixed pair = new DoublePairFixed(number1, number2);
		assertEquals(
			number1, pair.first
		);
		assertEquals(
			number2, pair.second
		);
	}

}