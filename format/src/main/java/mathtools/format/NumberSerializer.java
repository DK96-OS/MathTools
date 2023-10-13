package mathtools.format;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Encode Numbers into Strings and characters
 * @author DK96-OS : 2021 - 2022 */
public final class NumberSerializer {

    /** Put two 8-bit bytes into a 16-bit char
     * @param first The first 8 bits of the char
     * @param second The last 8 bits of the char
     * @return A char containing the input bytes information */
    public static char putBytes(
        final byte first,
        final byte second
    ) {
        // Ensure that the negative values are unique
        final int i0 = -1 < first ? (int) first : 256 + first;
        final int i1 = -1 < second ? (int) second : 256 + second;
        // Combine the values, convert to char
        return (char) ((i0 << 8) + i1);
    }

    /** Put a 16-bit short into a 16-bit char
     * @param value The short to convert
     * @return A char containing the input short information */
    public static char putShort(
        final short value
    ) {
        return 0 > value ? (char) (value + 65536) : (char) value;
    }

    /** Put a 32-bit Float into a String of length 2
     * @param value The Float to be serialized
     * @return A String of length 2 */
    @Nonnull
    public static char[] putFloat(
        final float value
    ) {
        final int i = Float.floatToIntBits(value);
        return new char[] {
            (char) i,
            (char) (i >>> 16)
        };
    }

    /** Put an array of Float into a char array
     * @param floats The array to convert
     * @return Characters obtained from the float array
     */
    @Nullable
    public static char[] putFloats(
        @Nonnull final float[] floats
    ) {
        final int charArrayLength = 2 * floats.length;
        if (3 > charArrayLength) {
            if (1 == floats.length)
                return putFloat(floats[0]);
            else
                return null;
        }
        final char[] array = new char[charArrayLength];
        int index = 0;
        for (
            final float value : floats
        ) {
            final int bits = Float.floatToRawIntBits(value);
            // Conversion to char ignores the larger 16 bits
            array[index++] = (char) bits;
            array[index++] = (char) (bits >>> 16);
        }
        return array;
    }

    private NumberSerializer() {}

}