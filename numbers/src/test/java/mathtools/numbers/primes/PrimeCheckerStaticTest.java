package mathtools.numbers.primes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.numbers.primes.PrimeChecker.getStaticPrime;
import static mathtools.numbers.primes.PrimeChecker.staticIsPrime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**  */
public final class PrimeCheckerStaticTest {

	@Test
	void testGetStaticPrime() {
		assertEquals(2, getStaticPrime(0));
		assertEquals(3, getStaticPrime(1));
		assertEquals(5, getStaticPrime(2));
		assertEquals(53, getStaticPrime(15));
	}

	@Test
	void testGetStaticPrimeOutOfBounds() {
		assertThrows(ArrayIndexOutOfBoundsException.class,
			() -> getStaticPrime(16));
		assertThrows(ArrayIndexOutOfBoundsException.class,
			() -> getStaticPrime(-1));
	}

	@ParameterizedTest
	@ValueSource(ints = { 3, 5, 7, 47, 53 })
	void testIsPrimeTrueInArray(final int number) {
		assertTrue(staticIsPrime(number));
	}

	@Test
	void testIsPrimeFalseBelowArrayLimit() {
		// Check all even numbers
		for (int n = 4; n < 54; n += 2)
			assertFalse(staticIsPrime(n));
		// Check multiples of 3
		for (int n = 6; n < 54; n += 4)
			assertFalse(staticIsPrime(n));
		// Check multiples of 11
		for (int n = 22; n < 54; n += 11)
			assertFalse(staticIsPrime(n));
	}

	@Test
	void testIsPrimeTrueBelowSquareLimit() {
	}

	@Test
	void testIsPrimeTrueAboveLimit() {
	}

}