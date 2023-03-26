package mathtools.numbers.strict;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing IntRange Mutable.
 * @author DK96-OS : 2022
 */
public final class IntRangeMutableTest {

	private static final int initialStart = 1;

	private static final int initialLast = 3200;

	private IntRangeMutable mRange;

	@BeforeEach
	void testSetup() {
		mRange = new IntRangeMutable(
			initialStart, initialLast
		);
	}

	@Test
	void testInitialCondition() {
		assertEquals(
			initialStart, mRange.getStartValue()
		);
		assertEquals(
			initialLast, mRange.getLastValue()
		);
	}

	@Test
	void testSingleValue() {
		final int value = 1;
		final IntRange range = new IntRangeMutable(
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
	void testReversedRange() {
		final IntRange range = new IntRangeMutable(
			Integer.MAX_VALUE, Integer.MIN_VALUE
		);
		assertEquals(
			Integer.MIN_VALUE, range.getStartValue()
		);
		assertEquals(
			Integer.MAX_VALUE, range.getLastValue()
		);
	}

	@Test
	void testSetStartAboveCurrent() {
		final int nextStart = 3;
		assertTrue(
			mRange.setStart(nextStart)
		);
		assertEquals(
			nextStart, mRange.getStartValue()
		);
	}

	@Test
	void testSetStartBelowCurrent() {
		final int nextStart = -1;
		assertTrue(
			mRange.setStart(nextStart)
		);
		assertEquals(
			nextStart, mRange.getStartValue()
		);
	}

	@Test
	void testSetStartFalse() {
		assertFalse(
			mRange.setStart(initialStart)
		);
		assertFalse(
			mRange.setStart(initialLast + 1)
		);
		assertFalse(
			mRange.setStart(Integer.MAX_VALUE)
		);
		// IntRange Properties have not changed
		assertEquals(
			initialStart, mRange.getStartValue()
		);
		assertEquals(
			initialLast, mRange.getLastValue()
		);
	}

	@Test
	void testSetLastAboveCurrent() {
		final int nextLast = initialLast + 300;
		assertTrue(
			mRange.setLast(nextLast)
		);
		assertEquals(
			nextLast, mRange.getLastValue()
		);
	}

	@Test
	void testSetLastBelowCurrent() {
		final int nextLast = initialLast - 10;
		assertTrue(
			mRange.setLast(nextLast)
		);
		assertEquals(
			nextLast, mRange.getLastValue()
		);
	}

	@Test
	void testSetLastToStart() {
		assertTrue(
			mRange.setLast(initialStart)
		);
		assertEquals(
			mRange.getStartValue(), mRange.getLastValue()
		);
	}

	@Test
	void testSetLastFalse() {
		assertFalse(
			mRange.setLast(initialLast)
		);
		assertFalse(
			mRange.setLast(initialStart - 1)
		);
		assertEquals(
			initialLast, mRange.getLastValue()
		);
	}

	@Test
	void testExpandRangeToMax() {
		assertTrue(
			mRange.expandRangeTo(Integer.MAX_VALUE)
		);
		assertEquals(
			initialStart, mRange.getStartValue()
		);
		assertEquals(
			Integer.MAX_VALUE, mRange.getLastValue()
		);
	}

	@Test
	void testExpandRangeToMin() {
		assertTrue(
			mRange.expandRangeTo(Integer.MIN_VALUE)
		);
		assertEquals(
			Integer.MIN_VALUE, mRange.getStartValue()
		);
		assertEquals(
			initialLast, mRange.getLastValue()
		);
	}

	@Test
	void testExpandRangeFalse() {
		assertFalse(
			mRange.expandRangeTo(initialStart)
		);
		assertFalse(
			mRange.expandRangeTo(initialStart + 1)
		);
		assertFalse(
			mRange.expandRangeTo(initialLast)
		);
		// IntRange Properties have not changed
		assertEquals(
			initialStart, mRange.getStartValue()
		);
		assertEquals(
			initialLast, mRange.getLastValue()
		);
	}

}