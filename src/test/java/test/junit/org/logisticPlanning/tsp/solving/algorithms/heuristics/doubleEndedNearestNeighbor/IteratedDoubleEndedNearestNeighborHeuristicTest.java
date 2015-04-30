package test.junit.org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.IteratedDoubleEndedNearestNeighborHeuristic;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the iterated double-ended nearest neighbor heuristic
 */
public class IteratedDoubleEndedNearestNeighborHeuristicTest extends
    TSPAlgorithmSymmetricTest {

  /** create */
  public IteratedDoubleEndedNearestNeighborHeuristicTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected IteratedDoubleEndedNearestNeighborHeuristic createAlgorithm() {
    return new IteratedDoubleEndedNearestNeighborHeuristic();
  }
}
