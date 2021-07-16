package mathtools.numbers.formats

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

/**  */
class DataTableTest {

    private val initVal = "Initial Value"
    private val finalVal = "Final Value"

    private lateinit var table: DataTable<Int, Float>

    @BeforeEach
    fun setup() {
        table = DataTable(arrayOf("Input Index"))
    }

    @Test
    fun testRowEntry() {
        table.enterRow(2, arrayListOf(
            "Jvm 8" to 15f,
            "Jvm 11" to 14f,
            "Jvm 14" to 12f,
            "Jvm 16" to 15f
        ))
        table.enterRow(5, arrayListOf(
            "Jvm 8" to 17f,
            "Jvm 11" to 16f,
            "Jvm 14" to 14f,
            "Jvm 16" to 17f
        ))
        table.printCSV()
    }

    @Test
    fun testDataEntry() {
        table.enter(1, initVal, 34f)
        table.enter(1, finalVal, 50f)
        table.enter(2, initVal, 35f)
        table.enter(2, finalVal, 51f)
        table.enter(3, initVal, 36f)
        table.enter(3, finalVal, 52f)
        table.enter(5, initVal, 36f)
        table.enter(5, finalVal, 52f)
        //
        table.printCSV()
    }

    @Test
    fun testColumnEntry() {
        table.enterColumn("Jvm 8", arrayListOf(
            2 to 15f,
            5 to 17f,
            9 to 20f,
            12 to 24f,
        ))
        table.enterColumn("Jvm 11", arrayListOf(
            2 to 14f,
            5 to 16f,
            9 to 20f,
            10 to 25f
        ))
        table.printCSV()
    }

}
