package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.EImprovementSelectionPolicy;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationVNS;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationVNS
 * variable neighborhood search} for permutations
 */
public class PermutationVNSBestImprovementTest extends
    TSPAlgorithmSymmetricTest {

  /** create */
  public PermutationVNSBestImprovementTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationVNS createAlgorithm() {
    final PermutationVNS r;
    r = new PermutationVNS();
    r.setImprovementSelectionPolicy(EImprovementSelectionPolicy.ALWAYS_USE_BEST_IMPROVEMENT);
    return r;
  }
}
