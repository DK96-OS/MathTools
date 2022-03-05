package mathtools.numbers.format;

import javax.annotation.Nonnull;

/** Encode Numbers into Strings and characters
 * @author DK96-OS : 2021 - 2022 */
public final class NumberSerializer {

    private NumberSerializer() {}

    /** Pack two 8-bit bytes into a 16-bit char
     * @param first The first 8 bits of the char
     * @param second The last 8 bits of the char
     * @return A char containing the input bytes information */
    public static char packBytes(
            final byte first,
            final byte second
    ) {
        final int i0 = first > -1 ? (int) first : 256 + first;
        final int i1 = second > -1 ? (int) second : 256 + second;
        return (char) ((i0 << 8) + i1);
    }

    /** Convert a 16-bit short into a 16-bit char
     * @param value The short to convert
     * @return A char containing the input short information */
    public static char packShort(
            final short value
    ) {
        return value < 0 ? (char) (value + 65536) : (char) value;
    }

    /** Pack a 32-bit Float into a String of length 2
     * @param value The Float to be serialized
     * @return A String of length 2 */
    @Nonnull
    public static String packFloat(
            final float value
    ) {
        final int i = Float.floatToIntBits(value);
        return String.valueOf(new char[] {
                (char) i,
                (char) (i >>> 16)
        });
    }

}
