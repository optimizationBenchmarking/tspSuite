package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt.ChainedMNSLocalNOpt;
import org.logisticPlanning.tsp.solving.operators.permutation.localOpt.ExhaustivelyEnumeratingLocal7Optimizer;

/**
 * the test of the local 7-opt algorithm
 */
public class ChainedMNSLocal7OptTestRandomOverlap extends
ChainedMNSLocalNOptTestRandomOverlap {

  /** create */
  public ChainedMNSLocal7OptTestRandomOverlap() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected ChainedMNSLocalNOpt createAlgorithm() {
    final ChainedMNSLocalNOpt algo;

    algo = super.createAlgorithm();
    algo.setLocalOptimizer(new ExhaustivelyEnumeratingLocal7Optimizer());
    return algo;
  }
}
