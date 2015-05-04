package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the local 7-opt algorithm with savings heuristic
 */
public class ChainedMNSLocal7OptTestWithSavingsRandomOverlap extends
ChainedMNSLocal7OptTestRandomOverlap {

  /** create */
  public ChainedMNSLocal7OptTestWithSavingsRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
