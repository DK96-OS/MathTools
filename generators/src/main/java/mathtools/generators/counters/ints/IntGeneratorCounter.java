package mathtools.generators.counters.ints;

import javax.annotation.Nullable;

import mathtools.generators.elements.ints.IntElementInterface;

/** Runs an Integer Element Generator alongside a Counter
 * @author DK96-OS : 2022 */
public class IntGeneratorCounter {

    private final IntElementInterface mGenerator;
    private final IntCounter mCounter;

    private Integer mInvalidValue = null;

    public IntGeneratorCounter(
            final IntElementInterface generator,
            final IntCounter counter
    ) {
        mGenerator = generator;
        mCounter = counter;
    }

    /** Run the [IntElementInterface] a number of times,
     *  counting the outcomes using [IntCounterInterface]
     * @param n The number of times to generate from the [IntElementInterface]
     * @return True if the [IntCounterInterface] counted all values successfully
     */
    public boolean countGeneratedValues(int n) {
        for (int i = 0; i < n; i++) {
            final int value = mGenerator.generate();
            if (!mCounter.count(value)) {
                mInvalidValue = value;
                return false;
            }
        }
        return true;
    }

    @Nullable
    public Integer getRejectedValue() { return mInvalidValue; }

}