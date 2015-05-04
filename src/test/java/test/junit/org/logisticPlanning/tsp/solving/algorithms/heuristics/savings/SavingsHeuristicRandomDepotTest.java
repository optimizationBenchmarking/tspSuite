package test.junit.org.logisticPlanning.tsp.solving.algorithms.heuristics.savings;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the savings heuristic
 */
public class SavingsHeuristicRandomDepotTest extends
    SavingsHeuristicDepot1Test {

  /** create */
  public SavingsHeuristicRandomDepotTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createAlgorithm() {
    SavingsHeuristic h;
    h = new SavingsHeuristic();
    h.setUseRandomDepot(true);
    return h;
  }
}
