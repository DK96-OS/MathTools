package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Testing [LongPairFixed] class.
 * @author DK96-OS : 2023
 */
public final class LongPairFixedTest {

	private final long number1 = Integer.MAX_VALUE + 1;
	private final long number2 = Integer.MIN_VALUE - 1;

	@Test
	public void testConstructor() {
		final LongPairFixed pair = new LongPairFixed(number1, number2);
		assertEquals(
			number1, pair.first
		);
		assertEquals(
			number2, pair.second
		);
	}

}