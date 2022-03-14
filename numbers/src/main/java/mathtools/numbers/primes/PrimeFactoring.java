package mathtools.numbers.primes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import mathtools.numbers.factors.BitFactoring;
import mathtools.numbers.factors.Factoring;
import mathtools.numbers.factors.NumberFactors;

/** Functions that help factor numbers by primes
 * @author DK96-OS : 2022 */
public final class PrimeFactoring {

    private PrimeFactoring() {}

    /** Obtain lowest Prime Number Factor that is greater than the limit.
     *  First, checks all primes below limit and divides them from the product.
     *
     * @param product The product to check for prime factors
     * @param limit The maximum value of any prime number that can be allowed
     * @param cache A PrimeCacheBase instance for obtaining Prime Numbers
     * @return The first prime number above limit, or null if none found */
    @Nullable
    public static Long firstPrimeAbove(
            long product,
            final long limit,
            @Nonnull final PrimeCacheBase cache
    ) throws IllegalArgumentException {
        // Validate Arguments
        if (limit < 2 || product < 3 && -3 < product )
            return null;
        // Handle negative products
        if (product < 0) {
            if (product == Long.MIN_VALUE)
                return null;
            else
                product = -product;
        }
        // Eliminate 2 if possible - use efficient bit factor method
        if (BitFactoring.isProductOf2(product)) {
            product = NumberFactors.INSTANCE.divideOutFactor(2, product);
        }
        final int maxIndex = cache.getMaxIndex();
        //
        for (int primeIndex = 1; primeIndex <= maxIndex; primeIndex++) {
            // Obtain the next prime from cache
            final int testPrime = cache.getPrime(primeIndex);
            // If the test prime is above limit
            if (limit < testPrime) {
                // Check if test prime divides the product
                if (product % testPrime == 0)
                    return (long) testPrime;
            } else {
                // Try to reduce the product by factoring
                final long factoredProduct = NumberFactors.INSTANCE.divideOutFactor(
                        testPrime, product
                );
                // If the product was factored
                if (factoredProduct != product) {
                    // If the product is now below the limit
                    if (factoredProduct <= limit)
                        return null;
                    product = factoredProduct;
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