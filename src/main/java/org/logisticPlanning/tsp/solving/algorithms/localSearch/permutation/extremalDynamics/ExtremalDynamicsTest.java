package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.extremalDynamics;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the extremal dynamics algorithm
 */
public class ExtremalDynamicsTest extends TSPAlgorithmSymmetricTest {

  /** create */
  public ExtremalDynamicsTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected ExtremalDynamics createAlgorithm() {
    return new ExtremalDynamics();
  }
}
