package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Testing [ShortPairFixed] class.
 * @author DK96-OS : 2023
 */
public final class ShortPairFixedTest {

	private final short number1 = Byte.MAX_VALUE + 1;
	private final short number2 = Byte.MIN_VALUE - 1;

	@Test
	public void testConstructor() {
		final ShortPairFixed pair = new ShortPairFixed(number1, number2);
		assertEquals(
			number1, pair.first
		);
		assertEquals(
			number2, pair.second
		);
	}

}