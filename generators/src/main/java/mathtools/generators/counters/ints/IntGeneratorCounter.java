package mathtools.generators.counters.ints;

import javax.annotation.Nullable;

import mathtools.generators.elements.ints.IntElementInterface;

/** Runs an Integer Element Generator alongside a Counter
 * @author DK96-OS : 2022 */
public final class IntGeneratorCounter {

    private final IntElementInterface mGenerator;

    private final IntCounter mCounter;

    /** The last value that was generated */
    private Integer mPrevValue = null;

    private Integer mInvalidValue = null;

    public IntGeneratorCounter(
            final IntElementInterface generator,
            final IntCounter counter
    ) {
        mGenerator = generator;
        mCounter = counter;
    }

    /** Run the Integer Generator Element a number of times,
     *  counting the outcomes using [IntCounterInterface]
     * @param nValues The number of values to generate
     * @return True if the [IntCounter] counted all values successfully
     */
    public boolean countGeneratedValues(
            final int nValues
    ) {
        // Remove any invalid value that may be stored
        mInvalidValue = null;
        // Validate count argument
        if (nValues < 1) return false;
        // Initialization required before the loop
        int value = mGenerator.generate();
        // If the Counter rejects this value
        if (!mCounter.count(value)) {
            mInvalidValue = value;
            return false;
        }
        for (int i = 1; i < nValues; i++) {
            value = mGenerator.generate();
            if (!mCounter.count(value)) {
                mInvalidValue = value;
                break;
            }
        }
        mPrevValue = value; // The last value that was generated is saved
        return true;
    }

    @Nullable
    public Integer getRejectedValue() { return mInvalidValue; }

    @Nullable
    public Integer getPreviousValue() { return mPrevValue; }

    public IntCounter getCounter() { return mCounter; }

}