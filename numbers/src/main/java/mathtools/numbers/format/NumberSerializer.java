package mathtools.numbers.format;

import javax.annotation.Nonnull;

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
        final int i0 = first > -1 ? (int) first : 256 + first;
        final int i1 = second > -1 ? (int) second : 256 + second;
        return (char) ((i0 << 8) + i1);
    }

    /** Put a 16-bit short into a 16-bit char
     * @param value The short to convert
     * @return A char containing the input short information */
    public static char putShort(
        final short value
    ) {
        return value < 0 ? (char) (value + 65536) : (char) value;
    }

    /** Put a 32-bit Float into a String of length 2
     * @param value The Float to be serialized
     * @return A String of length 2 */
    @Nonnull
    public static String putFloat(
        final float value
    ) {
        final int i = Float.floatToIntBits(value);
        return String.valueOf(new char[] {
                (char) i,
                (char) (i >>> 16)
        });
    }

    /** Put an array of Float into a String
     * @param floats The array to serialize
     * @return A String containing the data from the array */
    @Nonnull
    public static String putFloats(
        @Nonnull final float[] floats
    ) {
        if (floats.length < 2) {
            if (floats.length == 1) return putFloat(floats[0]);
            else return "";
        }
        final StringBuilder builder = new StringBuilder(2 * floats.length);
        for (float aFloat : floats) {
            final int bits = Float.floatToRawIntBits(aFloat);
            builder.append((char) bits);
            builder.append((char) (bits >>> 16));
        }
        return builder.toString();
    }

    private NumberSerializer() {}

}