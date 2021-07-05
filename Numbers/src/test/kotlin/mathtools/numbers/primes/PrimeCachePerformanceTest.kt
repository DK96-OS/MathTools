package mathtools.numbers.primes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import mathtools.numbers.statistics.Statistics
import kotlin.system.measureNanoTime

/** Performance Testing for Prime Number Caches */
class PrimeCachePerformanceTest {

    private val primes: List<Int> = BytePrimeCacheTest.primeList

    @ParameterizedTest
    @ValueSource(ints = [9, 9, 9, 15, 15, 21, 25, 27, 29, 31, 33, 35,
    55, 55, 55, 71, 71, 71, 75, 75, 75, 77, 77, 77])
    fun testFindPrime(startNumber: Int) {
        val cache = BytePrimeCache()
        val timeMeasurements = Array<Long>(5_000) {0}
        for (i in timeMeasurements.indices) {
            timeMeasurements[i] = measureNanoTime {
                cache.findPrime(startNumber)
            }
            cache.clear()
        }
        val dataList = timeMeasurements.toList()
        val mean = Statistics.calculateMeanLong(dataList)
        val sDev = Statistics.calculateStandardDeviation(dataList)
        println("FindPrimeResult ($startNumber) : u=$mean, o-=$sDev")
    }


	@ParameterizedTest
	@ValueSource(ints = [0, 4, 9, 12, 15, 16, 17, 18, 20,
	22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 53])
    fun testTargetedAccess(target: Int) {
        val cache = BytePrimeCache()
        val prime = primes[target]
        repeat(240_000) {
            assertEquals(prime, cache.getPrime(target))
            cache.clear()
        }
    }

}
