package mathtools.generators.elements.shorts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [LinearShortElement] class
 * @author DK96-OS : 2022 */
public final class LinearShortElementTest {

	private LinearShortElement mElement;

	@BeforeEach
	public void testSetup() {
		mElement = new LinearShortElement((short) 6, (short) 3);
	}

	@Test
	public void testConstructorInvalidArgs() {
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement((short) 0, (short) 3));
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement((short) -1, (short) 3));
		//
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement((short) 5, (short) 0));
		assertThrows(IllegalArgumentException.class,
			() -> new LinearShortElement((short) 5, (short) -1));
	}

	@Test
	public void testConstructorInitialValues() {
		assertEquals(
			6, mElement.getLength());
		assertEquals(
			3, mElement.getRate());
	}

	@Test
	public void testLengthSetter() {
		assertTrue(
			mElement.setLength((short) 80));
		assertEquals(
			80, mElement.getLength());
		assertTrue(
			mElement.setLength((short) 1));
		assertEquals(
			1, mElement.getLength());
	}

	@Test
	public void testLengthSetterInvalid() {
		assertFalse(
			mElement.setLength((short) 0));
		assertFalse(
			mElement.setLength((short) -1));
		assertFalse(
			mElement.setLength((short) 181));
		assertEquals(
			6, mElement.getLength());
	}

	@Test
	public void testRateSetter() {
		assertTrue(
			mElement.setRate((short) 50));
		assertEquals(
			50, mElement.getRate());
		assertTrue(
			mElement.setRate((short) 1));
		assertEquals(
			1, mElement.getRate());
		assertTrue(
			mElement.setRate((short) 200));
		assertEquals(
			200, mElement.getRate());
	}

	@Test
	public void testRateSetterInvalid() {
		assertFalse(
			mElement.setRate((short) 0));
		assertFalse(
			mElement.setRate((short) -1));
		assertFalse(
			mElement.setRate((short) 201));
		assertEquals(
			3, mElement.getRate());
	}

}