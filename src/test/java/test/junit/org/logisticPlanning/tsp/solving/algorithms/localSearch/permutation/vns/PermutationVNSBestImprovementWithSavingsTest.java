package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationVNS
 * variable neighborhood search} for permutations initialized with the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic
 * Savings heuristic}
 */
public class PermutationVNSBestImprovementWithSavingsTest extends
PermutationVNSBestImprovementTest {

  /** create */
  public PermutationVNSBestImprovementWithSavingsTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
