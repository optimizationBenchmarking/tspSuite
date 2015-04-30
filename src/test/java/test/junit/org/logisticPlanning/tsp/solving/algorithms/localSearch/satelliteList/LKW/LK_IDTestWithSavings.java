package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.satelliteList.LKW;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the LK_ID heuristic with the savings heuristic
 */
public class LK_IDTestWithSavings extends LK_IDTest {

  /** create */
  public LK_IDTestWithSavings() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
