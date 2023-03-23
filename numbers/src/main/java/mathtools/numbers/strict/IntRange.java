package mathtools.numbers.strict;

import com.google.common.math.IntMath;

/** A selection of consecutive integer values.
 * @author DK96-OS : 2022 - 2023
 */
public interface IntRange {

    /** The lowest int in this range.
     * @return Start Value.
     */
    int getStartValue();

    /** The largest int in this range.
     * @return Last Value.
     */
    int getLastValue();

    /** Determine whether this Range contains a value.
     * @param value The value to check.
     * @return True if value is in Range (endpoints included).
     */
    default boolean contains(
        final int value
    ) {
        return getStartValue() <= value
            && value <= getLastValue();
    }

    /** Obtain the Size of the Range.
     * @return The number of values in this Range.
     */
    default int getSize() throws ArithmeticException {
        return IntMath.checkedAdd(
            IntMath.checkedSubtract(
                getLastValue(), getStartValue()
            ), 1
        );
    }

}