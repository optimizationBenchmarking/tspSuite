package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.edgeGreedy.EdgeGreedyHeuristic;

/**
 * the test of the multi neighborhood search initialized with the edge
 * greedy heuristic
 */
public class MultiNeighborhoodSearchTestWithEdgeGreedy extends
MultiNeighborhoodSearchTest {

  /** create */
  public MultiNeighborhoodSearchTestWithEdgeGreedy() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected EdgeGreedyHeuristic createInitAlgorithm() {
    return new EdgeGreedyHeuristic();
  }
}
