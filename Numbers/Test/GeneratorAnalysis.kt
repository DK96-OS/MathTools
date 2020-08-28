/** Test Helper class for running Generators, and counting the results
  * Developed by DK96-OS 2018 - 2020 */
abstract class GeneratorAnalysis<G: Generator, C: Counter>(
    protected val generator: G,
    val counterList: ArrayList<C> = arrayListOf()
) {

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
    }

    /** Update the Generator Parameters with the given seed
     * @param seed A number representing a set of parameters you defined in setParams() */
    fun changeParameters(seed: Int) { setParams(generator, seed) }

    /** Sorts the counters by the number of times each result occurred */
    fun sortCounters() { counterList.sortBy { it.count } }

    fun printCounters() { counterList.forEach { println(it.counterToString()) } }

    /** Call between each Test */
    fun clearCounters() { counterList.clear() }

}
