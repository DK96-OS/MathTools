package mathtools.numbers.factors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static mathtools.numbers.factors.Factoring.divideOutFactor;

import org.junit.jupiter.api.Test;

/** Testing [Factoring] functions
 * @author DK96-OS : 2018 - 2022 */
public final class FactoringDivideOutFactorTest {

    @Test
    void testIntFactorInvalid() {
        assertEquals(30, divideOutFactor(30, (int) 0));
        assertEquals(30, divideOutFactor(30, (int) 1));
        assertEquals(30, divideOutFactor(30, (int) -1));
    }

    @Test
    void testLongFactorInvalid() {
        assertEquals(30, divideOutFactor(30, (long) 0));
        assertEquals(30, divideOutFactor(30, (long) 1));
        assertEquals(30, divideOutFactor(30, (long) -1));
    }

    @Test
    void testIntFactorNegative() {
        assertEquals(20, divideOutFactor(140, (int) -7));
    }

    @Test
    void testLongFactorNegative() {
        assertEquals(20, divideOutFactor(140, (long) -7));
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

}