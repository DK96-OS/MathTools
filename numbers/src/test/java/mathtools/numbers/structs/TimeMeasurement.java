package mathtools.numbers.structs;

/** Records Time Values for performing measurements
 * @author DK96-OS : 2022 */
public final class TimeMeasurement {

	/** The Starting time of the measurement */
	private long mStart;

	/** The End time of the measurement */
	private Long mEnd = null;

	/** Initialize a Time Measurement
	 * Records the time that this was created */
	public TimeMeasurement() {
		mStart = System.currentTimeMillis();
	}

	/** Obtain the measurement
	 * Records the end time if it has not been recorded.
	 * @return The difference between start and end time */
	public long measure() {
		// If an end time has not been recorded
		if (null == mEnd) {
			mEnd = System.currentTimeMillis();
		}
		// Compute the Difference
		return mEnd - mStart;
	}

	/** Reset the Start time */
	public void reset() {
		mStart = System.currentTimeMillis();
		mEnd = null;
	}

}