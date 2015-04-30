package test.junit.org.logisticPlanning.tsp.benchmarking.instances;

import org.junit.Assert;
import org.junit.Test;
import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.tsp.benchmarking.instances.Instance;

import test.junit.org.logisticPlanning.tsp.benchmarking.dist.DistanceComputerTest;

/**
 * Test the TSP instance
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BR17}
 * from <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/">TSPLib
 * </a>. We test both, loaded distance computers which are raw (i.e.,
 * potentially based on coordinate lists), and those which use matrices
 * backing the results. Additionally, we statically test a few known
 * distances.
 */
public class Instance_BR17_Test extends _InstanceTest {

  /**
   * test the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BR17}
   */
  public Instance_BR17_Test() {
    super(Instance.BR17);
  }

  /**
   * Compare a set of 17 known distances specified for testing with the
   * results provided by the distance computation in a hard-coded way.
   */
  @Test(timeout = 3600000)
  public final void testKnownDistances() {
    final DistanceComputer d1, d2;

    d1 = this.getDistanceComputerMat();
    Assert.assertNotNull(DistanceComputerTest.MATRIX_IS_NULL, d1);
    d2 = this.getDistanceComputerNoMat();
    Assert.assertNotNull(DistanceComputerTest.RAW_IS_NULL, d2);

    // The distance from 1 to 2 must be 3.
    Assert.assertEquals(3, d1.distance(1, 2));
    Assert.assertEquals(3, d2.distance(1, 2));

    // The distance from 1 to 3 must be 5.
    Assert.assertEquals(5, d1.distance(1, 3));
    Assert.assertEquals(5, d2.distance(1, 3));

    // The distance from 1 to 4 must be 48.
    Assert.assertEquals(48, d1.distance(1, 4));
    Assert.assertEquals(48, d2.distance(1, 4));

    // The distance from 1 to 5 must be 48.
    Assert.assertEquals(48, d1.distance(1, 5));
    Assert.assertEquals(48, d2.distance(1, 5));

    // The distance from 1 to 6 must be 8.
    Assert.assertEquals(8, d1.distance(1, 6));
    Assert.assertEquals(8, d2.distance(1, 6));

    // The distance from 1 to 7 must be 8.
    Assert.assertEquals(8, d1.distance(1, 7));
    Assert.assertEquals(8, d2.distance(1, 7));

    // The distance from 2 to 3 must be 3.
    Assert.assertEquals(3, d1.distance(2, 3));
    Assert.assertEquals(3, d2.distance(2, 3));

    // The distance from 2 to 4 must be 48.
    Assert.assertEquals(48, d1.distance(2, 4));
    Assert.assertEquals(48, d2.distance(2, 4));

    // The distance from 2 to 5 must be 48.
    Assert.assertEquals(48, d1.distance(2, 5));
    Assert.assertEquals(48, d2.distance(2, 5));

    // The distance from 2 to 6 must be 8.
    Assert.assertEquals(8, d1.distance(2, 6));
    Assert.assertEquals(8, d2.distance(2, 6));

    // The distance from 2 to 7 must be 8.
    Assert.assertEquals(8, d1.distance(2, 7));
    Assert.assertEquals(8, d2.distance(2, 7));

    // The distance from 3 to 4 must be 72.
    Assert.assertEquals(72, d1.distance(3, 4));
    Assert.assertEquals(72, d2.distance(3, 4));

    // The distance from 3 to 5 must be 72.
    Assert.assertEquals(72, d1.distance(3, 5));
    Assert.assertEquals(72, d2.distance(3, 5));

    // The distance from 3 to 6 must be 48.
    Assert.assertEquals(48, d1.distance(3, 6));
    Assert.assertEquals(48, d2.distance(3, 6));

    // The distance from 3 to 7 must be 48.
    Assert.assertEquals(48, d1.distance(3, 7));
    Assert.assertEquals(48, d2.distance(3, 7));

    // The distance from 3 to 8 must be 24.
    Assert.assertEquals(24, d1.distance(3, 8));
    Assert.assertEquals(24, d2.distance(3, 8));

    // The distance from 3 to 9 must be 24.
    Assert.assertEquals(24, d1.distance(3, 9));
    Assert.assertEquals(24, d2.distance(3, 9));

  }

}
