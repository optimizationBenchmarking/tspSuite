package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationVNS
 * variable neighborhood search} for permutations initialized with the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic
 * MST heuristic}
 */
public class PermutationVNSBestImprovementWithMSTTest extends
    PermutationVNSBestImprovementTest {

  /** create */
  public PermutationVNSBestImprovementWithMSTTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected MSTHeuristic createInitAlgorithm() {
    return new MSTHeuristic();
  }
}
