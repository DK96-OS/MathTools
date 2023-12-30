## MathTools
[![Tests Coverage](https://github.com/DK96-OS/MathTools/actions/workflows/ci_run.yml/badge.svg?branch=master)](https://github.com/DK96-OS/MathTools/actions/workflows/ci_run.yml)

Software Tools for Applied Mathematics are categorized into these modules:
### Modules
- __Arrays__
- __Format__
- __Generators__
- __Lists__
- __Numbers__
- __Pairs__
- __Statistics__

### Import in Gradle Build Script
Choose The Modules that you want to import:

	def mt_version = "0.7.2"
	dependencies {
		implementation 'io.github.dk96-os:arrays:$mt_version'
		implementation 'io.github.dk96-os:arrays-ktx:$mt_version'
		implementation 'io.github.dk96-os:format:$mt_version'
		implementation 'io.github.dk96-os:generators:$mt_version'
		implementation 'io.github.dk96-os:lists:$mt_version'
		implementation 'io.github.dk96-os:lists-ktx:$mt_version'
		implementation 'io.github.dk96-os:numbers:$mt_version'
		implementation 'io.github.dk96-os:pairs:$mt_version'
		implementation 'io.github.dk96-os:statistics:$mt_version'
	}

___

## MathTools Modules
Each module is published as a package that can be downloaded from GitHub packages.
Some modules depend on each other, but cross-module dependencies have been minimized.

### Arrays
Numerical array operations not included in the standard library.
- Whole Number types only

### Arrays-ktx
Extension methods on Numerical Array Types.
- Whole Number types only

___

### Format
Tools that help format numerical data.
- Rounding
- Percentages
- Serialization

___

### Generators
Generate Numbers of different types within specified ranges, and with optional relative probability.
Also contains number counting data structures of different capacities for different requirements.

___

### Lists
For specialized list operations, with an emphasis on Number types.
- Search within a range of indices in a List.

#### Lists-ktx
Kotlin extensions for the Lists module.

___

### Numbers
Specialized Numerical Operations, Data Structures, Factoring, and Prime Number Caches.

___

### Pairs
Simple Data Structures containing pairs of numbers of the same type.
- Fixed Pairs
- (Mutable) Pairs
___

### Statistics
Process lists and arrays of primitive Number types, to determine statistical characteristics.
- Determine the average, min, max, and standard deviation, simultaneously in 1 pass.
- Outlier Detection Policy

____
For additional information, see the [Project Wiki](https://github.com/DK96-OS/MathTools/wiki)
