package test.junit.org.logisticPlanning.tsp.benchmarking.instances;

import org.junit.Assert;
import org.junit.Test;
import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.tsp.benchmarking.instances.Instance;

import test.junit.org.logisticPlanning.tsp.benchmarking.dist.DistanceComputerTest;

/**
 * Test the TSP instance
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BERLIN52}
 * from <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/">TSPLib
 * </a>. We test both, loaded distance computers which are raw (i.e.,
 * potentially based on coordinate lists), and those which use matrices
 * backing the results. Additionally, we statically test a few known
 * distances. <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_PR1987OOA5CSTSPBBAC" />Manfred W. Padberg
 * and&nbsp;Giovanni Rinaldi: <span
 * style="font-weight:bold">&ldquo;Optimization of a 532-City Symmetric
 * Traveling Salesman Problem by Branch and Cut,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Operations Research
 * Letters</span> 6(1):1&ndash;7, March&nbsp;1987; published by Amsterdam,
 * The Netherlands: Elsevier Science Publishers B.V.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/0167-6377(87)90002-2"
 * >10.1016/0167-6377(87)90002-2</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/01676377">0167-6377</a></div></li>
 * </ol>
 */
public class Instance_BERLIN52_Test extends _InstanceTest {

  /**
   * test the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BERLIN52}
   */
  public Instance_BERLIN52_Test() {
    super(Instance.BERLIN52);
  }

