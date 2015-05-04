package test.junit.org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor.NearestNeighborHeuristic;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the nearest neighbor heuristic
 */
public class NearestNeighborHeuristicDepot1Test extends
    TSPAlgorithmSymmetricTest {

  /** create */
  public NearestNeighborHeuristicDepot1Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected NearestNeighborHeuristic createAlgorithm() {
    NearestNeighborHeuristic h;
    h = new NearestNeighborHeuristic();
    h.setUseRandomDepot(false);
    return h;
  }
}
