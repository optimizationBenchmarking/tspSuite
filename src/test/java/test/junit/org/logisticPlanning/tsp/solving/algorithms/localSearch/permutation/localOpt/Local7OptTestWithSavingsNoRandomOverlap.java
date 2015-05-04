package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.localOpt;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the local 7-opt algorithm with savings heuristic
 */
public class Local7OptTestWithSavingsNoRandomOverlap extends
Local7OptTestNoRandomOverlap {

  /** create */
  public Local7OptTestWithSavingsNoRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
