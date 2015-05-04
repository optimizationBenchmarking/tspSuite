package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.EImprovementSelectionPolicy;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationVNS;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationVNS
 * variable neighborhood search} for permutations
 */
public class PermutationVNSRandomImprovementTest extends
PermutationVNSBestImprovementTest {

  /** create */
  public PermutationVNSRandomImprovementTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationVNS createAlgorithm() {
    final PermutationVNS r;
    r = super.createAlgorithm();
    r.setImprovementSelectionPolicy(EImprovementSelectionPolicy.DECIDE_RANDOMLY_PER_ITERATION);
    return r;
  }
}
