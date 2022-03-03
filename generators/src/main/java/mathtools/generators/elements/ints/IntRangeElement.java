package mathtools.generators.elements.ints;

import com.google.common.math.IntMath;

/** A generator element that produces integers in a specific range.
 * Has a theoretical uniform probability of producing any integer in the range.
 * @author DK96-OS : 2022 */
public class IntRangeElement implements IntElementInterface {

    /** The lowest integer in the range (inclusive) */
    private int start;

    /** The largest integer in the range (inclusive) */
    private int end;

    /**  */
    private int bound;

    public IntRangeElement(
            int start, int end
    ) {
        if (start <= end) {
            this.start = start;
            this.end = end;
        } else {
            this.start = end;
            this.end = start;
        }
        bound = calculateBound(this.start, this.end);
    }

    @Override
    public int generate() {
        if (2 < bound)
            return start + random.nextInt(bound);
        switch (bound) {
            case 0: return start == end ? start : random.nextInt();
            case 1: case 2:
                return random.nextBoolean() ? start : end;
        }
        // Bound is negative
        // todo: Improve this later
        int trial = random.nextInt();
        byte counter = 0;
        while (!(start <= trial && trial <= end)) {
            trial = random.nextInt();
            if (++counter > 31) break;
        }
        return trial;
    }

    /** Modify the range of integers produced by this element.
     * Does not accept ranges where start is greater than end.
     * @return True if the range was able to be updated. false if no change occurred */
    public boolean setRange(
            int start, int end
    ) {
        if (start > end || this.start == start && this.end == end)
            return false;
        this.start = start;
        this.end = end;
        this.bound = calculateBound(start, end);
        return true;
    }

    /** The lowest integer in the range (inclusive) */
    public int getStart() { return start; }

    /** The largest integer in the range (inclusive) */
    public int getEnd() { return end; }

    int getBound() { return bound; }

    /** Determine the value to store as bound
     * @param start The first number in the Range
     * @param end The last number in the Range
     * @return A value representing the state of this element */
    static int calculateBound(
            int start, int end
    ) {
        if (end < start) return calculateBound(end, start);
        if (start == end) return 0;
        if (0 < start || end < -1) return 1 + end - start;
        //
        if (end == Integer.MAX_VALUE) {
            if (start == Integer.MIN_VALUE)
                return 0;   // Full Range of integers
        }
        try {
            int diff = IntMath.checkedSubtract(end, start);
            return IntMath.checkedAdd(1, diff);
        } catch (ArithmeticException e) {
            return -1;
        }
    }

}