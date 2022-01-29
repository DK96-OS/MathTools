package mathtools.numbers.statistics.outlier

object StandardDeviationPolicyTestResources {

	/** Two sided policy with max of 3 Standard Deviations */
	internal val policy3SD = StandardDeviationPolicy(3.0)

	/** Two sided policy with max of 2 Standard Deviations */
	internal val policy2SD = StandardDeviationPolicy(2.0)

	/** Upper sided policy with max of 2 Standard Deviations */
	internal val policy2SDUpper = StandardDeviationPolicy(
		2.0,
		upperOutliers = true,
		lowerOutliers = false
	)

	/** Lower sided policy with max of 2 Standard Deviations */
	internal val policy2SDLower = StandardDeviationPolicy(
		2.0,
		upperOutliers = false,
		lowerOutliers = true
	)

	internal val policyNoSide = StandardDeviationPolicy(
		3.0,
		upperOutliers = false,
		lowerOutliers = false
	)
}