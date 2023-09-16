package mathtools.numbers.strict;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Testing ByteRange Fixed.
 * @author DK96-OS : 2022
 */
public final class ByteRangeFixedTest {

	@Test
	public void testSimpleValues() {
		final byte start = 0;
		final byte last = 1;
		final ByteRange range = new ByteRangeFixed(start, last);
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
		final byte value = 1;
		final ByteRange range = new ByteRangeFixed(value, value);
		assertEquals(value, range.getStartValue());
		assertEquals(value, range.getLastValue());
		// Test Contains
		assertTrue(range.contains(value));
		assertEquals(1, range.getSize());
	}

	@Test
	public void testReversedRange() {
		final ByteRange range = new ByteRangeFixed(
			Byte.MAX_VALUE, Byte.MIN_VALUE
		);
		assertEquals(
			Byte.MIN_VALUE, range.getStartValue()
		);
		assertEquals(
			Byte.MAX_VALUE, range.getLastValue()
		);
		assertTrue(range.contains(Byte.MIN_VALUE));
		assertTrue(range.contains((byte) 0));
		assertTrue(range.contains(Byte.MAX_VALUE));
	}

	@Test
	public void testContains_ReturnsFalse() {
		final byte start = 20;
		final byte last = 40;
		final ByteRange range = new ByteRangeFixed(
			start, last
		);
		assertFalse(range.contains((byte) 0));
		assertFalse(range.contains((byte) 41));
		assertFalse(range.contains(Byte.MAX_VALUE));
	}

}