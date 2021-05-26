package mathtools.systems.measurement

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

/** A Queue for Managing a series of measurements in progress
 * Developed by DK96-OS : 2021 */
open class MeasurementQueue<
        D : MeasurementData,
        T : MeasurementContainer<D, R>,
        R : MeasurementResult,
        >
    (
    scope: CoroutineScope,
    inputs: Collection<T>? = null
) {
    private val context
    : WeakReference<CoroutineScope> = WeakReference(scope)

    /** Measurements in progress or queued */
    private val inputQueue = ArrayDeque<T>()

    init {
        if (inputs != null) inputQueue.addAll(inputs)
    }

    /** Add to the input queue */
    fun pushInputs(checkIds: Boolean = true, vararg trends: T)
    : Boolean = if (!checkIds) inputQueue.addAll(trends)
    else if (inputQueue.isEmpty()) inputQueue.addAll(trends)
    else {
        for (t in trends) {
            val match = inputQueue.find { it.id == t.id }
            if (match == null) inputQueue.addLast(t)
        }
        true
    }

    /** The First in the Input queue */
    fun getNext(): T? = inputQueue.firstOrNull()

    /** Mark the first Measurement as complete, move to complete queue */
    fun completeFirst(): Boolean {
        val first = inputQueue.removeFirstOrNull()
        return if (first != null) {
            val coContext = context.get()
            if (coContext != null) {
                coContext.launch(Dispatchers.IO) {
                    val result = first.getResults()
                    completedQueue.addLast(result)
                }
                true
            } else false
        } else false
    }

    /** After an input has been received and processed */
    private val completedQueue = ArrayDeque<MeasurementResult>()
}
