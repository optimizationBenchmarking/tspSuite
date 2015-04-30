package test.junit.org.logisticPlanning.tsp.benchmarking.instances;

import org.junit.Assert;
import org.junit.Test;
import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.tsp.benchmarking.instances.Instance;

import test.junit.org.logisticPlanning.tsp.benchmarking.dist.DistanceComputerTest;

/**
 * Test the TSP instance
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV33}
 * from <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/">TSPLib
 * </a>. We test both, loaded distance computers which are raw (i.e.,
 * potentially based on coordinate lists), and those which use matrices
 * backing the results. Additionally, we statically test a few known
 * distances.
 */
public class Instance_FTV33_Test extends _InstanceTest {

  /**
   * test the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#FTV33}
   */
  public Instance_FTV33_Test() {
    super(Instance.FTV33);
  }

  /**
   * Compare a set of 10 known distances specified for testing with the
   * results provided by the distance computation in a hard-coded way.
   */
  @Test(timeout = 3600000)
  public final void testKnownDistances() {
    final DistanceComputer d1, d2;

    d1 = this.getDistanceComputerMat();
    Assert.assertNotNull(DistanceComputerTest.MATRIX_IS_NULL, d1);
    d2 = this.getDistanceComputerNoMat();
    Assert.assertNotNull(DistanceComputerTest.RAW_IS_NULL, d2);

    // The distance from 1 to 2 must be 26.
    Assert.assertEquals(26, d1.distance(1, 2));
    Assert.assertEquals(26, d2.distance(1, 2));

    // The distance from 1 to 3 must be 82.
    Assert.assertEquals(82, d1.distance(1, 3));
    Assert.assertEquals(82, d2.distance(1, 3));

    // The distance from 1 to 4 must be 65.
    Assert.assertEquals(65, d1.distance(1, 4));
    Assert.assertEquals(65, d2.distance(1, 4));

    // The distance from 1 to 5 must be 100.
    Assert.assertEquals(100, d1.distance(1, 5));
    Assert.assertEquals(100, d2.distance(1, 5));

    // The distance from 2 to 1 must be 66.
    Assert.assertEquals(66, d1.distance(2, 1));
    Assert.assertEquals(66, d2.distance(2, 1));

    // The distance from 2 to 3 must be 56.
    Assert.assertEquals(56, d1.distance(2, 3));
    Assert.assertEquals(56, d2.distance(2, 3));

    // The distance from 2 to 4 must be 39.
    Assert.assertEquals(39, d1.distance(2, 4));
    Assert.assertEquals(39, d2.distance(2, 4));

    // The distance from 3 to 1 must be 43.
    Assert.assertEquals(43, d1.distance(3, 1));
    Assert.assertEquals(43, d2.distance(3, 1));

    // The distance from 3 to 2 must be 57.
    Assert.assertEquals(57, d1.distance(3, 2));
    Assert.assertEquals(57, d2.distance(3, 2));

    // The distance from 3 to 4 must be 16.
    Assert.assertEquals(16, d1.distance(3, 4));
    Assert.assertEquals(16, d2.distance(3, 4));

  }

}
