package mathtools.numbers.factors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static mathtools.numbers.factors.Factoring.divideOutFactor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/** Testing [Factoring] functions
 * @author DK96-OS : 2018 - 2022 */
public final class FactoringDivideOutFactorTest {

    @Test
    void testIntFactorInvalid() {
        assertEquals(30, divideOutFactor(30, 0));
        assertEquals(30, divideOutFactor(30, 1));
        assertEquals(30, divideOutFactor(30, -1));
    }

    @Test
    void testLongFactorInvalid() {
        assertEquals(30, divideOutFactor(30, 0L));
        assertEquals(30, divideOutFactor(30, 1L));
        assertEquals(30, divideOutFactor(30, -1L));
    }

    @Test
    void testIntFactorNegative() {
        assertEquals(
            3, divideOutFactor(6, -2));
        assertEquals(
            3, divideOutFactor(24, -2));
        assertEquals(
            20, divideOutFactor(140, -7));
        // With Negative Number
        assertEquals(
            -3, divideOutFactor(-6, -2));
        assertEquals(
            -3, divideOutFactor(-24, -2));
        assertEquals(
            -3, divideOutFactor(-48, -2));
        assertEquals(
            -15, divideOutFactor(-30, -2));
    }

    @Test
    void testLongFactorNegative() {
        assertEquals(
            3, divideOutFactor(6, -2L));
        assertEquals(
            3, divideOutFactor(24, -2L));
        assertEquals(
            20, divideOutFactor(140, -7L));
        // With Negative Number
        assertEquals(
            -3, divideOutFactor(-6, -2L));
        assertEquals(
            -3, divideOutFactor(-24, -2L));
        assertEquals(
            -3, divideOutFactor(-48, -2L));
        assertEquals(
            -15, divideOutFactor(-30, -2L));
    }

    @Test
    void testIntFactorMaxValues() {
        assertEquals(140, divideOutFactor(140, Integer.MAX_VALUE));
        assertEquals(1, divideOutFactor(Integer.MAX_VALUE, Integer.MAX_VALUE));
        // Min Values
        assertEquals(140, divideOutFactor(140, Integer.MIN_VALUE));
        assertEquals(1, divideOutFactor(Integer.MIN_VALUE, Integer.MIN_VALUE));
    }

    @Test
    void testLongFactorMaxValues() {
        assertEquals(140, divideOutFactor(140, Long.MAX_VALUE));
        assertEquals(1, divideOutFactor(Long.MAX_VALUE, Long.MAX_VALUE));
        // Min Values
        assertEquals(140, divideOutFactor(140, Long.MIN_VALUE));
        assertEquals(1, divideOutFactor(Long.MIN_VALUE, Long.MIN_VALUE));
    }

    @ParameterizedTest
    @ValueSource(ints = { 2, 3, 5, 7, 11, 13, 17 })
    void testIntFactorMediumSized(
        final int factor
    ) {
        assertEquals(353, divideOutFactor(353, factor));
        assertEquals(2909, divideOutFactor(2909, factor));
        assertEquals(7211, divideOutFactor(7211, factor));
        assertEquals(9013, divideOutFactor(9013, factor));
    }

    @ParameterizedTest
    @ValueSource(longs = { 2, 3, 5, 7, 11, 13, 17 })
    void testLongFactorMediumSized(
        final long factor
    ) {
        assertEquals(353, divideOutFactor(353, factor));
        assertEquals(2909, divideOutFactor(2909, factor));
        assertEquals(7211, divideOutFactor(7211, factor));
        assertEquals(9013, divideOutFactor(9013, factor));
    }

}