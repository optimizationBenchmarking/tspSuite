package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.hc;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.hc.UpdatingPermutationHillClimber;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the hill climbing of permutations
 */
public class UpdatingPermutationHillClimberTest extends
    TSPAlgorithmSymmetricTest {

  /** create */
  public UpdatingPermutationHillClimberTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected UpdatingPermutationHillClimber createAlgorithm() {
    return new UpdatingPermutationHillClimber();
  }
}
