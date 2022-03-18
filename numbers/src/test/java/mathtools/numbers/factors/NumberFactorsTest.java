package mathtools.numbers.factors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.numbers.factors.NumberFactors.isProductOf5;

import org.junit.jupiter.api.Test;

/** Testing the Number Factors static functions
 * @author DK96-OS : 2022 */
public final class NumberFactorsTest {

	@Test
	void testProductOf5() {
		for (long n = 0; n < 5; n++)
			assertFalse(isProductOf5(n));
		for (long n = 5; n <= 200; n += 5)
			assertTrue(isProductOf5(n));
	}

	@Test
	void testProductOf5Large() {
		for (long n = 10_101; n <= 200_101; n += 500)
			assertFalse(isProductOf5(n));
		for (long n = 10_100; n <= 200_100; n += 500)
			assertTrue(isProductOf5(n));
	}

	@Test
	void testProductOf5Negative() {
		for (long n = 0; n < 5; ++n)
			assertFalse(isProductOf5(-n));
		for (long n = 5; n <= 200; n += 5)
			assertTrue(isProductOf5(-n));
	}

}