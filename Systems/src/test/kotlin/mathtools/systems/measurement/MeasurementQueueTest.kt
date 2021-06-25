package mathtools.systems.measurement

import mathtools.systems.measurement.electrical.ElectricalMeasureQueue
import mathtools.systems.measurement.electrical.ElectricalMeasureData
import mathtools.systems.measurement.electrical.ElectricalMeasurementResult
import mathtools.systems.measurement.electrical.ElectricalParams
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random

/** Testing the Measurement Queue using Electrical System
 * Developed by DK96-OS : 2021 */
class MeasurementQueueTest {

    private lateinit var mQueue: ElectricalMeasureQueue

    @BeforeEach fun setup() {
        mQueue = ElectricalMeasureQueue()
    }

    @Test fun testContainerProcessingCycle() {runBlocking {
        mQueue.provideParams(
            ElectricalParams("M1", 30), ElectricalParams("M2", 30)
		)
        assertEquals("M1", mQueue.activeParams?.id)
        for (i in 0 until 29) {
            assertEquals(i, mQueue.activeCount)
            val remaining = mQueue.recordData(getMeasurement())
            assertEquals(29 - i, remaining)
        }
        assertEquals(29, mQueue.activeCount)
        assertEquals("M1", mQueue.activeParams?.id)
        assertEquals(0, mQueue.recordData(getMeasurement()))
        // Check Result
        val resultM1 = mQueue.resultQueue.removeFirstOrNull()
        assertEquals("M1", resultM1?.id)
        // Next cycle, start by checking new params
        assertEquals("M2", mQueue.activeParams?.id)
        assertEquals(0, mQueue.activeCount)
    } }

    @Test fun testDataInsertion() { runBlocking {
    	mQueue.provideParams(ElectricalParams("Hello", 5))
        for (i in 0 until 5) {
            val remaining = mQueue.recordData(getMeasurement())
            assertEquals(4 - i, remaining)
        }
        assertEquals(-1, mQueue.recordData(getMeasurement()))
        assertEquals(null, mQueue.activeParams)
        // Now add new Container Params
        mQueue.provideParams(ElectricalParams("M2", 5))
        assertEquals("M2", mQueue.activeParams?.id)
        assertEquals(5, mQueue.recordData(null))    // null returns remainder
        assertEquals(4, mQueue.recordData(getMeasurement()))
    } }
    
    @Test fun testLooping() { runBlocking {
    	mQueue.provideParams(
    		ElectricalParams("M1", 5, 1.5f),
    		ElectricalParams("M2", 5, 1.7f),
    		ElectricalParams("M3", 5, 1.9f),
    	)
        var dataCount = 0
        while (mQueue.activeParams != null) {
            val measurement = getMeasurement(mQueue.activeParams)
            mQueue.recordData(measurement)
            dataCount++
        }
        assertEquals(15, dataCount)
    } }

    private val random = Random(400)

	private fun getMeasurement(params: ElectricalParams?) 
	: ElectricalMeasureData? = if (params != null) 
		getMeasurement(params.power.toDouble(), params.voltage.toDouble())
	else null

	/** Create a new measurement with random fluctuations */
    private fun getMeasurement(
        inputPower: Double = 1.45,
        targetVoltage: Double = 450.0,
    ) : ElectricalMeasureData {
        val startTime = System.nanoTime()
        val avgPower = runBlocking {
            delay(random.nextInt(1, 9).toLong())
            inputPower + random.nextFloat() * 0.00035
        }
        val avgVolt = runBlocking {
            delay(random.nextInt(3, 7).toLong())
            targetVoltage + random.nextFloat() * 0.025
        }
        return ElectricalMeasureData(
            startTime, System.nanoTime(), avgPower, avgVolt
        )
    }

}
