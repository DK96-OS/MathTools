package mathtools.numbers.strict;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.math.LongMath;

import org.junit.jupiter.api.Test;

/** Testing LongRange Fixed.
 * @author DK96-OS : 2022
 */
public final class LongRangeFixedTest {

	final long tera = LongMath.pow(10, 12);

	final long mStart = 36 * tera;
	final long mLast = 45 * tera;

	@Test
	void testSimpleValues() {
		final LongRange range = new LongRangeFixed(mStart, mLast);
		assertEquals(mStart, range.getStartValue());
		assertEquals(mLast, range.getLastValue());
		// Test Contains on this range
		byte i = 30;
		for (; i * tera < range.getStartValue(); ++i)
			assertFalse(range.contains(i * tera));
		for (; i * tera <= range.getLastValue(); ++i)
			assertTrue(range.contains(i * tera));
		for (; i < 50; ++i)
			assertFalse(range.contains(i * tera));
	}

	@Test
	void testSingleValue() {
		final long value = 2 * tera;
		final LongRange range = new LongRangeFixed(value, value);
		assertEquals(value, range.getStartValue());
		assertEquals(value, range.getLastValue());
		// Test Size method
		assertEquals(1, range.getSize());
		// Test Contains method
		assertTrue(range.contains(value));
	}

	@Test
	void testReversedRange() {
		final LongRange range = new LongRangeFixed(mLast, mStart);
		assertEquals(mStart, range.getStartValue());
		assertEquals(mLast, range.getLastValue());
		assertTrue(range.contains(mStart));
		assertTrue(range.contains(mLast));
	}

	@Test
	void testGetSizeOverflow() {
		// Test Size method
		final long start = Long.MIN_VALUE;
		final long last = Long.MAX_VALUE;
		final LongRange range = new LongRangeFixed(start, last);
		assertThrows(ArithmeticException.class, () -> {
			long size = range.getSize();
			System.out.println(size);
		});
	}

}
