package test.junit.org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor.IteratedNearestNeighborHeuristic;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the iterated nearest neighbor heuristic
 */
public class IteratedNearestNeighborHeuristicTest extends
    TSPAlgorithmSymmetricTest {

  /** create */
  public IteratedNearestNeighborHeuristicTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected IteratedNearestNeighborHeuristic createAlgorithm() {
    return new IteratedNearestNeighborHeuristic();
  }
}
