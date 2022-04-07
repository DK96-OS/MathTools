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

	private static final short maxLength = 180;

	@BeforeEach
	public void testSetup() {
		mElement = new LinearShortElement(initialLength);
	}

	@Test
	public void testConstructorInvalidArgs() {
		// Length must be positive, non-zero
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement((short) 0));
		// Length limited to 200
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement((short) 201));
	}

	@Test
	public void testConstructorRandomInvalidArgs() {
		final Random rng = RandomProvider.fixedValue(1);
		// Length must be positive, non-zero
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement((short) 0, rng));
		// Length limited to 200
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement((short) 201, rng));
	}

	@Test
	public void testConstructorInitialValues() {
		assertEquals(
			6, mElement.getLength());
	}

	@Test
	public void testConstructorMaxValues() {
		assertDoesNotThrow(
			() -> new LinearShortElement(maxLength));
		// If length is increased, it will throw
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement(
				(short) (maxLength + 1)
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

}