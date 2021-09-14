package mathtools.systems.logs

/** */
interface LogTypeConverter {
    
    /** Convert a known type to a readable String, or return null */
    fun typeToString(type: Short): String?
    
    /** Identify known strings, assign a short number, or return null */
    fun stringToType(string: String): Short?
    
}
