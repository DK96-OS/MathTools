package mathtools.numbers.factors;

import org.junit.jupiter.api.Test;

import static mathtools.numbers.factors.BitFactoring.isProductOf2;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testing the BitFactoring functions
 * @author DK96-OS : 2022 */
public class BitFactoringTest {

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

}