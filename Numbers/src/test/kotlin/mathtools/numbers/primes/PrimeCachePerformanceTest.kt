package mathtools.numbers.primes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import mathtools.numbers.statistics.Statistics
import kotlin.system.measureNanoTime
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/** Performance Testing for Prime Number Caches */
class PrimeCachePerformanceTest {

    private val primes: List<Int> = BytePrimeCacheTest.primeList

    @ParameterizedTest
    @ValueSource(ints = [9, 9, 15, 15, 21, 25, 25, 27, 29, 33, 35,
    55, 55, 55, 71, 71, 71, 75, 75, 75, 77, 77, 77])
    fun testFindPrime(startNumber: Int) {
        runBlocking { coroutineScope { launch(Dispatchers.IO) {
            val cache = BytePrimeCache()
            val timeMeasurements = ArrayList<Long>(10_000)
            var foundPrime: Int? = null
            for (i in 0 until 10_000) {
                timeMeasurements.add(measureNanoTime {
                    foundPrime = cache.findPrime(startNumber)
                })
                cache.clear()
            }
            printTestResults(
                "TestFindPrime", "$startNumber, $foundPrime", timeMeasurements)
        } } }
    }

	@ParameterizedTest
	@ValueSource(ints = [0, 4, 9, 12, 15, 16, 17, 18, 20,
	22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 53])
    fun testTargetedAccess(target: Int) {
        val cache = BytePrimeCache()
        val prime = primes[target]
        val timeMeasurements = ArrayList<Long>(10_000)
        var foundPrime: Int? = null
        for (i in 0 until 10_000) {
            timeMeasurements.add(measureNanoTime {
                foundPrime = cache.getPrime(target)
            })
            assertEquals(prime, foundPrime)
            cache.clear()
        }
        printTestResults(
            "TestTargetedAccess", "$target, $foundPrime", timeMeasurements)
    }

    /** Process and print a statistical summary of the data */
    internal fun printTestResults(
        testName: String, params: String, dataList: ArrayList<Long>
    ) {
        val max = timeMeasurements.maxOf {it}
        timeMeasurements.removeIf {it == max}
        val mean = Statistics.calculateMeanLong(dataList)
        val sDev = Statistics.calculateStandardDeviation(dataList)
        val min = dataList.minOrNull()
        val max = dataList.maxOrNull()
        println("$testName($params): u=$mean, o-=$sDev, min=$min, max=$max")
    }

}
