package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.hc;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic;

/**
 * the test of the hill climbing of permutations initialized with the MST
 * heuristic
 */
public class UpdatingPermutationHillClimberWithMSTTest extends
UpdatingPermutationHillClimberTest {

  /** create */
  public UpdatingPermutationHillClimberWithMSTTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected MSTHeuristic createInitAlgorithm() {
    return new MSTHeuristic();
  }
}
