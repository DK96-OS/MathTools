package mathtools.lists.objects

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.random.Random

/** Testing the ObjectList Functions
 * @author DK96-OS : 2022 */
class ObjectListTest {

    private inner class SampleData(
        val groupId: Int, val r: Float,
    )

    private lateinit var source: MutableList<SampleData>
    
    @BeforeEach
    fun testSetup() {
        // Start with 1 one and 2 twos, and so forth
        source = arrayListOf(
	        SampleData(1, 45f),
	        SampleData(2, 48f), SampleData(2, 50f),
        )
        // Add one of each from 3 to 10
        for (i in 3 until 10) repeat(i) {
            source.add(SampleData(i, 40f + i * 5f))
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false]) 
    fun testGroupSort(presortUsed: Boolean) {
        var prevSourceSize = source.size
        for (index in 1 until 10) {
            val group = ObjectList.extractFrom(source, presortUsed) {
                it.groupId == index
            }
            assertEquals(index, group!!.size)
            assertEquals(prevSourceSize - index, source.size)
            prevSourceSize = source.size
        }
    }

    @Test
    fun testUnsortedGroupSort() {
        source.shuffle()    // Randomize the order of the initial dataset
        var prevSourceSize = source.size
        for (i in 1 until 10) {
            val group = ObjectList.extractFrom(source) { it.groupId == i }
            assertEquals(i, group!!.size)
            assertEquals(prevSourceSize - i, source.size)
            prevSourceSize = source.size
        }
    }

    @RepeatedTest(3)
    fun testSparseDataset() {
        // Remove 3 random groups from the source dataset
        val removed = arrayListOf<Int>()
        while (removed.size <3) {
            val newRand = Random.nextInt(1, 9)
            if (newRand !in removed) {
                source.removeIf { it.groupId == newRand }
                removed.add(newRand)
            }
        }
        // Compute the source size from the removed numbers
        var prevSourceSize = (1..9).sumOf {it} - removed.sumOf{it}
        assertEquals(prevSourceSize, source.size)
        source.shuffle()
        // Perform the same grouping operation and assertion
        for (i in 1 until 10) {
            val group = ObjectList.extractFrom(source) { it.groupId == i }
            if (i in removed)
                assertEquals(null, group)   // The selector didn't return true
            else {
                assertEquals(i, group!!.size)
                assertEquals(prevSourceSize - i, source.size)
                prevSourceSize = source.size
            }
        }
    }

}