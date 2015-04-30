package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.DevUpdatingEA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the developmental update of permutations
 */
public class DevUpdatingEATest extends TSPAlgorithmSymmetricTest {

  /** create */
  public DevUpdatingEATest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected DevUpdatingEA createAlgorithm() {
    return new DevUpdatingEA();
  }
}
