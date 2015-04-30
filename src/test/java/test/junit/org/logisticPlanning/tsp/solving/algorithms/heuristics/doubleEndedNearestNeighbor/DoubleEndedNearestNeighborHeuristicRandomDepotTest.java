package test.junit.org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic;

/**
 * Test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic
 * Double-Ended Nearest Neighbor Heuristic} with random depots.
 */
public class DoubleEndedNearestNeighborHeuristicRandomDepotTest extends
    DoubleEndedNearestNeighborHeuristicDepot1Test {

  /** create */
  public DoubleEndedNearestNeighborHeuristicRandomDepotTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected DoubleEndedNearestNeighborHeuristic createAlgorithm() {
    DoubleEndedNearestNeighborHeuristic h;
    h = new DoubleEndedNearestNeighborHeuristic();
    h.setUseRandomDepot(true);
    return h;
  }
}
