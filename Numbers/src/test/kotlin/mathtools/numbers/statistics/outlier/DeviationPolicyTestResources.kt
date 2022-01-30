package mathtools.numbers.statistics.outlier

object DeviationPolicyTestResources {

	/** Two sided policy with max of 3 Standard Deviations */
	internal val policy3SD = DeviationPolicy(3.0)

	/** Two sided policy with max of 2 Standard Deviations */
	internal val policy2SD = DeviationPolicy(2.0)

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