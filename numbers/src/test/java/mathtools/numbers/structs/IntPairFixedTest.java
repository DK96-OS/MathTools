package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Testing [IntPairFixed] class.
 * @author DK96-OS : 2023
 */
public final class IntPairFixedTest {

	private final int number1 = 1;
	private final int number2 = 2;

	@Test
	public void testConstructor() {
		final IntPairFixed pair = new IntPairFixed(number1, number2);
		assertEquals(
			number1, pair.first
		);
		assertEquals(
			number2, pair.second
		);
	}

}