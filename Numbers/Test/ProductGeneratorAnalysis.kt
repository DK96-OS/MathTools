import org.junit.Before
import org.junit.Test

/** Generator Analysis of the ProductGenerator
  * Developed by DK96-OS 2018 - 2020 */
class ProductGeneratorAnalysis : GeneratorAnalysis<ProductGenerator, PrimeCounter>(
    ProductGenerator(6..50L, 17)
) {

    override fun ProductGenerator.isCountedBy(counter: PrimeCounter)
        : Boolean = (product == counter.product)

    override fun createCounter(g: ProductGenerator) = PrimeCounter(g.product)

    override fun setParams(g: ProductGenerator, seed: Int) {
        when (seed) {
            1 -> g.setParametersAndRegenerate(6..50L, 17)
            2 -> g.setParametersAndRegenerate(30..90L, 23)
            3 -> g.setParametersAndRegenerate(31..168L, 70)
            else -> g.setParametersAndRegenerate(120..55691L, 190)
        }
    }

    @Before
    fun clear() { clearCounters() }

    @Test
    fun perform() {
        runXTimes(40000)
        sortCounters()
        printCounters()
    }

    @Test
    fun perform2() {
        changeParameters(2)
        runXTimes(40000)
        sortCounters()
        printCounters()
    }

    @Test
    fun perform3() {
        changeParameters(3)
        runXTimes(40000)
        sortCounters()
        printCounters()
    }

    @Test
    fun perform4() {
        changeParameters(4)
        runXTimes(40000)
        sortCounters()
        printCounters()
    }

}
