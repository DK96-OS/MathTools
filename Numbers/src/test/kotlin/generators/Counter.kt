package generators

/** Counts the number of some occurrence 
  * Developed by DK96-OS 2018 - 2020 */
abstract class Counter(
    var count: Int = 1
) {

    /** Must provide a string method */
    abstract fun counterToString(): String

}
