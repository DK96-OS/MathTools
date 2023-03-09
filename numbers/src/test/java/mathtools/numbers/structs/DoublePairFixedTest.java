package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Testing [DoublePairFixed] class.
 * @author DK96-OS : 2023
 */
public final class DoublePairFixedTest {

	private final double number1 = 1.0;
	private final double number2 = Double.MAX_VALUE;

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