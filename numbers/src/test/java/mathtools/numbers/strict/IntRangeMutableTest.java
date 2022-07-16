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
	public void testSetup() {
		mRange = new IntRangeMutable(
			initialStart, initialLast
		);
	}

	@Test
	public void testInitialCondition() {
		assertEquals(
			initialStart, mRange.getStartValue()
		);
		assertEquals(
			initialLast, mRange.getLastValue()
		);
	}

	@Test
	public void testSimpleValues() {
		final IntRange range = new IntRangeMutable(0, 1);
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
	public void testReversedRange() {
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
	public void testSetStartAboveCurrent() {
		final int nextStart = 3;
		assertTrue(
			mRange.setStart(nextStart)
		);
		assertEquals(
			nextStart, mRange.getStartValue()
		);
	}

	@Test
	public void testSetStartBelowCurrent() {
		final int nextStart = -1;
		assertTrue(
			mRange.setStart(nextStart)
		);
		assertEquals(
			nextStart, mRange.getStartValue()
		);
	}

	@Test
	public void testSetStartAboveLast() {
		final int nextStart = initialLast + 30000;
		assertFalse(
			mRange.setStart(nextStart)
		);
		assertEquals(
			initialStart, mRange.getStartValue()
		);
	}

	@Test
	public void testSetLastAboveCurrent() {
		final int nextLast = initialLast + 300;
		assertTrue(
			mRange.setLast(nextLast)
		);
		assertEquals(
			nextLast, mRange.getLastValue()
		);
	}

	@Test
	public void testSetLastBelowCurrent() {
		final int nextLast = initialLast - 10;
		assertTrue(
			mRange.setLast(nextLast)
		);
		assertEquals(
			nextLast, mRange.getLastValue()
		);
	}

	@Test
	public void testSetLastBelowStart() {
		final int nextLast = initialStart - 1;
		assertFalse(
			mRange.setLast(nextLast)
		);
		assertEquals(
			initialLast, mRange.getLastValue()
		);
	}

	@Test
	public void testExpandRangeTo() {

	}

	@Test
	public void test() {
	}

}