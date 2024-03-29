package mathtools.numbers.primes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static mathtools.numbers.primes.PrimeFactoring.firstPrimeAbove;

import org.junit.jupiter.api.Test;

/** Testing [PrimeFactoring] firstPrime method
 * @author DK96-OS : 2022 */
public final class PrimeFactoringFirstPrimeTest {

    private final PrimeCacheInterface cache = new ShortPrimeCache();

    @Test
    public void testSmallFirstPrimes() {
        assertEquals(
            7, firstPrimeAbove(49, 6, cache));
        assertEquals(
            5, firstPrimeAbove(5000, 4, cache));
        assertEquals(
            5, firstPrimeAbove(10_000, 4, cache));
        assertEquals(
            5, firstPrimeAbove(1_000_000, 2, cache));
        assertEquals(
            3, firstPrimeAbove(3_000_000, 2, cache));
        assertEquals(
            5, firstPrimeAbove(3_000_000, 3, cache));
        assertEquals(
            2, firstPrimeAbove(1_000_000, 1, cache));
    }

    @Test
    public void testNullResults() {
        assertNull(
            firstPrimeAbove(49, 7, cache));
        assertNull(
            firstPrimeAbove(5000, 71, cache));
        assertNull(
            firstPrimeAbove(5000, 5, cache));
        assertNull(
            firstPrimeAbove(10_000, 5, cache));
        assertNull(
            firstPrimeAbove(1_000_000, 5, cache));
    }

    @Test
    public void testNegativeNumber() {
        assertEquals(
            5, firstPrimeAbove(-5000, 4, cache));
        assertNull(
            firstPrimeAbove(-5000, 71, cache));
        assertEquals(
            7, firstPrimeAbove(-49, 6, cache));
    }

    @Test
    public void testNegativeLimit() {
        assertThrows(
            IllegalArgumentException.class,
            () -> firstPrimeAbove(5000, -4, cache)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> firstPrimeAbove(-49, -6, cache)
        );
    }

    @Test
    public void testMinValue() {
        assertNull(
            firstPrimeAbove(Long.MIN_VALUE, 200, cache));
    }

    @Test
    public void testNumberBaseCases() {
        assertNull(
            firstPrimeAbove(1, 10, cache));
        assertNull(
            firstPrimeAbove(0, 10, cache));
        assertNull(
            firstPrimeAbove(-1, 10, cache));
    }

}