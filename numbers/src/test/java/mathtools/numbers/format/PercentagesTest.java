package mathtools.numbers.format;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static mathtools.numbers.format.Percentages.strictPercent;

import org.junit.jupiter.api.Test;

/** Testing Percentages functions
 * @author DK96-OS : 2022 */
public final class PercentagesTest {

	@Test
	public void testStrictPercentOneArg() {
		assertEquals(
			"1%", strictPercent(0.01f));
		assertEquals(
			"2%", strictPercent(0.02f));
		assertEquals(
			"20%", strictPercent(0.2f));
		assertEquals(
			"21%", strictPercent(0.21f));
		assertEquals(
			"30%", strictPercent(0.3f));
		assertEquals(
			"31%", strictPercent(0.31f));
	}

	@Test
	public void testStrictPercent() {
		final byte zero = 0;
		assertEquals(
			"1%", strictPercent(0.01f, zero));
		assertEquals(
			"2%", strictPercent(0.02f, zero));
		assertEquals(
			"20%", strictPercent(0.2f, zero));
		assertEquals(
			"21%", strictPercent(0.21f, zero));
		assertEquals(
			"30%", strictPercent(0.3f, zero));
		assertEquals(
			"31%", strictPercent(0.31f, zero));
	}

	@Test
	public void testStrictPercent1Decimal() {
		final byte one = 1;
		assertEquals(
			"1.0%", strictPercent(0.01f, one));
		assertEquals(
			"2.5%", strictPercent(0.025f, one));
		assertEquals(
			"20.0%", strictPercent(0.2f, one));
		assertEquals(
			"21.1%", strictPercent(0.211f, one));
		assertEquals(
			"30.3%", strictPercent(0.303f, one));
		assertEquals(
			"31.8%", strictPercent(0.3177f, one));
	}

	@Test
	public void testStrictPercent3Decimal() {
		final byte three = 3;
		assertEquals(
			"1.01%", strictPercent(0.0101f, three));
		assertEquals(
			"2.575%", strictPercent(0.02575f, three));
		assertEquals(
			"20.0%", strictPercent(0.200f, three));
		assertEquals(
			"21.111%", strictPercent(0.21111f, three));
		assertEquals(
			"30.301%", strictPercent(0.303006f, three));
		assertEquals(
			"31.778%", strictPercent(0.317777f, three));
	}

	@Test
	public void testStrictPercent7Decimal() {
		final byte seven = 7;
		// Note that 7 is higher than allowed, reduced to 5
		assertEquals(
			"1.0101%", strictPercent(0.010101f, seven));
		assertEquals(
			"2.57503%", strictPercent(0.02575025f, seven));
		assertEquals(
			"20.0%", strictPercent(0.200000f, seven));
		assertEquals(
			"21.111%", strictPercent(0.21111f, seven));
		assertEquals(
			"30.30104%", strictPercent(0.3030104f, seven));
		assertEquals(
			"31.7777%", strictPercent(0.317777f, seven));
	}

	@Test
	public void testStrictPercentOneArgInvalid() {
		assertEquals(
			"0%", strictPercent(0f));
		assertEquals(
			"0%", strictPercent(-0.01f));
		assertEquals(
			"100%", strictPercent(10f));
	}

	@Test
	public void testStrictPercentNegative() {
		final byte zero = 0;
		assertEquals(
			"0%", strictPercent(-0.01f, zero));
		assertEquals(
			"0%", strictPercent(-0.02f, zero));
		assertEquals(
			"0%", strictPercent(-0.2f, zero));
	}

	@Test
	public void testStrictPercentNegativeDecimals() {
		assertEquals(
			"1%", strictPercent(0.01f, (byte) -1));
		assertEquals(
			"3%", strictPercent(0.03f, (byte) -3));
	}

	@Test
	public void testStrictPercentOneArgSpecialValues() {
		assertEquals(
			"0%", strictPercent(Float.NaN));
		assertEquals(
			"100%", strictPercent(Float.POSITIVE_INFINITY));
		assertEquals(
			"0%", strictPercent(Float.NEGATIVE_INFINITY));
	}

	@Test
	public void testStrictPercentSpecialValues() {
		final byte zero = 0;
		assertEquals(
			"0%", strictPercent(Float.NaN, zero));
		assertEquals(
			"100%", strictPercent(Float.POSITIVE_INFINITY, zero));
		assertEquals(
			"0%", strictPercent(Float.NEGATIVE_INFINITY, zero));
	}

}