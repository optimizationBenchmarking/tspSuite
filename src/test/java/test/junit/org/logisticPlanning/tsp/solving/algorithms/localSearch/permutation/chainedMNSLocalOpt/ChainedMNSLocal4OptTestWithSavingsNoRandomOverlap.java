package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the local 4-opt algorithm with savings heuristic
 */
public class ChainedMNSLocal4OptTestWithSavingsNoRandomOverlap extends
    ChainedMNSLocal4OptTestNoRandomOverlap {

  /** create */
  public ChainedMNSLocal4OptTestWithSavingsNoRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
