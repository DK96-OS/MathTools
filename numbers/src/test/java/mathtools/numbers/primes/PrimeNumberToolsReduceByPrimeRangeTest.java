package mathtools.numbers.primes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import kotlin.ranges.IntRange;
import mathtools.numbers.primes.cache.PrimeCacheArgumentProvider;

/** Testing [PrimeNumberTools] ReduceByPrimeRange method
 * @author DK96-OS : 2022 */
public final class PrimeNumberToolsReduceByPrimeRangeTest {

	@ParameterizedTest
	@ArgumentsSource(
		PrimeCacheArgumentProvider.class
	)
	public void testReduceByPrimesInvalidRange(
		final PrimeCacheInterface cache
	) {
		// Negative valued ranges are ignored
		assertNull(
			reduceByPrimeRange(
				new IntRange(-3, -1),
				-30,
				cache
			));
		// The invalid region of the range is ignored
		assertNull(
			reduceByPrimeRange(
				new IntRange(-3, 3),
				-60,
				cache
			));
		// Invalid region is ignored
		assertEquals(
			-15, reduceByPrimeRange(
				new IntRange(-1, 0),
				-60,
				cache
			));
	}

	@ParameterizedTest
	@ArgumentsSource(
		PrimeCacheArgumentProvider.class
	)
	public void testReduceByPrimesReversedRange(
		final PrimeCacheInterface cache
	) {
		assertEquals(
			4, reduceByPrimeRange(
				new IntRange(2, 1),
				60,
				cache
			));
		assertEquals(
			-4, reduceByPrimeRange(
				new IntRange(2, 1),
				-60,
				cache
			));
	}

	@ParameterizedTest
	@ArgumentsSource(
		PrimeCacheArgumentProvider.class
	)
	public void testReduceByPrimesIndivisibleProduct(
		final PrimeCacheInterface cache
	) {
		assertNull(
			reduceByPrimeRange(
				new IntRange(1, 3),
				-1,
				cache
			));
		assertNull(
			reduceByPrimeRange(
				new IntRange(1, 3),
				0,
				cache
			));
		assertNull(
			reduceByPrimeRange(
				new IntRange(1, 3),
				1,
				cache
			));
	}

	@ParameterizedTest
	@ArgumentsSource(
		PrimeCacheArgumentProvider.class
	)
	public void testReduceByPrimesNegative(
		final PrimeCacheInterface cache
	) {
		assertEquals(
			-2, reduceByPrimeRange(
				new IntRange(1, 2),
				-30,
				cache
			));
		assertEquals(
			-4, reduceByPrimeRange(
				new IntRange(1, 2),
				-60,
				cache
			));
		assertEquals(
			-12, reduceByPrimeRange(
				new IntRange(2, 3),
				-60,
				cache
			));
	}

	@ParameterizedTest
	@ArgumentsSource(
		PrimeCacheArgumentProvider.class
	)
	public void testReduceByPrimes(
		final PrimeCacheInterface cache
	) {
		assertEquals(
			2, reduceByPrimeRange(
				new IntRange(1, 2),
				30,
				cache
			));
		assertEquals(
			4, reduceByPrimeRange(
				new IntRange(1, 5),
				60,
				cache
			));
		assertEquals(
			12, reduceByPrimeRange(
				new IntRange(2, 2),
				60,
				cache
			));
	}

	/** Call reduceByPrimeRange in PrimeNumberTools
	 */
	@Nullable
	private static Long reduceByPrimeRange(
		final IntRange primeIndexRange,
		final long product,
		@Nonnull final PrimeCacheInterface cache
	) {
		return PrimeNumberTools.INSTANCE.reduceByPrimeRange(
			primeIndexRange,
			product,
			cache
		);
	}

}