package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.EImprovementSelectionPolicy;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationVNS;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationVNS
 * variable neighborhood search} for permutations initialized with the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic
 * MST heuristic}
 */
public class PermutationVNSRandomImprovementWithMSTTest extends
    PermutationVNSBestImprovementWithMSTTest {

  /** create */
  public PermutationVNSRandomImprovementWithMSTTest() {
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
