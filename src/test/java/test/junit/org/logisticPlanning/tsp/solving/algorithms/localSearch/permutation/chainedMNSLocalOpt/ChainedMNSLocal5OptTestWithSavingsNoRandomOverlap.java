package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the local 5-opt algorithm with savings heuristic
 */
public class ChainedMNSLocal5OptTestWithSavingsNoRandomOverlap extends
    ChainedMNSLocal5OptTestNoRandomOverlap {

  /** create */
  public ChainedMNSLocal5OptTestWithSavingsNoRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
