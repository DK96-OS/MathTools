package mathtools.numbers.structs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Testing [BytePairFixed] class.
 * @author DK96-OS : 2023
 */
public final class BytePairFixedTest {

	private final byte number1 = 1;
	private final byte number2 = 2;

	@Test
	public void testConstructor() {
		final BytePairFixed pair = new BytePairFixed(number1, number2);
		assertEquals(
			number1, pair.first
		);
		assertEquals(
			number2, pair.second
		);
	}

}