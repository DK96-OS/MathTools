package mathtools.numbers.strict;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Testing IntRange Mutable.
 * @author DK96-OS : 2022
 */
public final class IntRangeMutableTest {

	@Test
	public void testSimpleValues() {
		IntRange range = new IntRangeMutable(0, 1);
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
		IntRange range = new IntRangeMutable(value, value);
		assertEquals(
			value, range.getStartValue()
		);
		assertEquals(
			value, range.getLastValue()
		);
	}

	@Test
	public void testReversedRange() {
		IntRange range = new IntRangeMutable(
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