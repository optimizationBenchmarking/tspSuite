package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the local 3-opt algorithm with savings heuristic
 */
public class ChainedMNSLocal3OptTestWithSavingsNoRandomOverlap extends
ChainedMNSLocal3OptTestNoRandomOverlap {

  /** create */
  public ChainedMNSLocal3OptTestWithSavingsNoRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
