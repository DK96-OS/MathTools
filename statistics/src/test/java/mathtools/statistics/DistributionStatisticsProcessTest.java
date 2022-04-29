package mathtools.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static mathtools.statistics.DistributionStatistics.process;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Testing [DistributionStatistics] Process methods
 * @author DK96-OS : 2022 */
public final class DistributionStatisticsProcessTest {

	@Test
	public void testSingleElementArrays() {
		assertNull(
			process(new byte[]{ 1 }));
		assertNull(
			process(new short[]{ 1 }));
		assertNull(
			process(new int[]{ 1 }));
		assertNull(
			process(new long[]{ 1 }));
		assertNull(
			process(new float[]{ 1 }));
		assertNull(
			process(new double[]{ 1 }));
	}

	@Test
	public void testSingleList() {
		final List<Number> list = new ArrayList<>(1);
		list.add(24L);
		final DistributionStats stats = DistributionStats.Companion.process(
			list
		);
		assert null != stats;
		assertEquals(
			24.0, stats.getMean());
		assertEquals(
			0.0, stats.getStandardDeviation());
		assertEquals(
			24.0, stats.getMin());
		assertEquals(
			24.0, stats.getMax());
	}

	@Test
	public void testEmptyList() {
		final List<Number> emptyList = Collections.emptyList();
		assertNull(
			DistributionStats.Companion.process(emptyList));
	}

}