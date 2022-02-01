package mathtools.numbers.statistics.outlier

/** Provider of specific Deviation Policy instances for test cases
 * Developed by DK96-OS : 2022 */
object DeviationPolicyTestResources {

	/** Two sided policy with max of 2 Standard Deviations */
	internal val policy2SD = DeviationPolicy(2.0)

	/** Two sided policy with max of 1 Standard Deviations */
	internal val policy1SD = DeviationPolicy(1.0)

	/** Upper sided policy with max of 2 Standard Deviations */
	internal val policy2SDUpper = DeviationPolicy(
		2.0,
		upperOutliers = true,
		lowerOutliers = false
	)

	/** Lower sided policy with max of 2 Standard Deviations */
	internal val policy2SDLower = DeviationPolicy(
		2.0,
		upperOutliers = false,
		lowerOutliers = true
	)

}