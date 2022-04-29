package mathtools.numbers.primes.validate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.numbers.primes.validate.PrimeValidation.staticIsPrime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/** Testing [PrimeValidation] static methods
 * @author DK96-OS : 2022 */
public final class PrimeValidationStaticTest {

	@Test
	public void testIsPrimeSkips2() {
		assertTrue(
			staticIsPrime(2));
		assertTrue(
			staticIsPrime(4));
		assertTrue(
			staticIsPrime(8));
		assertTrue(
			staticIsPrime(64));
		// But does not skip 3
		assertFalse(
			staticIsPrime(6));
		assertFalse(
			staticIsPrime(9));
	}

	@Test
	public void testIsPrimeUnspecifiedElements() {
		assertTrue(
			staticIsPrime(0));
		assertTrue(
			staticIsPrime(1));
		assertTrue(
			staticIsPrime(-1));
	}

	@ParameterizedTest
	@ValueSource(ints = {
		3, 5, 7, 19, 29, 47, 53, 59, 61, 67, 71, 73, 101
	})
	public void testIsPrimeMultiples(
		final int prime
	) {
		// The prime is recognized
		assertTrue(
			staticIsPrime(prime));
		// The negative of the prime
		assertTrue(
			staticIsPrime(-prime));
		// Multiply by 3, and skip all even numbers
		int multiple = 3 * prime;
		//
		for (
			int n = 3;
			40_000_000 > n;
			n += 2
		) {
			if (staticIsPrime(multiple))
				break;
			multiple += prime + prime;
		}
		System.out.println(
			"Obtained Up to " + multiple + "for prime:" + prime
		);
	}

	@Test
	public void testIsPrimeLimitations() {
		// The square limit is the maximum value that can be correctly tested
		// Up to 53 is safe
		assertFalse(
			staticIsPrime(53 * 53));
		assertFalse(
			staticIsPrime(53 * 53 * 53));
		assertFalse(
			staticIsPrime(53 * 53 * 53));
		// some values above, because 53 is included
		assertFalse(
			staticIsPrime(53 * 59));
		// Or any other small prime
		assertFalse(
			staticIsPrime(41 * 59 * 67));
	}

	@Test
	public void testIsPrimeFailures() {
		// These calls are all failures to correctly identify non-primes
		assertTrue(
			staticIsPrime(59 * 59));
		assertTrue(
			staticIsPrime(61 * 61));
		assertTrue(
			staticIsPrime(67 * 67));
		// Anything containing primes above will fail
		assertTrue(
			staticIsPrime(59 * 101));
	}

}