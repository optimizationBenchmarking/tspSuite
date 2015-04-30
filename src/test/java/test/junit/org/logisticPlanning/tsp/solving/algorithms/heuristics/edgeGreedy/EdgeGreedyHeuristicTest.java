package test.junit.org.logisticPlanning.tsp.solving.algorithms.heuristics.edgeGreedy;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.edgeGreedy.EdgeGreedyHeuristic;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * The test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.edgeGreedy.EdgeGreedyHeuristic
 * edge greedy heuristic}.
 */
public class EdgeGreedyHeuristicTest extends TSPAlgorithmSymmetricTest {

  /** create */
  public EdgeGreedyHeuristicTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected EdgeGreedyHeuristic createAlgorithm() {
    return new EdgeGreedyHeuristic();
  }
}
