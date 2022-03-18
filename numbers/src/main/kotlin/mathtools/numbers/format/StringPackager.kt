package mathtools.numbers.format

/** Package numbers inside characters and strings
 * @author DK96-OS : 2021 - 2022 */
object StringPackager {

    /** Pack two Bytes into a Char */
    @Deprecated(
        "Moved to NumberSerializer",
        ReplaceWith(
            "NumberSerializer.packBytes(b0, b1)",
            "mathtools.numbers.format.NumberSerializer"),
    )
    fun packBytes(
        b0: Byte,
        b1: Byte,
    ) : Char = NumberSerializer.putBytes(b0, b1)

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
    @Deprecated("Moved to NumberDeserializer")
    fun unpackByte0(c: Char)
        : Byte = NumberDeserializer.getByte1(c)

    /** Unpack 2nd of 2 bytes from a character */
    @Deprecated("Moved to NumberDeserializer")
    fun unpackByte1(c: Char)
        : Byte = NumberDeserializer.getByte2(c)

    /** Unpack 1st of 2 bytes from a character */
    fun unpackUByte0(c: Char)
        : UByte = NumberDeserializer.getByte1(c).toUByte()

    /** Unpack 2nd of 2 bytes from a character */
    fun unpackUByte1(c: Char)
        : UByte = NumberDeserializer.getByte2(c).toUByte()

    /** Pack a short integer into a character */
    @Deprecated(
        "Moved to NumberSerializer",
        ReplaceWith(
            "NumberSerializer.packShort(s)",
            "mathtools.numbers.format.NumberSerializer")
    )
    fun packShort(s: Short)
        : Char = NumberSerializer.putShort(s)

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

    /** Pack N 32-bit float numbers into a string of length 2N */
    @Deprecated("Moved to NumberSerializer")
    fun packFloats(
        vararg floats: Float
    ) : String = NumberSerializer.putFloats(floats)

    /** Pack a 32-bit float into a String of length 2 */
    @Deprecated(
        "Moved to NumberSerializer",
        ReplaceWith(
            "NumberSerializer.packFloat(f)",
            "mathtools.numbers.format.NumberSerializer")
    )
    fun packFloat(
        f: Float
    ) : String = NumberSerializer.putFloat(f)

    /** Unpack a 32-bit float from two 16-bit characters */
    @Deprecated("Moved to NumberDeserializer")
    fun unpackFloat(
        c1: Char,
        c2: Char,
    ) : Float {
        val i1 = c1.code.toUInt()
        val i2 = c2.code.toUInt().shl(16)
        return Float.fromBits((i1 + i2).toInt())
    }

}