package mathtools.generators.elements.shorts;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import mathtools.generators.RandomProvider;

/** Testing [LinearShortElement] class
 * @author DK96-OS : 2022 */
public final class LinearShortElementTest {

	private LinearShortElement mElement;

	private static final short initialLength = 6;
	private static final short initialRate = 1;

	private static final short maxLength = 180;
	private static final short maxRate = 200;

	@BeforeEach
	public void testSetup() {
		mElement = new LinearShortElement(
			initialLength,
			initialRate
		);
	}

	@Test
	public void testConstructorInvalidArgs() {
		// Length and rate must be positive, non-zero
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement((short) 0, initialRate));
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement(initialLength, (short) 0));
		// Length and rate are limited to 200
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement((short) 201, initialRate));
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement(initialLength, (short) 201));
	}

	@Test
	public void testConstructorRandomInvalidArgs() {
		final Random rng = RandomProvider.fixedValue(1);
		// Length and rate must be positive, non-zero
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement((short) 0, initialRate, rng));
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement(initialLength, (short) 0, rng));
		// Length and rate are limited to 200
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement((short) 201, initialRate, rng));
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement(initialLength, (short) 201, rng));
	}

	@Test
	public void testConstructorInitialValues() {
		assertEquals(
			6, mElement.getLength());
		assertEquals(
			1, mElement.getRate());
	}

	@Test
	public void testConstructorMaxValues() {
		assertDoesNotThrow(
			() -> new LinearShortElement(maxLength, maxRate));
		// If either value is increased, it will throw
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement(
				(short) (maxLength + 1), maxRate
			));
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement(
				maxLength, (short) (maxRate + 1)
			));
	}

	@Test
	public void testLengthSetter() {
		assertFalse(
			mElement.setLength(initialLength));
		// Set to different value
		assertTrue(
			mElement.setLength((short) 80));
		assertEquals(
			80, mElement.getLength());
	}

	@Test
	public void testLengthSetterMaximumValue() {
		assertTrue(
			mElement.setLength(maxLength)); // Maximum accepted value
		assertEquals(
			maxLength, mElement.getLength());
	}

	@Test
	public void testLengthSetterInvalid() {
		assertFalse(
			mElement.setLength((short) 0));
		assertFalse(
			mElement.setLength((short) (maxLength + 1)));
		// Value is the same as initial
		assertEquals(
			initialLength, mElement.getLength());
	}

	@Test
	public void testRateSetter() {
		assertFalse(
			mElement.setRate(initialRate)); // Same as initial value
		// Set to different value succeeds
		assertTrue(
			mElement.setRate((short) 50));
		assertEquals(
			50, mElement.getRate());
	}

	@Test
	public void testRateSetterMaximumValue() {
		assertTrue(
			mElement.setRate(maxRate)); // Maximum accepted value
		assertEquals(
			maxRate, mElement.getRate());
	}

	@Test
	public void testRateSetterInvalid() {
		assertFalse(
			mElement.setRate((short) 0));
		assertFalse(
			mElement.setRate((short) (maxRate + 1)));
		assertEquals(
			initialRate, mElement.getRate());
	}

}