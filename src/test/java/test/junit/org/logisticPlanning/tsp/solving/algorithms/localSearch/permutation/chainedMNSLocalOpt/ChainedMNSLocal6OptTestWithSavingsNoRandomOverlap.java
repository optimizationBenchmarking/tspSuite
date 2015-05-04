package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the local 6-opt algorithm with savings heuristic
 */
public class ChainedMNSLocal6OptTestWithSavingsNoRandomOverlap extends
ChainedMNSLocal6OptTestNoRandomOverlap {

  /** create */
  public ChainedMNSLocal6OptTestWithSavingsNoRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
