# MathTools
Mathematical Software Components

## Numbers ##
This package focuses on number manipulation, currently containing components concerned with Prime Numbers.
1. Generating a list of prime numbers, starting with 2, 3, 5, 7, 11
2. Generating a product within a range, limiting the size of prime numbers involved

### Number Generator Theory ###
A number generator is a sequence of functions with a well defined set of possible outcomes for a given set of input parameters. 
**Generator Cycle**
The execution of the generator's function sequence to produce a single outcome.
**Outcome Probability**
The probability that a particular outcome will be produced during any generator cycle.
**Outcome Distribution**
The set of all possible outcome probabilities for a particular set of input parameters.
**Generator Normalization**
Often, the goal of a generator is to produce a uniform distribution of outcome probabilities for any set of input parameters. This is sometimes difficult to achieve. The process of testing a generator and adjusting the internal functions in order to produce an outcome distribution that is closer to uniform, is referred to as *generator normalization*. 
**Denormalization**
When a generator produces an outcome distribution that appears uniform for a subset of input parameters, it may be considered *partially normalized*. When this type of generator is used with input parameters not in this subset, it is considered *denormalized*. A denormalized generator may favour certain outcomes in a way that depends on the implementation of internal generator functions, and/or the input parameters. Denormalized generators should always be used with caution.

### Number Generator Testing Framework ###
Number generators are tested by counting the number of times each result appears after running the generator many times. A basic extendable framework is provided, along with an example of it's usage.

To determine the sample probability of any particular outcome, divide it's counter by the total number of generator cycles, and convert to a percentage. The error in this value decreases as the total number of cycles increases.

### Product Generator ###
This generator produces a number in a given range, and ensures that it does not contain a prime factor larger than a given limit.
**Normalization Status**
This generator is normalized for the set of input parameters where:
1. The prime number limit is greater than all prime numbers in the given range

Currently, this generator is denormalized for inputs with which it is primarily intended to be used. The normalized input sets are cases where the generator is unnnecessary.

**Denormalized Generator Usage**
Denormalization is caused by the function that handles prime factors that are above the prime factor limit.
When a large prime factor is found, it is incremented by 1 or -1 with a 50/50 chance for each. This is a simple way to remove a prime factor, because it splits the prime into two or more smaller factors, which may or may not all be prime.
The problems this simple solution causes include:
1. Increased probability of large even numbers
2. Additional occurrences of numbers immediately surrounding large prime numbers (also even numbers)
The result is an outcome distribution that prefers large even numbers. 
#### Example Test ####
Run the ProductGeneratorAnalysis with input parameters `(31..168L, 70)` for 40 000 cycles. To help interpret the results, here is the list of prime numbers in our product range.
**Relevant Prime Numbers**
2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167

Tests show that high even numbers, such as 168, 156, 164, 150, and notably 72 and 70 occur about 2 to 3 times as often as other numbers such as 135, 40, 51 and 43. This shows that prime factors below the limit are not affected, but primes above are clearly influencing the outcome probability of adjacent numbers.

**Side Note**
The sorted counters ascend rather smoothly, aside from a gap between the highest odd number and the next number. If you used the input parameters given above, you would see that the highest odd number has about 320 counts, while the next number has over 400.

**Conclusion**
The outcome probabilities are influenced by the internal implementation of the generator. This generator needs to be normalized.
