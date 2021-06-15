package mathtools.systems.measurement

import kotlinx.coroutines.Deferred
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

    /** Process the data in a container into a Result object */
    protected abstract suspend fun process(
        params: Params, data: List<Data>)

    /** Insert essential unprepared params into the waiting queue */
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

    /** Store a data measurement for the current active container
     * @return False if there is no active container or the container failed */
    suspend fun recordData(d: Data?): Boolean {
        val active = activeContainer
        return when {
            d == null -> false
            active == null -> false	// Parameters not read, prevent recording
            active.recordData(d) -> {
            	if (checkFullContainer(active)) activeContainer = null
            	true
            }
            checkFullContainer(active) -> {
            	activeContainer = null
                false
            }
            else -> false
        }
    }
    
	/** Retrieve any parameter from the waiting queue, create MQContainer */
    private fun prepareNext(): MQContainer<Params, Data>? {
        val params = waitQueue.removeFirstOrNull() ?: return null
        val container = MQContainer<Params, Data>(params)
        if (activeContainer == null) activeContainer = container
        return container
    }

	/** Check if the given container is full, launch the processing function */
    private suspend fun checkFullContainer(c: MQContainer<Params, Data>)
    : Boolean = if (c.count >= c.params.nDataPoints) coroutineScope {
    	launch(Dispatchers.IO) {completeContainer(c)}
        true
    } else false

    /** Process and persist this container */
    private suspend fun completeContainer(
        mqc: MQContainer<Params, Data>,
    ) { if (mqc.count > 0) process(mqc.params, mqc.getData()) }

    /** Clears all data, while checking the first container for unsaved data */
    suspend fun clear() { coroutineScope {
        val active = activeContainer
        val clearActiveJob = if (active != null)
            async(Dispatchers.IO) {completeContainer(active)} else null
        waitQueue.clear()
        clearActiveJob?.await()
    } }
}
