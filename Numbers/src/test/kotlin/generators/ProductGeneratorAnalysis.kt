package generators

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/** Generator Analysis of the ProductGenerator
  * Developed by DK96-OS : 2018 - 2021 */
class ProductGeneratorAnalysis : GeneratorAnalysis
<ProductGenerator, PrimeCounter>(
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
            else -> g.setParametersAndRegenerate(120..15691L, 190)
        }
    }

    @BeforeEach
    fun clear() { clearCounters() }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4])
    fun ouputDistributions(seed: Int) {
    	changeParameters(seed)
        runXTimes(40000)
        printMeanValues()
        printMedianRange()
        println("Error: ${getStandardError()}")
    }

}
