package test.junit.org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor.NearestNeighborHeuristic;

/**
 * the test of the nearest neighbor heuristic
 */
public class NearestNeighborHeuristicRandomDepotTest extends
    NearestNeighborHeuristicDepot1Test {

  /** create */
  public NearestNeighborHeuristicRandomDepotTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected NearestNeighborHeuristic createAlgorithm() {
    NearestNeighborHeuristic h;
    h = new NearestNeighborHeuristic();
    h.setUseRandomDepot(true);
    return h;
  }
}
