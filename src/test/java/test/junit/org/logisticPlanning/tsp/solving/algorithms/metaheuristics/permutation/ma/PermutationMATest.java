package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ma;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ma.PermutationMA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the permutation EA
 */
public class PermutationMATest extends TSPAlgorithmSymmetricTest {

  /** create */
  public PermutationMATest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationMA createAlgorithm() {
    return new PermutationMA();
  }
}
