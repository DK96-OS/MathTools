package mathtools.systems.measurement

/** A container for a set of measurements
 * Developed by DK96-OS : 2021 */
internal class MQContainer<Params : MQParams, Data>(
    val params: Params,
) {
    val count: Int get() = queue.size

    private val queue: ArrayDeque<Data> = ArrayDeque(params.nDataPoints)

    /** Records a measurement
     * @returns Count of remaining measurements before capacity reached */
    internal fun recordData(data: Data): Int {
        val diff = params.nDataPoints - count
        return if (diff > 0) {
    	    queue.addLast(data)
    	    diff - 1
        } else 0
    }

    /** Return the recorded data as a list */
    internal fun getData(): MutableList<Data> {
        val list = queue.toMutableList()
        queue.clear()
        return list
    }
}
