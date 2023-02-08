package mathtools.numbers.format

/** Package numbers inside characters and strings
 * @author DK96-OS : 2021 - 2022 */
@Deprecated(
    "No Longer Supported"
)
object StringPackager {

    /** Pack two UBytes into a Char */
    fun packUBytes(
        u0: UByte,
        u1: UByte,
    ) : Char = NumberSerializer.putBytes(u0.toByte(), u1.toByte())

    /** Split a Char in half, and extract two Bytes from it */
    fun unpackBytes(c: Char)
        : Pair<Byte, Byte> = NumberDeserializer.getBytes(c).run {
        first to second
    }

    /** Unpack 1st of 2 bytes from a character */
    fun unpackUByte0(c: Char)
        : UByte = NumberDeserializer.getByte1(c).toUByte()

    /** Unpack 2nd of 2 bytes from a character */
    fun unpackUByte1(c: Char)
        : UByte = NumberDeserializer.getByte2(c).toUByte()

    /** Pack an unsigned short integer into a character */
    fun packUShort(s: UShort)
        : Char = s.toInt().toChar()

    /** Unpack a short integer from a character */
    fun unpackShort(c: Char)
        : Short {
        val cCode = c.code
        return if (cCode < 0)
            (cCode + 65536).toShort() else cCode.toShort()
    }

}