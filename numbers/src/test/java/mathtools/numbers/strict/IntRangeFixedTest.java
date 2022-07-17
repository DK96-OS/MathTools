package mathtools.numbers.strict;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Testing IntRange Fixed.
 * @author DK96-OS : 2022
 */
public final class IntRangeFixedTest {

	@Test
	public void testSimpleValues() {
		final IntRange range = new IntRangeFixed(0, 1);
		assertEquals(
			0, range.getStartValue()
		);
		assertEquals(
			1, range.getLastValue()
		);
	}

	@Test
	public void testSingleValue() {
		final int value = 1;
		final IntRange range = new IntRangeFixed(
			value, value
		);
		assertEquals(
			value, range.getStartValue()
		);
		assertEquals(
			value, range.getLastValue()
		);
	}

	@Test
	public void testReversedRange() {
		final IntRange range = new IntRangeFixed(
			Integer.MAX_VALUE, Integer.MIN_VALUE
		);
		assertEquals(
			Integer.MIN_VALUE, range.getStartValue()
		);
		assertEquals(
			Integer.MAX_VALUE, range.getLastValue()
		);
	}

}