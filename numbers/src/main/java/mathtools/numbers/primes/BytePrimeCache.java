package mathtools.numbers.primes;

import com.google.common.primitives.Bytes;

import mathtools.numbers.primes.validate.PrimeValidation;
import mathtools.numbers.primes.validate.StaticPrimes;

/** An array-based cache for Prime Numbers using bytes
 * @author DK96-OS : 2021 - 2022 */
public class BytePrimeCache
	implements PrimeCacheInterface {

	/** The largest index that can be stored */
	public static final int MAX_INDEX = 53;

	/** The largest prime value that can be stored */
	public static final int MAX_PRIME = 251;

	private byte[] mArray;

	private byte mHighestCachedIndex = StaticPrimes.MAX_INDEX;  // The static array contains 16 elements

	@Override
	public int getMaxIndex() { return MAX_INDEX; }

	@Override
	public int getMaxPrime() { return MAX_PRIME; }

	@Override
	public int getHighestCachedIndex() { return mHighestCachedIndex; }

	@Override
	public void clear() {
		mHighestCachedIndex = StaticPrimes.MAX_INDEX;
		mArray = null;
	}

	@Override
	public int getPrime(
		final int index
	) {
		// If in the static domain
		if (16 > index)
			return StaticPrimes.getStaticPrime(index);
		// Within this cache's domain
		if (MAX_INDEX >= index) {
			// The index of the prime in the array
			final int arrayIndex = index - 16;
			// If the array already contains this prime
			if (index <= mHighestCachedIndex)
				return readFromArray(arrayIndex);
			// If the array is empty
			if (null == mArray) {
				// Initialize with 4 values
				mArray = new byte[]{
					59, 61, 67, 71
				};
				mHighestCachedIndex = 19;
				// If the request can be fulfilled
				if (4 > arrayIndex)
					// no need to use readFromArray method
					return mArray[arrayIndex];
			}
			// Extend the Array
			if (!extendArray(arrayIndex))
				throw new IllegalStateException(
					"Cannot extend prime number byte array"
				);
			// The array now has the prime
			return readFromArray(arrayIndex);
		}
		// Outside of this cache's domain
		throw new IllegalArgumentException(
			"This cache does not contain primes above " + MAX_PRIME
		);
	}

	/** Read a value from internal data structure
	 * @param arrayIndex The index in the internal array
	 * @return An Integer value that was stored in the array
	 */
	private int readFromArray(
		final int arrayIndex
	) {
		int value = mArray[arrayIndex];
		// The first 15 bytes are positive
		if (14 < arrayIndex)
			value += 256;
		return value;
	}

	/** Convert the value to be stored in the data structure
	 * @param value The value that needs to be inserted into the Array
	 * @return Whether the inputs were valid and value was inserted
	 */
	private static boolean writeToArray(
		final byte[] array,
		final int arrayIndex,
		final int value
	) {
		if (255 >= value) {
			if (Byte.MAX_VALUE < value) {
				array[arrayIndex] = (byte) (value - 256);
				return true;
			} else if (0 < value) {
				array[arrayIndex] = (byte) value;
				return true;
			}
		}
		throw new IllegalArgumentException(
			String.format(
				"Invalid input: %d", value
			)
		);
	}

	/** Extend the member array to contain at least the requested array index
	 * @param arrayIndex The index in the array that the prime would be at
	 * @return Whether the extend operation was successful */
	private boolean extendArray(
		final int arrayIndex
	) {
		// How many primes are needed to reach the requested prime
		final int additionalPrimes = 1 + arrayIndex - mArray.length;
		// Ensure this number is non-zero positive
		if (1 > additionalPrimes)
			return false;
		//
		int prevPrime = readFromArray(mArray.length - 1);
		int testPrime = prevPrime + 2;
		// Create a new array for the new primes
		final byte[] newPrimes = new byte[additionalPrimes];
		int newPrimeIndex = 0;
		// Fill the newPrimes Array
		for (;
			newPrimeIndex < additionalPrimes;
			++newPrimeIndex
		) {
			final Integer nextPrime = PrimeValidation.findPrime(
				testPrime, this
			);
			if (null == nextPrime)
				return false;
			// Insert prime into array
			final boolean success = writeToArray(
				newPrimes,
				newPrimeIndex,
				nextPrime
			);
			if (!success)
				return false;
			// Consecutive prime skip
			testPrime = nextPrime + ((2 == nextPrime - prevPrime) ? 4 : 2);
			prevPrime = nextPrime;
		}
		// Combine the new primes with the existing primes
		mArray = Bytes.concat(
			mArray, newPrimes
		);
		mHighestCachedIndex = (byte) (15 + mArray.length);
		return true;
	}

}