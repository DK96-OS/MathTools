package mathtools.numbers.primes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.numbers.primes.StaticPrimes.containsNumber;
import static mathtools.numbers.primes.StaticPrimes.getStaticPrime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/** Testing [StaticPrimes] methods
 * @author DK96-OS : 2022 */
public final class StaticPrimesTest {

	@Test
	public void testGetStaticPrime() {
		assertEquals(
			2, getStaticPrime(0));
		assertEquals(
			3, getStaticPrime(1));
		assertEquals(
			5, getStaticPrime(2));
		assertEquals(
			53, getStaticPrime(15));
	}

	@Test
	public void testGetStaticPrimeOutOfBounds() {
		assertThrows(
			ArrayIndexOutOfBoundsException.class,
			() -> getStaticPrime(16)
		);
		assertThrows(
			ArrayIndexOutOfBoundsException.class,
			() -> getStaticPrime(-1)
		);
	}

	@ParameterizedTest
	@ValueSource(
		ints = { 2, 3, 5, 7, 47, 53 }
	)
	public void testContainsPrimeTrue(
		final int number
	) {
		assertTrue(
			containsNumber(number));
	}

	@ParameterizedTest
	@ValueSource(
		ints = { 1, 4, 6, 8, 48, 54 }
	)
	public void testContainsPrimeFalse(
		final int number
	) {
		assertFalse(
			containsNumber(number));
	}

}