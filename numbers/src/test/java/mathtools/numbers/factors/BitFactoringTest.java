package mathtools.numbers.factors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.numbers.factors.BitFactoring.divide2;
import static mathtools.numbers.factors.BitFactoring.isProductOf2;

import org.junit.jupiter.api.Test;

import mathtools.numbers.primes.PrimeCacheInterface;
import mathtools.numbers.primes.ShortPrimeCache;

/** Testing the BitFactoring functions
 * @author DK96-OS : 2022 */
public final class BitFactoringTest {

	private final PrimeCacheInterface cache = new ShortPrimeCache();

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
        for (int n = 1; n < Short.MAX_VALUE; n += 2) {
            assertFalse(isProductOf2(-n));
            assertFalse(isProductOf2((long) -n));
        }
        for (int n = 2; n > 0; n *= 2) {
            assertTrue(isProductOf2(-n));
            assertTrue(isProductOf2((long) -n));
        }
    }

    @Test
    void testProductOf2LargeInt() {
        final int lowerBoundInt = Integer.MAX_VALUE - 100;
        for (int n = Integer.MAX_VALUE; n >= lowerBoundInt; n -= 2)
            assertFalse(isProductOf2(n));
        for (int n = Integer.MAX_VALUE - 1; n >= lowerBoundInt; n -= 2)
            assertTrue(isProductOf2(n));
    }

    @Test
    public void testProductOf2LargeLong() {
        final long lowerBound = Long.MAX_VALUE - 100;
        for (long n = Long.MAX_VALUE; n >= lowerBound; n -= 2)
            assertFalse(BitFactoring.isProductOf2(n));
        for (long n = Long.MAX_VALUE - 1; n >= lowerBound; n -= 2)
            assertTrue(BitFactoring.isProductOf2(n));
    }

    @Test
    public void testProductOf2_PrimeNumbers_ReturnFalse() {
        for (int index = 1; index < cache.getMaxIndex(); ++index) {
            final int prime = cache.getPrime(index);
            assertFalse(isProductOf2(prime));
            assertFalse(isProductOf2((long) prime));
        }
    }

    @Test
    public void testProductOf2_EdgeCases() {
        assertFalse(
            isProductOf2(0));
        assertFalse(
            isProductOf2(0L));
        assertFalse(
            isProductOf2(Integer.MAX_VALUE));
        assertFalse(
            isProductOf2(Long.MAX_VALUE));
    }

    @Test
    public void testProductOf2_EdgeCases_ReturningTrue() {
        assertTrue(
            isProductOf2(Integer.MIN_VALUE));
        assertTrue(
            isProductOf2(Integer.MAX_VALUE + 1L));
        assertTrue(
            isProductOf2(Long.MIN_VALUE));
    }

    @Test
    public void testDivideBy2_PowersOf2_Returns1() {
    	long product = 1;
    	int power = 1;
    	for (; power < 31; ++power) {
            product *= 2;
            int intResult = BitFactoring.divide2((int) product);
            long longResult = divide2(product);
            assertEquals(1, intResult);
            assertEquals(1L, longResult);
    	}
    }
    
    @Test
    public void testDivideBy2_NegativePowersOf2_ReturnsNegative1() {
        long product = -2L;
        for (int power = 1; power < 31; ++power) {
            int intResult = BitFactoring.divide2((int) product);
            long longResult = divide2(product);
            assertEquals(-1, intResult);
            assertEquals(-1L, longResult);
            product *= 2;
        }
    }
    
    @Test
    public void testDivideBy2_PrimeNumbers_ReturnsPrime() {
    	for (int index = 1; index < cache.getMaxIndex() / 2; ++index) {
            final int prime = cache.getPrime(index);
            int intResult = BitFactoring.divide2(prime);
            long longResult = divide2((long) prime);
            assertEquals(prime, intResult);
            assertEquals((long) prime, longResult);
        }
    }
    
    @Test
    public void testDivideBy2_EdgeCases() {
        assertEquals(
            -1, BitFactoring.divide2(Integer.MIN_VALUE)
        );
        assertEquals(
            1, divide2(Integer.MAX_VALUE + 1L)
        );
        assertEquals(
                -1L, divide2(Long.MIN_VALUE)
        );
        assertEquals(
                Long.MAX_VALUE, divide2(Long.MAX_VALUE)
        );
    }

}