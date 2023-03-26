package mathtools.numbers.strict;

import com.google.common.math.LongMath;

/** A selection of consecutive long values.
 * @author DK96-OS : 2023
 */
public interface LongRange {

    /** The lowest long in the range.
     * @return Start Value.
     */
    long getStartValue();

    /** The largest long in the range.
     * @return Last Value.
     */
    long getLastValue();

    /** Determine whether this Range contains a value.
     * @param value The value to check.
     * @return True if value is in Range (endpoints included).
     */
    default boolean contains(
        final long value
    ) {
        return getStartValue() <= value
            && value <= getLastValue();
    }

    /** Obtain the Size of the Range.
     * @return The number of values in this Range.
     */
    default long getSize() throws ArithmeticException {
        return LongMath.checkedAdd(
            LongMath.checkedSubtract(
                getLastValue(), getStartValue()
            ), 1L
        );
    }

}