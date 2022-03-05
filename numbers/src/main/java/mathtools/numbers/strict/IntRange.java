package mathtools.numbers.strict;

/** A group of consecutive integer values
 * @author DK96-OS : 2022 */
public interface IntRange {

    /** The lowest int that can be counted by this counter */
    int getStartValue();

    /** The largest int that can be counted by this counter */
    int getLastValue();

    /** Obtain the array index of the given value in the range, or -1
     * This method assumes that the counter is using an array to store counts
     * @param value The integer value to obtain the index of
     * @return An integer index corresponding to a position in an array, or -1 if not within the range */
    default int indexOf(
            final int value
    ) {
        final int start = getStartValue();
        // Value must be within the range
        if (value < start || value > getLastValue())
            return -1;
        // Expected: index == 0 when value == start
        final int idx = value - start;
        // Ensure value is within valid range
        return (idx < 0) ? -1 : idx;
    }

}
