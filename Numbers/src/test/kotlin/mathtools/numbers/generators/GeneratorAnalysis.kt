package mathtools.numbers.generators

import kotlin.math.sqrt
import mathtools.numbers.statistics.Statistics

/** Generator Testing Framework for outcome analysis
  * Developed by DK96-OS : 2018 - 2021 */
abstract class GeneratorAnalysis<G: Generator, C: Counter>(
    protected val generator: G,
    protected val counterList: ArrayList<C> = arrayListOf()
) {
    var totalCycles: Long = 0L
        private set

    protected val meanCount: Float get() = totalCycles / counterList.size.toFloat()
    protected val meanPercent: Float get() = 100f / counterList.size

    /** Update the Generator with new parameters based on the seed value
     * @param g The generator to update
     * @param seed A number that you use to specify a set of generator parameters */
    protected abstract fun setParams(g: G, seed: Int)

    /** Determines whether this counter represents this generator result */
    protected abstract fun G.isCountedBy(counter: C): Boolean

    /** Create a new counter when a new result appears. Copy the result from generator */
    protected abstract fun createCounter(g: G): C

    /** Runs the Generator and counts the occurrence of each result
     * @param x The number of time to run the generator */
    fun runXTimes(x: Long) {
        for (i in 0L until x) {
            generator.generate()
            val counter = counterList.find { generator.isCountedBy(it) }
            if (counter != null) counter.count++
            else counterList.add(createCounter(generator))
        }
        totalCycles += x
    }

    /** Update the Generator Parameters with the given seed
     * @param seed A number representing a set of parameters you defined in setParams() */
    fun changeParameters(seed: Int) { setParams(generator, seed) }

    /** Sorts the counters by the number of times each result occurred */
    fun sortCounters(ascending: Boolean = true) {
        if (ascending) counterList.sortBy { it.count }
        else counterList.sortByDescending { it.count }
    }

    /** Determines the Median value of all counters
     * @param sortedAscending Pass true if counters have already been sorted, ascending. */
    fun getMedian(sortedAscending: Boolean = false): Float {
        if (counterList.size < 3) throw IllegalStateException()
        if (!sortedAscending) sortCounters(true)
        val halfIndex = counterList.size / 2
        return if (halfIndex * 2 == counterList.size)
            counterList[halfIndex].count.toFloat()
        else
            (counterList[halfIndex].count + counterList[halfIndex + 1].count) / 2f
    }

    /** Calculates the Standard Deviation of the Probability */
    fun getStandardDeviation(): Float {
        val total: Float = totalCycles / 100f
        val probabilities = counterList.map { it.count / total }
        return Statistics.calculateStandardDevFloat(probabilities, meanPercent)
    }

    /** Calculates the Standard Error, the Standard Deviation of the Mean
     * @param sDev Provide the Standard Deviation if already known */
    fun getStandardError(sDev: Float? = null)
        : Float = ((sDev ?: getStandardDeviation()) / sqrt(counterList.size.toDouble())).toFloat()

    /** Calculates the Fractional Error in the Error
     * Helps determine the appropriate number of measurements to minimize the error */
    fun getErrorInError(): Float = (1.0 / sqrt(2.0 * counterList.size - 2.0)).toFloat()

    /** Call between each Test */
    fun clearCounters() {
        totalCycles = 0L
        counterList.clear()
    }

    /** Prints all counters, their represented values and outcome probability */
    fun printCounters() {
        val total = totalCycles / 100f   // Pre-convert to percentage
        counterList.forEach {
            val percentage = it.count / total
            println(it.counterToString() + " = $percentage %")
        }
    }

    /** Prints the Mean value, Standard Deviation and Error */
    fun printMeanValues() {
        val sDev = getStandardDeviation()
        val sError = "%.2f".format(getStandardError(sDev) * (1f + getErrorInError()))
        val sDevStr = "%.2f".format(sDev)
        println("Mean%=$meanPercent +/- $sError \tSDev=$sDevStr%")
    }

    /** Determines the Median counter value, as well as the differences
     * @param sortedAscending Pass true if counters have already been sorted, ascending. */
    fun printMedianRange(sortedAscending: Boolean = false) {
        val median = getMedian(sortedAscending)
        val total = totalCycles / 100f
        val min = counterList[0].count
        val max = counterList[counterList.size - 1].count
        val medianRangeFraction = "%.3f".format((median - min) / (max - min))
        println("Median%=${median / total} : Fraction of Range=$medianRangeFraction%")
        val rMinStr = "%.4f".format(min / total)
        val rMaxStr = "%.4f".format(max / total)
        println("Range%: ($rMinStr, $rMaxStr)")
    }

    /** Runs the generator using a specific seed for multiple sample sizes
     * @param seed The identifier for the input parameters to test
     * @param sampleSizes List of the sample sizes to test in sequence */
    fun compareSampleSizes(seed: Int, vararg sampleSizes: Long) {
        changeParameters(seed)
        for (s in sampleSizes) {
            println("\n\t$s Cycles, Seed $seed:")
            clearCounters()
            runXTimes(s)
            printMeanValues()
            printMedianRange()
        }
    }

    fun compareSeeds(seedRange: IntRange,
                     vararg sampleSize: Long = longArrayOf(500, 5000, 50000)) {
        for (i in seedRange) {
            compareSampleSizes(i, *sampleSize)
            println("\n_________________")
        }
    }

}
