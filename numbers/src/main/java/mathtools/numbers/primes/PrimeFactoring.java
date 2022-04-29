package mathtools.numbers.primes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import mathtools.numbers.factors.BitFactoring;
import mathtools.numbers.factors.Factoring;
import mathtools.numbers.factors.IntOperations;
import mathtools.numbers.factors.NumberFactors;
import mathtools.numbers.structs.IntPair;

/** Functions that help factor numbers by primes.
 * @author DK96-OS : 2022 */
public final class PrimeFactoring {

    /** Obtain lowest Prime Number Factor that is greater than the limit.
     *  Checks all primes below limit and divides them from the number.
     * @param number The number to decompose into prime factors
     * @param limit The maximum prime number to ignore. Should be positive, greater than 1.
     * @param cache A PrimeCache Interface that will provide Prime Numbers
     * @return The first prime number above limit, or null if none found
     * @throws IllegalArgumentException If the Limit is zero or negative
     */
    @Nullable
    public static Long firstPrimeAbove(
        long number,
        final long limit,
        @Nonnull final PrimeCacheInterface cache
    ) throws IllegalArgumentException {
        // Validate Limit, must be positive
        if (1 > limit)
            throw new IllegalArgumentException(
                String.format(
                    "Invalid Limit: %d", limit
                )
            );
        // Validate Number, make it positive
        if (3 > number) {
            if (0 > number) {
                // Protect against MinValue
                if (Long.MIN_VALUE == number)
                    return null;
                number = -number;
            } else
                return null;
        }
        // Check for factor of 2 using Bits
        if (BitFactoring.isProductOf2(number)) {
            // If 2 is over the limit, then first Prime is found
            if (2 > limit)
                return 2L;
            // Divide out 2
            number = Factoring.divideOutFactor(
                number, 2
            );
            // If the number only contained factors of 2
            if (3 > number)
                return null;
        }
        // Get the limitations of the prime number cache
        final int maxIndex = cache.getMaxIndex();
        for (
            int primeIndex = 1;
            primeIndex <= maxIndex;
            ++primeIndex
        ) {
            // Obtain the next prime from cache
            final int testPrime = cache.getPrime(primeIndex);
            // If the test prime is above limit
            if (limit < testPrime) {
                // Check if test prime divides the number
                if (0 == number % testPrime)
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
            if (Long.MIN_VALUE == limit)
                return false;
            return hasPrimeAbove(
                number,
                -limit,
                cache
            );
        }
        // Limit is greater than number, there is no checking necessary
        if (limit >= number)
            return false;
        // Remove any factors of 2
        if (BitFactoring.isProductOf2(number))
            number = Factoring.divideOutFactor(
                number, 2
            );
        // Search primes in increasing order
        int primeIdx = 1;
        int testPrime = cache.getPrime(primeIdx);
        //
        while (testPrime <= limit) {
            number = Factoring.divideOutFactor(
                number, testPrime
            );
            if (number <= limit)
                return false;
            testPrime = cache.getPrime(++primeIdx);
        }
        return true;
    }

    /** Determine the prime factors, including the power.
     * @param product The assumed product to factor.
     * @param cache The PrimeCache Interface that will provide prime numbers.
     * @return A List of Integer Pairs. The factor is first, then the power.
     */
    @Nonnull
    public static List<IntPair> getPrimeFactors(
        final int product,
        @Nonnull final PrimeCacheInterface cache
    ) {
        // If the product is 1 or negative, return empty
        if (2 > product)
            return Collections.emptyList();
        // Create a new ArrayList for the Prime Factors
        final ArrayList<IntPair> list = new ArrayList<>();
        // Divide from the product as factors are discovered
        long remainingComposite;
        // Check for Prime Factor of 2
        if (BitFactoring.isProductOf2(product)) {
            // How many factors of 2
            final int count = NumberFactors.countFactor(
                2, product // It is odd that the args are reversed
            ); // Factor 2nd is preferred in the future.
            // Divide every 2 from remainder
            remainingComposite = Factoring.divideOutFactor(
                product, 2
            );
            // Add the Prime Factor entry to the list
            list.add(
                new IntPair(2, count));
            // If the remainder contains no more prime factors
            if (3 > remainingComposite)
                return list;
        } else
            remainingComposite = product;
        // Search primes in increasing order
        int testIdx = 1;
        int testPrime = 3;
        //
        while (
            1 < remainingComposite &&
            testPrime <= remainingComposite
        ) {
            final int exp = NumberFactors.countFactor(
                testPrime, remainingComposite
            );
            if (0 < exp) {
                final IntPair pair = IntOperations.exponent(
                    testPrime, exp
                );
                remainingComposite /= (1 < exp) ?
                    pair.getFirst() : testPrime;
                list.add(
                    new IntPair(testPrime, exp));
            }
            testPrime = cache.getPrime(++testIdx);
        }
        return list;
    }

    private PrimeFactoring() {}

}