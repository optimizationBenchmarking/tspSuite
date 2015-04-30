package test.junit.org.logisticPlanning.tsp.solving.algorithms.heuristics.savings;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * the test of the savings heuristic
 */
public class SavingsHeuristicDepot1Test extends TSPAlgorithmSymmetricTest {

  /** create */
  public SavingsHeuristicDepot1Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createAlgorithm() {
    SavingsHeuristic h;
    h = new SavingsHeuristic();
    h.setUseRandomDepot(false);
    return h;
  }
}
