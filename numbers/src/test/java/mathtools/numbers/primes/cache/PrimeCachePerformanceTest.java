package mathtools.numbers.primes.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;

import mathtools.numbers.primes.BytePrimeCache;
import mathtools.numbers.primes.PrimeCacheInterface;
import mathtools.numbers.primes.TimeMeasurement;
import mathtools.statistics.Statistics;

/** Performance Testing for Prime Number Caches
 * @author DK96-OS : 2022 */
public final class PrimeCachePerformanceTest {

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
		0, 4, 9, 12, 15, 16, 16, 16, 17, 17, 18, 20, 22, 24, 24, 25,
		26, 28, 30, 32, 34, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 53
	})
	public void testTargetedAccess(
		final int target
	) {
		Integer foundPrime = null;
		final TimeMeasurement measurement = new TimeMeasurement();
		for (
			int i = 0;
			N_DATA_POINTS > i;
			++i
		) {
			measurement.reset();
			foundPrime = cache.getPrime(target);
			timeData.add(measurement.measure());
			cache.clear();
		}
		printTestResults(
			"TestTargetedAccess",
			String.format(
				"%d, %d", target, foundPrime
			),
			timeData
		);
	}

	/** Process and print a statistical summary of the data */
	public static void printTestResults(
		final String testName,
		final String params,
		final ArrayList<Long> dataList
	) {
		final double mean = Statistics.INSTANCE.calculateMean(dataList);
		final double sDev = Statistics.INSTANCE.calculateSDev(dataList, mean);
		final Long min = Collections.min(dataList);
		//
		System.out.printf(
			"%s(%s): u=%f, o-=%f, min=%d\n",
			testName,
			params,
			mean,
			sDev,
			min
		);
	}

}