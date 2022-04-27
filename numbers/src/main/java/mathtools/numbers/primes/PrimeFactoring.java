package mathtools.numbers.primes;

import static mathtools.numbers.factors.IntOperations.exponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import mathtools.numbers.factors.BitFactoring;
import mathtools.numbers.factors.Factoring;
import mathtools.numbers.factors.NumberFactors;
import mathtools.numbers.structs.IntPair;

/** Functions that help factor numbers by primes.
 * @author DK96-OS : 2022 */
public final class PrimeFactoring {

    /** Obtain lowest Prime Number Factor that is greater than the limit.
     *  Checks all primes below limit and divides them from the number.
     * @param number The number to check for prime factors
     * @param limit The maximum value of any prime number that can be allowed
     * @param cache A PrimeCacheBase instance for obtaining Prime Numbers
     * @return The first prime number above limit, or null if none found
     */
    @Nullable
    public static Long firstPrimeAbove(
        long number,
        final long limit,
        @Nonnull final PrimeCacheInterface cache
    ) throws IllegalArgumentException {
        // Validate Arguments
        if (2 > limit || 3 > number && -3 < number) return null;
        // Negate negative numbers
        if (0 > number) {
            // Protect against MinValue
            if (Long.MIN_VALUE == number) return null;
            number = -number;
        }
        // Eliminate 2 if possible - use efficient bit factor method
        if (BitFactoring.isProductOf2(number)) {
            number = Factoring.divideOutFactor(number, 2);
        }
        // Get the limitations of the cache
        final int maxIndex = cache.getMaxIndex();
        //
        for (int primeIndex = 1; primeIndex <= maxIndex; primeIndex++) {
            // Obtain the next prime from cache
            final int testPrime = cache.getPrime(primeIndex);
            // If the test prime is above limit
            if (limit < testPrime) {
                // Check if test prime divides the number
                if (0 == number % testPrime) return (long) testPrime;
            } else {
                // Try to reduce the number by factoring
                final long factoredProduct = Factoring.divideOutFactor(
                    number, testPrime
                );
                // If the number was factored
                if (factoredProduct != number) {
                    // If the number is now below the limit
                    if (factoredProduct <= limit) return null;
                    // Update
                    number = factoredProduct;
                }
            }
        }
        return null;
    }

    /** Determine if number contains prime above limit.
     * @param number The number to check.
     * @param limit The maximum prime number allowed.
     * @return Whether number contains a prime factor above the limit.
     */
    public static boolean hasPrimeAbove(
        long number,
        final long limit,
        @Nonnull final PrimeCacheInterface cache
    ) {
        // Flip all negative limits
        if (0 > limit) {
            // Protect against MinValue
            if (Long.MIN_VALUE == limit) return false;
            return hasPrimeAbove(number, -limit, cache);
        }
        // Limit is greater than number, there is no checking necessary
        if (limit >= number) return false;
        // Remove any factors of 2
        if (BitFactoring.isProductOf2(number))
            number = Factoring.divideOutFactor(number, 2);
        //
        int primeIdx = 1;
        int testPrime = cache.getPrime(primeIdx);
        while (testPrime <= limit) {
            number = Factoring.divideOutFactor(number, testPrime);
            if (number <= limit) return false;
            testPrime = cache.getPrime(++primeIdx);
        }
        return true;
    }

    /** Determine the prime factors, including the power.
     * @param product The assumed product to factor
     * @return A List of Integer Pairs. The factor is stored first, then the power.
     */
    @Nonnull
    public static List<IntPair> getPrimeFactors(
        final int product,
        @Nonnull final PrimeCacheInterface cache
    ) {
        if (1 >= product) return Collections.emptyList();
        //
        final ArrayList<IntPair> list = new ArrayList<>();
        long remainingComposite;
        //
        if (BitFactoring.isProductOf2(product)) {
            final int count = NumberFactors.countFactor(2, product);
            remainingComposite = product / exponent(2, count).getFirst();
            list.add(new IntPair(2, count));
            if (3 > remainingComposite) return list;
        } else
            remainingComposite = product;
        //
        int testIdx = 1;
        int testPrime = 3;
        //
        while (1 < remainingComposite &&
            testPrime <= remainingComposite
        ) {
            final int exp = NumberFactors.countFactor(
                testPrime, remainingComposite
            );
            if (0 < exp) {
                remainingComposite /= (1 < exp) ?
                    exponent(testPrime, exp).getFirst() : testPrime;
                list.add(new IntPair(testPrime, exp));
            }
            testPrime = cache.getPrime(++testIdx);
        }
        return list;
    }

    private PrimeFactoring() {}

}