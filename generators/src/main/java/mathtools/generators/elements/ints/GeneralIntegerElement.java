package mathtools.generators.elements.ints;

/** A generalized Integer Generator Element
 * Provides the ability to easily switch Element type without managing instances
 * @author DK96-OS : 2020 - 2022 */
public class GeneralIntegerElement implements IntElementInterface {

    protected IntElementInterface mElement;

    IntElementInterface getElement() { return mElement; }

    /** Create General Element with a uniform range of positive Integer
     *  Instantiates an [IntRangeElement] with range (0, Integer.MaxValue) */
    public GeneralIntegerElement() {
        mElement = new IntRangeElement(0, Integer.MAX_VALUE);
    }

    /** Wrap any Integer Element */
    public GeneralIntegerElement(
            final IntElementInterface element
    ) {
        mElement = element;
    }

    @Override
    public int generate() { return mElement.generate(); }

    /** Use an [IntRangeElement] with the given Range
     * @param start The first value in the range
     * @param end The last value in the range
     * @return Whether the operation was successful. See [IntRangeElement.setRange()] */
    public boolean setSimpleElement(
            final int start,
            final int end
    ) {
        final IntElementInterface exist = mElement;
        if (exist instanceof IntRangeElement)
            return ((IntRangeElement) exist).setRange(start, end);
		else
            mElement = new IntRangeElement(start, end);
        return true;
    }

}