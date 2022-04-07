package mathtools.generators.elements.shorts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static mathtools.generators.elements.shorts.LinearShortElement.countLinearStates;

import org.junit.jupiter.api.Test;

/** Testing [LinearShortElement] static methods
 * @author DK96-OS : 2022 */
public final class LinearShortElementStaticTest {

	@Test
	public void testCountLinearStatesNormal() {
		short i = 1;
		assertEquals(
			1, countLinearStates(i++));
		assertEquals(
			3, countLinearStates(i++));
		assertEquals(
			6, countLinearStates(i++));
		//
		int previous = 6;
		for (;
		     181 > i;
		     ++i
		) {
			final int next = countLinearStates(i);
			assertEquals(
				previous + i, next);
			previous = next;
		}
	}

	@Test
	public void testCountLinearStatesTooLarge() {
		assertEquals(
			16290, countLinearStates((short) 181));
		assertEquals(
			16290, countLinearStates((short) 200));
		assertEquals(
			16290, countLinearStates((short) 2000));
	}

	@Test
	public void testCountLinearStatesNegative() {
		assertEquals(
			0, countLinearStates((short) -1));
		assertEquals(
			0, countLinearStates((short) 0));
		assertEquals(
			0, countLinearStates((short) -180));
	}

}