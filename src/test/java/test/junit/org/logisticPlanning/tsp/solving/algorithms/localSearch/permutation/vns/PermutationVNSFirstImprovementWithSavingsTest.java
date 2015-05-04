package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.EImprovementSelectionPolicy;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationVNS;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationVNS
 * variable neighborhood search} for permutations initialized with the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic
 * Savings heuristic}
 */
public class PermutationVNSFirstImprovementWithSavingsTest extends
PermutationVNSBestImprovementWithSavingsTest {

  /** create */
  public PermutationVNSFirstImprovementWithSavingsTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationVNS createAlgorithm() {
    final PermutationVNS r;
    r = super.createAlgorithm();
    r.setImprovementSelectionPolicy(EImprovementSelectionPolicy.ALWAYS_USE_FIRST_IMPROVEMENT);
    return r;
  }
}
