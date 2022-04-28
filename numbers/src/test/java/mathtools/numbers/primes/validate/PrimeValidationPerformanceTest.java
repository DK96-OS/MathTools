package mathtools.numbers.primes.validate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import mathtools.numbers.primes.BytePrimeCache;
import mathtools.numbers.primes.PrimeCacheInterface;
import mathtools.numbers.primes.cache.PrimeCachePerformanceTest;
import mathtools.numbers.structs.TimeMeasurement;

/** Performance Measurements of PrimeValidation methods
 * @author DK96-OS : 2022 */
public final class PrimeValidationPerformanceTest {

	/** The number of data points per test */
	private static final int N_DATA_POINTS = 10_000;

	/** The Prime Cache to use during tests */
	private PrimeCacheInterface cache;

	/** The data obtained from Time Measurements */
	private ArrayList<Long> timeData;

	@BeforeEach
	public void testSetup() {
		cache = new BytePrimeCache();
		timeData = new ArrayList<>(N_DATA_POINTS);
	}

	@ParameterizedTest
	@ValueSource(ints = {
		9, 9, 15, 15, 21, 25, 25, 27, 29, 33, 35, 55, 55, 71, 71,
		75, 75, 75, 77, 77, 91, 93, 95, 97, 183, 185, 187, 189,
		191, 213, 215, 217, 219, 243, 245, 247, 249, 251
	})
	public void testFindPrime(
		final int startNumber
	) {
		final TimeMeasurement measurement = new TimeMeasurement();
		// The results should all be the same
		Integer result = null;
		//
		for (int counter = 0;
		     N_DATA_POINTS > counter;
		     ++counter
		) {
			measurement.reset();
			result = PrimeValidation.findPrime(
				startNumber, cache
			);
			timeData.add(measurement.measure());
		}
		PrimeCachePerformanceTest.printTestResults(
			"FindPrime",
			String.format("%d -> %d", startNumber, result),
			timeData
		);
	}

	@ParameterizedTest
	@ValueSource(ints = {
		11, 11, 15, 15, 25, 25, 29, 29, 71, 73, 75, 109,
		141, 143, 143, 145, 145, 147, 197, 199, 199, 201,
		219, 221, 223, 241, 243, 245, 249, 251, 253, 255
	})
	public void testIsPrime(
		final int target
	) {
		Boolean wasPrime = null;
		final TimeMeasurement measurement = new TimeMeasurement();
		for (
			int i = 0;
			N_DATA_POINTS > i;
			++i
		) {
			measurement.reset();
			wasPrime = PrimeValidation.isPrime(
				target, cache
			);
			timeData.add(measurement.measure());
		}
		PrimeCachePerformanceTest.printTestResults(
			"TestIsPrime",
			String.format(
				"%d, %s", target, wasPrime
			),
			timeData
		);
	}

}