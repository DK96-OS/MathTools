package mathtools.generators.elements.ints;

import java.security.SecureRandom;
import java.util.Random;

/** A generator element that produces integers in a specific range.
 * Has a theoretical uniform probability of producing any integer in the range.
 * @author DK96-OS : 2022 */
public class IntRangeElement implements IntElementInterface {

    /** The lowest integer in the range (inclusive) */
    private int mStart;

    /** The largest integer in the range (inclusive) */
    private int mEnd;

    /** A key state parameter that determines the generator behavior.
     * Values of 0, 1 and 2 are handled specifically.
     * When positive, is used as an argument in Random.nextInt(bound).
     * When negative, integers are generated until one in range appears. */
    private int mBound;

    private final Random mRandom;

    /** Constructs with given IntRange parameters, and SecureRandom generator
     * Validates start and end values, will reverse them to produce positive range
     * @param start The first value in the range
     * @param end The last value in the range */
    public IntRangeElement(
            final int start,
            final int end
    ) {
        if (start <= end) {
            mStart = start;
            mEnd = end;
        } else {
            mStart = end;
            mEnd = start;
        }
        mBound = calculateBound(mStart, mEnd);
        mRandom = new SecureRandom();
    }

    /** Constructs with given IntRange parameters, and RNG of choice
     * Validates start and end values, will reverse them to produce positive range
     * @param start The first value in the range
     * @param end The last value in the range
     * @param random The RNG to use */
    IntRangeElement(
            final int start,
            final int end,
            final Random random
    ) {
        if (start <= end) {
            mStart = start;
            mEnd = end;
        } else {
            mStart = end;
            mEnd = start;
        }
        mBound = calculateBound(mStart, mEnd);
        mRandom = random;
    }

    @Override
    public int generate() {
        if (2 < mBound)
            return mStart + mRandom.nextInt(mBound);
        switch (mBound) {
            case 0: return mStart == mEnd ? mStart : mRandom.nextInt();
            case 1: case 2:
                return mRandom.nextBoolean() ? mStart : mEnd;
        }
        // Bound is negative
        int trial = mRandom.nextInt();
        byte counter = 0;
        while (!(mStart <= trial && trial <= mEnd)) {
            trial = mRandom.nextInt();
            if (++counter > 31) break;
        }
        return trial;
    }

    /** Modify the range of integers produced by this element.
     * Does not accept ranges where start is greater than end.
     * @return True if the range was able to be updated. false if no change occurred */
    public boolean setRange(
            final int start,
            final int end
    ) {
        if (start > end || mStart == start && mEnd == end)
            return false;
        mStart = start;
        mEnd = end;
        mBound = calculateBound(start, end);
        return true;
    }

    /** The lowest integer in the range (inclusive) */
    public int getStart() { return mStart; }

    /** The largest integer in the range (inclusive) */
    public int getEnd() { return mEnd; }

    int getBound() { return mBound; }

    /** Determine the value to store as bound
     * @param start The first number in the Range
     * @param end The last number in the Range
     * @return A value representing the state of this element */
    static int calculateBound(
            final int start,
            final int end
    ) {
        if (end < start) return calculateBound(end, start);
        if (start == end) return 0;
        if (0 < start || end < -1) return 1 + end - start;
        //
        if (end == Integer.MAX_VALUE) {
            if (start == Integer.MIN_VALUE)
                return 0;   // Full Range of integers
        }
        return end + 1 - start;
    }

}