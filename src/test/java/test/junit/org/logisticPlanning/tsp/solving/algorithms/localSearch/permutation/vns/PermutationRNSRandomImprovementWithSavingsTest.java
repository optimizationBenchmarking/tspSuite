package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.EImprovementSelectionPolicy;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationRNS;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationRNS
 * random neighborhood search} for permutations initialized with the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic
 * Savings heuristic}
 */
public class PermutationRNSRandomImprovementWithSavingsTest extends
    PermutationRNSBestImprovementWithSavingsTest {

  /** create */
  public PermutationRNSRandomImprovementWithSavingsTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationRNS createAlgorithm() {
    final PermutationRNS r;
    r = super.createAlgorithm();
    r.setImprovementSelectionPolicy(EImprovementSelectionPolicy.DECIDE_RANDOMLY_PER_ITERATION);
    return r;
  }
}
