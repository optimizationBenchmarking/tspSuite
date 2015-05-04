package test.junit.org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * Tests of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic
 * Double-Ended Nearest Neighbor Heuristic} with depot 1.
 */
public class DoubleEndedNearestNeighborHeuristicDepot1Test extends
TSPAlgorithmSymmetricTest {

  /** create */
  public DoubleEndedNearestNeighborHeuristicDepot1Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected DoubleEndedNearestNeighborHeuristic createAlgorithm() {
    DoubleEndedNearestNeighborHeuristic h;
    h = new DoubleEndedNearestNeighborHeuristic();
    h.setUseRandomDepot(false);
    return h;
  }
}
