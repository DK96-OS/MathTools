package mathtools.systems.logs

/** An example type mapping for testing purposes */
internal class ExampleTypeConverter : LogTypeConverter {

    fun typeToString(type: Short)
    : String? = when (type) {
        (1).toShort() -> "NewA"
        (2).toShort() -> "NewB"
        (3).toShort() -> "NewC"
        (4).toShort() -> "EditA"
        (5).toShort() -> "EditB"
        (6).toShort() -> "EditC"
        else -> null
    }
        
    fun stringToType(string: String)
    : Short? = when (type) {
        "NewA" -> 1
        "NewB" -> 2
        "NewC" -> 3
        "EditA" -> 4
        "EditB" -> 5
        "EditC" -> 6
        else -> null
    }
}
