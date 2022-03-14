package mathtools.numbers.primes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import mathtools.numbers.factors.BitFactoring;
import mathtools.numbers.factors.Factoring;

/** Functions that help factor numbers by primes
 * @author DK96-OS : 2022 */
public final class PrimeFactoring {

    private PrimeFactoring() {}

    /** Obtain lowest Prime Number Factor that is greater than the limit.
     *  Checks all primes below limit and divides them from the number.
     * @param number The number to check for prime factors
     * @param limit The maximum value of any prime number that can be allowed
     * @param cache A PrimeCacheBase instance for obtaining Prime Numbers
     * @return The first prime number above limit, or null if none found */
    @Nullable
    public static Long firstPrimeAbove(
            long number,
            final long limit,
            @Nonnull final PrimeCacheBase cache
    ) throws IllegalArgumentException {
        // Validate Arguments
        if (limit < 2 || number < 3 && -3 < number )
            return null;
        // Handle negative products
        if (number < 0) {
            if (number == Long.MIN_VALUE)
                return null;
            else
                number = -number;
        }
        // Eliminate 2 if possible - use efficient bit factor method
        if (BitFactoring.isProductOf2(number)) {
            number = Factoring.divideOutFactor(number, 2);
        }
        final int maxIndex = cache.getMaxIndex();
        //
        for (int primeIndex = 1; primeIndex <= maxIndex; primeIndex++) {
            // Obtain the next prime from cache
            final int testPrime = cache.getPrime(primeIndex);
            // If the test prime is above limit
            if (limit < testPrime) {
                // Check if test prime divides the number
                if (number % testPrime == 0)
                    return (long) testPrime;
            } else {
                // Try to reduce the number by factoring
                final long factoredProduct = Factoring.divideOutFactor(
                        number, testPrime
                );
                // If the number was factored
                if (factoredProduct != number) {
                    // If the number is now below the limit
                    if (factoredProduct <= limit)
                        return null;
                    number = factoredProduct;
                }
            }
        }
        return null;
    }

    /** Determine if number contains prime above limit
     * @param number The number to check
     * @param limit The maximum prime number allowed
     * @return Whether number contains a prime factor above the limit */
    public static boolean hasPrimeAbove(
            long number,
            final long limit,
            @Nonnull final PrimeCacheBase cache
    ) {
        if (limit >= number) return false;
        if (BitFactoring.isProductOf2(number)) {
            number = Factoring.divideOutFactor(number, 2);
        }
        int primeIdx = 1;
        int testPrime = cache.getPrime(primeIdx);
        while (testPrime <= limit) {
            number = Factoring.divideOutFactor(number, testPrime);
            if (number <= limit) return false;
            testPrime = cache.getPrime(++primeIdx);
        }
        return true;
    }

}