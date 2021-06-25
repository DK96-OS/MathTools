package mathtools.systems.measurement

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/** A Queue for Managing a series of measurements in progress
 * Developed by DK96-OS : 2021 */
abstract class MeasurementQueue<Params : MQParams, Data> {

    /** Parameter inputs given, waiting to become containerized  */
    private val waitQueue = ArrayDeque<Params>(2)

    /** The active Container */
    private var activeContainer: MQContainer<Params, Data>? = null
    
    /** Obtain the parameters of the active measurement container */
    val activeParams: Params?
    	get() = (activeContainer ?: prepareNext())?.params
    
    /** Obtain the data count of the active measurement container */
    val activeCount: Int
    	get() = (activeContainer ?: prepareNext())?.count ?: 0

    /** Process the data in a completed container */
    protected abstract suspend fun process(
        params: Params, data: MutableList<Data>)

    /** Insert measurement parameters into the queue */
    suspend fun provideParams(vararg params: Params) {
        when {
            params.size == 1 -> if (activeContainer == null)
                activeContainer = MQContainer(params.first())
            else 
                waitQueue.addLast(params.first())
            params.size > 1 -> {
                waitQueue.addAll(params)
                if (activeContainer == null) prepareNext()
            }
            else -> return
        }
    }

    /** Store a data measurement in the current active container
     * @return The remaining measurements for the active container, 
     *  or -1 if no container is active */
    suspend fun recordData(d: Data?): Int {
        val active = activeContainer
        return when {
            active == null -> -1    // Data likely won't match container
            d == null -> active.params.nDataPoints - active.count
            else -> coroutineScope {
                val result = active.recordData(d)
                if (result == 0) {
                    activeContainer = null
                    launch(Dispatchers.IO) {
                        process(active.params, active.getData())
                    }
                } else if (result < 0) activeContainer = null
                result
            }
        }
    }
    
	/** Create MQContainer for the next parameter waiting in the queue */
    private fun prepareNext(): MQContainer<Params, Data>? {
        val params = waitQueue.removeFirstOrNull() ?: return null
        val container = MQContainer<Params, Data>(params)
        if (activeContainer == null) activeContainer = container
        return container
    }

    /** Clears all data, while checking the first container for unsaved data */
    suspend fun clear() { coroutineScope {
        val active = activeContainer
        val clearActiveJob = if (active != null && active.count > 0) 
            async(Dispatchers.IO) {
                process(active.params, active.getData())
            } else null
        waitQueue.clear()
        clearActiveJob?.await()
    } }
}
