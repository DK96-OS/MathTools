# MathTools
Mathematical Software Components

## Numbers ##
This package focuses on number manipulation, currently containing components concerned with Prime Numbers.
1. Generating a list of prime numbers, starting with 2, 3, 5, 7, 11
2. Generating a product within a range, limiting the size of prime numbers involved

### Number Generator Theory ###
A number generator is a sequence of functions with a well defined set of possible outcomes for a given set of input parameters. 

#### Generator Cycle
The execution of the generator's function sequence to produce a single outcome.

#### Outcome Probability
The probability that a particular outcome will be produced during any generator cycle.

#### Outcome Distribution
The set of all possible outcome probabilities for a particular set of input parameters.

#### Generator Normalization
Often, the goal of a generator is to produce a uniform distribution of outcome probabilities for any set of input parameters. This is sometimes difficult to achieve. The process of testing a generator and adjusting the internal functions in order to produce an outcome distribution that is closer to uniform, is referred to as *generator normalization*. 

#### Denormalization
When a generator produces an outcome distribution that appears uniform for a subset of input parameters, it may be considered *partially normalized*. When this type of generator is used with input parameters not in this subset, it is considered *denormalized*. A denormalized generator may favour certain outcomes in a way that depends on the implementation of internal generator functions, and/or the input parameters. Denormalized generators should always be used with caution.

### Number Generator Testing Framework ###
Number generators are tested by counting the number of times each result appears after running the generator many times. A basic extendable framework is provided, along with an example of it's usage.

To determine the sample probability of any particular outcome, divide it's counter by the total number of generator cycles, and convert to a percentage. The error in this value decreases as the total number of cycles increases.

### Product Generator ###
This normalized generator produces numbers that do not contain prime factors greater than a specified limit.
#### Implementation ####
1. Utilizes the random function on the range parameter to produce a uniform distribution of initial numbers during each cycle.
2. The random number is tested to see if it meets the prime factor limit condition.
3. While the random number does not meet the generator conditions, another random number is chosen.
#### Constraints ####
*IllegalArgumentExceptions will be thrown if any of these constraints are not met!*
* The maxPrime parameter must be greater than 1.
* The range parameter's first value must be greater than 1.
* The range must contain at least 3 numbers.
