package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.localOpt;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the local 4-opt algorithm with savings heuristic
 */
public class Local4OptTestWithSavingsNoRandomOverlap extends
Local4OptTestNoRandomOverlap {

  /** create */
  public Local4OptTestWithSavingsNoRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
