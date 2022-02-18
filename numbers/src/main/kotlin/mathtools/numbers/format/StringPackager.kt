package mathtools.numbers.format

/** Package numbers inside characters and strings
 * @author DK96-OS : 2021 - 2022 */
object StringPackager {

    /** Pack two Bytes into a Char */
    fun packBytes(
        b0: Byte,
        b1: Byte,
    ) : Char {
        val first = if (b0 > -1)
            b0.toInt() else 256 + b0
        val second = if (b1 > -1)
            b1.toInt() else 256 + b1
        return (first.shl(8) + second).toChar()
    }

    /** Pack two UBytes into a Char */
    fun packUBytes(
        u0: UByte,
        u1: UByte,
    ) : Char = (
        u0.toInt().shl(8) + u1.toInt()
               ).toChar()

    /** Split a Char in half, and extract two Bytes from it */
    fun unpackBytes(c: Char)
        : Pair<Byte, Byte> = unpackByte0(c) to c.code.toByte()

    /** Unpack 1st of 2 bytes from a character */
    fun unpackByte0(c: Char)
        : Byte = c.code.ushr(8).toByte()

    /** Unpack 2nd of 2 bytes from a character */
    fun unpackByte1(c: Char)
        : Byte = c.code.toByte()

    /** Unpack 1st of 2 bytes from a character */
    fun unpackUByte0(c: Char)
        : UByte = c.code.ushr(8).toUByte()

    /** Unpack 2nd of 2 bytes from a character */
    fun unpackUByte1(c: Char)
        : UByte = c.code.toUByte()

    /** Pack a short integer into a character */
    fun packShort(s: Short)
        : Char = s.toUShort().toInt().toChar()

    /** Pack an unsigned short integer into a character */
    fun packUShort(s: UShort)
        : Char = s.toInt().toChar()

    /** Unpack a short integer from a character */
    fun unpackShort(c: Char)
        : Short = c.code.toUShort().toShort()

    /** Pack N 32-bit float numbers into a string of length 2N */
    fun packFloats(
        vararg floats: Float
    ) : String = when (floats.size) {
        1 -> packFloat(floats[0])
        else -> buildString {
            for (f in floats) {
                val i = f.toBits()
                append(i.toChar())
                append(i.ushr(16).toChar())
            }
        }
    }

    /** Pack a 32-bit float into a String of length 2 */
    fun packFloat(
        f: Float
    ) : String = buildString {
        val i = f.toBits()
        append(i.toChar())
        append(i.ushr(16).toChar())
    }

    /** Unpack a 32-bit float from two 16-bit characters */
    fun unpackFloat(
        c1: Char,
        c2: Char,
    ) : Float {
        val i1 = c1.code.toUInt()
        val i2 = c2.code.toUInt().shl(16)
        return Float.fromBits((i1 + i2).toInt())
    }

}