package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the local 5-opt algorithm with savings heuristic
 */
public class ChainedMNSLocal5OptTestWithSavingsRandomOverlap extends
    ChainedMNSLocal5OptTestRandomOverlap {

  /** create */
  public ChainedMNSLocal5OptTestWithSavingsRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
