package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationRNS
 * random neighborhood search} for permutations initialized with the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic
 * Savings heuristic}
 */
public class PermutationRNSBestImprovementWithSavingsTest extends
PermutationRNSBestImprovementTest {

  /** create */
  public PermutationRNSBestImprovementWithSavingsTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
