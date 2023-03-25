package mathtools.numbers.strict;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing LongRangeMutable class.
 * @author DK96-OS : 2023
 */
public class LongRangeMutableTest {

	private final long start0 = 0;
	private final long last0 = 20;

	private LongRangeMutable mRange0;

	@BeforeEach
	void testSetup() {
		mRange0 = new LongRangeMutable(start0, last0);
	}

	@Test
	void testInitialCondition() {
		assertEquals(start0, mRange0.getStartValue());
		assertEquals(last0, mRange0.getLastValue());
	}

	@Test
	void testReversedValues_ConstructorFixes() {
		mRange0 = new LongRangeMutable(last0, start0);
		assertEquals(start0, mRange0.getStartValue());
		assertEquals(last0, mRange0.getLastValue());
	}

	@Test
	void testSetStart_BelowLast_ReturnsTrue() {
		long newStart = last0 - 2;
		assertTrue(mRange0.setStart(newStart));
		assertEquals(newStart, mRange0.getStartValue());
		assertEquals(last0, mRange0.getLastValue());
	}

	@Test
	void testSetStart_AboveLast_ReturnsFalse() {
		long newStart = last0 + 2;
		assertFalse(mRange0.setStart(newStart));
		assertEquals(start0, mRange0.getStartValue());
		assertEquals(last0, mRange0.getLastValue());
	}

	@Test
	void testSetStart_EqualToLast_ReturnsTrue() {
		assertTrue(mRange0.setStart(last0));
		assertEquals(last0, mRange0.getStartValue());
		assertEquals(last0, mRange0.getLastValue());
	}

	@Test
	void testSetLast_AboveStart_ReturnsTrue() {
		long newLast = start0 + 1;
		assertTrue(mRange0.setLast(newLast));
		assertEquals(start0, mRange0.getStartValue());
		assertEquals(newLast, mRange0.getLastValue());
	}

	@Test
	void testSetLast_BelowStart_ReturnsFalse() {
		long newLast = start0 - 1;
		assertFalse(mRange0.setLast(newLast));
		assertEquals(start0, mRange0.getStartValue());
		assertEquals(last0, mRange0.getLastValue());
	}

	@Test
	void testSetLast_EqualToStart_ReturnsTrue() {
		assertTrue(mRange0.setLast(start0));
		assertEquals(start0, mRange0.getStartValue());
		assertEquals(start0, mRange0.getLastValue());
	}

	@Test
	void testExpandRangeTo_BelowStart_ReturnsTrue() {
		long expandStart = start0 - 10;
		assertTrue(mRange0.expandRangeTo(expandStart));
		assertEquals(expandStart, mRange0.getStartValue());
		assertEquals(last0, mRange0.getLastValue());
		// Expand again
		expandStart -= 1000;
		assertTrue(mRange0.expandRangeTo(expandStart));
		assertEquals(expandStart, mRange0.getStartValue());
		assertEquals(last0, mRange0.getLastValue());
	}

	@Test
	void testExpandRangeTo_AboveLast_ReturnsTrue() {
		long expandLast = last0 + 10;
		assertTrue(mRange0.expandRangeTo(expandLast));
		assertEquals(expandLast, mRange0.getLastValue());
		assertEquals(start0, mRange0.getStartValue());
		// Expand again
		expandLast += 1000;
		assertTrue(mRange0.expandRangeTo(expandLast));
		assertEquals(expandLast, mRange0.getLastValue());
		assertEquals(start0, mRange0.getStartValue());
	}

	@Test
	void testExpandRangeTo_WithinRange_ReturnsFalse() {
		long expandValue = (start0 + last0) / 2;
		assertFalse(mRange0.expandRangeTo(expandValue));
		assertEquals(start0, mRange0.getStartValue());
		assertEquals(last0, mRange0.getLastValue());
	}

}