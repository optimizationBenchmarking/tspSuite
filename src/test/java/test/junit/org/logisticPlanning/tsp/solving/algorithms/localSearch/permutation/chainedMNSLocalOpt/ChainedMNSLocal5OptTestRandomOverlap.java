package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt.ChainedMNSLocalNOpt;
import org.logisticPlanning.tsp.solving.operators.permutation.localOpt.ExhaustivelyEnumeratingLocal5Optimizer;

/**
 * the test of the local 5-opt algorithm
 */
public class ChainedMNSLocal5OptTestRandomOverlap extends
ChainedMNSLocalNOptTestRandomOverlap {

  /** create */
  public ChainedMNSLocal5OptTestRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected ChainedMNSLocalNOpt createAlgorithm() {
    final ChainedMNSLocalNOpt algo;

    algo = super.createAlgorithm();
    algo.setLocalOptimizer(new ExhaustivelyEnumeratingLocal5Optimizer());
    return algo;
  }
}
