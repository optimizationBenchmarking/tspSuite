package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.EImprovementSelectionPolicy;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationRNS;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationRNS
 * random neighborhood search} for permutations
 */
public class PermutationRNSBestImprovementTest extends
TSPAlgorithmSymmetricTest {

  /** create */
  public PermutationRNSBestImprovementTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationRNS createAlgorithm() {
    final PermutationRNS r;
    r = new PermutationRNS();
    r.setImprovementSelectionPolicy(EImprovementSelectionPolicy.ALWAYS_USE_BEST_IMPROVEMENT);
    return r;
  }
}
