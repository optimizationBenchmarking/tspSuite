package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor.NearestNeighborHeuristic;

/**
 * the test of the multi neighborhood search initialized with the nearest
 * neighbor heuristic
 */
public class MultiNeighborhoodSearchTestWithNearestNeighbor extends
MultiNeighborhoodSearchTest {

  /** create */
  public MultiNeighborhoodSearchTestWithNearestNeighbor() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected NearestNeighborHeuristic createInitAlgorithm() {
    return new NearestNeighborHeuristic();
  }
}