  /**
   * Compare a set of 27 known distances specified for testing with the
   * results provided by the distance computation in a hard-coded way.
   */
  @Test(timeout = 3600000)
  public final void testKnownDistances() {
    final DistanceComputer d1, d2;

    d1 = this.getDistanceComputerMat();
    Assert.assertNotNull(DistanceComputerTest.MATRIX_IS_NULL, d1);
    d2 = this.getDistanceComputerNoMat();
    Assert.assertNotNull(DistanceComputerTest.RAW_IS_NULL, d2);

    // The distance from 1 to 2 must be 666.
    Assert.assertEquals(666, d1.distance(1, 2));
    Assert.assertEquals(666, d2.distance(1, 2));
    // As the problem is symmetric, the distance from 2 to 1 must be 666 as
    // well.
    Assert.assertEquals(666, d1.distance(2, 1));
    Assert.assertEquals(666, d2.distance(2, 1));
    // The distance from 1 to 3 must be 281.
    Assert.assertEquals(281, d1.distance(1, 3));
    Assert.assertEquals(281, d2.distance(1, 3));
    // As the problem is symmetric, the distance from 3 to 1 must be 281 as
    // well.
    Assert.assertEquals(281, d1.distance(3, 1));
    Assert.assertEquals(281, d2.distance(3, 1));
    // The distance from 1 to 4 must be 396.
    Assert.assertEquals(396, d1.distance(1, 4));
    Assert.assertEquals(396, d2.distance(1, 4));
    // As the problem is symmetric, the distance from 4 to 1 must be 396 as
    // well.
    Assert.assertEquals(396, d1.distance(4, 1));
    Assert.assertEquals(396, d2.distance(4, 1));
    // The distance from 1 to 5 must be 291.
    Assert.assertEquals(291, d1.distance(1, 5));
    Assert.assertEquals(291, d2.distance(1, 5));
    // As the problem is symmetric, the distance from 5 to 1 must be 291 as
    // well.
    Assert.assertEquals(291, d1.distance(5, 1));
    Assert.assertEquals(291, d2.distance(5, 1));
    // The distance from 1 to 6 must be 326.
    Assert.assertEquals(326, d1.distance(1, 6));
    Assert.assertEquals(326, d2.distance(1, 6));
    // As the problem is symmetric, the distance from 6 to 1 must be 326 as
    // well.
    Assert.assertEquals(326, d1.distance(6, 1));
    Assert.assertEquals(326, d2.distance(6, 1));
    // The distance from 1 to 7 must be 641.
    Assert.assertEquals(641, d1.distance(1, 7));
    Assert.assertEquals(641, d2.distance(1, 7));
    // As the problem is symmetric, the distance from 7 to 1 must be 641 as
    // well.
    Assert.assertEquals(641, d1.distance(7, 1));
    Assert.assertEquals(641, d2.distance(7, 1));
    // The distance from 2 to 3 must be 649.
    Assert.assertEquals(649, d1.distance(2, 3));
    Assert.assertEquals(649, d2.distance(2, 3));
    // As the problem is symmetric, the distance from 3 to 2 must be 649 as
    // well.
    Assert.assertEquals(649, d1.distance(3, 2));
    Assert.assertEquals(649, d2.distance(3, 2));
    // The distance from 2 to 4 must be 1047.
    Assert.assertEquals(1047, d1.distance(2, 4));
    Assert.assertEquals(1047, d2.distance(2, 4));
    // As the problem is symmetric, the distance from 4 to 2 must be 1047
    // as
    // well.
    Assert.assertEquals(1047, d1.distance(4, 2));
    Assert.assertEquals(1047, d2.distance(4, 2));
    // The distance from 2 to 5 must be 945.
    Assert.assertEquals(945, d1.distance(2, 5));
    Assert.assertEquals(945, d2.distance(2, 5));
    // As the problem is symmetric, the distance from 5 to 2 must be 945 as
    // well.
    Assert.assertEquals(945, d1.distance(5, 2));
    Assert.assertEquals(945, d2.distance(5, 2));
    // The distance from 2 to 6 must be 978.
    Assert.assertEquals(978, d1.distance(2, 6));
    Assert.assertEquals(978, d2.distance(2, 6));
    // As the problem is symmetric, the distance from 6 to 2 must be 978 as
    // well.
    Assert.assertEquals(978, d1.distance(6, 2));
    Assert.assertEquals(978, d2.distance(6, 2));
    // The distance from 2 to 7 must be 45.
    Assert.assertEquals(45, d1.distance(2, 7));
    Assert.assertEquals(45, d2.distance(2, 7));
    // As the problem is symmetric, the distance from 7 to 2 must be 45 as
    // well.
    Assert.assertEquals(45, d1.distance(7, 2));
    Assert.assertEquals(45, d2.distance(7, 2));
    // The distance from 3 to 4 must be 604.
    Assert.assertEquals(604, d1.distance(3, 4));
    Assert.assertEquals(604, d2.distance(3, 4));
    // As the problem is symmetric, the distance from 4 to 3 must be 604 as
    // well.
    Assert.assertEquals(604, d1.distance(4, 3));
    Assert.assertEquals(604, d2.distance(4, 3));
    // The distance from 3 to 5 must be 509.
    Assert.assertEquals(509, d1.distance(3, 5));
    Assert.assertEquals(509, d2.distance(3, 5));
    // As the problem is symmetric, the distance from 5 to 3 must be 509 as
    // well.
    Assert.assertEquals(509, d1.distance(5, 3));
    Assert.assertEquals(509, d2.distance(5, 3));
    // The distance from 3 to 6 must be 543.
    Assert.assertEquals(543, d1.distance(3, 6));
    Assert.assertEquals(543, d2.distance(3, 6));
    // As the problem is symmetric, the distance from 6 to 3 must be 543 as
    // well.
    Assert.assertEquals(543, d1.distance(6, 3));
    Assert.assertEquals(543, d2.distance(6, 3));
    // The distance from 3 to 7 must be 611.
    Assert.assertEquals(611, d1.distance(3, 7));
    Assert.assertEquals(611, d2.distance(3, 7));
    // As the problem is symmetric, the distance from 7 to 3 must be 611 as
    // well.
    Assert.assertEquals(611, d1.distance(7, 3));
    Assert.assertEquals(611, d2.distance(7, 3));
    // The distance from 4 to 5 must be 104.
    Assert.assertEquals(104, d1.distance(4, 5));
    Assert.assertEquals(104, d2.distance(4, 5));
    // As the problem is symmetric, the distance from 5 to 4 must be 104 as
    // well.
    Assert.assertEquals(104, d1.distance(5, 4));
    Assert.assertEquals(104, d2.distance(5, 4));
    // The distance from 4 to 6 must be 70.
    Assert.assertEquals(70, d1.distance(4, 6));
    Assert.assertEquals(70, d2.distance(4, 6));
    // As the problem is symmetric, the distance from 6 to 4 must be 70 as
    // well.
    Assert.assertEquals(70, d1.distance(6, 4));
    Assert.assertEquals(70, d2.distance(6, 4));
    // The distance from 4 to 7 must be 1026.
    Assert.assertEquals(1026, d1.distance(4, 7));
    Assert.assertEquals(1026, d2.distance(4, 7));
    // As the problem is symmetric, the distance from 7 to 4 must be 1026
    // as
    // well.
    Assert.assertEquals(1026, d1.distance(7, 4));
    Assert.assertEquals(1026, d2.distance(7, 4));
    // The distance from 5 to 6 must be 35.
    Assert.assertEquals(35, d1.distance(5, 6));
    Assert.assertEquals(35, d2.distance(5, 6));
    // As the problem is symmetric, the distance from 6 to 5 must be 35 as
    // well.
    Assert.assertEquals(35, d1.distance(6, 5));
    Assert.assertEquals(35, d2.distance(6, 5));
    // The distance from 5 to 7 must be 924.
    Assert.assertEquals(924, d1.distance(5, 7));
    Assert.assertEquals(924, d2.distance(5, 7));
    // As the problem is symmetric, the distance from 7 to 5 must be 924 as
    // well.
    Assert.assertEquals(924, d1.distance(7, 5));
    Assert.assertEquals(924, d2.distance(7, 5));
    // The distance from 6 to 7 must be 957.
    Assert.assertEquals(957, d1.distance(6, 7));
    Assert.assertEquals(957, d2.distance(6, 7));
    // As the problem is symmetric, the distance from 7 to 6 must be 957 as
    // well.
    Assert.assertEquals(957, d1.distance(7, 6));
    Assert.assertEquals(957, d2.distance(7, 6));
    // The distance from 51 to 52 must be 625.
    Assert.assertEquals(625, d1.distance(51, 52));
    Assert.assertEquals(625, d2.distance(51, 52));
    // As the problem is symmetric, the distance from 52 to 51 must be 625
    // as well.
    Assert.assertEquals(625, d1.distance(52, 51));
    Assert.assertEquals(625, d2.distance(52, 51));
    // The distance from 50 to 51 must be 830.
    Assert.assertEquals(830, d1.distance(50, 51));
    Assert.assertEquals(830, d2.distance(50, 51));
    // As the problem is symmetric, the distance from 51 to 50 must be 830
    // as well.
    Assert.assertEquals(830, d1.distance(51, 50));
    Assert.assertEquals(830, d2.distance(51, 50));
    // The distance from 50 to 52 must be 1151.
    Assert.assertEquals(1151, d1.distance(50, 52));
    Assert.assertEquals(1151, d2.distance(50, 52));
    // As the problem is symmetric, the distance from 52 to 50 must be 1151
    // as well.
    Assert.assertEquals(1151, d1.distance(52, 50));
    Assert.assertEquals(1151, d2.distance(52, 50));
    // The distance from 49 to 50 must be 265.
    Assert.assertEquals(265, d1.distance(49, 50));
    Assert.assertEquals(265, d2.distance(49, 50));
    // As the problem is symmetric, the distance from 50 to 49 must be 265
    // as well.
    Assert.assertEquals(265, d1.distance(50, 49));
    Assert.assertEquals(265, d2.distance(50, 49));
    // The distance from 49 to 51 must be 742.
    Assert.assertEquals(742, d1.distance(49, 51));
    Assert.assertEquals(742, d2.distance(49, 51));
    // As the problem is symmetric, the distance from 51 to 49 must be 742
    // as well.
    Assert.assertEquals(742, d1.distance(51, 49));
    Assert.assertEquals(742, d2.distance(51, 49));
    // The distance from 49 to 52 must be 1197.
    Assert.assertEquals(1197, d1.distance(49, 52));
    Assert.assertEquals(1197, d2.distance(49, 52));
    // As the problem is symmetric, the distance from 52 to 49 must be 1197
    // as well.
    Assert.assertEquals(1197, d1.distance(52, 49));
    Assert.assertEquals(1197, d2.distance(52, 49));
  }

}
