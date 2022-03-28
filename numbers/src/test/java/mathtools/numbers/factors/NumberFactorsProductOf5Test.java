package mathtools.numbers.factors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.numbers.factors.NumberFactors.isProductOf5;

import org.junit.jupiter.api.Test;

/** Testing [NumberFactors] Product of 5 function
 * @author DK96-OS : 2022 */
public final class NumberFactorsProductOf5Test {

	@Test
	void testProductOf5() {
		for (long n = 0; 5 > n; n++)
			assertFalse(
				isProductOf5(n)
			);
		for (long n = 5; 200 >= n; n += 5)
			assertTrue(
				isProductOf5(n)
			);
	}

	@Test
	void testProductOf5Large() {
		final long largeFactor = 4_100_100_100L;
		long n = largeFactor;
		for (;
			3 * largeFactor >= n;
			n += largeFactor
		) assertTrue(
			isProductOf5(n)
		);
	}

	@Test
	void testNonProductLarge() {
		final long largeFactor = 4_100_100_101L;
		// Every 5 largeFactor, it will be a product
		long n = largeFactor;
		for (;
			4 * largeFactor >= n;
			n += largeFactor
		) assertFalse(
			isProductOf5(n)
		);
	}

	@Test
	void testProductOf5Negative() {
		for (long n = 0; 5 > n; ++n)
			assertFalse(
				isProductOf5(-n)
			);
		for (long n = 5; 200 >= n; n += 5)
			assertTrue(
				isProductOf5(-n)
			);
	}

}