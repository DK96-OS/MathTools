/** Test Helper class for running Generators, and counting the results
  * Developed by DK96-OS 2018 - 2020 */
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

    fun printCounters() {
        val total = totalCycles / 100f   // Pre-convert to percentage
        counterList.forEach {
            val percentage = it.count / total
            println(it.counterToString() + " = $percentage %")
        }
    }

    /** Call between each Test */
    fun clearCounters() {
        totalCycles = 0L
        counterList.clear()
    }

    /** Prints the Mean counter value and it's probability */
    fun printMeanValues() { println("Mean: ($meanCount, $meanPercent %)") }

    /** Determines the Median counter value, as well as the differences
     * @param sortedAscending Pass true if counters have already been sorted, ascending. */
    fun printMedianRange(sortedAscending: Boolean = false) {
        if (counterList.size < 3)
            throw IllegalStateException()
        if (!sortedAscending)
            sortCounters(true)
        val halfIndex = counterList.size / 2
        val median = if (halfIndex * 2 == counterList.size)
            counterList[halfIndex].count.toFloat()
        else
            (counterList[halfIndex].count + counterList[halfIndex + 1].count) / 2f
        val total = totalCycles.toFloat()
        val medianPercent = 100f * median / total
        println("Median: ($median, $medianPercent %)")
        val min = counterList[0].count
        val max = counterList[counterList.size - 1].count
        println("\tRange: ($min, $max) or (${100 * min / total} %, ${100 * max / total} %)")
        val lowerLimitDiff = median - min
        val upperLimitDiff = max - median
        println("\tMedian " + when {
            lowerLimitDiff > upperLimitDiff ->
                "closer to UpperLimit by ${lowerLimitDiff - upperLimitDiff}"
            upperLimitDiff > lowerLimitDiff ->
                "closer to LowerLimit by ${upperLimitDiff - lowerLimitDiff}"
            else -> "is centered exactly within the range"
        })
    }

}
