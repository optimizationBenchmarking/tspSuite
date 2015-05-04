package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the local 6-opt algorithm with savings heuristic
 */
public class ChainedMNSLocal6OptTestWithSavingsRandomOverlap extends
ChainedMNSLocal6OptTestRandomOverlap {

  /** create */
  public ChainedMNSLocal6OptTestWithSavingsRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
