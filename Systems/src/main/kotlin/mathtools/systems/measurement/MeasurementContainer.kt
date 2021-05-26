package mathtools.systems.measurement

import java.util.*
import java.util.concurrent.ArrayBlockingQueue

/** A container for identically initialized set of measurements
 * Developed by DK96-OS : 2021 */
abstract class MeasurementContainer<
        D: MeasurementData,
        R: MeasurementResult
        >(
    open val id: Any,
    nMeasurements: Int, // The number of measurements to take before completion
) {
    val measurementCount: Int get() = queue.size

    private val queue: Queue<D> = ArrayBlockingQueue(nMeasurements)

    private var recentResult: R? = null

    /** Records a measurement
     * @returns true if successful, false if measurement count at capacity */
    fun recordData(data: D) = queue.offer(data)

    /** Create and return the results */
    internal suspend fun getResults(): R {
        val calculatedResult = recentResult
        return if (calculatedResult != null) calculatedResult else {
            val newResult = complete(queue.toList())
            recentResult = newResult
            newResult
        }
    }

    /** Process measurement data and produce a results object */
    protected abstract suspend fun complete(list: List<D>): R

    /** Clear out all measurement data, prepare to reuse container */
    open suspend fun clean() {
        recentResult = null
        queue.clear()
    }
}
