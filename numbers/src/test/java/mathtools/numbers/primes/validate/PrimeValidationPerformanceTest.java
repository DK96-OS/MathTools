package mathtools.numbers.primes.validate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import mathtools.numbers.primes.BytePrimeCache;
import mathtools.numbers.primes.PrimeCacheInterface;
import mathtools.numbers.primes.PrimeCachePerformanceTest;
import mathtools.numbers.structs.TimeMeasurement;

/** Performance Measurements of PrimeValidation methods
 * @author DK96-OS : 2022 */
public final class PrimeValidationPerformanceTest {

	/** The number of data points per test */
	private static final int nDataPoints = 10_000;

	/** The Prime Cache to use during tests */
	private PrimeCacheInterface cache;

	/** The data obtained from Time Measurements */
	private ArrayList<Long> timeData;

	@BeforeEach
	public void testSetup() {
		cache = new BytePrimeCache();
		timeData = new ArrayList<>(nDataPoints);
	}

	@ParameterizedTest
	@ValueSource(ints = {
		9, 9, 15, 15, 21, 25, 25, 27, 29, 33, 35,
		55, 55, 71, 71, 75, 75, 75, 77, 77, 91, 93, 95, 97, 183, 185,
		187, 189, 191, 213, 215, 217, 219, 243, 245, 247, 249, 251,
	})
	public void testFindPrime(
		final int startNumber
	) {
		final TimeMeasurement measurement = new TimeMeasurement();
		// The results should all be the same
		Integer result = null;
		//
		for (int counter = 0;
		     nDataPoints > counter;
		     ++counter
		) {
			measurement.reset();
			result = PrimeValidation.findPrime(
				startNumber, cache
			);
			timeData.add(measurement.measure());
		}
		PrimeCachePerformanceTest.Companion.printTestResults(
			"FindPrime",
			String.format("%d -> %d", startNumber, result),
			timeData
		);
	}

}