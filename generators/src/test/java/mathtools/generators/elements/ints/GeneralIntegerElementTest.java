package mathtools.generators.elements.ints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [GeneralIntegerElement]
 * @author DK96-OS : 2022 */
public final class GeneralIntegerElementTest {

    private GeneralIntegerElement mElement;

    @BeforeEach
    void testSetup() {
        mElement = new GeneralIntegerElement();
    }

    @Test
    void testIntRangeElementUpdates() {
        // The initial element
        final IntRangeElement innerElem =
                (IntRangeElement) mElement.getElement();
        assertEquals(
                0, innerElem.getStart());
        assertEquals(
                Integer.MAX_VALUE, innerElem.getEnd());
        // Update the range
        assertTrue(
                mElement.setSimpleElement(5, 10));
        assertEquals(
                5, innerElem.getStart());
        assertEquals(
                5, innerElem.getStart());
    }

    @Test
    void testIntRangeElementGeneratedValues() {
        assertTrue(
                mElement.setSimpleElement(88, 88));
        assertEquals(
                88, mElement.generate());
        //
        assertTrue(
                mElement.setSimpleElement(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(
                Integer.MAX_VALUE, mElement.generate());
        //
        assertTrue(
                mElement.setSimpleElement(Integer.MIN_VALUE, Integer.MIN_VALUE));
        assertEquals(
                Integer.MIN_VALUE, mElement.generate());
    }

    @Test
    void testSwitchElementsToIntRange() {
        mElement = new GeneralIntegerElement(
                () -> (6789 + 876996) % 7807
        );
        assertTrue(
                mElement.setSimpleElement(5, 5));
        assertEquals(
                5, mElement.generate());
    }

}