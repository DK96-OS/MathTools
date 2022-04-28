package mathtools.numbers.primes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static mathtools.numbers.primes.PrimeFactoring.firstPrimeAbove;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import javax.annotation.Nonnull;

import mathtools.numbers.primes.cache.PrimeCacheArgumentProvider;

/** Testing [PrimeFactoring] firstPrime method
 * @author DK96-OS : 2022 */
public final class PrimeFactoringFirstPrimeTest {

    private final PrimeCacheInterface cache = new BytePrimeCache();

    @ParameterizedTest
    @ArgumentsSource(
        PrimeCacheArgumentProvider.class
    )
    public void testFirstPrimeAbove(
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
    @ArgumentsSource(
        PrimeCacheArgumentProvider.class
    )
    public void testFirstPrimeAboveArgsNegative(
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
    public void testFirstPrimeAboveArgsMinValue() {
        final PrimeCacheInterface cache = new ShortPrimeCache();
        assertNull(
            firstPrimeAbove(Long.MIN_VALUE, 200, cache));
    }

    @Test
    public void testFirstPrimeAboveArgsProductBaseCases() {
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

    @ParameterizedTest
    @ArgumentsSource(
        PrimeCacheArgumentProvider.class
    )
    public void testFirstPrimeAboveLimit(
        final PrimeCacheInterface cache
    ) {
        assertNull(
            firstPrimeAbove(5000, 71, cache));
        assertNull(
            firstPrimeAbove(5000, 5, cache));
        assertEquals(
            5,
            firstPrimeAbove(5000, 4, cache));
        assertEquals(
            5,
            firstPrimeAbove(5000, 4, cache));
        assertEquals(
            7,
            firstPrimeAbove(49, 6, cache));
        assertNull(
            firstPrimeAbove(49, 7, cache));
    }

    @ParameterizedTest
    @ArgumentsSource(
        PrimeCacheArgumentProvider.class
    )
    public void testFirstPrimeAboveLimitNegative(
        final PrimeCacheInterface cache
    ) {
        // Ignore negative sign on product
        assertNull(
            firstPrimeAbove(-5000, 71, cache));
        assertEquals(
            5,
            firstPrimeAbove(-5000, 4, cache)
        );
        assertEquals(
            7,
            firstPrimeAbove(-49, 6, cache)
        );
        // Null when limit is negative
        assertNull(
            firstPrimeAbove(-5000, -4, cache));
        assertNull(
            firstPrimeAbove(-49, -6, cache));
    }

    @ParameterizedTest
    @ArgumentsSource(
        PrimeCacheArgumentProvider.class
    )
    public void testFirstPrimeAboveLimitInvalid(
        final PrimeCacheInterface cache
    ) {
        // Null when product is 1 or zero
        assertNull(
            firstPrimeAbove(-1, 5, cache));
        assertNull(
            firstPrimeAbove(0, 5, cache));
        assertNull(
            firstPrimeAbove(1, 5, cache));
    }

}