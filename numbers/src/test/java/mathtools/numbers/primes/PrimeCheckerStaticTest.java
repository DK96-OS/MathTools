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

/** Testing the Static methods of [PrimeChecker]
 * @author DK96-OS : 2022 */
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
	@ValueSource(ints = { 2, 3, 5, 7, 47, 53 })
	void testIsPrimeTrueInArray(int number) {
		assertTrue(staticIsPrime(number));
	}

	@Test
	void testIsPrimeSkips2() {
		assertTrue(staticIsPrime(2));
		assertTrue(staticIsPrime(4));
		assertTrue(staticIsPrime(8));
		assertTrue(staticIsPrime(64));
		// But does not skip 3
		assertFalse(staticIsPrime(6));
		assertFalse(staticIsPrime(9));
	}

	@Test
	void testIsPrimeUnspecifiedElements() {
		assertTrue(staticIsPrime(0));
		assertTrue(staticIsPrime(1));
		assertTrue(staticIsPrime(-1));
	}

	@ParameterizedTest
	@ValueSource(ints = { 3, 5, 7, 19, 29, 47, 53, 59, 61, 67, 71, 73, 101 })
	void testIsPrimeMultiples(
		final int prime
	) {
		// The prime is recognized
		assertTrue(staticIsPrime(prime));
		// The negative of the prime
		assertTrue(staticIsPrime(-prime));
		// Multiply by 3, and skip all even numbers
		int multiple = 3 * prime;
		//
		for (int n = 3; n < 40000000; n += 2) {
			if (staticIsPrime(multiple)) break;
			multiple += prime + prime;
		}
		System.out.println(
			"Obtained Up to "+multiple +"for prime:"+prime);
	}

	@Test
	void testIsPrimeLimitations() {
		// The square limit is the maximum value that can be correctly tested
		// Up to 53 is safe
		assertFalse(staticIsPrime(53 * 53));
		assertFalse(staticIsPrime(53 * 53 * 53));
		assertFalse(staticIsPrime(53 * 53 * 53));
		// some values above, because 53 is included
		assertFalse(staticIsPrime(53 * 59));
		// Or any other small prime
		assertFalse(staticIsPrime(41 * 59 * 67));
	}

	@Test
	void testIsPrimeFailures() {
		// These calls are all failures to correctly identify non-primes
		assertTrue(staticIsPrime(59 * 59));
		assertTrue(staticIsPrime(61 * 61));
		assertTrue(staticIsPrime(67 * 67));
		// Anything containing primes above will fail
		assertTrue(staticIsPrime(59 * 101));
	}

}