package mathtools.numbers.primes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static mathtools.numbers.primes.PrimeFactoring.getPrimeFactors;

import org.junit.jupiter.api.Test;

import java.util.List;

import javax.annotation.Nonnull;

import mathtools.numbers.factors.IntOperations;
import mathtools.numbers.structs.IntPair;

/** Testing [PrimeFactoring] GetPrimeFactors method
 * @author DK96-OS : 2022 */
public final class PrimeFactoringGetPrimeFactorsTest {

	private final PrimeCacheInterface cache = new ShortPrimeCache();

	@Test
	public void testProductsOf2() {
		// The Products 2, 4, 8, 16, 32, ... will be tested
		int product = 2;
		for (
			byte power = 1;
			16 > power;
			++power
		) {
			final List<IntPair> factors = getPrimeFactors(
				product, cache
			);
			assertEquals(
				1, factors.size());
			final IntPair primePair = factors.get(0);
			// The only Prime Factor is a 2
			assertEquals(
				2, primePair.getFirst());
			// The power matches
			assertEquals(
				power, primePair.getSecond());
			// Increase the Product with the Power
			product *= 2;
			System.out.printf(
				"Increased Product to %d", product
			);
		}
	}

	@Test
	public void testProductsOf7() {
		int product = 7;
		for (
			byte power = 1;
			9 > power;
			++power
		) {
			final List<IntPair> factors = getPrimeFactors(
				product, cache
			);
			assertEquals(
				1, factors.size());
			final IntPair primePair = factors.get(0);
			// The only Prime Factor is a 9
			assertEquals(
				7, primePair.getFirst());
			// The power matches
			assertEquals(
				power, primePair.getSecond());
			// Increase the Product with the Power
			product *= 7;
			System.out.printf(
				"Increased Product to %d", product
			);
		}
	}

	@Test
	public void testInvalidProduct() {
		assertEquals(
			0, getPrimeFactors(1, cache).size());
		assertEquals(
			0, getPrimeFactors(0, cache).size());
		assertEquals(
			0, getPrimeFactors(-10, cache).size());
	}

	@Test
	public void testLargePrimes() {
		final IntPair[] expect = {
			new IntPair(2, 12),
			new IntPair(3, 2)
		};
		final List<IntPair> results = getPrimeFactors(
			evaluate(expect), cache
		);
		assertEquals(
			2, results.size());
		final IntPair prime2 = results.get(0);
		final IntPair prime3 = results.get(1);
		//
		assertEquals(
			2, prime2.getFirst());
		assertEquals(
			12, prime2.getSecond());
		//
		assertEquals(
			3, prime3.getFirst());
		assertEquals(
			2, prime3.getSecond());
	}

	/** Determine the Composite Value of this List of Prime Factors
	 * @param factors The List of Prime Factors to summarize
	 * @return The Product of Factors in the List
	 */
	private static int evaluate(
		@Nonnull final IntPair[] factors
	) {
		int productSum = 1;
		for (final IntPair pair : factors) {
			final IntPair product = IntOperations.exponent(
				pair.getFirst(), pair.getSecond()
			);
			if (0 != product.getSecond())
				throw new IllegalArgumentException(
					String.format(
						"Failed to Evaluate %d to the power of %d",
						pair.getFirst(),
						pair.getSecond()
					)
				);
			productSum *= product.getFirst();
		}
		return productSum;
	}

}