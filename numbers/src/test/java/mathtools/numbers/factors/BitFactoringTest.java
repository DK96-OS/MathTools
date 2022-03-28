package mathtools.numbers.factors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.numbers.factors.BitFactoring.isProductOf2;

import org.junit.jupiter.api.Test;

/** Testing the BitFactoring functions
 * @author DK96-OS : 2022 */
public final class BitFactoringTest {

    @Test
    void testProductOf2() {
        assertFalse(isProductOf2(0));
        assertFalse(isProductOf2(0L));
        for (int n = 1; n < 66; n += 2) {
            assertFalse(isProductOf2(n));
            assertFalse(isProductOf2((long) n));
        }
        for (int n = 2; n < 67; n += 2) {
            assertTrue(isProductOf2(n));
            assertTrue(isProductOf2((long) n));
        }
    }

    @Test
    void testProductOf2Negative() {
        for (int n = 1; n < 66; n += 2) {
            assertFalse(isProductOf2(-n));
            assertFalse(isProductOf2((long) -n));
        }
        for (int n = 2; n < 67; n += 2) {
            assertTrue(isProductOf2(-n));
            assertTrue(isProductOf2((long) -n));
        }
    }

    @Test
    void testProductOf2LargeInt() {
        final int lowerBoundInt = Integer.MAX_VALUE - 100;
        for (int n = Integer.MAX_VALUE; n >= lowerBoundInt; n -= 2)
            assertFalse(BitFactoring.isProductOf2(n));
        for (int n = Integer.MAX_VALUE - 1; n >= lowerBoundInt; n -= 2)
            assertTrue(BitFactoring.isProductOf2(n));
    }

    @Test
    void testProductOf2LargeLong() {
        final long lowerBound = Long.MAX_VALUE - 100;
        for (long n = Long.MAX_VALUE; n >= lowerBound; n -= 2)
            assertFalse(BitFactoring.isProductOf2(n));
        for (long n = Long.MAX_VALUE - 1; n >= lowerBound; n -= 2)
            assertTrue(BitFactoring.isProductOf2(n));
    }

}