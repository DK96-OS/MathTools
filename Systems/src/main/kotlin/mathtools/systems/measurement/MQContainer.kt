package mathtools.systems.measurement

/** A container for a set of measurements
 * Developed by DK96-OS : 2021 */
internal class MQContainer<Params : MQParams, Data>(
    val params: Params,
) {
    val count: Int get() = queue.size

    private val queue: ArrayDeque<Data> = ArrayDeque(params.nDataPoints.toInt())

    /** Records a measurement
     * @returns true if successful, false if measurement count at capacity */
    internal fun recordData(data: Data) { queue.addLast(data) }

    /** Return the recorded data as a list */
    internal fun getData(): List<Data> {
        val list = queue.toList()
        queue.clear()
        return list
    }
}

