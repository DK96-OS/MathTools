package mathtools.generators.counters.ints;

import javax.annotation.Nonnull;

/** A tree of [IntRangeCounter] instances
 * @author DK96-OS : 2022 */
public final class IntCounterTree implements IntRangeCounter {

    /** Counter for the lower range of values */
    private final IntRangeCounter mLeft;

    /** Counter for the higher range of values */
    private final IntRangeCounter mRight;

    /** Create a new Tree of [IntRangeCounter]
     * @param left The lower range of values
     * @param right The higher range of values */
    public IntCounterTree(
            @Nonnull final IntRangeCounter left,
            @Nonnull final IntRangeCounter right
    ) {
        if (left.getLastValue() >= right.getStartValue())
            throw new IllegalArgumentException(
                    "Invalid sub-tree ranges"
            );
        mLeft = left;
        mRight = right;
    }

    @Override
    public int getStartValue() {
        return mLeft.getStartValue();
    }

    @Override
    public int getLastValue() {
        return mRight.getStartValue();
    }

    @Override
    public boolean count(
            final int value
    ) {
        if (value <= mLeft.getLastValue())
            return mLeft.count(value);
        else
            return mRight.count(value);
    }

}