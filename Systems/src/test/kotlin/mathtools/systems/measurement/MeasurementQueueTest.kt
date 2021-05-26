package mathtools.systems.measurement

import mathtools.systems.measurement.electrical.ElectricalMeasureContainer
import mathtools.systems.measurement.electrical.ElectricalMeasureData
import mathtools.systems.measurement.electrical.ElectricalMeasurementResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random

/** Testing the Measurement Queue using Electrical System
 * Developed by DK96-OS : 2021 */
class MeasurementQueueTest {

    class ElectricalMeasurementQueue(scope: CoroutineScope)
        : MeasurementQueue<ElectricalMeasureData,
            ElectricalMeasureContainer, ElectricalMeasurementResult
            >(scope)

    private lateinit var practiceQueue: ElectricalMeasurementQueue

    @BeforeEach fun setup() {
        runBlocking {
            practiceQueue = ElectricalMeasurementQueue(this)
        }
    }

    @Test fun testContainerProcessingCycle() {
        practiceQueue.pushInputs(true,
            ElectricalMeasureContainer("M1", 30),
            ElectricalMeasureContainer("M2", 30),
            ElectricalMeasureContainer("A1", 20),
        )
        assertEquals("M1", practiceQueue.getNext()?.id)
        assertEquals(true, practiceQueue.completeFirst())
        assertEquals("M2", practiceQueue.getNext()?.id)
        assertEquals(true, practiceQueue.completeFirst())
        assertEquals("A1", practiceQueue.getNext()?.id)
        assertEquals(true, practiceQueue.completeFirst())
        assertEquals(null, practiceQueue.getNext())
    }

    @Test fun testQueueContainerReuse() {
        val container1 = ElectricalMeasureContainer("C1", 3)
        practiceQueue.pushInputs(false, container1)
        assertEquals(0, container1.measurementCount)
        assertEquals(true, 
        	container1.recordData(getMeasurement(1.0, 500.0)))
        assertEquals(true, 
        	container1.recordData(getMeasurement(1.0, 501.0)))
        assertEquals(true, 
        	container1.recordData(getMeasurement(1.01, 500.0)))
        assertEquals(container1, practiceQueue.getNext())
        assertEquals(3, container1.measurementCount)
        practiceQueue.completeFirst()
        val res1 = runBlocking { container1.getResults() }
        assertEquals("C1", res1.id)
        val time = res1.timeDuration
        val seconds = time * 1.0e-9
        println("Time: $time ns  =  ${seconds} s")
        println("Energy: ${res1.energy}, Charge: ${res1.chargeMoved}")
    }

    private val random = Random(400)

	/** Create a new measurement with random fluctuations */
    private fun getMeasurement(
        inputPower: Double = 1.45,
        targetVoltage: Double = 450.0,
    ): ElectricalMeasureData {
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
