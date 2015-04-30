package test.junit.org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor.NearestNeighborHeuristic;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963.BABLittle1963
 * branch and bound algorithm} published by Little et al. in 1963.
 * initialized with a
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor.NearestNeighborHeuristic
 * nearest neighbor heuristic}.
 */
public class BABLittle1963NearestNeighborTest extends BABLittle1963Test {

  /** create */
  public BABLittle1963NearestNeighborTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected NearestNeighborHeuristic createInitAlgorithm() {
    return new NearestNeighborHeuristic();
  }
}
