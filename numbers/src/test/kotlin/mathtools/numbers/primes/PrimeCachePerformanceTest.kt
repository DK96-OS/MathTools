package mathtools.numbers.primes

import mathtools.numbers.primes.PrimeChecker.findPrime
import mathtools.statistics.Statistics
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.system.measureNanoTime

/** Performance Testing for Prime Number Caches */
class PrimeCachePerformanceTest {

    /** The number of data points per test */
    private val nDataPoints = 10_000

    @ParameterizedTest
    @ValueSource(ints = [9, 9, 15, 15, 21, 25, 25, 27, 29, 33, 35,
        55, 55, 71, 71, 75, 75, 75, 77, 77, 91, 93, 95, 97, 183, 185,
        187, 189, 191, 213, 215, 217, 219, 243, 245, 247, 249, 251,
    ])
    fun testFindPrime(startNumber: Int) {
        val cache = BytePrimeCache()
        val timeList = ArrayList<Long>(nDataPoints)
        var foundPrime: Int? = null
        for (i in 0 until nDataPoints) {
            timeList.add(measureNanoTime {
                foundPrime = findPrime(startNumber, cache)
            })
            cache.clear()
        }
        printTestResults(
            "TestFindPrime", "$startNumber, $foundPrime", timeList)
    }

    @ParameterizedTest
    @ValueSource(ints = [11, 11, 15, 15, 25, 25, 29, 29, 71, 73, 75, 109,
        141, 143, 143, 145, 145, 147, 197, 199, 199, 201, 219, 221, 223,
        241, 243, 245, 249, 251, 253, 255,
    ])
    fun testIsPrime(target: Int) {
        val cache = BytePrimeCache()
        val timeList = ArrayList<Long>(nDataPoints)
        var wasPrime: Boolean? = null
        for (i in 0 until nDataPoints) {
            timeList.add(measureNanoTime {
                wasPrime = cache.isPrime(target)
            })
            cache.clear()
        }
        printTestResults("TestIsPrime", "$target, $wasPrime", timeList)
    }

	@ParameterizedTest
	@ValueSource(ints = [0, 4, 9, 12, 15, 16, 16, 16, 17, 17, 18, 20,
	    22, 24, 24, 25, 26, 28, 30, 32, 34, 34, 36, 38, 40, 42, 44, 46,
	    48, 50, 52, 53
	])
    fun testTargetedAccess(target: Int) {
        val cache = BytePrimeCache()
        val timeList = ArrayList<Long>(nDataPoints)
        var foundPrime: Int? = null
        for (i in 0 until nDataPoints) {
            timeList.add(measureNanoTime {
                foundPrime = cache.getPrime(target)
            })
            cache.clear()
        }
        printTestResults(
            "TestTargetedAccess", "$target, $foundPrime", timeList)
    }

    /** Process and print a statistical summary of the data */
    internal fun printTestResults(
        testName: String, params: String, dataList: ArrayList<Long>
    ) {
        repeat (3) {    // Remove the top 3 longest measurements
            val max = dataList.maxOrNull()
            dataList.removeIf {it == max}
        }
        val mean = Statistics.calculateMean(dataList)
        val sDev = Statistics.calculateSDev(dataList)
        val min = dataList.minOrNull()
        //val max = dataList.maxOrNull()
        println("$testName($params): u=$mean, o-=$sDev, min=$min")
    }

}