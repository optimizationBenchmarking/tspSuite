package test.junit.org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundHeldCarp;

import org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundHeldCarp.BAB_HK;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the Branch-and-Bound algorithm with Held-Karp relaxation
 */
public class BAB_HKTest extends TSPAlgorithmSymmetricTest {

  /** create */
  public BAB_HKTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected BAB_HK createAlgorithm() {
    return new BAB_HK();
  }
}
