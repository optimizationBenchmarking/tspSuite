package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic;

/**
 * the test of the multi neighborhood search initialized with the Minimum
 * Spanning Tree heuristic
 */
public class MultiNeighborhoodSearchTestWithMST extends
    MultiNeighborhoodSearchTest {

  /** create */
  public MultiNeighborhoodSearchTestWithMST() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected MSTHeuristic createInitAlgorithm() {
    return new MSTHeuristic();
  }
}
