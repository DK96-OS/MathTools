package mathtools.numbers.strict;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Testing ShortRange Fixed.
 * @author DK96-OS : 2022
 */
public final class ShortRangeFixedTest {

	@Test
	public void testSimpleValues() {
		final short start = 0;
		final short last = 1;
		final ShortRange range = new ShortRangeFixed(start, last);
		assertEquals(start, range.getStartValue());
		assertEquals(last, range.getLastValue());
		// Test Contains
		assertTrue(range.contains(start));
		assertTrue(range.contains(last));
		// Test Size
		assertEquals(2, range.getSize());
	}

	@Test
	public void testSingleValue() {
		final short value = 1;
		final ShortRange range = new ShortRangeFixed(value, value);
		assertEquals(value, range.getStartValue());
		assertEquals(value, range.getLastValue());
		// Test Contains
		assertTrue(range.contains(value));
		assertEquals(1, range.getSize());
	}

	@Test
	public void testReversedRange() {
		final ShortRange range = new ShortRangeFixed(
			Short.MAX_VALUE, Short.MIN_VALUE
		);
		assertEquals(
			Short.MIN_VALUE, range.getStartValue()
		);
		assertEquals(
			Short.MAX_VALUE, range.getLastValue()
		);
		assertTrue(range.contains(Short.MIN_VALUE));
		assertTrue(range.contains((short) 0));
		assertTrue(range.contains(Short.MAX_VALUE));
	}

	@Test
	public void testContains_ReturnsFalse() {
		final short start = 20;
		final short last = 40;
		final ShortRange range = new ShortRangeFixed(
			start, last
		);
		assertFalse(range.contains((short) 0));
		assertFalse(range.contains((short) 41));
		assertFalse(range.contains(Short.MAX_VALUE));
	}

}