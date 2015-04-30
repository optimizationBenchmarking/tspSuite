package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the local 7-opt algorithm with savings heuristic
 */
public class ChainedMNSLocal7OptTestWithSavingsNoRandomOverlap extends
    ChainedMNSLocal7OptTestNoRandomOverlap {

  /** create */
  public ChainedMNSLocal7OptTestWithSavingsNoRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
