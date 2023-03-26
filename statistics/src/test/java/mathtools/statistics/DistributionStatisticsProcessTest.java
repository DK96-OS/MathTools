package mathtools.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static mathtools.statistics.DistributionStatistics.process;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Testing [DistributionStatistics] Process methods.
 * @author DK96-OS : 2022
 */
public final class DistributionStatisticsProcessTest {

	@Test
	public void testSingleElement_IntegerArrays_ReturnsStats() {
		final byte val = 5;
		DistributionStats result;
		//
		result = process(new byte[]{ val });
		assertNotNull(result);
		assertEquals(val, result.getMin());
		assertEquals(val, result.getMax());
		assertEquals(val, result.getMean());
		assertEquals(0, result.getStandardDeviation());
		//
		result = process(new short[]{ val });
		assertNotNull(result);
		assertEquals(val, result.getMin());
		assertEquals(val, result.getMax());
		assertEquals(val, result.getMean());
		assertEquals(0, result.getStandardDeviation());
		//
		result = process(new int[]{ val });
		assertNotNull(result);
		assertEquals(val, result.getMin());
		assertEquals(val, result.getMax());
		assertEquals(val, result.getMean());
		assertEquals(0, result.getStandardDeviation());
		//
		result = process(new long[]{ val });
		assertNotNull(result);
		assertEquals(val, result.getMin());
		assertEquals(val, result.getMax());
		assertEquals(val, result.getMean());
		assertEquals(0, result.getStandardDeviation());
	}

	@Test
	public void testSingleElement_FloatArrays_ReturnsStats() {
		final float val = 10.5f;
		DistributionStats result;
		//
		result = process(new float[]{ val });
		assertNotNull(result);
		assertEquals(val, result.getMin());
		assertEquals(val, result.getMax());
		assertEquals(val, result.getMean());
		assertEquals(0, result.getStandardDeviation());
		//
		result = process(new double[]{ val });
		assertNotNull(result);
		assertEquals(val, result.getMin());
		assertEquals(val, result.getMax());
		assertEquals(val, result.getMean());
		assertEquals(0, result.getStandardDeviation());
	}

	@Test
	public void testSingleValueMultiElement_IntegerArrays() {
		final byte val = 5;
		DistributionStats result;
		//
		result = process(new byte[]{val, val});
		assertNotNull(result);
		assertEquals(val, result.getMin());
		assertEquals(val, result.getMax());
		assertEquals(val, result.getMean());
		assertEquals(0, result.getStandardDeviation());
		//
		result = process(new short[]{val, val});
		assertNotNull(result);
		assertEquals(val, result.getMin());
		assertEquals(val, result.getMax());
		assertEquals(val, result.getMean());
		assertEquals(0, result.getStandardDeviation());
		//
		result = process(new int[]{val, val});
		assertNotNull(result);
		assertEquals(val, result.getMin());
		assertEquals(val, result.getMax());
		assertEquals(val, result.getMean());
		assertEquals(0, result.getStandardDeviation());
		//
		result = process(new long[]{val, val});
		assertNotNull(result);
		assertEquals(val, result.getMin());
		assertEquals(val, result.getMax());
		assertEquals(val, result.getMean());
		assertEquals(0, result.getStandardDeviation());
	}

	@Test
	public void testSingleValueMultiElement_FloatArrays() {
		final float val = 10.5f;
		DistributionStats result;
		//
		result = process(new float[]{ val, val });
		assertNotNull(result);
		assertEquals(val, result.getMin());
		assertEquals(val, result.getMax());
		assertEquals(val, result.getMean());
		assertEquals(0, result.getStandardDeviation());
		//
		result = process(new double[]{ val, val });
		assertNotNull(result);
		assertEquals(val, result.getMin());
		assertEquals(val, result.getMax());
		assertEquals(val, result.getMean());
		assertEquals(0, result.getStandardDeviation());
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
		assertEquals(
			1, stats.getCount()
		);
	}

	@Test
	public void testEmptyList() {
		final List<Number> emptyList = Collections.emptyList();
		assertNull(
			DistributionStats.Companion.process(emptyList));
	}

}
