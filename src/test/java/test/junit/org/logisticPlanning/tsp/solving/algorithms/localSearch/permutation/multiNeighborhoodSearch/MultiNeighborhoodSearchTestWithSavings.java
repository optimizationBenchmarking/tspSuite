package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the multi neighborhood search initialized with the Savings
 * heuristic
 */
public class MultiNeighborhoodSearchTestWithSavings extends
    MultiNeighborhoodSearchTest {

  /** create */
  public MultiNeighborhoodSearchTestWithSavings() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
