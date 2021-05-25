package generators

/** Counter for Primes
  * Developed by DK96-OS 2018 - 2020 */
class PrimeCounter(
    val product: Long
)
    : Counter() {

    override fun counterToString() = "$product : $count"

}
