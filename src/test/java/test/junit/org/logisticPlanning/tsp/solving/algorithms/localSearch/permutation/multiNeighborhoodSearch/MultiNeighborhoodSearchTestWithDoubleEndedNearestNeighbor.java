package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic;

/**
 * the test of the multi neighborhood search initialized with the double
 * ended nearest neighbor heuristic
 */
public class MultiNeighborhoodSearchTestWithDoubleEndedNearestNeighbor
extends MultiNeighborhoodSearchTest {

  /** create */
  public MultiNeighborhoodSearchTestWithDoubleEndedNearestNeighbor() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected DoubleEndedNearestNeighborHeuristic createInitAlgorithm() {
    return new DoubleEndedNearestNeighborHeuristic();
  }
}
