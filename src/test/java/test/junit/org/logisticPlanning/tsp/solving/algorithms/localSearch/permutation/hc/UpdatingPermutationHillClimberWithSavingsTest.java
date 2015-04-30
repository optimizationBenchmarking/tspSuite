package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.hc;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the hill climbing of permutations initialized with the
 * Savings heuristic
 */
public class UpdatingPermutationHillClimberWithSavingsTest extends
    UpdatingPermutationHillClimberTest {

  /** create */
  public UpdatingPermutationHillClimberWithSavingsTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
