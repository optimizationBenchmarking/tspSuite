package test.junit.org.logisticPlanning.tsp.solving.algorithms.heuristics.savings;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.IteratedSavingsHeuristic;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the savings heuristic
 */
public class IteratedSavingsHeuristicTest extends
TSPAlgorithmSymmetricTest {

  /** create */
  public IteratedSavingsHeuristicTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected IteratedSavingsHeuristic createAlgorithm() {
    return new IteratedSavingsHeuristic();
  }
}
