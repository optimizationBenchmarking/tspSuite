package test.junit.org.logisticPlanning.tsp.benchmarking.instances;

import org.junit.Assert;
import org.junit.Test;
import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.tsp.benchmarking.instances.Instance;

import test.junit.org.logisticPlanning.tsp.benchmarking.dist.DistanceComputerTest;

/**
 * Test the TSP instance
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR17}
 * from <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/">TSPLib
 * </a>. We test both, loaded distance computers which are raw (i.e.,
 * potentially based on coordinate lists), and those which use matrices
 * backing the results. Additionally, we statically test a few known
 * distances.
 */
public class Instance_GR17_Test extends _InstanceTest {

  /**
   * test the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#GR17}
   */
  public Instance_GR17_Test() {
    super(Instance.GR17);
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

    // The distance from 2 to 1 must be 633.
    Assert.assertEquals(633, d1.distance(2, 1));
    Assert.assertEquals(633, d2.distance(2, 1));
    // As the problem is symmetric, the distance from 1 to 2 must be 633 as
    // well.
    Assert.assertEquals(633, d1.distance(1, 2));
    Assert.assertEquals(633, d2.distance(1, 2));
    // The distance from 3 to 1 must be 257.
    Assert.assertEquals(257, d1.distance(3, 1));
    Assert.assertEquals(257, d2.distance(3, 1));
    // As the problem is symmetric, the distance from 1 to 3 must be 257 as
    // well.
    Assert.assertEquals(257, d1.distance(1, 3));
    Assert.assertEquals(257, d2.distance(1, 3));
    // The distance from 3 to 2 must be 390.
    Assert.assertEquals(390, d1.distance(3, 2));
    Assert.assertEquals(390, d2.distance(3, 2));
    // As the problem is symmetric, the distance from 2 to 3 must be 390 as
    // well.
    Assert.assertEquals(390, d1.distance(2, 3));
    Assert.assertEquals(390, d2.distance(2, 3));
    // The distance from 4 to 1 must be 91.
    Assert.assertEquals(91, d1.distance(4, 1));
    Assert.assertEquals(91, d2.distance(4, 1));
    // As the problem is symmetric, the distance from 1 to 4 must be 91 as
    // well.
    Assert.assertEquals(91, d1.distance(1, 4));
    Assert.assertEquals(91, d2.distance(1, 4));
    // The distance from 4 to 2 must be 661.
    Assert.assertEquals(661, d1.distance(4, 2));
    Assert.assertEquals(661, d2.distance(4, 2));
    // As the problem is symmetric, the distance from 2 to 4 must be 661 as
    // well.
    Assert.assertEquals(661, d1.distance(2, 4));
    Assert.assertEquals(661, d2.distance(2, 4));
    // The distance from 4 to 3 must be 228.
    Assert.assertEquals(228, d1.distance(4, 3));
    Assert.assertEquals(228, d2.distance(4, 3));
    // As the problem is symmetric, the distance from 3 to 4 must be 228 as
    // well.
    Assert.assertEquals(228, d1.distance(3, 4));
    Assert.assertEquals(228, d2.distance(3, 4));
    // The distance from 5 to 1 must be 412.
    Assert.assertEquals(412, d1.distance(5, 1));
    Assert.assertEquals(412, d2.distance(5, 1));
    // As the problem is symmetric, the distance from 1 to 5 must be 412 as
    // well.
    Assert.assertEquals(412, d1.distance(1, 5));
    Assert.assertEquals(412, d2.distance(1, 5));
    // The distance from 5 to 2 must be 227.
    Assert.assertEquals(227, d1.distance(5, 2));
    Assert.assertEquals(227, d2.distance(5, 2));
    // As the problem is symmetric, the distance from 2 to 5 must be 227 as
    // well.
    Assert.assertEquals(227, d1.distance(2, 5));
    Assert.assertEquals(227, d2.distance(2, 5));
    // The distance from 5 to 3 must be 169.
    Assert.assertEquals(169, d1.distance(5, 3));
    Assert.assertEquals(169, d2.distance(5, 3));
    // As the problem is symmetric, the distance from 3 to 5 must be 169 as
    // well.
    Assert.assertEquals(169, d1.distance(3, 5));
    Assert.assertEquals(169, d2.distance(3, 5));
    // The distance from 5 to 4 must be 383.
    Assert.assertEquals(383, d1.distance(5, 4));
    Assert.assertEquals(383, d2.distance(5, 4));
    // As the problem is symmetric, the distance from 4 to 5 must be 383 as
    // well.
    Assert.assertEquals(383, d1.distance(4, 5));
    Assert.assertEquals(383, d2.distance(4, 5));
  }

}
