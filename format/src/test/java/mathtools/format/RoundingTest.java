package mathtools.format;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static mathtools.format.Rounding.round;

import org.junit.jupiter.api.Test;

/** Testing [Rounding] functions
 * @author DK96-OS : 2022 */
public final class RoundingTest {

	@Test
	void testNoDecimals() {
		assertEquals(
			1f, round(1.476f, 0));
		assertEquals(
			2f, round(1.876f, 0));
		//
		assertEquals(
			1343f, round(1343.476f, 0));
		assertEquals(
			1344f, round(1343.876f, 0));
	}

	@Test
	void testOneDecimal() {
		assertEquals(
			1.9f, round(1.876f, 1));
		assertEquals(
			1.9f, round(1.856f, 1));
		assertEquals(
			1.8f, round(1.846f, 1));
	}

	@Test
	void testTwoDecimals() {
		assertEquals(
			1.88f, round(1.876f, 2));
		assertEquals(
			1.87f, round(1.874f, 2));
	}

	@Test
	void testMoreDecimals() {
		assertEquals(
			8.3f, round(8.3f, 3));
		//
		assertEquals(
			8.376f, round(8.376f, 3));
		assertEquals(
			8.377f, round(8.3765f, 3));
		//
		assertEquals(
			8.3765f, round(8.3765f, 4));
		assertEquals(
			8.3766f, round(8.37657f, 4));
	}

	@Test
	void testFiveDecimals() {
		assertEquals(
			1.87655f, round(1.87655f, 5));
		assertEquals(
			1.87655f, round(1.876551f, 5));
		assertEquals(
			1.87656f, round(1.876555f, 5));
		assertEquals(
			1.87656f, round(1.876555f, 5));
	}

}