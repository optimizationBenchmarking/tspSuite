package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the local 4-opt algorithm with savings heuristic
 */
public class ChainedMNSLocal4OptTestWithSavingsRandomOverlap extends
    ChainedMNSLocal4OptTestRandomOverlap {

  /** create */
  public ChainedMNSLocal4OptTestWithSavingsRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
