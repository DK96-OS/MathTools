package mathtools.numbers.primes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static mathtools.numbers.primes.PrimeFactoring.firstPrimeAbove;
import static mathtools.numbers.primes.PrimeFactoring.hasPrimeAbove;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import javax.annotation.Nonnull;

import mathtools.numbers.primes.cache.PrimeCacheArgumentProvider;

/** Testing [PrimeFactoring] functions
 * @author DK96-OS : 2022 */
public final class PrimeFactoringTest {

    private final PrimeCacheInterface cache = new BytePrimeCache();

    @ParameterizedTest
    @ArgumentsSource(PrimeCacheArgumentProvider.class)
    void testFirstPrimeAbove(
            @Nonnull final PrimeCacheInterface cache
    ) {
        assertEquals(
                7, firstPrimeAbove(49, 6, cache));
        assertNull(
                firstPrimeAbove(49, 7, cache));
        //
        assertEquals(
                5, firstPrimeAbove(5000, 4, cache));
        assertEquals(
                5, firstPrimeAbove(5000, 4, cache));
        //
        assertNull(
                firstPrimeAbove(5000, 71, cache));
        assertNull(
                firstPrimeAbove(5000, 5, cache));
    }

    @ParameterizedTest
    @ArgumentsSource(PrimeCacheArgumentProvider.class)
    void testFirstPrimeAboveArgsNegative(
            @Nonnull final PrimeCacheInterface cache
    ) {
        // Check negative inputs
        assertEquals(
                5, firstPrimeAbove(-5000, 4, cache));
        assertNull(
                firstPrimeAbove(-5000, 71, cache));
        // Null when limit is negative
        assertNull(
                firstPrimeAbove(5000, -4, cache));
        assertNull(
                firstPrimeAbove(-49, -6, cache));
    }

    @Test
    void testFirstPrimeAboveArgsMinValue() {
        PrimeCacheInterface cache = new ShortPrimeCache();
        assertNull(
                firstPrimeAbove(Long.MIN_VALUE, 200, cache));
    }

    @Test
    void testFirstPrimeAboveArgsProductBaseCases() {
        PrimeCacheInterface cache = new BytePrimeCache();
        assertNull(
                firstPrimeAbove(2, 2, cache));
        assertNull(
                firstPrimeAbove(2, 10, cache));
        assertNull(
                firstPrimeAbove(1, 10, cache));
        assertNull(
                firstPrimeAbove(0, 10, cache));
        assertNull(
                firstPrimeAbove(-1, 10, cache));
        assertNull(
                firstPrimeAbove(-2, 2, cache));
    }

    @Test
    void testHasPrimeGreaterLimit() {
        assertFalse(
            hasPrimeAbove(29, 29, cache));
        assertFalse(
            hasPrimeAbove(29, 31, cache));
    }

    @Test
    void testHasPrimeNegativeLimit() {
        assertFalse(
            hasPrimeAbove(29, -29, cache));
        assertFalse(
            hasPrimeAbove(29, -31, cache));
    }

}